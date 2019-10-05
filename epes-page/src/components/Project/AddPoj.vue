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
                       placeholder="科室名称" v-model="name">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务编码：</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" placeholder="部门编码" v-model="code">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">所属科室：</label>
              <div class="col-lg-10">
                <select class="form-control" id="deptSel" v-model="deptid">
                  <option v-for="dept in depts"  :value='dept.id' :key="dept.deptid">{{ dept.name }}</option>
                </select>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务状态：</label>
              <div class="col-lg-10">
                <select class="form-control" v-model="dr">
                  <option value=0 selected>启用状态</option>
                  <option value=1 >结束状态</option>
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
        depts:[],
        dept:[],
        name:"",
        code:"",
        dr: 0,
        deptid:"",
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
          path: '/Project'
        });
      },
      submit:function(){
        const that = this;
        const data = {
          "name": this.name,
          "code": this.code,
          "deptid": this.deptid,
          "dr" : this.dr
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
