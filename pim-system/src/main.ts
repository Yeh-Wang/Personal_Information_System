import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App).use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(router).mount('#app')
// // 挂载路由导航守卫
// // to 将要访问的路径
// // from 代表从哪个路径跳转而来
// // next 是个函数，表示放行 next() 放行  next('/login') 强制跳转
// router.beforeEach((to, from, next) => {
//   if (to.path.startsWith('/login')) {
//     window.sessionStorage.removeItem('Token')
//     next()
//   } else {
//     let user = window.sessionStorage.getItem('Token')
//     if (!user) {
//       next({
//         path: '/login'
//       })
//     } else {
//       next()
//     }
//   }
// });
