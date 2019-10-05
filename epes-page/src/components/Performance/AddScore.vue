<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
          <ol class="breadcrumb">
            <li><a href="indexpage">首页</a></li>
            <li class="active"><span>绩效管理</span></li>
          </ol>
        </div>
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>绩效评分</h2>
          </header>
          <div class="main-box-body clearfix">

            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">工作日期：</label>
              <div class="col-lg-10">
                <input type="date" class="form-control" placeholder="开始日期" v-model="startdate">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">至：</label>
              <div class="col-lg-10">
                <input type="date" class="form-control" placeholder="结束日期" v-model="enddate">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">被评人员</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" v-model="empname" readonly >
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">绩效评分：</label>
              <div class="col-lg-10">
                <select class="form-control" v-model="score">
                  <option value="3">优秀</option>
                  <option value="2">良好</option>
                  <option value="1">中</option>
                  <option value="0">差</option>
                </select>
              </div>
            </div>
            <div class="form-group col-lg-12 col-xs-12">
              <label for="input" class="col-lg-2 control-label">绩效评语：</label>
              <div class="col-lg-10">
                <textarea id="input" class="form-control" v-model="review" rows="3"></textarea>
              </div>
            </div>
            <div class="form-group text-center">
              <button type="button" class="btn btn-success " @click="submit">提交</button>
              <button type="button" class="btn btn-info" @click="goBack">返回</button>
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
        startdate:"",
        empname:"",
        empid:"",
        enddate:"",
        score:"",
        review:"",
        leaderid:""
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.leaderid = sessionStorage.getItem("userid");
        this.empid = this.$route.query.empid;
        this.empname = this.$route.query.empname;
      },
      goBack:function () {
        this.$router.push({
          path: '/Performance'
        });
      },
      submit:function(){
        const that = this;
        const data = {
          'startdate':this.startdate,
          'empid':this.empid,
          'enddate': this.enddate,
          'score':this.score,
          'review': this.review,
          'leaderid':this.leaderid
        };
        that.$http.post(global.appCtx + '/score/addScore',data).then(function (response) {
          alert(response.data.msg);
          if (response.data.success === "success"){
            this.goBack();
          }
        },function (error) {
          console.log(error);
        });
      }
    },
    update: function () {}
  }
</script>

<style>

</style>
