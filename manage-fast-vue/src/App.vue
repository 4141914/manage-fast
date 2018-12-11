<!--
测试用的表123 @author huochai @email 4141914@gmail.com @date 2018-01-15 21:01:00
-->
<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>

<style>
  @import "../node_modules/font-awesome/css/font-awesome.min.css";

  body {
    margin: 0px;
    padding: 0px;
    font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
    font-weight: 400;
    background: #1F2D3D;
  }

  .el-table th {
    background-color: #F8F8F9;
    height: 41px;
    padding: 0px 0
  }

  .el-table td {
    height: 41px;
    padding: 0px 0
  }
</style>
