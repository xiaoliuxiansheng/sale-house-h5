import Vue from "vue"
import VueRouter from "vue-router"
import routes from "./router/index"
import Index from "./component/index.vue"
Vue.use(VueRouter)

const router=new VueRouter({
    routes
})

new Vue({
    router,
    render:h=>h(Index)
}).$mount("#app")

/*const router = new VueRouter({
    routes
  })
  
  // 4. Create and mount the root instance.
  // Make sure to inject the router with the router option to make the
  // whole app router-aware.
  const app = new Vue({
    router,
    render:h=>h(Index)
  }).$mount('#app')*/