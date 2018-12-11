<template>
  <div>
    <el-row>
      <el-col :span="20">
        <div class="head-wrap">
          <img src="../../assets/logo4.png" class="logo"> <span>ManageFast<i style="color:#20a0ff">&nbsp;后台系统 - 1.0</i></span>
        </div>
      </el-col>
      <el-col :span="4" style="font-size:26px;  text-align: right" class="alertBox">
        <el-tooltip class="item tip-logout" effect="dark" content="退出" placement="bottom">
          <a style="color: #f4f7fc" @click="logout()"><i
            class="fa fa-sign-out"
            aria-hidden="true"></i></a>
        </el-tooltip>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {}
    },
    methods: {
      logout() {
        const that = this;
        that.$http.get("/api/user/logout").then(res => {
          this.$cookie.delete('token')
          this.$router.replace({path: 'login'})
        }).catch(res => {
          that.$message.error("出错!" + res);
        });
      }
    },
    computed: {},
    components: {},
  }
</script>

<style scoped>

  .tip-logout {
    float: right;
    margin-right: 20px;
    cursor: pointer;
  }

  .head-wrap {

  }

  .logo {
    width: 40px;
    float: left;
    margin: 10px 10px 10px 18px;
  }
</style>
