// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/index'
import Vant from 'vant'
import Axios from 'axios'
// Axios.defaults.baseURL ="http://111.230.43.181:8081/api"
Vue.prototype.$axios=Axios
Axios.defaults.baseURL = 'http://192.168.1.2:8081/api'
Vue.use(Vant)
Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
