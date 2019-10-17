<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12 col-xs-12" v-if="pojs.length <= 0">
        <label class="col-lg-2 control-label" style="color: red;">未查询到该月的任务列表</label>
      </div>
      <div class="col-lg-12" v-for="(poj,index) in pojs">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>任务详情</h2>
          </header>
          <div class="main-box-body clearfix">
            <div class="form-group col-lg-12 col-xs-12">
              <label class="col-lg-2 control-label">任务名称：</label>
              <div class="col-lg-10">
                <h4 style="font-weight: bolder;">{{poj.name}}</h4>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">所属科室：</label>
              <div class="col-lg-10">
                <label>{{ poj.deptname }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">人员分配：</label>
              <div class="col-lg-10">
                <label>{{ poj.userName }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">开始时间：</label>
              <div class="col-lg-10">
                <label>{{ poj.startdate }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">结束时间：</label>
              <div class="col-lg-10">
                <label>{{ poj.enddate }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务类型：</label>
              <div class="col-lg-10">
                <label class="radio-inline" v-if="poj.type == 0">
                  年度任务
                </label>
                <label class="radio-inline" v-if="poj.type == 1">
                  月度任务
                </label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务种类：</label>
              <div class="col-lg-10">
                {{ poj.parent_name }}
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务子类：</label>
              <div class="col-lg-10">
                {{ poj.sub_type_name }}
              </div>
            </div>
            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务描述：</label>
              <div class="col-lg-11">
                <textarea v-model="poj.content" class="form-control" rows="3" disabled></textarea>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务要求：</label>
              <div class="col-lg-11">
                <textarea v-model="poj.demand" class="form-control" rows="4" disabled></textarea>
              </div>
            </div>
            <div class="form-group col-lg-12 col-xs-12">
              <!-- 分割线 -->
              <div class="fenge" style="width: 100%;height: 10px;border-bottom: #707070 solid 2px;"></div>
            </div>

            <div>
              <input type="text" name="pojId" :id="poj.id" v-model="pojid[index]=poj.id" hidden >
              <input type="text" name="pojName" v-model="pojname[index]=poj.name" hidden >
              <header class="main-box-header clearfix">
                <h2>日志填报</h2>
              </header>
              <div class="form-group col-lg-12 col-xs-12">
                <label
                  class="col-lg-1 control-label">进展情况：</label>
                <div class="col-lg-11">
                  <textarea v-model="progress[index]" class="form-control" rows="4"></textarea>
                </div>
              </div>

              <div class="form-group col-lg-12 col-xs-12">
                <label
                  class="col-lg-1 control-label">问题及原因分析：</label>
                <div class="col-lg-11">
                  <textarea v-model="question[index]" class="form-control" rows="4"></textarea>
                </div>
              </div>

              <!--任务自评-->
             <!-- <header class="main-box-header clearfix">
                <h2>任务自评</h2>
              </header>
              <input type="text" id="pojId" v-model="pojid[index]=poj.id" hidden >
              <div class="form-group col-lg-6 col-xs-12">
                <label
                  class="col-lg-2 control-label">进度评价：</label>
                <div class="col-lg-10">
                    <div class="radio radio-inline" style="margin-top: 0;">
                    <input type="radio" :id="poj.id+'progressScore'+1" v-model="progressScore[index]" value="0" :name="poj.id+'progressScore'"
                           checked>
                    <label :for="poj.id+'progressScore'+1"> 提前 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" :id="poj.id+'progressScore'+2" v-model="progressScore[index]" value="1" :name="poj.id+'progressScore'">
                    <label :for="poj.id+'progressScore'+2"> 正常 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" :id="poj.id+'progressScore'+3" v-model="progressScore[index]" value="2" :name="poj.id+'progressScore'">
                    <label :for="poj.id+'progressScore'+3"> 滞后 </label>
                  </div>
                </div>
              </div>

              <div class="form-group col-lg-6 col-xs-12">
                <label class="col-lg-2 control-label">质量评价：</label>
                <div class="col-lg-10">
                  <div class="radio radio-inline" style="margin-top: 0;">
                    <input type="radio" :id="poj.id+'qualityScore'+1" v-model="qualityScore[index]" value="0" :name="poj.id+'qualityScore'" checked>
                    <label :for="poj.id+'qualityScore'+1"> 好 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" :id="poj.id+'qualityScore'+2" v-model="qualityScore[index]" value="1" :name="poj.id+'qualityScore'">
                    <label :for="poj.id+'qualityScore'+2"> 中 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" :id="poj.id+'qualityScore'+3" v-model="qualityScore[index]" value="2" :name="poj.id+'qualityScore'">
                    <label :for="poj.id+'qualityScore'+3"> 差 </label>
                  </div>
                </div>
              </div>-->
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-12 col-xs-12 text-center main-box" style="padding: 10px;">
        <label class="col-lg-4 control-label" style="color: red;" v-if="limitDateState === 1">日志填报时间为每月{{limitDate}}号至月底</label>
        <button type="button" class="btn btn-success " v-if="pojs.length > 0" @click="submit" :disabled="limitDateState === 1">提交</button>
        <!--<button type="button" class="btn btn-info" @click="goBack">返回</button>-->
      </div>
    </div>
    <footer id="footer-bar" class="row">
      <p id="footer-copyright" class="col-xs-12">
        &copy; 2019 <a href="#" target="_blank">山东亿维·创联</a>. Powered by
        Chenglong.
      </p>
    </footer>
  </div>
</template>


<script>

  import $ from "jquery"
  import global from "../Global"

  export default {
    name: "workLogIndex",
    data() {
      return {
        pojs : [],
        pojid: [],
        pojname:[],
        progress: [],//进展情况
        question: [],//问题及原因
        //progressScore: [], //进度评分
        //qualityScore: [],//质量评分
        userid: '',
        logs: [],
        nowDate: '',
        limitDateState: 0, //是否在日期限制，0为可编辑日志，1为不可编辑日志
        limitDate: ''
      }
    },
    mounted: function () {
      this.userid = sessionStorage.getItem("userid");
      //时间校验，判断是否到达日志填报日期
      this.limitDate = global.performance_reporting_time;
      var date = new Date();
      if ( parseInt(this.limitDate) <= date.getDate() ){
        this.limitDateState = 0;
      } else {
        this.limitDateState = 1;
      }
      this.$nextTick(function () {
        this.init();
      });
    },
    watch:{
      'progress' : function () {
        console.log(this.progress);
      }
    },
    methods: {
      init: function () {
        this.getPoj();
      },
      getPoj: function () {
        const that = this;
        const data = {
          'userId': this.userid,
          'state' : '01'
        };
        that.$http.post(global.appCtx + '/projct/findPojByUserId', data).then(function (response) {
          this.pojs = response.data;
          for (var i =0;i<this.pojs.length;i++){
            this.pojid[i] = this.pojs[i].id;
          }
          this.getLog();
        }, function (error) {
          console.log(error);
          alert("网络连接失败");
        });
      },
      getLog: function () {
        const that = this;
        var date = new Date();
        var dateSrc = "";
        if (date.getMonth()+1<10){
          dateSrc=date.getFullYear()+"-0"+(date.getMonth()+1);
        } else {
          dateSrc=date.getFullYear()+"-"+(date.getMonth()+1);
        }
        const data = {
          'userid': this.userid,
          'pojids': this.pojid,
          'logDate' : dateSrc
        };
        that.$http.post(global.appCtx + '/worklog/findLogsByPojOfUser', data).then(function (response) {
          var log = response.data.data;
          for (var i=0;i<log.length;i++){
            var pid = log[i].pojid;
            for (var j=0;j<that.pojid.length;j++){
              if (pid === that.pojid[j]) {
                this.progress[j] = log[i].progress;
                this.question[j] = log[i].question;
              }
            }
          }
          console.log(this.progress);
        }, function (error) {
          console.log(error);
          alert("获取历史日志失败！");
        });
      },
      goBack: function () {
        this.$router.push({
          path: '/WorkLog'
        });
      },
      submit: function () {
        const that = this;
        //校验
        const data = {
          'pojids': this.pojid,
          'userid': this.userid,
          'pojnames': this.pojname,
          'progress': this.progress,
          'questions': this.question
          // 'progressScore': this.progressScore,
          // 'qualityScore': this.qualityScore

        };
        that.$http.post(global.appCtx + '/worklog/addLogs', data).then(function (response) {
          if (response.data.state === "200") {
            alert("保存成功！");
          } else {
            console.log(response.data.error);
            alert("保存失败！");
          }
        }, function (error) {
          console.log(error);
          alert("提交失败！");
        });
      }
    },
    update: function () {
    }
  }
</script>
