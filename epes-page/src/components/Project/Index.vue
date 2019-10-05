<template xmlns="http://www.w3.org/1999/html">
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="row">
          <div class="col-lg-12">
            <ol class="breadcrumb">
              <li><a href="indexpage">首页</a></li>
              <li class="active"><span>任务管理</span></li>
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
                      <span style="margin-left:5%;margin-top: 5%;color: #5c6e7a;font-weight:bold;">任务名称:</span>
                      <label style="margin-top: 5%;">
                        <input type="text" v-model="name" class="form-control"/>
                      </label>
                    </div>

                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                      <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">编号:</span>
                      <label style="margin-top: 5%;">
                        <input type="text" v-model="code" class="form-control" />
                      </label>
                    </div>
                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                      <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">科室:</span>
                      <label style="margin-top: 5%;">
                        <select class="form-control" v-model="deptId">
                          <option value=''>所有科室</option>
                          <option  v-for="dept in depts" :value='dept.id' :key="dept.deptId">{{ dept.name }}</option>
                        </select>
                      </label>
                    </div>
                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                      <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">状态:</span>
                      <label style="margin-top: 5%;">
                        <select class="form-control" v-model="dr">
                          <option value="">所有状态</option>
                          <option  value=0 >开启状态</option>
                          <option  value=1 >终止状态</option>
                        </select>
                      </label>
                    </div>
                    <div class="form-group col-lg-3 col-md-3 col-sm-12 col-xs-12">
                      <button type="button" style="margin-top: 5%;" class="btn btn-primary" @click="getPojs">
                        <i class="fa fa-search"></i> 搜索
                      </button>
                      <button type="button" style="margin-top: 5%;" class="btn btn-primary" @click="refresh">
                        <i class="fa fa-refresh"></i> 刷新
                      </button>
                      <button type="button" style="margin-top: 5%;" class="btn btn-success" @click="addDept">
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
          <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6" v-for="poj in pojs" :key="poj.id">
            <div class="main-box clearfix profile-box-contact">
              <div class="main-box-body clearfix">
                <div class="profile-box-header gray-bg clearfix">
                  <h2>任务名称：{{ poj.name }}</h2><br/>>
                  <h2>编  号：{{ poj.code }}</h2>
                  <ul class="contact-details">
                    <li>
                      <i class="fa fa-eye"></i> 开始时间：{{ poj.startdate }}
                    </li>
                    <li>
                      <i class="fa fa-eye-slash"></i> 结束时间：{{ poj.enddate }}
                    </li>
                    <li > <!-- v-if="" -->
                      <i class="fa fa-phone"></i> 所属科室：{{ poj.deptname }}
                    </li>
                  </ul>
                </div>
                <div class="profile-box-footer clearfix">
                  <div class="margin-10" style="margin-left:5%;">
                    <button type="button" class="btn btn-primary " @click="modifiy(poj.id)">
                      <i class="fa fa-wrench"></i>
                      编辑
                    </button>
                    <button type="button" class="btn btn-default " @click="disabled(poj.id,1)" v-if="poj.dr==0">
                      <i class="fa fa-times"></i>
                      终止
                    </button>
                    <button type="button" class="btn btn btn-warning" @click="disabled(poj.id,0)" v-else>
                      <i class="fa fa-unlock-alt "></i>
                      启动
                    </button>
                    <button type="button" class="btn btn-danger " @click="deletePoj(poj.id)">
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
        name:'',
        deptId:'',
        code:'',
        dr:'',
        depts:[],
        deptModel:[],
        pojs:[],
        poj:[],
        dept:[]
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.getDept();
        this.getPojs();
      },
      getPojs: function () {
        const that = this;
        const data = {
          'pageIndex':0,
          'size':20,
          'searchMap':{
            'name': this.name,
            'deptid': this.deptId,
            'code': this.code,
            'dr': this.dr
          }
        };
        that.$http.post(global.appCtx + '/projct/findAll',data).then(function (response) {
          this.pojs = response.data;
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
      disabled: function (id,dr) {
        const that = this;
        const data = {
          'id': id,
          'dr':dr
        };
        that.$http.post(global.appCtx + '/projct/disabled',data).then(function (response) {
          if (response.data.success === "success"){
            alert(response.data.msg);
          }
          this.refresh();
        },function (error) {
          console.log(error);
        });
      },
      refresh: function () {
        this.name = '';
        this.code = '';
        this.dr = '';
        this.deptId='';
        this.getPojs();
      },
      modifiy: function (id) {
        this.$router.push({
          path: '/PojModifiy',
          query: { pojid: id }
        });
      },
      deletePoj:function(id){
        const that = this;
        const data ={
          "id": id
        };
        that.$http.post(global.appCtx + '/projct/delete',data).then(function (response) {
          if (response.data.success === "success"){
            alert(response.data.msg);
          }
          this.refresh();
        },function (error) {
          console.log(error);
        });
      },
      addDept: function () {
        this.$router.push({
          path: '/AddPoj',
        });
      }
    },
    watch:{
    }
  }

</script>

<style>

</style>
