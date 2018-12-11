<template>
  <el-submenu
    v-if="menuNav.list && menuNav.list.length >= 1"
    :data-idx="menuNav.menuId + ''"
    :index="menuNav.menuId + ''">
    <template slot="title">
      <i :class="['site-sidebar__menu-icon', menuNav.icon ? menuNav.icon : 'fa fa-circle-o']"></i>
      <span>{{ menuNav.name }}</span>
    </template>
    <SubMenuNav
      v-for="item in menuNav.list"
      :key="item.id"
      :menu-nav="item">
    </SubMenuNav>
  </el-submenu>
  <!--<el-menu-item index="/generator">代码生成器</el-menu-item>-->
  <el-menu-item
    v-else
    :index="menuNav.url + ''">
    <i :class="['site-sidebar__menu-icon', menuNav.icon ? menuNav.icon : 'fa fa-circle-o']"></i>
    <span>{{ menuNav.name }}</span>
  </el-menu-item>
</template>

<script>
  import SubMenuNav from './SubMenuNav'
  import {getRouteNameByUrl} from '../../util/index.js'

  export default {
    name: "SubMenuNav",
    props: {
      menuNav: Object,
      required: true
    },
    created: function () {
    },
    components: {
      SubMenuNav
    },
    methods: {
      // 跳转到菜单导航对应路由
      gotoRouteHandle(url) {
        var routeName = getRouteNameByUrl(url)
        if (/\S/.test(routeName)) {
          this.$router.push({path: routeName})
        }
      }
    }
  }
</script>
