<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>科室新增</h2>
          </header>
          <div class="main-box-body clearfix">
            <div class="form-group col-lg-6 col-xs-12">
              <label for="inputEmail1" class="col-lg-2 control-label">科室名称：</label>
              <div class="col-lg-10">
                <input type="email" class="form-control" id="inputEmail1"
                       placeholder="科室名称" v-model="deptname">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">科室编码：</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" placeholder="科室编码" v-model="deptcode">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">科长：</label>
              <div class="col-lg-10" >
                <select class="form-control" v-model="userid" >
                  <option :value="user.id" v-for="user in userInfo" :key="user.id">{{ user.name }}</option>
                </select>
                <!-- <input class="form-control" list="userlist" type="text" v-model="dept.userName">
                 <div style="overflow-y:auto;height:10px">
                   <datalist id="userlist">
                     <option :label="user.code" :value="user.name"  :title="user.id"  v-for="user in userInfo"></option>
                   </datalist>-->
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">科室状态：</label>
              <div class="col-lg-10">
                <select class="form-control" v-model="deptdr">
                  <option v-if="dept.dr == 0"  value=0 selected>启用状态</option>
                  <option v-else  value=0 >启用状态</option>
                  <option v-if="dept.dr == 1"  value=1 selected>禁用状态</option>
                  <option v-else  value=1 >禁用状态</option>
                </select>
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
        dept:[],
        deptModel:[],
        userInfo:[],
        deptname:"",
        userid:"",
        deptdr: 0,
        deptcode:""
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.getUser();

      },
      getUser:function () {
        const that = this;
        that.$http.get(global.appCtx + '/UserInfo/findAllUser').then(function (response) {
          this.userInfo = response.data;
        },function (error) {
          console.log(error);
        });
      },
      goBack:function () {
        this.$router.push({
          path: '/Dept'
        });
      },
      submit:function(){
        const that = this;
        const data = {
          "name": this.deptname,
          "code": this.deptcode,
          "userid": this.userid,
          "dr" : this.deptdr
        };
        that.$http.post(global.appCtx + '/dept/addDept',data).then(function (response) {
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
