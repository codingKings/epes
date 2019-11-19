

<template>
  <div id="content-wrapper">
    <div class="main-box">
      <div class="main-body">
        <div class="row" >
          <div class="col-lg-6">
            <div class="main-box">
              <header class="main-box-header clearfix">
                <h2>雷达图</h2>
              </header>
              <div class="main-box-body clearfix" >
                <!-- <div id="pieChart"
                     style="height: 400px; padding: 0px; position: relative;"></div> -->
                     <!-- <div id="main" style="width: 600px;height: 600px;"></div> -->
                  <div id="book-category"></div>
              </div>
            </div>
          </div>
          <div class="col-lg-6">
            <div class="main-box">
              <header class="main-box-header clearfix">
                <h2>柱状图</h2>
              </header>
              <div class="main-box-body clearfix">
                <div id="Histogram"
                     style="height: 400px; padding: 0px; position: relative;"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  import $ from "jquery"
  import echarts from 'echarts'

  export default {
    name: "Echarts",
    data() {
      return {
        itmes: {}
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
        //this.drawPie('main')
      this._getCategory();
        
      });
    },
    methods: {
      init: function () {
        this.getHistogram();
      },
      
      _getCategory(){
        
            getCategory().then(res=>{
                 let data = res.data.data
                 
                 this.bookCategory(this.formatData(data))
              })
          },
            formatData(data){
                let indicator = []
                let bookCat = []
                let borrowCat = []
                data.book.forEach(info => {
                    let key =  info.category;
                    let value = info.number;
                    bookCat[key] = value                 
                })
                let bookValues = Object.values(bookCat)       //book中的number组成的数组      
                let bookMax = Math.max(...bookValues)
                let bookKeys = Object.keys(bookCat)   //book数组中的category组成的数组
                bookKeys.forEach(key => {
                    data.borrow.forEach(item=>{
                        if(item.category == key){
                            borrowCat[key] = item.number
                        }
                    })  
                })
                
                let borrowValues = Object.values(borrowCat) 
                let borrowMax = Math.max(...borrowValues)
                let max = bookMax > borrowMax ? bookMax : borrowMax
                bookKeys.forEach(item => indicator.push({
                    'name':item,
                    'max':max
                }))
                let result = {
                    indicator : indicator,
                    bookCat : bookValues,
                    borrowCat : borrowValues
                }
                return result
            },
            bookCategory(data){
                let bookCategoryChart = echarts.init(document.getElementById('book-category'))
                let option = {
                    tooltip: {
                        trigger: 'item',
                        position:function(p){    //其中p为当前鼠标的位置
                            return [p[0], p[1]];
                        },
                    },
                    legend: {
                        orient: 'vertical',
                        right: '5%',
                        textStyle: {
                            color:'#DFE0E5',
                            fontWeight: 'bold'
                        },
                        data: ['书刊类别分布', '借阅类别分布']
                    },
                    radar: {
                        splitNumber: 2, // 雷达图圈数设置
                        center: ['50%','50%'],
                        radius : '65%',
                        name: {
                            textStyle: {
                                color: '#DFE0E5',
                                backgroundColor: '#121E36'
                            }
                        },
                        indicator: data.indicator,
                        splitArea : {
                            show : false,
                            areaStyle : {
                                color: 'rgba(255,0,0,0)', // 图表背景的颜色
                            },
                        },
                    },
                    series: [{
                        name: '书刊类别 vs 借阅类别',
                        type: 'radar',
                        data : [
                            {
                                value : data.bookCat,
                                name : '书刊类别分布',
                                itemStyle: {
                                    normal: {
                                        color:'#F75325'  //显示颜色与填充颜色对应
                                    }
                                },
                                areaStyle: {
                                    normal: {
                                        color: '#F75325'    //填充的颜色
                                    }
                                }
                            },
                            {
                                value : data.borrowCat,
                                name : '借阅类别分布',
                                itemStyle: {
                                    normal: {
                                        color:'#7B52CC'
                                    }
                                },
                                areaStyle: {
                                    normal: {
                                        color: '#7B52CC'
                                    }
                                }
                            }
                        ]
                    }]
                };
                bookCategoryChart.setOption(option)
            },


//        drawPie() {
              
//                 var charts = echarts.init(document.getElementById('main'))
        
//                 var option = {
//                     title: {
//                         text: "这个是测试",
//                         link: 'https://www.baidu.com',
//                         target: "blank",
//                         textAlign: 'left',
 
//                     },
//                     tooltip: {
                      
//                     },//提示层
//                     legend: {
//                         data: ['张瑞']
//                     },
//                     radar: {
//                         name: {
//                             textStyle: {
//                                 color: '#fff', //字体颜色
//                                 backgroundColor: '#999', //背景色
//                                 borderRadius: 3, //圆角
//                                 padding: [3, 5] //padding
//                             }
//                         },
//                         center: ['50%', '50%'],
//                         radius: '60%',
//                         startAngle: 270,
//                         indicator: [{
//                                 name: '专业素质',
//                                 max: 600
//                             },
//                             {
//                                 name: '敬业精神',
//                                 max: 600
//                             },
//                             {
//                                 name: '主动担当',
//                                 max: 600
//                             },
//                             {
//                                 name: '工作成效',
//                                 max: 600
//                             },
//                             {
//                                 name: '创新能力',
//                                 max: 600
//                             },
//                             {
//                                 name: '团结协作',
//                                 max: 600
//                             }
//                         ],
//                     },
//                     series: [{
//                         name: '测试标题名字',
//                         type: 'radar',
//                         data: [{
//                             value: ["600", "80", "100","80","100","80"],
//                             name: "张"
//                         }]
//                     }]
//                 }
//                 charts.setOption(option);
//             },


      getHistogram:function () {
        const dom = document.getElementById("Histogram");
        const myChart = this.$echarts.init(dom);
        const app = {};
        app.title = '坐标轴刻度与标签对齐';
        myChart.setOption({
          color: ['#3398DB'],
          tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
              type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis : [
            {
              type : 'category',
              data : ['1~1月份', '1~2月份', '1~3月份', '1~4月份', '1~5月份', '1~7月份', '1~8月份','1~9月份','1~10月份','1~11月份','1~12月份'],
              axisTick: {
                alignWithLabel: true
              }
            }
          ],
          yAxis : [
            {
              type : 'value'
            }
          ],
          series : [
            {
              name:'直接访问',
              type:'bar',
              barWidth: '60%',
              data:[10, 52, 200, 334, 390, 330, 220]
            }
          ]
        });
      }
    }
  }
</script>

<style>

</style>
