<template xmlns="http://www.w3.org/1999/html">
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="row">
          <div class="col-lg-12">
            <ol class="breadcrumb">
              <li><a href="indexpage">首页</a></li>
              <li class="active"><span>绩效考评</span></li>
            </ol>
          </div>
        </div>

        <div class="search">
          <div class="row">
            <div class="col-lg-12">
              <div class="main-box">
                <div class="main-body">
                  <div class="row text-center">
                    <h2>查看历史考评</h2>
                    <button class="btn btn-primary" @click="goBack">返回</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <section id="cd-timeline" class="cd-container">
              <div class="cd-timeline-block" v-for="score in scores" :key="score.score">
                <div class="cd-timeline-img cd-picture">
                  <i class="fa fa-file-o fa-2x"></i>
                </div>
                <div class="cd-timeline-content">
                  <h2>时间区间：{{ score.startdate }} - {{score.enddate}}</h2>
                  <p>评价者：{{ score.leaderName }}</p>
                  <p v-if="score.score == 0">评分：差</p>
                  <p v-if="score.score == 1">评分：中</p>
                  <p v-if="score.score == 2">评分：良好</p>
                  <p v-if="score.score == 3">评分：优秀</p>
                  <p>评语：{{score.review}}</p>
                </div>
              </div>
            </section>
          </div>
        </div>
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
    name: "UserManagement",
    data() {
      return {
        scores:[],
        score:[],
        userid:""
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.userid = this.$route.query.id;
        this.getScore();
      },
      getScore:function () {
        const that = this;
        const userid = this.userid;
        const data = {
          'userid': userid
        };
        that.$http.post(global.appCtx + '/score/showScore',data).then(function (response) {
          this.scores = response.data;
        },function (error) {
          console.log(error);
        });
      },
      goBack:function(){
        this.$router.go(-1);
      }
    },
    watch:{
    }
  }

</script>

<style>

</style>
