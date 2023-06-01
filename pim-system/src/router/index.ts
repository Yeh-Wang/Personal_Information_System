import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import LoginView from '../views/LoginView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    // 根页面重定向到/login
    path: '/',
    redirect: "/login",
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path:'/home/:username',
    name:'home',
    component: () => import('../views/HomeView.vue'),

    children:[
      {
        path:'addressBook',
        name: 'addressBook',
        component: () => import('../components/AddressBookMain.vue')
      },
      {
        path:'memoOfSchedule',
        name: 'memoOfSchedule',
        component: () => import('../components/memoOfScheduleMain.vue')
      },
      {
        path:'taskSchedule',
        name:'taskSchedule',
        component: () => import('../components/TaskSchedule.vue')
      },
      {
        path:'financialManage',
        name:'financialManage',
        component: () => import('../components/FinancialManage.vue')
      }
    ]
  },
  {
    path: '/:username/personalReports',
    name: 'personalReports',
    component: () => import(/* webpackChunkName: "about" */ '../views/PersonalReportsView.vue')
  },
  {
    path: '/:username/personalInfo',
    name: 'PerInfo',
    component: () => import('../views/PerInfo.vue')
  }
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
