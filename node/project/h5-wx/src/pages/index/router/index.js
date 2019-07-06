import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
const routers =new Router({
  routes:[
    {
      path:'/',
      meta:{
        title:'首页'
      },
      name:'home',
      component:resolve => require.ensure([],() => resolve(require("../component/Home.vue")))
    },
    {
      path:'/details',
      meta:{
        title:'详情'
      },
      name:'details',
      component:resolve =>require.ensure([],() => resolve(require("../component/Details.vue")))
    },
    {
      path:'/list',
      meta:{
        title:'列表'
      },
      name:'list',
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
