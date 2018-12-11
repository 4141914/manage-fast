<!--
菜单管理 @author lijianan @email 4141914@gmail.com @date 2018-04-12 22:47:08
-->
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="父菜单ID，一级菜单为0" prop="parentId">
            <el-select v-model="form.parentId" placeholder="请选择">
              <el-option
                v-for="item in filterDate()"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="菜单名称" prop="name">
            <el-input placeholder="请输入菜单名称" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="菜单URL" prop="url">
            <el-input placeholder="请输入菜单URL" v-model="form.url"></el-input>
          </el-form-item>
          <el-form-item label="授权" prop="perms">
            <el-input placeholder="请输入授权(多个用逗号分隔，如：user:list,user:create)" v-model="form.perms"></el-input>
          </el-form-item>
          <el-form-item label="类型" prop="type">
            <el-select v-model="form.type" placeholder="请选择">
              <el-option label="目录" :value="0"></el-option>
              <el-option label="菜单" :value="1"></el-option>
              <el-option label="按钮" :value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="菜单图标" prop="icon">
            <el-input placeholder="请输入菜单图标" v-model="form.icon"></el-input>
          </el-form-item>
          <el-form-item label="排序" prop="orderNum">
            <el-input placeholder="请输入排序" v-model.number="form.orderNum"></el-input>
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
  export default {
    props: ["refresh"],
    data() {
      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        dataList: [],
        rules: {
          parentId: [
            {required: true, type: 'number', message: "请输入父菜单ID，一级菜单为0", trigger: 'blur'}
          ],
          name: [
            {required: true, message: "请输入菜单名称", trigger: 'blur'}
          ],
          url: [
            {required: true, message: "请输入菜单URL", trigger: 'blur'}
          ],
          type: [
            {required: true, type: 'number', message: "请输入类型   0：目录   1：菜单   2：按钮", trigger: 'blur'}
          ],
          orderNum: [
            {required: true, type: 'number', message: "请输入排序", trigger: 'blur'}
          ],

        }
      }
    },
    computed: {},
    created: function () {
      const that = this;
      that.$http.post("/api/menu/queryList", {}).then(res => {
        this.dataList = res.data
      }).catch(res => {
        that.$message.error("获取菜单管理列表失败：" + res);
      });
    },
    methods: {
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.post("/api/menu/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
          parentId: null,
          name: null,
          url: null,
          perms: null,
          type: null,
          icon: null,
          orderNum: null,
        }
      },
      addDialog() {//新增
        this.title = "新增菜单管理";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改菜单管理";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
      filterDate() {
        const that = this;
        let list = [{id: 0, name: "根节点"}];
        if (that.dataList == null || that.dataList.length == 0) {
          return list;
        }
        for (let i = 0; i < that.dataList.length; i++) {
          if (that.dataList[i].type != 2) {
            list.push(that.dataList[i]);
          }
        }
        return list;
      },
    },
    components: {}
  }
</script>
<style></style>
