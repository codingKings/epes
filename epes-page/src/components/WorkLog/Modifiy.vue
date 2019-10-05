<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>日志编辑</h2>
          </header>
          <div class="main-box-body clearfix">

            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">工作日期：</label>
              <div class="col-lg-10">
                <input type="date" class="form-control" placeholder="工作日期" v-model="log.workdate">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">任务：</label>
              <div class="col-lg-10">
                <select class="form-control" v-model="log.pojid">
                  <template v-for="poj in pojs">
                  <option  :value="poj.id" v-if="poj.isSelected" selected :key="poj.id">{{poj.name}}</option>
                  <option  :value="poj.id" v-if="!poj.isSelected" :key="poj.id" >{{poj.name}}</option>
                  </template>
                </select>
              </div>
            </div>
            <div class="form-group col-lg-12 col-xs-12">
              <label for="input" class="col-lg-2 control-label">工作内容：</label>
              <div class="col-lg-10">
                <textarea id="input" v-model="log.content" class="form-control" rows="6"></textarea>
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
        deptId:"",
        pojs:[],
        poj:[],
        log:[]
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
        this.getPojs();
        this.getLog();

      },
      getUserid:function (id) {
        this.userid = id;
      },
      getLog:function () {
        const that = this;
        const data = {
          'id': this.$route.query.logid
          };
        that.$http.post(global.appCtx + '/worklog/findLogByid',data).then(function (response) {
          this.log = response.data;
        },function (error) {
          console.log(error);
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
          for(var i=0;i<this.pojs.length;i++){
            if (this.pojs[i].id === this.log.pojid){
              this.pojs[i] = {id:this.pojs[i].id,name:this.pojs[i].name,isSelected:true};
            } else {
              this.pojs[i] = {id:this.pojs[i].id,name:this.pojs[i].name,isSelected:false};
            }
          }
        },function (error) {
          console.log(error);
        });
      },
      goBack:function () {
        this.$router.push({
          path: '/WorkLog'
        });
      },
      submit:function(){
        const that = this;
        that.$http.post(global.appCtx + '/worklog/update',this.log).then(function (response) {
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
