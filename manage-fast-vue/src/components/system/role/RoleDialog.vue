<!--
角色 @author lijianan @email 4141914@gmail.com @date 2018-04-13 20:59:41
-->
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="角色名称" prop="roleName">
            <el-input placeholder="请输入角色名称" v-model="form.roleName"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input placeholder="请输入备注" v-model="form.remark"></el-input>
          </el-form-item>
          <el-form-item label="授权">
            <el-tree
              :data="menuList"
              :props="menuListTreeProps"
              node-key="id"
              ref="menuListTree"
              :default-expand-all="true"
              show-checkbox>
            </el-tree>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" style="text-align: right">
      <el-button @click="show = false">取消</el-button>
      <el-button type="primary" @click="save()">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import {treeDataTranslate} from '../../../util/index.js'

  export default {
    props: ["refresh"],
    data() {
      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        menuList: [],
        menuListTreeProps: {
          label: 'name',
          children: 'children'
        },
        rules: {
          roleName: [
            {required: true, message: "请输入角色名称", trigger: 'blur'}
          ]
        },
        tempKey: -666666 // 临时key, 用于解决tree半选中状态项不能传给后台接口问题. # 待优化
      }
    },
    methods: {
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }

          let requestData = {...that.form};
          requestData.menuIdList = [].concat(this.$refs.menuListTree.getCheckedKeys(), this.tempKey, this.$refs.menuListTree.getHalfCheckedKeys())

          that.$http.post("/api/role/" + that.dialogMode, JSON.stringify(requestData)).then(res => {
            that.show = false;
            that.$message.success(that.title + "成功!");
            that.refresh();
          }).catch(res => {
            that.$message.error(that.title + "出错!" + res);
          });
        });
      },
      initForm() {//初始数据
        return {
          id: null,
          roleName: null,
          remark: null
        }
      },
      initMenu(checkedKeys) {
        const that = this;
        that.$http.post("/api/menu/queryList", {}).then(res => {
          this.menuList = treeDataTranslate(res.data, "id")
          this.$refs.menuListTree.setCheckedKeys(checkedKeys)
        }).catch(res => {
          that.$message.error("获取菜单管理列表失败：" + res);
        });
      },
      addDialog() {//新增
        this.title = "新增角色";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;

        this.initMenu([]);
      },
      editDialog(row) {//修改
        this.title = "修改角色";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;


        let requestData = {roleId: row.id};
        this.$http.post("/api/roleMenu/queryList", JSON.stringify(requestData)).then(res => {
          let roleMenuList = res.data;

          let roleMenuIdList = [];
          for (let roleMenu of roleMenuList) {
            roleMenuIdList.push(roleMenu.menuId);
          }

          var idx = roleMenuIdList.indexOf(this.tempKey)
          if (idx !== -1) {
            roleMenuIdList.splice(idx, roleMenuIdList.length - idx)
          }

          this.initMenu(roleMenuIdList);
        }).catch(res => {
          this.$message.error("获取菜单管理列表失败：" + res);
        });
      },
    },
    components: {}
  }
</script>
<style></style>
