<template>
    <div class="common-layout">
        <el-container>
            <el-aside style="width: 200px;height: 100%;min-height: 100vh;background-color: #2c3e50;overflow: hidden">
                <!--    div高度                               div的背景颜色                字体颜色                字体居中布局-->
                <div style="height: 5px"></div>
                <div style="height: 60px;color: white;display: flex;align-items: center;justify-content: center;font-size: large">
                    <img src="../assets/logo.png" style="width: 50px;height: 50px;padding-top: 5px">
                    <img src="../assets/font.png" style="width: 150px;height: 40px;padding-top: 5px">
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
                    <el-menu-item v-if="flag" index="/log" style="padding-left: 41px;font-size: large">
                        <el-icon>
                            <setting/>
                        </el-icon>
                        <span>日志</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-container style="overflow: hidden">
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
                    <el-carousel class="carousel" :interval="4000" type="card" height="250px" autoplay="true"
                                 loop="true">
                        <el-carousel-item style="height: 250px">
                            <img src="D:\graduate\web\src\assets\1.png" style="height: 100%;width: 100%">
                        </el-carousel-item>
                        <el-carousel-item style="height: 250px">
                            <img src="D:\graduate\web\src\assets\2.jpg" style="height: 100%;width: 100%">
                        </el-carousel-item>
                        <el-carousel-item style="height: 250px">
                            <img src="D:\graduate\web\src\assets\3.jpg" style="height: 100%;width: 100%">
                        </el-carousel-item>
                        <el-carousel-item style="height: 250px">
                            <img src="D:\graduate\web\src\assets\4.jpg" style="height: 100%;width: 100%">
                        </el-carousel-item>
                        <el-carousel-item style="height: 250px">
                            <img src="D:\graduate\web\src\assets\5.jpg" style="height: 100%;width: 100%">
                        </el-carousel-item>
                        <el-carousel-item style="height: 250px">
                            <img src="D:\graduate\web\src\assets\6.png" style="height: 100%;width: 100%">
                        </el-carousel-item>
                    </el-carousel>
                    <div style="width: 100%;height: 10px"></div>

                    <div class="txt1"
                         style="background-color: #f2f2f2 ;width: 700px;height: 380px;font-size: 24px;font-family: 华文楷体;border-radius: 8px;text-indent: 10em">
                        <div style="font-weight: bolder;border: 2px">垃圾分类的好处</div>
                        1.减少占地。生活垃圾中有些物质不易降解，使土地受到严重侵蚀。垃圾分类，去掉能回收的、不易降解的物质，减少垃圾数量达60%以上。<br>
                        2.减少环境污染。废弃的电池含有金属汞、镉等有毒的物质，会对人类产生严重的危害；土壤中的废塑料会导致农作物减产；抛弃的废塑料被动物误食，导致动物死亡的事故时有发生，因此回收利用可以减少危害。<br>
                        3.变废为宝。中国每年使用塑料快餐盒达40亿个，方便面碗5—7亿个，一次性筷子数十亿双，这些占生活垃圾的8—15%；1吨废塑料可回炼600公斤的柴油；回收1500吨废纸，可免于砍伐用于生产1200吨纸的林木。
                    </div>
                    <div class="txt2"
                         style="background-color: honeydew;width: 500px;height: 380px;font-size: 24px;border-radius: 8px;text-indent: 7em;font-family: 华文楷体">
                        <div style="font-weight: bolder;border: 2px">垃圾不分类的危害</div>
                        生活垃圾中有些物质不易降解，使土地受到严重侵蚀。废弃的电池含有金属汞、镉等有毒的物质，会对人类产生严重的危害;土壤中的废塑料会导致农作物减产;抛弃的废塑料被动物误食，导致动物死亡的事故时有发生。
                        很多不分类处理的垃圾还会给人类健康带来威胁。例如：各种电池类垃圾若不分类处理，就会释放有害物质；剩余餐饮类垃圾若不分类处理，将会滋生蚊子、苍蝇，使细菌大量繁殖、产生的沼气会使垃圾爆炸。
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script setup>
import {
    Setting, House, Grid, Operation, SwitchButton, FullScreen,
} from '@element-plus/icons-vue';
let flag = false
</script>
<script>
export default {
    methods: {
        back() {
            this.$router.push('/')
        },
        fullscreen() {
            document.documentElement.requestFullscreen()
        }
    }
}
</script>
<style>
.el-menu-item:hover {
    color: aquamarine !important;
}

.el-menu-item.is-active {
    background-color: #40a9ff !important;
    border-radius: 8px;
    margin: 4px 4px;
}

.el-menu-item {
    margin: 4px;
    height: 50px;
    line-height: 40px;
}

.el-header {
    box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
    display: flex;
    align-items: center;
}

.carousel, .breadcrumb {
    user-select: none;
    cursor: pointer;
}

.txt1 {
    float: left;
    position: relative;
    box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
    cursor: default;
    user-select: none;
}

.txt2 {
    float: right;
    box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
    cursor: default;
    user-select: none;
}

.exit:hover {
    color: crimson !important;
    user-select: none;
    cursor: pointer !important;
}

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.8;
    line-height: 200px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}
</style>