<!--suppress ALL -->
<template>
  <div>
    <div id="theme-wrapper">
    <header class="navbar" id="header-navbar">
        <div class="container">
          <a href="#" id="logo" class="navbar-brand">
            <img src="../static/img/logo.png" alt="" class="normal-logo logo-white"/>
            <img src="../static/img/logo-black.png" alt="" class="normal-logo logo-black"/>
            <img src="../static/img/logo-small.png" alt="" class="small-logo hidden-xs hidden-sm hidden"/>
          </a>
          <div class="clearfix">
            <button class="navbar-toggle" data-target=".navbar-ex1-collapse" data-toggle="collapse" type="button">
              <span class="sr-only">Toggle navigation</span>
              <span class="fa fa-bars"></span>
            </button>
            <div class="nav-no-collapse navbar-left pull-left hidden-sm hidden-xs">
              <ul class="nav navbar-nav pull-left">
                <li>
                  <a class="btn" id="make-small-nav">
                    <i class="fa fa-bars"></i>
                  </a>
                </li>
              </ul>
            </div>
            <div class="nav-no-collapse pull-right" id="header-nav">
              <ul class="nav navbar-nav pull-right">
                <li class="mobile-search">
                  <a class="btn">
                    <i class="fa fa-search"></i>
                  </a>
                  <div class="drowdown-search">
                    <form role="search">
                      <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search...">
                        <i class="fa fa-search nav-search-icon"></i>
                      </div>
                    </form>
                  </div>
                </li>
                <li class="dropdown hidden-xs">
                  <a class="btn dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-warning"></i>
                    <span class="count">8</span>
                  </a>
                  <ul class="dropdown-menu notifications-list">
                    <li class="pointer">
                      <div class="pointer-inner">
                        <div class="arrow"></div>
                      </div>
                    </li>
                    <li class="item-header">You have 6 new notifications</li>
                    <li class="item">
                      <a href="#">
                        <i class="fa fa-comment"></i>
                        <span class="content">New comment on ‘Awesome P...</span>
                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                      </a>
                    </li>
                    <li class="item">
                      <a href="#">
                        <i class="fa fa-plus"></i>
                        <span class="content">New user registration</span>
                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                      </a>
                    </li>
                    <li class="item">
                      <a href="#">
                        <i class="fa fa-envelope"></i>
                        <span class="content">New Message from George</span>
                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                      </a>
                    </li>
                    <li class="item">
                      <a href="#">
                        <i class="fa fa-shopping-cart"></i>
                        <span class="content">New purchase</span>
                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                      </a>
                    </li>
                    <li class="item">
                      <a href="#">
                        <i class="fa fa-eye"></i>
                        <span class="content">New order</span>
                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                      </a>
                    </li>
                    <li class="item-footer">
                      <a href="#">
                        View all notifications
                      </a>
                    </li>
                  </ul>
                </li>
                <li class="dropdown hidden-xs">
                  <a class="btn dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-envelope-o"></i>
                    <span class="count">16</span>
                  </a>
                  <ul class="dropdown-menu notifications-list messages-list">
                    <li class="pointer">
                      <div class="pointer-inner">
                        <div class="arrow"></div>
                      </div>
                    </li>
                    <li class="item first-item">
                      <a href="#">
                        <img src="../static/img/samples/messages-photo-1.png" alt=""/>
                        <span class="content">
                          <span class="content-headline">
                            George Clooney
                          </span>
                          <span class="content-text">
                            Look, just because I don't be givin' no man a foot massage don't make it
                            right for Marsellus to throw...
                          </span>
                        </span>
                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                      </a>
                    </li>
                    <li class="item">
                      <a href="#">
                        <img src="../static/img/samples/messages-photo-2.png" alt=""/>
                        <span class="content">
                          <span class="content-headline">
                            Emma Watson
                          </span>
                          <span class="content-text">
                            Look, just because I don't be givin' no man a foot massage don't make it
                            right for Marsellus to throw...
                          </span>
                        </span>
                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                      </a>
                    </li>
                    <li class="item">
                      <a href="#">
                        <img src="../static/img/samples/messages-photo-3.png" alt=""/>
                        <span class="content">
                          <span class="content-headline">
                            Robert Downey Jr.
                          </span>
                          <span class="content-text">
                            Look, just because I don't be givin' no man a foot massage don't make it
                            right for Marsellus to throw...
                          </span>
                        </span>
                        <span class="time"><i class="fa fa-clock-o"></i>13 min.</span>
                      </a>
                    </li>
                    <li class="item-footer">
                      <a href="#">
                        View all messages
                      </a>
                    </li>
                  </ul>
                </li>
                <li class="hidden-xs">
                  <a class="btn">
                    <i class="fa fa-cog"></i>
                  </a>
                </li>
                <li class="dropdown profile-dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <img src="../static/img/samples/scarlet-159.png" alt=""/>
                    <span class="hidden-xs">{{ userInfo.name }}</span> <b class="caret"></b>
                  </a>
                  <ul class="dropdown-menu">
                    <li><a href="#"><i class="fa fa-user"></i>修改个人信息</a></li>
                    <li><a href="#"><i class="fa fa-cog"></i>修改密码</a></li>
                    <li><a href="#" @click="exit"><i class="fa fa-power-off" ></i>退出登陆</a></li>
                  </ul>
                </li>
                <li class="hidden-xxs">
                  <a class="btn" @click="exit">
                    <i class="fa fa-power-off"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </header>
    <div id="page-wrapper" class="container" style="width: 100%">
        <div class="row">
          <div id="nav-col">
            <section id="col-left" class="col-left-nano">
              <div id="col-left-inner" class="col-left-nano-content">
                <div id="user-left-box" class="clearfix hidden-sm hidden-xs">
                  <img alt="" src="../static/img/samples/scarlet-159.png"/>
                  <div class="user-box">
                    <span class="name">
                      {{ userInfo.name }}<br/>
                      欢迎使用
                    </span>
                    <span class="status">
                      <i class="fa fa-circle"></i>
                      在线
                    </span>
                  </div>
                </div>
                <div class="collapse navbar-collapse navbar-ex1-collapse" id="sidebar-nav">
                  <ul class="nav nav-pills nav-stacked" v-if="meun.pid != 0">
                    <li class="active">
                      <a href="/">
                        <i class="fa fa-dashboard"></i>
                        <span>首页</span>
                      </a>
                    </li>
                    <li v-for="meun in meuns" :key="meun" >
                      <a  class="dropdown-toggle" :href="meun.url">
                        <i class="fa fa-table"></i>
                        <span>{{meun.name}}</span>
                        <i class="fa fa-chevron-circle-right drop-icon"></i>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </section>
          </div>
          <router-view/>
        </div>
      </div>
  </div>
    <div id="config-tool" class="closed">
    <a id="config-tool-cog">
      <i class="fa fa-cog"></i>
    </a>
    <div id="config-tool-options">
      <h4>布局选项</h4>
      <ul>
        <li>
          <div class="checkbox-nice">
            <input type="checkbox" id="config-fixed-header"/>
            <label for="config-fixed-header">
              固定顶菜单
            </label>
          </div>
        </li>
        <li>
          <div class="checkbox-nice">
            <input type="checkbox" id="config-fixed-sidebar"/>
            <label for="config-fixed-sidebar">
              固定菜单
            </label>
          </div>
        </li>
        <li>
          <div class="checkbox-nice">
            <input type="checkbox" id="config-fixed-footer"/>
            <label for="config-fixed-footer">
              固定页脚
            </label>
          </div>
        </li>
        <li>
          <div class="checkbox-nice">
            <input type="checkbox" id="config-boxed-layout"/>
            <label for="config-boxed-layout">
              盒装布局
            </label>
          </div>
        </li>
      </ul>
      <br/>
      <h4>主题颜色</h4>
      <ul id="skin-colors" class="clearfix">
        <li>
          <a class="skin-changer" data-skin="" data-toggle="tooltip" title="默认"
             style="background-color: #34495e;">
          </a>
        </li>
        <li>
          <a class="skin-changer" data-skin="theme-white" data-toggle="tooltip" title="浅绿"
             style="background-color: #2ecc71;">
          </a>
        </li>
        <li>
          <a class="skin-changer blue-gradient" data-skin="theme-blue-gradient" data-toggle="tooltip"
             title="蓝色渐变">
          </a>
        </li>
        <li>
          <a class="skin-changer" data-skin="theme-turquoise" data-toggle="tooltip" title="海绿"
             style="background-color: #1abc9c;">
          </a>
        </li>
        <li>
          <a class="skin-changer" data-skin="theme-amethyst" data-toggle="tooltip" title="紫晶"
             style="background-color: #9b59b6;">
          </a>
        </li>
        <li>
          <a class="skin-changer" data-skin="theme-blue" data-toggle="tooltip" title="蓝色"
             style="background-color: #2980b9;">
          </a>
        </li>
        <li>
          <a class="skin-changer" data-skin="theme-red" data-toggle="tooltip" title="红色"
             style="background-color: #e74c3c;">
          </a>
        </li>
        <li>
          <a class="skin-changer" data-skin="theme-whbl" data-toggle="tooltip" title="白蓝"
             style="background-color: #3498db;">
          </a>
        </li>
      </ul>
    </div>
  </div>
  </div>
</template>

<script>
  import $ from "jquery"
  import global from "./components/Global"

  export default {
    name: 'App',
    data() {
      return {
        userInfo:[],
        meuns:[],
        userid:"",
        meun:[],
        meun2:[]
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.verification();
        this.getMenu();
      },
      verification:function(){
        var userid = this.$route.query.id;
        const that = this;
        if (userid === undefined || userid === null || userid === ""){
          userid = sessionStorage.getItem('userid');
          if (userid === undefined || userid === null || userid === ""){
            alert("登陆已失效，请先登录！");
            window.location.href = global.appCtx;
          }
          this.getUserInfo(userid);
          this.userid= userid;
        } else {
         sessionStorage.setItem('userid',this.$route.query.id);
         // sessionStorage.setItem('username',this.$route.query.name);
         this.getUserInfo(userid);
         this.userid = userid;
        }
      },
      getUserInfo:function (id) {
        const that = this;
        const data = {
          "id": id
        }
        that.$http.post(global.appCtx+'/UserInfo/findUserById',data).then(function (response) {
          this.userInfo = response.data;
          sessionStorage.setItem('user_dept',this.userInfo.deptid);
          var roles = this.userInfo.userRolesList;
          var role = '';
          if (roles != null && roles != undefined){
            for(var i = 0;i< roles.length; i++){
              role += roles[i].roleid.toString()+",";
            }
          }
          //保存权限
          sessionStorage.setItem("user_role",role);
        },function (error) {
          console.log(error);
        });
      },
      getMenu:function () {
        const that = this;
        const data = {
          "userid" : this.userid
        }
        that.$http.post(global.appCtx+'/getApp',data).then(function (response) {
         this.meuns = response.data;
        },function (error) {
          console.log(error);
        });
      },
      exit:function () {
        sessionStorage.removeItem("userid");
        sessionStorage.removeItem("user_dept");
        sessionStorage.removeItem('user_role');
        // sessionStorage.removeItem('username');
        window.location.href = global.appCtx;
      }
    },
    update: function () {}
  }
</script>
<style>

</style>
