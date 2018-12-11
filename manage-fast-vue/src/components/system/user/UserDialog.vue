<!--
系统用户 @author lijianan @email 4141914@gmail.com @date 2018-04-14 10:05:24
-->
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="用户名" prop="username">
            <el-input placeholder="请输入用户名" v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input placeholder="请输入姓名" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" type="password" placeholder="密码"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="comfirmPassword">
            <el-input v-model="form.comfirmPassword" type="password" placeholder="确认密码"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input placeholder="请输入邮箱" v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="mobile">
            <el-input placeholder="请输入手机号" v-model="form.mobile"></el-input>
          </el-form-item>
          <el-form-item label="角色" size="mini" prop="roleIdList">
            <el-checkbox-group v-model="form.roleIdList">
              <el-checkbox v-for="role in roleList"
                           :key="role.id"
                           :label="role.id">
                {{ role.roleName }}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="状态" size="mini" prop="status">
            <el-radio-group v-model="form.status">
              <el-radio :label="0">禁用</el-radio>
              <el-radio :label="1">正常</el-radio>
            </el-radio-group>
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
      var validatePassword = (rule, value, callback) => {
        if (this.form.id == null && (value == null || value == '')) {
          callback(new Error('密码不能为空'))
        }
        callback()
      }
      var validateComfirmPassword = (rule, value, callback) => {
        if (this.form.id == null && (value == null || value == '')) {
          callback(new Error('确认密码不能为空'))
        }

        if (this.form.password !== value) {
          callback(new Error('确认密码与密码输入不一致'))
        }

        callback()
      }

      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        roleList: [],
        rules: {
          username: [
            {required: true, message: "请输入用户名", trigger: 'blur'}
          ],
          name: [
            {required: true, message: "请输入姓名", trigger: 'blur'}
          ],
          password: [
            {validator: validatePassword, trigger: 'blur'}
          ],
          comfirmPassword: [
            {validator: validateComfirmPassword, trigger: 'blur'}
          ],
          email: [
            {required: true, message: "请输入邮箱", trigger: 'blur'}
          ],
          mobile: [
            {required: true, message: "请输入手机号", trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.post("/api/user/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
          username: null,
          password: null,
          comfirmPassword: null,
          salt: null,
          email: null,
          mobile: null,
          status: 1,
          createUserId: null,
          createTime: null,
          roleIdList: [],
          name: null,
        }
      },
      initRoleList() {
        this.$http.post("/api/role/queryList", {}).then(res => {
          this.roleList = res.data;
        }).catch(res => {
          this.$message.error("获取角色列表失败：" + res);
        });
      },
      addDialog() {//新增
        this.title = "新增系统用户";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;

        this.initRoleList();
      },
      editDialog(row) {//修改
        this.title = "修改系统用户";
        this.dialogMode = "update";
        this.form = {...row, comfirmPassword: null};
        this.show = true;

        this.$http.post("/api/userRole/queryList", JSON.stringify({userId: row.id})).then(res => {
          let userRoleList = res.data;

          this.form.roleIdList = [];
          for (let userRole of userRoleList) {
            this.form.roleIdList.push(userRole.roleId);
          }

        }).catch(res => {
          this.$message.error("获取角色列表失败：" + res);
        });

        this.initRoleList();
      }
    },
    components: {}
  }
</script>
<style></style>
