// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview' // 引入iview依赖
import Message from 'iview'
import 'iview/dist/styles/iview.css' // 引入iview css样式
import axios from 'axios' // 1、在这里引入axios
import qs from 'qs' //解决参数无法传递

Vue.prototype.qs = qs;
Vue.prototype.$axios = axios;
Vue.prototype.$Message = Message

Vue.config.productionTip = false

//request 拦截器实现
axios.interceptors.request.use(
 config => {
  //config.baseURL = 'http://114.115.134.1:9090/'
  config.baseURL = 'http://192.168.124.6:9099/'
  config.withCredentials = true // 允许携带token ,这个是解决跨域产生的相关问题
  config.timeout = 6000
  let token = sessionStorage.getItem('token')
  if (token) {
    console.log("set token success");
    config.headers = {
      'authToken': token,
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    }
  }
  if (config.url === 'refresh') {
    config.headers = {
      'refresh-token': sessionStorage.getItem('refresh_token'),
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    }
  }
  return config
 },
 error => {
  return Promise.reject(error)
 }
)

//在 response 拦截器实现
axios.interceptors.response.use(
  response => {
    // 定时刷新access-token
    if (!response.data.value && response.data.data.message === 'token invalid') {
    // 刷新token
    store.dispatch('refresh').then(response => {
      sessionStorage.setItem('access_token', response.data)
    }).catch(error => {
      throw new Error('token刷新' + error)
    })
    }
    return response
  },
  error => {
    return Promise.reject(error)
  }
)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
