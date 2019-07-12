import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
const routers =new Router({
  routes:[
    {
      path:'/',
      meta:{
        title:'登录'
      },
      name:'home',
      component:resolve => require.ensure([],() => resolve(require("../component/Login.vue")))
    },
    {
      path:'/List',
      meta:{
        title:'列表'
      },
      name:'details',
      component:resolve =>require.ensure([],() => resolve(require("../component/List.vue")))
    }
  ]
})
routers.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next();
})
export default routers
