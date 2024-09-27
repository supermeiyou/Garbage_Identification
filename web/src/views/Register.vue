<template>
    <body style="background-color: darkseagreen">
    <div style="height: 100svh;display: flex;align-items: center;justify-content: center;overflow: hidden">
        <div style="display: flex;background-color: white;width: 60%;height:60%;border-radius: 10px;overflow: hidden;align-items: center;justify-content: center">
            <div class="img" style="flex: 1">
                <img src="D:\graduate\web\src\assets\注册.png" alt="" style="width: 100%;margin-left: 25px">
            </div>
            <div style="flex: 1;display: flex;align-items: center;justify-content: center">
                <el-form :model="user" style="width: 80%">
                    <div style="font-size: 20px;font-weight: bold;text-align: center;margin-bottom: 20px">
                        欢迎注册垃圾智能识别系统
                    </div>
                    <el-form-item prop="account">
                        <el-input prefix-icon="el-icon-user" size="large" placeholder="请输入账号"
                                  v-model="user.account"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input prefix-icon="el-icon-lock" size="large" show-password placeholder="请输入密码"
                                  v-model="user.passwords"></el-input>
                    </el-form-item>
                    <el-form-item prop="telephone">
                        <el-input prefix-icon="el-icon-lock" size="large" show-password placeholder="请再次输入密码"
                                  v-model="again_password"></el-input>
                    </el-form-item>
                    <el-form-item prop="telephone">
                        <el-input prefix-icon="el-icon-lock" size="large" show-password placeholder="请输入手机号"
                                  v-model="user.telephone"></el-input>
                    </el-form-item>
                    <el-form-item size="large">
                        <el-button class="regitser_btn" type="success" style="width: 100%" @click="register">
                            注  册
                        </el-button>
                    </el-form-item>
                    <div style="display: flex">
                        <div style="flex: 1">
                            <span @click="login()" style="color: #0f9876;cursor: pointer">
                                返回
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
import {ref} from "vue";
import request from "@/utils/request";
import {createRouter as $router, useRouter} from 'vue-router'
import {ElMessage} from "element-plus";
let user = ref({
    account: '',
    passwords: '',
    telephone:'',
})
let again_password =ref('')

const register=async ()=>{
    if(user.value.account!==''&&user.value.passwords!==''&&user.value.telephone!==''&&again_password.value!==''){
        if(user.value.passwords!==again_password.value){
            ElMessage({type: 'error', message: '两次密码错误'})
        }
        else {
            let res = await request.post('/user/add', user.value)
            if(res.data){
                ElMessage({type: 'success', message: '注册成功'})
            }
            else ElMessage({type: 'error', message: '注册失败已有该账户'})
        }
    }
    else {
        ElMessage({type: 'error', message: '信息不能为空'})
    }
}
</script>

<script>
export default{
    methods:{
        login(){
            this.$router.push('/')
        }
    }
}
</script>
<style>
.img {
    box-shadow: 3px 3px 10px rgba(238, 241, 244, 0.75);
}
</style>