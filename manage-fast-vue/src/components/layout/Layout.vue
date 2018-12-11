<template>
  <div>
    <el-row class="panel">
      <el-col :span="24" class="panel-top">
        <Header></Header>
      </el-col>
      <el-col :span="24" class="panel-center">
        <aside style="width:230px;">
          <h5 class="admin"><i class="fa fa-user" aria-hidden="true" style="margin-right:5px;"></i>欢迎管理员：超级管理员</h5>
          <nav-menu></nav-menu>
        </aside>
        <section class="panel-c-c">
          <div class="grid-content bg-purple-light">
            <el-row :gutter="20">
              <el-col :span="24" class="site-breadcrumb">
                <breadcrumb></breadcrumb>
              </el-col>
            </el-row>
            <br/>
            <el-card>
              <el-row :gutter="20">
                <el-col :span="24" style="background-color:#fff;box-sizing: border-box;">
                  <router-view></router-view>
                </el-col>
              </el-row>
            </el-card>
          </div>
        </section>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import Header from './Header.vue'
  import NavMenu from './NavMenu.vue'
  import Breadcrumb from './Breadcrumb.vue'

  export default {
    data() {
      return {
        name: "name"
      }
    },
    methods: {
      getName() {
        return this.name;
      }
    },
    computed: {},
    components: {Header, NavMenu, Breadcrumb},
  }
</script>

<style scoped>
  .panel {
    position: absolute;
    top: 0px;
    bottom: 0px;
    width: 100%;
  }

  .panel-top {
    height: 60px;
    line-height: 60px;
    background: #1F2D3D;
    color: #c0ccda;
  }

  .panel-center {
    background: #324057;
    position: absolute;
    top: 60px;
    bottom: 0px;
    overflow: hidden;
  }

  .panel-c-c {
    background: #f1f2f7;
    position: absolute;
    right: 0px;
    top: 0px;
    bottom: 0px;
    left: 230px;
    overflow-y: scroll;
    padding: 20px;
  }

  .admin {
    color: #c0ccda;
    text-align: center;
  }
</style>
