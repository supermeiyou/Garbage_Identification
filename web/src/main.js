import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from "@/router/index.js";
import VueAxios from "vue-axios";
import axios from "axios";
import request from '@/utils/request'
const app = createApp(App)

app.use(ElementPlus)
app.use(router)
app.use(VueAxios,axios)
app.mount('#app')