<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>日志上传</h2>
          </header>
          <div class="main-box-body clearfix">

            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">工作日期：</label>
              <div class="col-lg-10">
                <input type="date" class="form-control" placeholder="工作日期" v-model="workdate">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">任务：</label>
              <div class="col-lg-10">
                <select class="form-control" v-model="pojid">
                  <option v-for="poj in pojs" :value="poj.id" :key="poj.pojid">{{poj.name}}</option>
                </select>
              </div>
            </div>
            <div class="form-group col-lg-12 col-xs-12">
              <label for="input" class="col-lg-2 control-label">工作内容：</label>
              <div class="col-lg-10">
                <textarea id="input" class="form-control" v-model="content" rows="6"></textarea>
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
        pojid:"",
        workdate:"",
        content:"",
        userid:"",
        pojs:[],
        poj:[],
        deptId:""
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();

      });
    },
    methods: {
      init: function () {
        this.deptId = sessionStorage.getItem("user_dept");
        this.userid = sessionStorage.getItem("userid");
        this.getPojs();
      },
      goBack:function () {
        this.$router.push({
          path: '/WorkLog'
        });
      },
      getPojs: function () {
        const that = this;
        const data = {
          'searchMap':{
            "deptid":this.deptId,
            'dr': 0
          }
        };
        that.$http.post(global.appCtx + '/projct/findPojByDept',data).then(function (response) {
          this.pojs = response.data;
        },function (error) {
          console.log(error);
        });
      },
      submit:function(){
        const that = this;
        const data = {
          'pojid':this.pojid,
          'workdate': this.workdate,
          'content':this.content,
          'userid':this.userid
        };
        that.$http.post(global.appCtx + '/worklog/addLog',data).then(function (response) {
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
