import { createRouter,createWebHistory } from "vue-router";
import home from "@/views/HomeView.vue";
import login from "@/views/Login.vue";
import identify from "@/views/Garbage_identification.vue"
import sort from "@/views/Garbage_sorting.vue"
import analysis from "@/views/Statistical_analysis.vue"
import register from "@/views/Register.vue"
import forget from "@/views/Forget.vue"
import log from "@/views/Log.vue"
const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:'/home',
            component:home,
        },
        {
            path:'/',
            component:login,
        },
        {
            path:'/identify',
            component:identify,
        },
        {
            path:'/sort',
            component:sort,
        },
        {
            path:'/analysis',
            component:analysis,
        },
        {
            path: '/register',
            component :register
        },
        {
            path: '/forget',
            component: forget
        },
        {
            path: '/log',
            component: log
        }
    ]
})
export default router;
