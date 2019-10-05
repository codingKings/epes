<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>任务编辑</h2>
          </header>
          <div class="main-box-body clearfix">
              <div class="form-group col-lg-6 col-xs-12">
                <label for="inputEmail1" class="col-lg-2 control-label">任务名称：</label>
                <div class="col-lg-10">
                  <input type="email" class="form-control" id="inputEmail1"
                         placeholder="科室名称" v-model="poj.name">
                </div>
              </div>
              <div class="form-group col-lg-6 col-xs-12">
                <label
                       class="col-lg-2 control-label">任务编码：</label>
                <div class="col-lg-10">
                  <input type="text" class="form-control" placeholder="任务编码" v-model="poj.code" readonly>
                </div>
              </div>
              
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">所属科室：</label>
              <div class="col-lg-10">
                
                <select class="form-control" id="deptSel" v-model="poj.deptid">
                  <template v-for="dept in depts">
                    <option  v-if="dept.isSelected" selected :value='dept.id' :key="dept.dept">{{ dept.name }}</option>
                    <option  v-if="!dept.isSelected" :value='dept.id' :key="dept.id">{{ dept.name }}</option>
                  </template>
                </select>
              </div>
            </div>
              <div class="form-group col-lg-6 col-xs-12">
                <label
                  class="col-lg-2 control-label">任务状态：</label>
                <div class="col-lg-10">
                  <select class="form-control" v-model="poj.dr">
                    <option v-if="poj.dr == 0"  value=0 selected>启用状态</option>
                    <option v-else  value=0 >启用状态</option>
                    <option v-if="poj.dr == 1"  value=1 selected>结束状态</option>
                    <option v-else  value=1 >结束状态</option>
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
        depts:[],
        poj:[]
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.getPoj();
        this.getDept();
      },
      getPoj: function () {
        const that = this;
        const pojid= this.$route.query.pojid;
        const data = {
          'id': pojid
        };
        that.$http.post(global.appCtx + '/projct/findPojById',data).then(function (response) {
          this.poj = response.data;
        },function (error) {
          console.log(error);
        });
      },
      getDept:function () {
        const that = this;
        that.$http.get(global.appCtx + '/dept/findAllDept').then(function (response) {
          this.depts = response.data;
          for(var i=0;i<this.depts.length;i++){
            if (this.depts[i].id === this.poj.deptid){
              this.depts[i] = {id:this.depts[i].id,name:this.depts[i].name,isSelected:true};
            } else {
              this.depts[i] = {id:this.depts[i].id,name:this.depts[i].name,isSelected:false};
            }
          }
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
        that.$http.post(global.appCtx + '/projct/update',this.poj).then(function (response) {
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
