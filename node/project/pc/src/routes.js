import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/login',
      component: () => import('./views/Login.vue'),
      name: '',
      hidden: true
    },
    {
      path: '/404',
      component: () => import('./views/404.vue'),
      name: '',
      hidden: true
    },
    {
      path: '/',
      component: () => import('./views/Home.vue'),
      name: '招商经理信息管理',
      iconCls: 'el-icon-message', // 图标样式class
      redirect:{name:'列表'},
      children: [
        { path: '/main', component: () => import('./views/Main.vue'), name: '主页', hidden: true },
        { path: '/manager', component: () => import('./views/nav1/Table.vue'), name: '列表' },
        // { path: '/form', component: () => import('./views/nav1/Form.vue'), name: '新增' }
      ]
    },
    {
      path: '/',
      component: () => import('./views/Home.vue'),
      name: '房屋出售出售',
      iconCls: 'fa fa-id-card-o',
      children: [
        { path: '/newhouse', component: () => import('./views/nav2/Page4.vue'), name: '新增房屋资源' },
        { path: '/houserent', component: () => import('./views/nav2/Page6.vue'), name: '房屋出租资源' },
        { path: '/housesell', component: () => import('./views/nav2/Page5.vue'), name: '房屋出售资源' }
      ]
    },
    {
      path: '/',
      component: () => import('./views/Home.vue'),
      name: '商业楼盘信息',
      iconCls: 'fa fa-id-card-o',
      children: [
        { path: '/newbuild', component: () => import('./views/nav4/Page7.vue'), name: '新增楼盘信息' },
        { path: '/buildlist', component: () => import('./views/nav4/Page8.vue'), name: '楼盘信息列表' }
      ]
    },
    {
      path: '/',
      component: () => import('./views/Home.vue'),
      name: '新房源信息',
      iconCls: 'fa fa-bar-chart',
      children: [
        { path: '/newresource', component: () => import('./views/charts/echarts.vue'), name: '新房源信息' }
      ]
    },
    {
      path: '*',
      hidden: true,
      redirect: { path: '/404' }
    }
  ]
})
