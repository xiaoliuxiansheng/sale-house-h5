import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './routes'
// import Mock from './mock'
import 'font-awesome/css/font-awesome.min.css'
import store from './vuex/store'
import Vuex from 'vuex'
import Axios from 'axios'
// Axios.defaults.baseURL = 'http://111.230.43.181:8081/api'
Axios.defaults.baseURL = 'http://111.230.43.181:8081/api'
Vue.prototype.$axios=Axios
// Mock.bootstrap()
Vue.use(ElementUI)
Vue.use(Vuex)
router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    sessionStorage.removeItem('user')
  }
  let user = JSON.parse(sessionStorage.getItem('user'))
  if (!user && to.path !== '/login') {
    next({ path: '/login' })
  } else {
    next()
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
