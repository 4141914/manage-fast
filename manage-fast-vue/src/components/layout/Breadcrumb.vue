<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :key="p.path" v-for="p in sitePath">{{p.name}}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
  export default {
    created() {
      this.sitePath = getSitePath(this.$route.matched);
    },
    data() {
      return {
        sitePath: []
      }
    },
    methods: {},
    watch: {
      '$route': function (to, from) {
        this.sitePath = getSitePath(to.matched);
      }
    }
  }
  const getSitePath = function (routes) {
    let sitePathArray = routes;
    if (sitePathArray.length == 1) {
      sitePathArray.splice(0, 1);
    }
    return sitePathArray;
  }
</script>

<style scoped>

</style>
