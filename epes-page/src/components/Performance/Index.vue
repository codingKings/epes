<template>
  <div id="content-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <div class="row">
              <div class="col-lg-12">
                <ol class="breadcrumb">
                  <li><a href="/">首页</a></li>
                  <li class="active"><span>绩效管理</span></li>
                </ol>
              </div>
            </div>

            <div class="search">
              <div class="row">
                <div class="col-lg-12">
                  <div class="main-box">
                    <div class="main-body">
                      <div class="row">
                        <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                          <span style="margin-left:5%;margin-top: 5%;color: #5c6e7a;font-weight:bold;">用户名:</span>
                          <label style="margin-top: 5%;">
                            <input type="text" v-model="name" class="form-control"/>
                          </label>
                        </div>
                         <!--   -->
                        <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                          <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">科室:</span>
                          <label style="margin-top: 5%;">
                            <select class="form-control" v-model="deptId">
                              <option value=''>所有科室</option>
                              <option  v-for="dept in depts" :value='dept.id' :key="dept.deptId" >{{ dept.name }}</option>
                            </select>
                          </label>
                        </div>
                        <div class="form-group col-lg-3 col-md-3 col-sm-12 col-xs-12">
                          <button type="button" style="margin-top: 5%;" class="btn btn-primary" @click="getUserInfo">
                            <i class="fa fa-search"></i> 搜索
                          </button>
                          <button type="button" style="margin-top: 5%;" class="btn btn-primary" @click="refresh">
                            <i class="fa fa-refresh"></i> 刷新
                          </button>
                          <!-- <button type="button" style="margin-top: 5%;" class="btn btn-danger" @click="showScore(userId)">
                            <i class="fa fa-terminal"></i> 绩效自评
                          </button> -->
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div v-if="userinfo.length === 0" style="color: #000000;margin-left: 3%;">
                未查找到相应人员
              </div>
              <div class="col-lg-2 col-md-4 col-sm-6 col-xs-6"  v-for="user in userinfo" :key="user.code">
                <div class="main-box clearfix profile-box-contact">
                  <div class="main-box-body clearfix">
                    <div class="profile-box-header gray-bg clearfix">
                      <!-- <img src="../../../static/img/samples/user.jpg" alt=""
                           class="profile-img img-responsive"/> --> 
                      <h2>{{ user.name }}</h2>
                      <div class="job-position">
                        <label>科室：</label>{{ user.deptName }} 
                        <!--, {{ user.sex }} -->
                      </div>
                      <!-- <ul class="contact-details">
                        <li>
                          <i class="fa fa-map-marker"></i> {{ user.address }}
                        </li>
                        <li>
                          <i class="fa fa-eye"></i> {{ user.age }}

                        </li>
                        <li>
                          <i class="fa fa-phone"></i> {{ user.phone }}
                        </li>
                      </ul> -->
                    </div>
                    <div class="profile-box-footer clearfix">
                      <div class="margin-10" style="margin-left:5%;">
                        <button type="button" class="btn btn-primary "  :style="{'background-color':(user.isScore==='Y'? '#95A5A6':'green')}" @click="showScore(user.id,user.isScore)">
                          <i class="fa fa-wrench"></i>
                                                                                绩效考评
                        </button>
<!--                         <button type="button" class="btn btn-primary " @click="showLog(user.id,user.name)">
                          <i class="fa fa-wrench"></i>
                          查看日志
                        </button> -->
                        <!--<button type="button" class="btn btn-warning" @click="showScore(user.id)">-->
                          <!--<i class="fa fa-unlock-alt"></i>-->
                          <!--查看历史考评-->
                        <!--</button>-->
                      </div>
                    </div>
                  </div>
                </div>
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
    name: "performanceIndex",
    data() {
      return {
        msg:"",
        userinfo: [],
        userid:'',//登陆人自己
        pageIndex: 0,
        size: 999,
        name:'',
        deptId:'',
        code:'',
        depts:[],
        deptModel:[],
        isScore:''
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.userId = sessionStorage.getItem("userid");
        this.getUserInfo();
        this.getDept();
      },
      getUserInfo: function () {
        const that = this;
        const data = {
          'pageIndex':this.pageIndex,
          'size':this.size,
          'searchMap':{
            'name': this.name,
            'deptid': this.deptId,
            'code': this.code
          },
          'userId':this.userId,
          
        };
        that.$http.post(global.appCtx + '/UserInfo/findUserIsScoreBypage',data).then(function (response) {
          this.userinfo = response.data;
        },function (error) {
          console.log(error);
        });
      },
      getDept:function () {
        const that = this;
        that.$http.get(global.appCtx + '/dept/findAllDept').then(function (response) {
          this.depts = response.data;
        },function (error) {
          console.log(error);
        });
      },
      refresh: function () {
        this.name = '';
        this.code = '';
        this.getUserInfo();
      },
      showLog:function (id,name) {
        this.$router.push({
          path: '/AddScore',
          query: {
            id: id ,
            name: name
          }
        });
      },
      showScore:function (id,isScore) {
        this.$router.push({
          path: '/AddScore',
          query: {
            id: id,
            isScore:isScore
          }
        });
      }
    },
    watch:{
    }
  }

</script>

<style>

</style>
