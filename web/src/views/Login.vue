<template>
    <body style="background-color: darkseagreen">
    <div style="height: 100svh;display: flex;align-items: center;justify-content: center;overflow: hidden">
        <div style="display: flex;background-color: white;width: 60%;height:60%;border-radius: 10px;overflow: hidden;align-items: center;justify-content: center">
            <div class="img" style="flex: 1">
                <img src="@\assets\登录.png" alt="" style="width: 100%">
            </div>
            <div style="flex: 1;display: flex;align-items: center;justify-content: center">
                <el-form :model="user" style="width: 80%">
                    <div style="font-size: 20px;font-weight: bold;text-align: center;margin-bottom: 20px">
                        欢迎登入垃圾识别系统
                    </div>
                    <el-form-item prop="account">
                        <el-input prefix-icon="el-icon-user" size="large" placeholder="请输入账号"
                                  v-model="user.account"></el-input>
                    </el-form-item>
                    <el-form-item prop="passwords">
                        <el-input prefix-icon="el-icon-lock" size="large" show-password placeholder="请输入密码"
                                  v-model="user.passwords"></el-input>
                    </el-form-item>
                    <el-form-item prop="validCode">
                        <div style="display: flex">
                            <el-input placeholder="请输入验证码" v-model="ValidCodeMode" style="flex: 1"></el-input>
                            <div class="code" @click="refreshCode" style="flex: 1">
                                <ValidCode title="点击刷新验证码" :identifyCode="identifyCode"></ValidCode>
                            </div>
                        </div>
                    </el-form-item>
                    <el-form-item size="large">
                        <el-button class="login_btn" type="success" style="width: 100%" @click="login">
                            登 录
                        </el-button>
                    </el-form-item>
                    <div style="display: flex">
                        <div style="flex: 1">
                            还没有账号？请
                            <span @click="register()" style="color: #0f9876;cursor: pointer">
                                注册
                            </span>
                        </div>
                        <div style="flex: 1;text-align: right">
                            <span @click="forget()" style="color: #0f9876 ;cursor: pointer">
                                忘记密码
                            </span>
                        </div>
                    </div>
                </el-form>
            </div>
        </div>
    </div>
    </body>

</template>
<script setup>

import ValidCode from 'D:\\graduate\\web\\src\\components\\ValidCode.vue'
import {ElMessage} from 'element-plus'
import {ref, onMounted} from 'vue'
import {useRouter} from 'vue-router'
import request from '@/utils/request'
//获取路由器
let $router = useRouter()
let ValidCodeMode = ref('') //输入框验证码
let identifyCode = ref('') //图形验证码
let identifyCodes = ref('1234567890abcdefjhijklinopqrsduvwxyz') //验证码出现的数字和字母
let user = ref({
    account: '',
    passwords: ''
})

//组件挂载
onMounted(() => {
    identifyCode.value = ''
    makeCode(identifyCodes.value, 4)
})

// 生成随机数
const randomNum = (min, max) => {
    max = max + 1
    return Math.floor(Math.random() * (max - min) + min)
}
// 随机生成验证码字符串
const makeCode = (o, l) => {
    for (let i = 0; i < l; i++) {
        identifyCode.value += o[randomNum(0, o.length)]
    }
}
// 更新验证码
const refreshCode = () => {
    identifyCode.value = ''
    makeCode(identifyCodes.value, 4)
}
//登录
const login = async () => {
    if (!user.value.account || !user.value.passwords) {
        ElMessage({type: 'error', message: '账号或密码不为空！'})
        return
    } else {
        //验证验证码为空
        if (!ValidCodeMode.value) {
            ElMessage({type: 'error', message: '验证码不能为空！'})
            return
        }
        //验证验证码是否正确
        if (ValidCodeMode.value !== identifyCode.value) {
            ElMessage({type: 'error', message: '验证码错误'})
            refreshCode()
            return
        } else {
            let res = await request.post('/login', user.value);
            if (res.data) {
                ElMessage({type: 'success', message: '登录成功'})
                await $router.push('/home')
            } else
                ElMessage({type: 'error', message: '用户名或密码错误'})
        }
    }
}
</script>
<script>
export default {
    methods: {
        register() {
            this.$router.push('/register')
        },
        forget() {
            this.$router.push('/forget')
        }
    }
}
</script>
<style>
.img {
    box-shadow: 3px 3px 10px rgba(238, 241, 244, 0.75);
}
</style>