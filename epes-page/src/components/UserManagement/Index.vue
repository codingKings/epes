<template>
  <div id="content-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <div class="row">
              <div class="col-lg-12">
                <ol class="breadcrumb">
                  <li><a href="indexpage">首页</a></li>
                  <li class="active"><span>科员管理</span></li>
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
                          <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">工号:</span>
                          <label style="margin-top: 5%;">
                            <input type="text" v-model="code" class="form-control" />
                          </label>
                        </div>
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
                          <button type="button" style="margin-top: 5%;" class="btn btn-success" @click="addUser">
                            <i class="fa fa-plus"></i> 新建
                          </button>
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6" v-for="user in userinfo" :key="user.id">
                <div class="main-box clearfix profile-box-contact">
                  <div class="main-box-body clearfix">
                    <div class="profile-box-header gray-bg clearfix">
                      <img src="../../../static/img/samples/user.jpg" alt=""
                           class="profile-img img-responsive"/>
                      <h2>{{ user.name }}</h2>
                      <div class="job-position">
                        {{ user.deptName }} , {{ user.sex }}
                      </div>
                      <ul class="contact-details">
                        <li>
                          <i class="fa fa-map-marker"></i> {{ user.address }}
                        </li>
                        <li>
                          <i class="fa fa-eye"></i> {{ user.age }}

                        </li>
                        <li>
                          <i class="fa fa-phone"></i> {{ user.phone }}
                        </li>
                      </ul>
                    </div>
                    <div class="profile-box-footer clearfix">
                      <div class="margin-10" style="margin-left:5%;">
                        <button type="button" class="btn btn-primary " @click="modifiy(user.id)">
                          <i class="fa fa-wrench"></i>
                          编辑
                        </button>
                        <button type="button" class="btn btn-warning" @click="resetPassword(user.id)">
                          <i class="fa fa-unlock-alt"></i>
                          重置密码
                        </button>
                        <button type="button" class="btn btn btn-danger" @click="deleteUser(user.id)">
                          <i class="fa fa-trash"></i>
                          删除
                        </button>
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
    name: "UserManagement",
    data() {
      return {
        msg:"",
        userinfo: [],
        pageIndex: 0,
        size: 20,
        name:'',
        deptId:'',
        code:'',
        depts:[],
        deptModel:[]
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
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
          }
        };
        that.$http.post(global.appCtx + '/UserInfo/findUserBypage',data).then(function (response) {
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
      resetPassword: function (id) {
        const that = this;
        const data = {
          'id': id
        };

        that.$http.post(global.appCtx + '/UserInfo/resetPassword',data).then(function (response) {
          alert(response.data.msg);
        },function (error) {
          console.log(error);
        });
      },
      refresh: function () {
        this.name = '';
        this.deptId = '';
        this.code = '';
        this.getUserInfo();
      },
      deleteUser: function (id) {
        const that = this;
        const data = {
          'id': id
        };
        that.$http.post(global.appCtx + '/UserInfo/deleteUser',data).then(function (response) {
          this.refresh();
          alert(response.data.msg);
        },function (error) {
          console.log(error);
        });
      },
      modifiy:function (id) {
        this.$router.push({
          path: '/UserModifiy',
          query: { userId: id }
      });
      },
      addUser: function () {
        this.$router.push({
          path: '/AddUser',
        });
      }
    },
    watch:{
    }
  }

</script>

<style>

</style>
