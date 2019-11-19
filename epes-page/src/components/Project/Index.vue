<template xmlns="http://www.w3.org/1999/html">
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="row">
          <div class="col-lg-12">
            <ol class="breadcrumb">
              <li><a href="/">首页</a></li>
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

                    <!--<div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">-->
                      <!--<span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">编号:</span>-->
                      <!--<label style="margin-top: 5%;">-->
                        <!--<input type="text" v-model="code" class="form-control" />-->
                      <!--</label>-->
                    <!--</div>-->
                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4" v-if="role===1">
                      <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">科室:</span>
                      <label style="margin-top: 5%;">
                        <select class="form-control" v-model="deptId">
                          <option value=''>所有科室</option>
                          <option  v-for="dept in depts" :value='dept.id' :key="dept.deptId">{{ dept.name }}</option>
                        </select>
                      </label>
                    </div>

                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4" v-if="role===1">
                      <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">执行人:</span>
                      <label style="margin-top: 5%;">
                        <select class="form-control" v-model="deptUserId">
                          <option value=''>所有员工</option>
                          <option v-for="user in deptUsers" :value='user.id' :key="user.id">{{ user.name }}</option>
                        </select>
                      </label>
                    </div>
                    <div class="form-group col-lg-3 col-md-3 col-sm-4 col-xs-4">
                      <span style="margin-top: 5%;color: #5c6e7a;font-weight:bold;">状态:</span>
                      <label style="margin-top: 5%;">
                        <select class="form-control" v-model="state">
                          <option value="">所有状态</option>
                          <option  value='00' >待审核</option>
                          <option  value='01' >审核通过</option>
                          <option  value='02' >审核不通过</option>
                        </select>
                      </label>
                    </div>
                    <div class="form-group col-lg-3 col-md-3 col-sm-12 col-xs-12" style="padding-bottom:20px">
                      <button type="button" style="margin-top: 5%;" class="btn btn-primary" @click="getPojs">
                        <i class="fa fa-search"></i> 搜索
                      </button>
                      <button type="button" style="margin-top: 5%;" class="btn btn-primary" @click="refresh">
                        <i class="fa fa-refresh"></i> 刷新
                      </button>
                        <button type="button" style="margin-top: 5%;" class="btn btn-success" @click="addDept">
                        <i class="fa fa-plus"></i> 新建
                      </button>

                      <!--<button v-if="role == 1" type="button" style="margin-top: 5%;" class="btn btn-success" @click="addDept">-->
                        <button type="button" style="margin-top: 5%;" class="btn btn-success" @click="excl(event)">
                        <i></i> 导出
                      </button>
                    </div>
                  </div>

                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div v-if="pojs.length === 0" style="color: #000000;margin-left: 3%;">
            未查找到相应任务
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12" style="margin-bottom: 35px;margin-top:-30px" v-for="poj in pojs" :key="poj.id" >
            <div class="main-box clearfix profile-box-contact " >
              <div class="main-box-body clearfix">
                <div class="profile-box-header gray-bg clearfix" style="height: 220px;padding:0 20px;">
                  <h2>任务名称：{{ poj.name }}</h2><br/>
                  <h5>任务类型：
                    <label style="color:greenyellow;">{{poj.sub_type_name}}</label>
                    <!--<label v-if="poj.type == 0" style="color:greenyellow;">年度任务</label>-->
                    <!--<label v-if="poj.type == 1" style="color:yellow;">月度任务</label>-->
                  </h5>
                  <label style="color:yellow;" v-if="poj.state ==='00'">待审核</label>
                  <label style="color:red;" v-if="poj.state ==='02'">审核不通过</label>
                  <ul class="contact-details">
                    <li>
                      <i class="fa fa-eye"></i> 日期： <span>1~{{curDate}}月份</span>
                    </li>
                    <!-- <li>
                      <i class="fa fa-eye-slash"></i> 结束时间：{{ poj.enddate }}
                    </li> -->
                    <li> <!-- v-if="" -->
                      <i class="fa fa-flag"></i> 执 行 人 ：{{ poj.userName }}
                    </li>
                  </ul>
                </div>
                <div class="profile-box-footer clearfix">
                  <div class="margin-10" style="margin-left:5%;">
                    <button type="button" class="btn btn-primary " @click="show(poj.id)">
                      <i class="fa fa-wrench"></i>
                      查看详情
                    </button>
                    <t v-if="role===1">
                      <button type="button" class="btn btn-default " @click="disabled(poj.id,1)" v-if="poj.dr===0">
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
                    </t>
                    <t v-else>
                      <button type="button" class="btn btn-default btn-warning" @click="modifiy(poj.id)" v-if="poj.state==='02' || poj.state==='00'">
                        <i class="fa fa-cog"></i>
                        编辑
                      </button>
                      <button type="button" class="btn btn-danger " @click="deletePoj(poj.id)" v-if="poj.state==='02' || poj.state==='00'">
                        <i class="fa fa-trash"></i>
                        删除
                      </button>
                    </t>
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
    name: "projectIndex",
    data() {
      return {
        msg:"",
        // userinfo: [],
        userId : '',
        name:'',
        deptId:'',
        deptUserId:'',
        // code:'',
        dr:'',
        state:'',
        depts:[],
        deptModel:[],
        pojs:[],
        poj:[],
        dept:[],
        deptUsers:[],
        role: 0,
        curDate:new Date().getMonth()
      }
    },
    
    mounted: function () {
      //查找是否有新增权限
      var roles = sessionStorage.getItem("user_role");
      if (roles.indexOf("01")!= -1){
        this.role = 1;
      }
      this.$nextTick(function () {
        this.init();
      });

    },
    methods: {
      init: function () {
        this.getDept();
        this.getPojs();
      },

      excl: function (event) {
        this.$axios({
          method: 'post',
          url: global.appCtx +'/score/putExcel',
          headers:{
            'Content-Type':'application/json'
          },
          responseType: 'blob'
        }).then(function (res) {
          const link = document.createElement('a')
          let blob = new Blob([res.data],{type: 'application/vnd.ms-excel'});
          link.style.display = 'none'
          link.href = URL.createObjectURL(blob);
          let num = '';
          for(var i=0;i < 10;i++){
            num += Math.ceil(Math.random() * 10)
          }
          link.setAttribute('download', '评分_' + num + '.xls')
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link)
        }).then(function (error) {
          console.log(error)
        })
      },

      getPojs: function () {
        const that = this;
        this.userId = sessionStorage.getItem("userid");
        console.log(this.userId);
        var data = {
          'pageIndex':0,
          'size':20,
          'searchMap':{
            'name': this.name,
            'deptid': this.deptId,
            // 'code': this.code,
            'state': this.state
          }
        };
        if (this.role !=1){
          data.searchMap.userid = this.userId;
        } else {
          data.searchMap.userid = this.deptUserId;
        }
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
        this.deptId='';
        this.state = '';
        this.deptUserId ='';
        this.getPojs();
      },
      modifiy: function (id) {
        this.$router.push({
          path: '/PojModifiy',
          query: { pojid: id }
        });
      },
      show: function(id){
        this.$router.push({
          path: '/ShowPoj',
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
    watch: {
      //选择部门后显示部门职员
      'deptId': function () {
        this.$http.get(global.appCtx + '/dept/findDeptUsers?id=' + this.deptId).then(function (response) {
          this.deptUsers = response.data;
        }, function (error) {
          console.log(error);
        });
      }
    }
  }

</script>

<style>

</style>
