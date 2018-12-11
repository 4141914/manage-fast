import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Layout from '../components/layout/Layout.vue'
import Home from '../components/home/Home.vue'
import generatorList from '../components/system/generator/generatorList.vue'
import MenuList from '../components/system/menu/MenuList.vue'
import RoleList from '../components/system/role/RoleList.vue'
import UserList from '../components/system/user/UserList.vue'

Vue.use(Router)

export default new Router({
  // mode: 'hash',
  routes: [
    {
      path: '/login',
      name: '登录',
      component: Login
    },
    {
      path: '/',
      component: Layout,
      redirect: '/home',
      children: [
        {path: '/home', name: '首页', component: Home},
        {path: '/generator', name: '代码生成器', component: generatorList},
        {path: '/menu', name: '菜单管理', component: MenuList},
        {path: '/role', name: '角色管理', component: RoleList},
        {path: '/user', name: '用户管理', component: UserList},
      ],
      beforeEnter(to, from, next) {
        let token = Vue.cookie.get('token')
        if (!token || !/\S/.test(token)) {
          next({path: 'login'})
        }
        next()
      }
    }
  ]
})
