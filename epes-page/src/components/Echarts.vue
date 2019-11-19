

<template>
  <div id="content-wrapper">
    <div class="main-box">
      <div class="main-body">
        <div class="row" >
          <div class="col-lg-6">
            <div class="main-box">
              <header class="main-box-header clearfix">
                <h2>饼图</h2>
              </header>
              <div class="main-box-body clearfix" >
                <div id="pieChart"
                     style="height: 400px; padding: 0px; position: relative;"></div>
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
      });
    },
    methods: {
      init: function () {
        this.getAge();
        this.getHistogram();
      },
      getAge: function () {
        const dom = document.getElementById("pieChart");
        const myChart = this.$echarts.init(dom);
        let app = {};
        myChart.setOption({
          backgroundColor: '#ecffea',

          title: {
            text: '部门全年绩效统计',
            left: 'center',
            top: 20,
            textStyle: {
              color: '#1b537a'
            }
          },

          tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },

          visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
              colorLightness: [0, 1]
            }
          },
          series : [
            {
              name:'绩效评分',
              type:'pie',
              radius : '55%',
              center: ['50%', '50%'],
              data:[
                {value:335, name:'优秀'},
                {value:310, name:'良好'},
                {value:274, name:'中'},
                {value:235, name:'差'},
              ].sort(function (a, b) { return a.value - b.value; }),
              roseType: 'radius',
              label: {
                normal: {
                  textStyle: {
                    color: '#1b537a'
                  }
                }
              },
              labelLine: {
                normal: {
                  lineStyle: {
                    color: '#ffc76b'
                  },
                  smooth: 0.2,
                  length: 10,
                  length2: 20
                }
              },
              itemStyle: {
                normal: {
                  color: '#058dbe',
                  shadowBlur: 200,
                  shadowColor: 'rgba(0, 0, 0, 0.15)'
                }
              },

              animationType: 'scale',
              animationEasing: 'elasticOut',
              animationDelay: function (idx) {
                return Math.random() * 200;
              }
            }
          ]
        });
      },
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
