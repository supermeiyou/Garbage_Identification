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
                <el-main style="background-color: #f2f2f2">
                    <div style="display: flex">
                        <el-upload title="请上传图片" style="padding-left: 110px;margin-top: 10px"
                                   class="avatar-uploader"
                                   action="http://localhost:8080/file/upload"
                                   :show-file-list="false"
                                   :on-success="handleAvatarSuccess"
                        >
                            <img v-if="imageUrl" :src="imageUrl" class="avatar"/>
                            <el-icon v-else class="avatar-uploader-icon">
                                <Plus/>
                            </el-icon>
                        </el-upload>
                        <el-image :src="src" style="width: 330px;height: 330px;padding-top: 20px;padding-left: 320px">
                            <div slot="placeholder" class="image-slot">
                                加载中<span class="dot">...</span>
                            </div>
                        </el-image>
                    </div>
                    <div>
                        <el-button type="success" round size="large"
                                   style="margin-top: 10px;margin-left: 220px;text-indent: 10px;letter-spacing: 10px;font-weight: bold"
                                   @click="confirm">
                            识别
                        </el-button>
                    </div>
                    <div style="height: 10px"></div>
                    <div class="a" style="display: flex;align-items: center;justify-content: center;border-radius: 5px">
                        <div style="flex: 1;font-size: 40px;font-weight: bold;font-family: 华文楷体;text-align: center">
                            垃圾种类对应表
                        </div>
                        <div class="chapterList"
                             style="flex: 1;padding-bottom: 20px;border: 5px;border-radius: 6px ;width: 100%;height: 200px;background-color: darkseagreen;overflow-y:scroll;overflow-x:hidden">
                            <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
                                <li style="font-size: 25px" v-for="i in count" :key="i" class="infinite-list-item">{{
                                    i
                                    }}
                                </li>
                            </ul>
                        </div>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script lang="ts">
import {
    FullScreen,
    Grid, House, Operation, Setting, SwitchButton
} from "@element-plus/icons-vue";
import api from "../router/api.js"

export default {
    name: 'identify',
    components: {SwitchButton, FullScreen, Operation, Grid, Setting, House},
    data() {
        return {
            imageUrl: '',
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'
        };
    },
    methods: {
        back() {
            this.$router.push('/')
        },
        fullscreen() {
            document.documentElement.requestFullscreen()
        },
        async confirm() {
            const response = await api.getPicture()
            this.src = "data:image/png;base64, " + response.data
        },
    }
}
</script>
<script lang="ts" setup>
import {ref} from 'vue'
import {Plus} from '@element-plus/icons-vue'
import type {UploadProps} from 'element-plus'

const imageUrl = ref('')
const count = ['Books_and_papers 书籍纸张', 'Metal_kitchenware 金属厨具', 'chopping_block 砧板', 'Defacing_plastic 污损塑料',
    'chopsticks 筷子', 'Ceramic_ware 陶瓷器皿', 'Plug_wires 插头电线', 'Toiletries 洗护用品', 'Plastic_toys 塑料玩具',
    'shoes 鞋', 'Peel_and_pulp 果皮果肉', 'glassware 玻璃器皿', 'plush_toy 毛绒玩具', 'Defaced_paper 污损用纸', 'Plasticware 塑料器皿',
    'Cartons 纸盒纸箱', 'flowerpot 花盆', 'pack 包', 'Metalware 金属器皿', 'Dry_cell_battery 干电池', 'Spice_bottles 调料瓶',
    'Vegetable_leaves 菜帮菜叶', 'wok 锅', 'Cooking_oil_drums 食用油桶', 'Drink_bottles 饮料瓶', 'Power_bank 充电宝', 'Cans 易拉罐', 'toothpick  牙签',
    'Leftovers 剩饭剩菜', 'Big_bones 大骨头', 'fishbone 鱼骨', 'trash_can 垃圾桶', 'Wine_bottles 酒瓶', 'Metal_food_cans 金属食品罐', 'Disposable_fast_food_boxes 一次性快餐盒', 'Cigarette_butts 烟蒂', 'Old_clothes 旧衣服',
    'Plastic_hangers 塑料衣架', 'pillow 枕头', 'Expired_medications 过期药物', 'Tea_residues 茶叶渣', 'ointment 软膏',
    'eggshell 蛋壳', 'Express_paper_bags 快递纸袋',]
const load = () => {
    count.values++
}
const handleAvatarSuccess: UploadProps['onSuccess'] = (
    response,
    uploadFile
) => {
    imageUrl.value = URL.createObjectURL(uploadFile.raw!)
}
</script>
<style scoped>
.avatar-uploader .avatar {
    width: 330px;
    height: 330px;
    display: block;
}
</style>
<style>
.avatar-uploader .el-upload {
    border: 5px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 330px;
    height: 330px;
    text-align: center;
}

.chapterList {
    box-shadow: 2px 0 6px rgba(0, 21, 41, .75);
}

.a {
    box-shadow: 2px 0 6px rgba(2, 21, 41, .75);
}

.infinite-list {
    height: 300px;
    padding: 0;
    margin: 0;
    list-style: none;
}

.infinite-list .infinite-list-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    background: aquamarine;
    margin: 10px;
    color: var(--el-color-primary);
    border-radius: 6px;
}

.infinite-list .infinite-list-item + .list-item {
    margin-top: 10px;
}
</style>

