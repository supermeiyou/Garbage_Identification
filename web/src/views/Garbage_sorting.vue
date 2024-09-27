<template>
    <div class="common-layout">
        <el-container>
            <el-aside style="width: 200px;min-height: 100vh;background-color: #2c3e50">
                <!--    div高度                               div的背景颜色                字体颜色                字体居中布局-->
                <div style="height: 5px"></div>
                <div style="height: 60px;color: white;display: flex;align-items: center;justify-content: center;font-size: large">
                    <img src="../assets/logo.png" style="width: 50px;height: 50px;padding-top: 5px">
                    <img src="../assets/font.png" style="width: 130px;height: 40px;padding-top: 5px">
                </div>
                <!--                                            当前浏览器访问的路由-->
                <el-menu router background-color="#2c3e50" text-color="rgba(255,255,255,0.65)" active-text-color="#fff"
                         style="border: none" :default-active="$route.path">
                    <el-menu-item index="/home" style="padding-left: 41px;font-size: large">
                        <el-icon>
                            <House/>
                        </el-icon>
                        <span>网站首页</span>
                    </el-menu-item>
                    <el-menu-item index="/identify" style="padding-left: 41px;font-size: large">
                        <el-icon>
                            <Grid/>
                        </el-icon>
                        <span>垃圾识别</span>
                    </el-menu-item>
                    <el-menu-item index="/sort" style="padding-left: 41px;font-size: large">
                        <el-icon>
                            <Operation/>
                        </el-icon>
                        <span>垃圾分类</span>
                    </el-menu-item>
                    <el-menu-item index="/analysis" style="padding-left: 41px;font-size: large">
                        <el-icon>
                            <setting/>
                        </el-icon>
                        <span>统计分析</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header>
                    <i title="全屏" @click="fullscreen()" style="font-size: 26px;padding-top: 7px">
                        <el-icon>
                            <FullScreen/>
                        </el-icon>
                    </i>
                    <div style="width: 10px"></div>
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item :to="{ path: '/home' }" style="font-size: 18px">首页</el-breadcrumb-item>
                        <el-breadcrumb-item style="font-size: 18px">活动管理</el-breadcrumb-item>
                        <el-breadcrumb-item style="font-size: 18px">活动列表</el-breadcrumb-item>
                        <el-breadcrumb-item style="font-size: 18px">活动详情</el-breadcrumb-item>
                    </el-breadcrumb>
                    <div @click="back()" class="exit"
                         style="cursor: default; font-size: 18px ;flex: 1;width:20px;display: flex;align-items: center;height: 20px;justify-content: flex-end">
                        <i style="font-size: 20px;padding-top: 7px">
                            <el-icon>
                                <SwitchButton/>
                            </el-icon>
                        </i>
                        <div style="align-items: center;justify-content: center">退出</div>
                    </div>
                </el-header>
                <el-main>
                    <el-container>
                        <div style="flex-direction: row;align-items: center;justify-content: center">
                            <div style="padding-top: 40px"></div>
                            <div class="recycle"
                                 style="border: 2px;border-radius: 8px;padding-top: 40px;height: 200px;width: 1200px; align-items: center;justify-content: center">
                                <img class="img" title="可回收垃圾" src="D:\graduate\web\src\assets\可回收垃圾.png"
                                     @click="show_r()" style="height: 170px;width: 200px;padding-left: 68px">
                                <img class="img" title="厨余垃圾" src="D:\graduate\web\src\assets\厨余垃圾.png"
                                     @click="show_c()" style="height: 170px;width: 200px;padding-left: 87px">
                                <img class="img" title="有害垃圾" src="D:\graduate\web\src\assets\有害垃圾.png"
                                     @click="show_h()" style="height: 170px;width: 200px;padding-left: 87px">
                                <img class="img" title="其他垃圾" src="D:\graduate\web\src\assets\其他垃圾.png"
                                     @click="show_o()" style="height: 170px;width: 200px;padding-left: 87px">
                            </div>
                            <div v-if="show0" style="padding-top: 30px">
                                <el-empty description="请点击对应分类图标进行投放"></el-empty>
                            </div>
                            <div class="tag" v-if="show1"
                                 style="width: 94%;height:30%;padding-top: 30px;display: flex;justify-content: center;margin: 20px">
                                <div style="width: 75%;height: 50%">
                                    <el-tag class="el-tag-r" size="large" effect="dark" type="primary"
                                            style="font-size: 15px" v-for="name in recycle.data">{{ name }}
                                    </el-tag>
                                </div>
                            </div>
                            <div class="tag" v-if="show2"
                                 style="padding-top: 60px;display: flex;align-items: center;justify-content: center;margin: 20px">
                                <div style="width: 75%;height: 50%;display: flex;justify-content: center">
                                    <el-tag class="el-tag-k" size="large" effect="dark" style="font-size: 15px"
                                            v-for="name in chicken.data">{{ name }}
                                    </el-tag>
                                </div>
                            </div>
                            <div class="tag" v-if="show3"
                                 style="padding-top: 60px;display: flex;align-items: center;justify-content: center;margin: 20px">
                                <div style="width: 75%;height: 50%;display: flex;justify-content: center">
                                    <el-tag class="el-tag-h" size="large" effect="dark" style="font-size: 15px"
                                            type="primary" v-for="name in harm.data">{{ name }}
                                    </el-tag>
                                </div>
                            </div>
                            <div class="tag" v-if="show4"
                                 style="padding-top: 60px;display: flex;align-items: center;justify-content: center;margin: 20px">
                                <div style="width: 75%;height: 50%;display: flex;justify-content: center">
                                    <el-tag class="el-tag-o" size="large" effect="dark" style="font-size: 15px"
                                            type="primary" v-for="name in other.data">{{ name }}
                                    </el-tag>
                                </div>
                            </div>
                            <div v-if="flag" style="display: flex;align-items: center;justify-content: center">
                                <el-button plain @click="dialogVisible = true" class="btn" type="success">投放</el-button>
                                <el-dialog
                                        title="提示"
                                        v-model="dialogVisible"
                                        width="30%"
                                        :before-close="handleClose">
                                    <span>确定要投放吗？</span>
                                    <template #footer>
                                        <div class="dialog-footer">
                                            <el-button @click="dialogVisible = false">取消</el-button>
                                            <el-button type="primary" @click="dialogVisible = false">
                                                确认
                                            </el-button>
                                        </div>
                                    </template>
                                </el-dialog>

                            </div>
                        </div>
                    </el-container>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script  setup>
import {
    Setting, House, Grid, Operation, SwitchButton, FullScreen,
} from '@element-plus/icons-vue';
import {ref} from "vue";
import {ElMessageBox} from "element-plus";
const dialogVisible = ref(false)
const types = ["primary", "success", "info", "warning", "danger"]
const handleClose = (/*done: () => void*/) => {
    ElMessageBox.confirm('确定要关闭吗？')
        .then(() => {
            /*done()*/
        })
        .catch(() => {
            // catch error
        })
}
</script>
<script>
import request from "@/utils/request.js";

const recycle = await request.post("/recycle");
/* ['纸盒纸箱', '书籍纸张', '金属厨具', '砧板', '筷子', '鞋', '毛绒玩具', '污损用纸', '包', '锅', '易拉罐', '金属食品罐', '旧衣服', '枕头', '快递纸袋', '饮料瓶'];*/
const chicken = await request.post("/kitchen");
/*['果皮果肉', '调料瓶', '菜帮菜叶', '剩饭剩菜', '大骨头', '鱼骨', '茶叶渣', '蛋壳'];*/
const harm = await request.post("/harm");
/*['污损塑料', '插头电线', '干电池', '充电宝', '一次性快餐盒', '过期药物', '塑料器皿', '塑料玩具'];*/
const other = await request.post("/other");
/*['陶瓷器皿', '洗护用品', '玻璃器皿', '花盆', '食用油桶', '饮料瓶', '牙签', '垃圾桶', '酒瓶', '烟蒂', '塑料衣架', '软膏'];*/
export default {
    data() {
        return {
            show0: true,
            show1: false,
            show2: false,
            show3: false,
            show4: false,
            flag: false,
        };
    },
    methods: {
        fullscreen() {
            document.documentElement.requestFullscreen()
        },
        back() {
            this.$router.push('/')
        },
        show_r() {
            this.show0 = false;
            this.show1 = true;
            this.show2 = false;
            this.show3 = false;
            this.show4 = false;
            this.flag = true
        },
        show_c() {
            this.show0 = false;
            this.show1 = false;
            this.show2 = true;
            this.show3 = false;
            this.show4 = false;
            this.flag = true
        },
        show_h() {
            this.show0 = false;
            this.show1 = false;
            this.show2 = false;
            this.show3 = true;
            this.show4 = false;
            this.flag = true
        },
        show_o() {
            this.show0 = false;
            this.show1 = false;
            this.show2 = false;
            this.show3 = false;
            this.show4 = true;
            this.flag = true
        }
    }
}
</script>
<style scoped>
.recycle, .tag {
    box-shadow: 1px 0 4px rgba(0, 21, 41, .35);
}

.c, .img:hover {
    cursor: pointer;
}

.btn {
    width: 150px;
    height: 60px;
}

.el-tag-r {
    height: 45px !important;
    width: 95px !important;
    margin: 5px !important;
    border: 100px !important;
    border-radius: 6px !important;
    background-color: forestgreen !important;
}

.el-tag-k {
    height: 45px !important;
    width: 95px !important;
    margin: 10px !important;
    border: 100px !important;
    border-radius: 6px !important;
    background-color: darkblue !important;
}

.el-tag-h {
    height: 45px !important;
    width: 95px !important;
    margin: 5px !important;
    border: 100px !important;
    border-radius: 6px !important;
    background-color: firebrick !important;
}

.el-tag-o {
    height: 45px !important;
    width: 95px !important;
    margin: 5px !important;
    border: 100px !important;
    border-radius: 6px !important;
    background-color: dodgerblue !important;
}
</style>