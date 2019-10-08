<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>任务详情</h2>
          </header>
          <div class="main-box-body clearfix">
            <div class="form-group col-lg-6 col-xs-12">
              <label for="inputEmail1" class="col-lg-2 control-label">任务名称：</label>
              <div class="col-lg-10">
                <input type="email" class="form-control" id="inputEmail1"
                       placeholder="任务名称" v-model="poj.name" disabled>
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
            <header class="main-box-header clearfix">
              <h2>日志填报</h2>
            </header>
            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">进展情况：</label>
              <div class="col-lg-11">
                <textarea v-model="progress" class="form-control" rows="4" ></textarea>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">问题及原因分析：</label>
              <div class="col-lg-11">
                <textarea v-model="question" class="form-control" rows="4" ></textarea>
              </div>
            </div>

            <header class="main-box-header clearfix">
              <h2>任务自评</h2>
            </header>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">进度评价：</label>
              <div class="col-lg-10">
                <label class="radio-inline">
                  <input type="radio" name="progressScore" v-model="progressScore" id="optionsRadios1" value="0"> 提前
                </label>
                <label class="radio-inline">
                  <input type="radio" name="progressScore" v-model="progressScore" id="optionsRadios2"  value="1"> 正常
                </label>
                <label class="radio-inline">
                  <input type="radio" name="progressScore" v-model="progressScore" id="optionsRadios3"  value="2"> 滞后
                </label>
              </div>
            </div>

            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">质量评价：</label>
              <div class="col-lg-10">
                <label class="radio-inline">
                  <input type="radio" name="qualityScore" v-model="qualityScore" id="options1" value="0"> 好
                </label>
                <label class="radio-inline">
                  <input type="radio" name="qualityScore" v-model="qualityScore" id="options2"  value="1"> 中
                </label>
                <label class="radio-inline">
                  <input type="radio" name="qualityScore" v-model="qualityScore" id="options3"  value="2"> 差
                </label>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12 text-center">
              <button type="button" class="btn btn-success " @click="submit">保存</button>
              <button type="button" class="btn btn-info" @click="goBack">返回</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>

  import $ from "jquery"
  import global from "../Global"

  export default {
    name: "table1",
    data() {
      return {
        dept:[],
        deptModel:[],
        userInfo:[],
        poj:{},
        progress:"",//进展情况
        question:"",//问题及原因
        progressScore : "", //进度评分
        qualityScore :"" ,//质量评分
        userid:''
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
      this.userid = sessionStorage.getItem("userid");
    },
    methods: {
      init: function () {
        this.getPoj();
      },
      getPoj: function () {
        const that = this;
        const pojid= this.$route.query.pojid;
        const data = {
          'id': pojid
        };
        that.$http.post(global.appCtx + '/projct/findPojById',data).then(function (response) {
          this.poj = response.data;
        },function (error) {
          console.log(error);
        });
      },
      getLog: function(){

      },
      goBack:function () {
        this.$router.push({
          path: '/WorkLog'
        });
      },
      submit:function(){
        const that = this;
        //校验
        const data = {

            'pojid' : this.poj.id,
            'userid' : this.userid,
            'pojname' : this.poj.name,
            'progress' : this.progress,
            'question' : this.question,
            'progressScore' : this.progressScore,
            'qualityScore' : this.qualityScore

        };
        that.$http.post(global.appCtx + '/worklog/addLog',data).then(function (response) {
          if (response.data.success === "success"){
            alert("保存成功！");
          }
        },function (error) {
          console.log(error);
        });
      }
    },
    update: function () {}
  }
</script>
