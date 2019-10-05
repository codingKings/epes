<template xmlns="http://www.w3.org/1999/html">
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="row">
          <div class="col-lg-12">
            <ol class="breadcrumb">
              <li><a href="indexpage">首页</a></li>
              <li class="active"><span>绩效评分</span></li>
            </ol>
          </div>
        </div>

        <div class="search">
          <div class="row">
            <div class="col-lg-12">
              <div class="main-box">
                <div class="main-body">
                  <div class="row text-center">
                  <h2>{{ username }}的工作日志</h2>
                  </div>
                  <div class="row">
                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                      <span style="margin-left:5%;margin-top: 5%;color: #5c6e7a;font-weight:bold;">日志时间:</span>
                      <label style="margin-top: 5%;">
                        <input type="date" v-model="startdate" class="form-control"/>
                      </label>
                    </div>
                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                      <span style="margin-left:5%;margin-top: 5%;color: #5c6e7a;font-weight:bold;">至</span>
                      <label style="margin-top: 5%;">
                        <input type="date" v-model="enddate" class="form-control"/>
                      </label>
                    </div>

                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                      <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">任务:</span>
                      <label style="margin-top: 5%;">
                        <select class="form-control" v-model="pojid">
                          <option value="">所有状态</option>
                          <option v-for="poj in pojs"  :value="poj.id" :key="poj.id">{{poj.name}}</option>
                        </select>
                      </label>
                    </div>

                    <div class="form-group col-lg-3 col-md-3 col-sm-12 col-xs-12">
                      <button type="button" style="margin-top: 5%;" class="btn btn-primary" @click="getLog">
                        <i class="fa fa-search"></i> 搜索
                      </button>

                      <button type="button" style="margin-top: 5%;" class="btn btn-success" @click="addScore">
                        <i class="fa fa-plus"></i> 绩效评分
                      </button>
                      <button type="button" style="margin-top: 5%;" class="btn btn-primary" @click="goBack">
                        <i class="fa fa-refresh"></i> 返回
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
            <div class="row">
              <div class="col-lg-12">
                <section id="cd-timeline" class="cd-container">
                  <div class="cd-timeline-block" v-for="log in logs" :key="log.pojid">
                    <div class="cd-timeline-img cd-picture">
                      <i class="fa fa-file-o fa-2x"></i>
                    </div>
                    <div class="cd-timeline-content">
                      <h2>{{ log.pojname }}</h2>
                      <p>{{ log.workdate }}</p>
                      <p>{{log.content}}</p>
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
        pojid:"",
        workdate:"",
        logs:[],
        log:[],
        pojs:[],
        deptid:"",
        pojname:"",
        startdate:"",
        enddate:"",
        userid:"",
        username:""
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.deptId = sessionStorage.getItem("user_dept");
        this.userid = this.$route.query.id;
        this.username = this.$route.query.name;
        this.getLog();
        this.getPojs();
      },
 /*     getUserInfo: function () {
        const that = this;
        const data = {
          'pageIndex':this.pageIndex,
          'size':this.size,
          'searchMap':{
            'name': this.name,
            'deptid': this.deptId,
            'code': this.code
          }
        };
        that.$http.post(global.appCtx + '/UserInfo/findUserBypage',data).then(function (response) {
          this.userinfo = response.data;
        },function (error) {
          console.log(error);
        });
      },*/
      getLog:function () {
        const that = this;
        const userid = this.userid;
        const data = {
            'startdate': this.startdate,
            'pojid': this.pojid,
            'userid': userid,
            'enddate': this.enddate
        };
        that.$http.post(global.appCtx + '/worklog/findLogOnUser',data).then(function (response) {
          this.logs = response.data;
        },function (error) {
          console.log(error);
        });
      },
      getPojs: function () {
        const that = this;
        const data = {
          'searchMap':{
            "deptid":this.deptId,
            'dr': 0
          }
        };
        that.$http.post(global.appCtx + '/projct/findPojByDept',data).then(function (response) {
          this.pojs = response.data;
        },function (error) {
          console.log(error);
        });
      },
      disabled: function (id,dr) {
        const that = this;
        const data = {
          'id': id,
          'dr':dr
        };
        that.$http.post(global.appCtx + '/dept/disabled',data).then(function (response) {
          if (response.data.success === "success"){
            alert(response.data.msg);
          }
          this.refresh();
        },function (error) {
          console.log(error);
        });
      },
      refresh: function () {
        this.pojid = '';
        this.startdate="";
        this.enddate="";
        this.getLog();
      },
      modifiy: function (id) {
        this.$router.push({
          path: '/LogModifiy',
          query: { logid: id }
        });
      },
      addScore: function () {
        this.$router.push({
          path: '/AddScore',
          query: {
            empid: this.userid,
            empname: this.username
          }
        });
      },
      goBack:function(){
        this.$router.push({
          path: '/Performance',
        });
      },
      deletelog:function (id) {
        const that = this;
        const data = {
          'id': id,
        };
        that.$http.post(global.appCtx + '/worklog/delete',data).then(function (response) {
          if (response.data.success === "success"){
            alert(response.data.msg);
          }
          this.refresh();
        },function (error) {
          console.log(error);
        });
      }
    },
    watch:{
    }
  }

</script>

<style>

</style>
