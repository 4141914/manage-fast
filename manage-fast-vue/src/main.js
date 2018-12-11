// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index.js'
import VueCookie from 'vue-cookie'
import http from './util/http'
import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-default/index.css'
import 'element-ui/lib/theme-chalk/index.css';

import installFilter from './filters/filters';
import {isAuth} from './util/index'

installFilter(Vue);

//将$http绑定到全局
Vue.prototype.$http = http;
// 挂载权限方法
Vue.prototype.isAuth = isAuth

Vue.use(VueCookie)
Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
