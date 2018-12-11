<template>
  <el-form :model="form" ref="form" :rules="rules" label-position="left" label-width="0px" class="login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="form.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="form.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <!--<el-checkbox checked class="remember">记住密码</el-checkbox>-->
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click="login()">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          username: null,
          password: null
        },
        rules: {
          username: [
            {required: true, message: "请输入用户名", trigger: 'blur'}
          ],
          password: [
            {required: true, message: "请输入密码", trigger: 'blur'}
          ],
        }
      }
    },
    methods: {
      login() {
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.get("api/user/login", {
            params: {
              username: that.form.username,
              password: that.form.password,
            }
          }).then(res => {
            if (!res.data.token) {
              that.$message.error(res.data.msg);
              return;
            }
            this.$cookie.set('token', res.data.token, {expires: `${res.data.expire || 0}s`})
            this.$router.replace({path: '/home'})
            // if (res.data.success == true) {
            //   window.location.href = '/';
            // } else {
            //   that.$message.error(res.data.msgMap["1"]);
            // }
          }).catch(res => {
            that.$message.error("出错!" + res);
          });
        });
      }
    },
    computed: {},
    components: {},
  }
</script>

<style scoped>
  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .remember {
    margin: 0px 0px 35px 0px;
  }


</style>
