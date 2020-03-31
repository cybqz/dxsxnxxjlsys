import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

const router =  new Router({
  //mode:"history",
  base: '/cleg',
  routes: [
    {
      path: '/',
      name: 'home',
      component: (resove) => require(['@/components/home/index'], resove),
      meta: {
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录才能进入的
      },
    },
    {
      path: '/share',
      name: 'share',
      component: (resove) => require(['@/components/share/index'], resove),
      meta: {
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录才能进入的
      },
    },
    {
      path:'/shareDatail',
      name: 'shareDatail',
      component: (resove) => require(['@/components/share/detail'], resove),
      meta: {
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录才能进入的
      },
    },
    {
      path:'/addShare',
      name: 'addShare',
      component: (resove) => require(['@/components/share/addShare'], resove),
      meta: {
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录才能进入的
      },
    },
    {
      path:'/shareList',
      name: 'shareList',
      component: (resove) => require(['@/components/share/list'], resove),
      meta: {
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录才能进入的
      },
    },
    {
      path: '/talk',
      name: 'talk',
      component: (resove) => require(['@/components/talk/index'], resove),
      meta: {
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录才能进入的
      },
    },
    {
      path: '/team',
      name: 'team',
      component: (resove) => require(['@/components/team/index'], resove),
      meta: {
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录才能进入的
      },
    },
    {
      path: '/login',
      name: 'login',
      component: (resove) => require(['@/components/login'], resove),
    },
  ]
})


//登录鉴权
router.beforeEach((to, from, next) => {
  let token = sessionStorage.getItem("token");
  if (to.meta.requireAuth) {  // 需要权限,进一步进行判断
    if (token) {  // 通过sessionStorage获取当前的token是否存在
      next();
    }
    else {    //如果没有权限,重定向到登录页,进行登录
      next({
        path: '/login',
        // query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  }
  else { //不需要权限 直接跳转
    next();
  }
})


export default router