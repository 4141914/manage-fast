import Vue from 'vue'
import Vuex from 'vuex'
import menu from '../components/layout/Menu.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    menu: menu
  }
})
