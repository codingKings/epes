<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>新增用户</h2>
          </header>
          <div class="main-box-body clearfix">
            <div class="form-group col-lg-6 col-xs-12">
              <label for="inputEmail1" class="col-lg-2 control-label">用户名：</label>
              <div class="col-lg-10">
                <input type="email" class="form-control" id="inputEmail1"
                       placeholder="用户名" v-model="name">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">登录名：</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" placeholder="登录名" v-model="loginName">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">部  门：</label>
              <div class="col-lg-10">
                <select class="form-control" id="deptSel" v-model="deptid">
                  <template v-for="dept in depts">
                  <option  v-if="dept.isSelected" selected :value='dept.id' :key="dept.id">{{ dept.name }}</option>
                  <option  v-if="!dept.isSelected" :value='dept.id' :key="dept.id">{{ dept.name }}</option>
                  </template>
                </select>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">工  号：</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" placeholder="工号" v-model="code" >
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">性  别：</label>
              <div class="col-lg-10">
                <select class="form-control" id="sexSel" v-model="sex">
                  <option value="男" selected> 男 </option>
                  <option value="女"> 女 </option>
                </select>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">年  龄：</label>
              <div class="col-lg-10">
                <input type="number" class="form-control" placeholder="年龄" v-model="age">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">电  话：</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" placeholder="电话" v-model="phone">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12" >
              <label
                class="col-lg-2 control-label">地  址：</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" placeholder="地址" v-model="address">
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
        name:'',
        sex:'',
        deptid:'',
        phone:'',
        address:'',
        code:'',
        age:'',
        loginName:'',
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
          path: '/UserManagement'
        });
      },
      submit:function(){
        const that = this;
        const data = {
          'name':this.name,
          'sex':this.sex,
          'deptid':this.deptid,
          'phone':this.phone,
          'address':this.address,
          'code':this.code,
          'age':this.age,
          'loginName':this.loginName,
        };
        that.$http.post(global.appCtx + '/UserInfo/addUser',data).then(function (response) {
          alert(response.data.msg);
          if (response.data.success === "success"){
            this.$router.push({
              path: '/UserManagement'
            });
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
