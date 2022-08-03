import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/components/Login";
import student from "@/components/student";
import head_teacher from "@/components/head_teacher";
import map from "@/components/routedesign";
import routedesign from "@/components/routedesign";
import PE_teacher from "@/components/PE_teacher";
import collegetable from "@/components/collegetable";
import studentcheck from "@/components/studentcheck";
import showroute from "@/components/showroute";


import vueRsource from 'vue-resource'
Vue.use(vueRsource);
Vue.use(VueRouter)

const routes = [
  {path:'/',redirect:'/login',component: Login},
  { name: 'login', path: '/login', component: Login },
  {name:'student',path:'/student',component: student},
  {name:'head_teacher',path:'/headteacher',component: head_teacher},
  {name:'route-design',path: '/route-design',component: routedesign},
  {name:'PE_teacher',path:'/PE_teacher',component: PE_teacher},
  {name: 'collegetable',path:'/collegetable',component: collegetable},
  {name:'studentcheck',path:'/studentcheck',component: studentcheck},
  {name:'showroute',path:'/showroute',component: showroute}
]

const router = new VueRouter({
  routes
})

export default router
