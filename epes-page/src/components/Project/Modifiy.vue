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
                       placeholder="任务名称" v-model="poj.name" disabled>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">所属科室：</label>
              <div class="col-lg-10">
                <label>{{ poj.deptname }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">人员分配：</label>
              <div class="col-lg-10">
                <label>{{ poj.userName }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务类型：</label>
              <div class="col-lg-10">
                <label class="radio-inline" v-if="poj.type === '0'">
                  年度任务
                </label>
                <label class="radio-inline" v-if="poj.type === '1'">
                  月度任务
                </label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务种类：</label>
              <div class="col-lg-10">
                {{ poj.parent_name }}
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务子类：</label>
              <div class="col-lg-10">
                {{ poj.sub_type_name }}
              </div>
            </div>

            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务状态：</label>
              <div class="col-lg-4">
                <select class="form-control" v-model="poj.dr" disabled>
                  <option v-if="poj.dr==0" value=0 selected>启用状态</option>
                  <option v-if="poj.dr==1" value=1 selected>结束状态</option>
                </select>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">开始时间：</label>
              <div class="col-lg-10">
                <label>{{ poj.startdate }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">结束时间：</label>
              <div class="col-lg-10">
                <label>{{ poj.enddate }}</label>
              </div>
            </div>
            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务描述：</label>
              <div class="col-lg-11">
                <textarea v-model="poj.content" class="form-control" rows="3" ></textarea>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务要求：</label>
              <div class="col-lg-11">
                <textarea v-model="poj.demand" class="form-control" rows="4" ></textarea>
              </div>
            </div>
            <div class="form-group text-center">
              <button type="button" class="btn btn-success " @click="update">提交</button>
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
    name: "projectModifiy",
    data() {
      return {
        dept:[],
        deptModel:[],
        userInfo:[],
        poj:{}
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
      update: function () {
        this.poj.state = '00';
        this.$http.post(global.appCtx + '/projct/update',this.poj).then(function (response) {
          alert(response.data.msg);
          if (response.data.success === "success") {
            this.goBack();
          }
        },function (error) {
          console.log(error);
          alert("提交失败！");
        });
      }
    },
    update: function () {
    }
  }
</script>
