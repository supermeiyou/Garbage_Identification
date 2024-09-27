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
                    <div class="type"
                         style="padding-left: 20px;position:relative;align-items: center;justify-content: center">
                        <div style="font-weight: bolder;font-size: larger">种类分析</div>
                        <div style="height: 5px"></div>
                        <el-checkbox-group v-model="checktype" @change="handleCheckedTypeChange">
                            <el-checkbox style="font-weight: bold" size="large" v-for="i in type" :label="i" :key="i">
                                {{ i }}
                            </el-checkbox>
                        </el-checkbox-group>
                        <div class="submit" style="padding-top: 20px;text-align: center">
                            <el-button type="success" @click="show_all()" round size="large"
                                       style="text-indent: 8px;letter-spacing: 10px;font-weight: bold">统 计
                            </el-button>
                        </div>
                    </div>
                    <div class="statistics" style="margin-top: 20px;height: 365px">
                        <div style="font-weight: bolder;font-size: larger;text-align: center">数据统计</div>
                        <el-row :gutter="10" style="margin-top: 10px">
                            <el-col :span="9">
                                <el-card style="margin-left: 10px">
                                    <div style="width: 100%;height: 280px" id="col"></div>
                                </el-card>
                            </el-col>
                            <el-col :span="15">
                                <el-card style="margin-right: 10px">
                                    <div style="display: flex">
                                        <div style="width: 50%;height: 140px" id="pie1"></div>
                                        <div style="width: 50%;height: 140px" id="pie2"></div>
                                    </div>
                                    <div style="display: flex">
                                        <div style="width: 50%;height: 140px" id="pie3"></div>
                                        <div style="width: 50%;height: 140px" id="pie4"></div>
                                    </div>
                                </el-card>
                            </el-col>
                        </el-row>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script setup>
import {
    FullScreen,
    Grid, House, Operation, Setting, SwitchButton
} from "@element-plus/icons-vue";
</script>
<script>
import {ref} from "vue";
import request from "@/utils/request.js";
let r = ref(0);
let c = ref(0);
let h = ref(0);
let o = ref(0);
let ar = []
let ac = []
let ah = []
let ao = []
import * as echarts from 'echarts';
const type_option = await request.post("/rubbish") ;
const recycle = await request.post("/recycle")
/*['纸盒纸箱', '书籍纸张', '金属厨具', '砧板', '筷子', '鞋', '毛绒玩具', '污损用纸', '包', '锅', '易拉罐', '金属食品罐', '旧衣服', '枕头', '快递纸袋', '饮料瓶'];*/
const kitchen = await request.post("/kitchen")
/*   ['果皮果肉', '调料瓶', '菜帮菜叶', '剩饭剩菜', '大骨头', '鱼骨', '茶叶渣', '蛋壳'];*/
const harm = await request.post("/harm")
const other = await request.post("/other")
/*['污损塑料', '插头电线', '干电池', '充电宝', '一次性快餐盒', '过期药物', '塑料器皿', '塑料玩具'];*/
const coloption = {
    title: {
        text: '垃圾分类数据图',
    },
    tooltip: {
        trigger: 'axis'
    },
    xAxis: {
        type: 'category',
        data: ['可回收垃圾', '厨余垃圾', '有害垃圾', '其他垃圾']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            data: [r.value, c.value, h.value, o.value],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
                color: 'rgba(180, 180, 180, 0.2)'
            }
        }
    ]
};
const pie1option = {
    title: {
        text: '对应垃圾统计图',
    },
    tooltip: {
        trigger: 'item'
    },
    series: [
        {
            name: '可回收垃圾',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
const pie2option = {
    tooltip: {
        trigger: 'item'
    },
    series: [
        {
            name: '厨余垃圾',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
const pie3option = {
    tooltip: {
        trigger: 'item'
    },
    series: [
        {
            name: '有害垃圾',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
const pie4option = {
    tooltip: {
        trigger: 'item'
    },
    series: [
        {
            name: '其他垃圾',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
function refreshData1() {
    //刷新数据
    let chart = echarts.getInstanceByDom(document.getElementById('col'))
    let option = chart.getOption()

    option.series[0].data[0] = r.value
    option.series[0].data[1] = c.value
    option.series[0].data[2] = h.value
    option.series[0].data[3] = o.value
    chart.setOption(option)
}
function refreshData2() {
    let chart = echarts.getInstanceByDom(document.getElementById('pie1'))
    let option = chart.getOption()
    option.series[0].data = ar;
    chart.setOption(option)
}
function refreshData3() {
    let chart = echarts.getInstanceByDom(document.getElementById('pie2'))
    let option = chart.getOption()
    option.series[0].data = ac;
    chart.setOption(option)
}
function refreshData4() {
    let chart = echarts.getInstanceByDom(document.getElementById('pie3'))
    let option = chart.getOption()
    option.series[0].data = ah;
    chart.setOption(option)
}
function refreshData5() {
    let chart = echarts.getInstanceByDom(document.getElementById('pie4'))
    let option = chart.getOption()
    option.series[0].data = ao;
    chart.setOption(option)
}

export default {
    data() {
        return {
            checktype: [],
            type: type_option.data,
            dialogVisible: false
        }
    },
    mounted() {
        this.show_chart();
    },
    methods: {
        fullscreen() {
            document.documentElement.requestFullscreen()
        },
        back() {
            this.$router.push('/')
        },
        handleCheckedTypeChange(val) {
            let checkedCount = val.length;
        },
        show_chart() {
            let colDom = document.getElementById('col')
            let colChart = echarts.init(colDom)
            colChart.setOption(coloption)

            let pie1Dom = document.getElementById('pie1')
            let pie1Chart = echarts.init(pie1Dom)
            pie1Chart.setOption(pie1option)

            let pie2Dom = document.getElementById('pie2')
            let pie2Chart = echarts.init(pie2Dom)
            pie2Chart.setOption(pie2option)

            let pie3Dom = document.getElementById('pie3')
            let pie3Chart = echarts.init(pie3Dom)
            pie3Chart.setOption(pie3option)

            let pie4Dom = document.getElementById('pie4')
            let pie4Chart = echarts.init(pie4Dom)
            pie4Chart.setOption(pie4option)
        },
        show_all() {
            r.value = 0;
            c.value = 0;
            h.value = 0;
            o.value = 0;
            ar = []
            ac = []
            ah = []
            ao = []
            Object.keys(this.checktype).forEach((item) => {
                if (recycle.data.includes(this.checktype[item]))
                    r.value++;
                else if (kitchen.data.includes(this.checktype[item]))
                    c.value++;
                else if (harm.data.includes(this.checktype[item]))
                    h.value++;
                else o.value++;
            })
            Object.keys(this.checktype).forEach((item) => {
                if (recycle.data.includes(this.checktype[item])) {
                    let p = {value: 1, name: this.checktype[item]}
                    ar.push(p)
                } else if (kitchen.data.includes(this.checktype[item])) {
                    let p = {value: 1, name: this.checktype[item]}
                    ac.push(p)
                } else if (harm.data.includes(this.checktype[item])) {
                    let p = {value: 1, name: this.checktype[item]}
                    ah.push(p)
                } else {
                    let p = {value: 1, name: this.checktype[item]}
                    ao.push(p)
                }
            })
            refreshData1()
            refreshData2()
            refreshData3()
            refreshData4()
            refreshData5()
        }
    }
}
</script>
<style scoped>
.type, .statistics {
    box-shadow: 1px 0 4px rgba(0, 21, 41, .35);
    border-radius: 5px;
}
</style>