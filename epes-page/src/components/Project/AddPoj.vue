<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>任务新增</h2>
          </header>
          <div class="main-box-body clearfix">
            <div class="form-group col-lg-6 col-xs-12">
              <label for="inputEmail1" class="col-lg-2 control-label">任务名称：</label>
              <div class="col-lg-10">
                <input type="email" class="form-control" id="inputEmail1"
                       placeholder="任务名称" v-model="name">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">所属科室：</label>
              <div class="col-lg-10" v-if="role==1">
                <select class="form-control" id="deptSel" v-model="deptid">
                  <option v-for="dept in depts"  :value='dept.id' :key="dept.deptid">{{ dept.name }}</option>
                </select>
              </div>
              <div class="col-lg-10" v-else>
                <label>{{ deptname }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">人员分配：</label>
              <div class="col-lg-10" v-if="role==1">
                <select class="form-control" id="user" v-model="bindUser">
                  <option v-if="deptid == '' || deptid == null || deptid == undefined" style="color: red;" checked>请先选择部门</option>
                  <option v-if="(deptid != '' && deptid != null && deptid != undefined ) && deptUsers.size <= 0" style="color: red;" checked>该部门未配置人员</option>
                  <option v-for="user in deptUsers"  :value='user.id' :key="user.id">{{ user.name }}</option>
                </select>
              </div>
              <div class="col-lg-10" v-else>
                <label>{{ bindUserName }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务类型：</label>
              <div class="col-lg-10">
                <label class="radio-inline">
                  <input type="radio" name="task_type" v-model="type" id="optionsRadios3" value="0" checked/> 年度任务
                </label>
                <label class="radio-inline">
                  <input type="radio" name="task_type" v-model="type" id="optionsRadios4"  value="1"> 月度任务
                </label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务状态：</label>
              <div class="col-lg-4">
                <select class="form-control" v-model="dr">
                  <option value=0 selected>启用状态</option>
                  <option value=1 >结束状态</option>
                </select>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务描述：</label>
              <div class="col-lg-11">
                <textarea v-model="content" class="form-control"  placeholder="请填写任务描述..." rows="3"></textarea>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务要求：</label>
              <div class="col-lg-11">
                <textarea v-model="demand" class="form-control"  placeholder="请填写任务要求..." rows="4"></textarea>
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
  </div>
</template>

<script>

  import $ from "jquery"
  import global from "../Global"

  export default {
    name: "table1",
    data() {
      return {
        depts:[],
        deptUsers:[],//选择部门后的部门职员
        name:"",
        code:"",
        type:"",
        dr: 0,
        deptname:"",
        deptid:"",//所属科室
        content:"",//任务描述
        bindUser : "",//执行人
        bindUserName :"",//执行人名称
        demand : "",//任务要求
        role : 0
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
      //查找是否有新增权限
      var roles = sessionStorage.getItem("user_role");
      console.log(roles);
      if (roles.indexOf("01")!= -1){
        this.role = 1;
      } else {
        this.bindUser = sessionStorage.getItem("userid");
        this.deptid = sessionStorage.getItem("user_dept");

        //获取科室名称
        this.$http.post(global.appCtx + '/dept/findDeptById?id='+this.deptid).then(function (response) {
          this.deptname = response.data.name;
        },function (error) {
          console.log(error);
        });

        //获取当前职工名称
        this.$http.post(global.appCtx + '/UserInfo/findUserById?id='+this.bindUser).then(function (response) {
          this.bindUserName = response.data.name;
        },function (error) {
          console.log(error);
        });
      }
    },
    watch:{
      //选择部门后显示部门职员
      'deptid' : function () {
        this.$http.get(global.appCtx + '/dept/findDeptUsers?id='+this.deptid).then(function (response) {
          console.log(response.data);
          this.deptUsers = response.data;
          this.deptname = response.data[0].deptname;
        },function (error) {
          console.log(error);
        });
      },
      'bindUser' : function () {
        this.$http.post(global.appCtx + '/UserInfo/findUserById?id='+this.bindUser).then(function (response) {
          this.bindUserName = response.data.name;
        },function (error) {
          console.log(error);
        });
      }
    },
    methods: {
      init: function () {
        this.getDept();

      },
      getDept:function () {
        const that = this;
        that.$http.get(global.appCtx + '/dept/findAllDept').then(function (response) {
          this.depts = response.data;
        },function (error) {
          console.log(error);
        });
      },
      goBack:function () {
        this.$router.push({
          path: '/Project'
        });
      },
      submit:function(){
        const that = this;
        if (this.type === ""){
          alert("请选择任务类型");
          return;
        }
        if (this.name === ""){
          alert("请填写任务名称");
          return;
        }
        if (this.bindUser === ""){
          alert("请选择执行人");
          return;
        }
        if (this.content === ""){
          alert("请填写任务描述");
          return;
        }
        if (this.demand === ""){
          alert("请填写任务要求");
          return;
        }
        const data = {
          "name": this.name,
          "code": this.code,
          "deptid": this.deptid,
          "dr" : this.dr,
          "type" : this.type,
          "userId" : this.bindUser,
          "content" : this.content,
          "demand" : this.demand,
          "userName" : this.bindUserName,
          "deptname" : this.deptname
        };
        that.$http.post(global.appCtx + '/projct/addPoj',data).then(function (response) {
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
