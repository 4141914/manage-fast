<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-menu style="border-top: 1px solid #475669;"
                 class="el-menu-vertical-demo"
                 background-color="#324057"
                 text-color="#c0ccda"
                 theme="dark" unique-opened router default-active="home">
          <el-menu-item style="width:230px" index="home" @click="$router.push({ path: 'home' })">
            <i class="site-sidebar__menu-icon fa fa-home"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <SubMenuNav
            style="width:230px"
            v-for="menuNav in menuNavList"
            :key="menuNav.id"
            :menu-nav="menuNav">
          </SubMenuNav>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {mapState, mapGetters, mapMutations, mapActions} from 'vuex';
  import SubMenuNav from './SubMenuNav.vue'

  export default {
    data() {
      return {
        name: "name",
      }
    },
    created: function () {
      this.getMenuNavList();
    },
    methods: {
      ...mapActions({
        getMenuNavList: 'menu/getMenuNavList',
      }),
    },
    computed: {
      ...mapState({
        menuNavList: (state) => state.menu.menuNavList
      }),
    },
    components: {
      SubMenuNav
    },
  }
</script>

<style scoped>

</style>
