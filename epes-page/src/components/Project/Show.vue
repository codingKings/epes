<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>任务详情</h2>
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
            <!-- <div class="form-group col-lg-6 col-xs-12">
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
            </div> -->
            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">年度任务：</label>
              <div class="col-lg-11">
                <textarea v-model="poj.content" class="form-control" rows="3" disabled></textarea>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">目标要求：</label>
              <div class="col-lg-11">
                <textarea v-model="poj.demand" class="form-control" rows="4" disabled></textarea>
              </div>
            </div>
            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务进度：</label>
              <div class="col-lg-12" style="margin-left:8px;width:98%;padding:0px; border:1px solid white">
                <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;height:15px;border:1px solid black">

                  <div id="progress0" ></div>
                  <div id="progress1" ></div>
                  <div id="progress2" ></div>
                  <div id="progress3" ></div>
                  <div id="progress4" ></div>
                  <div id="progress5" ></div>
                  <div id="progress6" ></div>
                  <div id="progress7" ></div>
                  <div id="progress8" ></div>
                  <div id="progress9" ></div>
                  <div id="progress10" ></div>
                  <div id="progress11" ></div>
                

                </div>
              </div>
            </div>
            <div class="form-group text-center">
              <t v-if="role===1 && poj.state==='00'">
                <button type="button" class="btn btn-success " @click="approval('01')">审核通过</button>
                <!-- <button type="button" class="btn btn-warning " @click="approval('02')">审核不通过</button> -->
                <div  class="btn btn-warning " @click="openMask()">审核不通过</div>
                <dialog-bar v-model="sendVal" type="danger" title="审批意见"  v-on:cancel="clickCancel()" 
                v-on:danger="clickDanger"  dangerText="确定">
                </dialog-bar>
              </t>

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
  import dialogBar from './Dialog.vue'

  export default {
    components:{
        'dialog-bar': dialogBar,
    },
    name: "projectShow",
    data() {
      return {
        dept:[],
        deptModel:[],
        userInfo:[],
        poj:[],
        role:0,
        sendVal: false,
        startDate:'',
        endDate:'',
        progressScore:[],
      }
    },
    mounted: function () {
      //this.getSelfProgress();
      this.$nextTick(function () {
        this.init();
      });

      //查找是否有新增权限
      var roles = sessionStorage.getItem("user_role");
      if (roles.indexOf("01")!== -1){
        this.role = 1;
      }
      this.startDate = global.startDate;
      this.endDate = global.endDate;
    },
    methods: {
      init: function () {
        this.getPoj();
        this.getDept();
        this.getSelfProgress();
      },
      getSelfProgress(){
        var userid = sessionStorage.getItem("userid");
        
        const data={
          pojid:this.$route.query.pojid,
          userid:userid,
          startDate:this.startDate,
          endDate:this.endDate
        };
        
        this.$http.post(global.appCtx+'/score/getSelfProgress',data).then(function(response){
          for(var i=0;i<12;i++){
            var obj = document.getElementById("progress"+i);
            
            if(response.data[i].progressScore==='0'){
              
              obj.style.cssText="float:left;width:8.3333%;height:100%;background-color:blue";
            }
            if(response.data[i].progressScore==='1'){
              
              obj.style.cssText="float:left;width:8.3333%;height:100%;background-color:yellow";
            }
            if(response.data[i].progressScore==='2'){
              obj.style.cssText="float:left;width:8.3333%;height:100%;background-color:red";
            }
            if(response.data[i].progressScore===undefined ||response.data[i]===null||response.data[i]===''){
              obj.style.cssText="float:left;width:8.3333%;height:100%;background-color:white";
            }
           
          };
          // alert("this.progressScore[0]:"+typeof this.progressScore[0])
          // alert("this.progressScore[1]:"+typeof this.progressScore[1])
          console.log("---------->prog:"+this.progressScore)
          // console.log("------>prog[0]"+this.progressScore[0])
          // console.log("------------->prog[1]"+this.progressScore[1])
          // console.log("---------->prog[2]"+this.progressScore[2]);
        },function(error){
          alert('查询失败！')
        });
      },
      openMask(index){
      
            this.sendVal = true;
        },
      clickCancel(){
        console.log('点击了取消');
      },
     
      clickDanger:function(option){
        
        console.log("--------"+option)
        if(option === undefined || option===null || option ===''){
          alert("请填写审批意见！")
          return;
        }
        var data = {
          'pojid' : this.poj.id,
          'option':option,
          'state' : '02'
        };
        
        this.$http.post(global.appCtx + '/projct/updatePojText',data).then(function (response) {
          console.log(response.status);
          //alert(response.data.msg);
          if (response.status=== 200) {
            alert("发送成功！")
            this.goBack();
          }
        },function (error) {
          console.log(error);
          alert("提交失败！网络通信中断了！");
        });
      },
      clickConfirm(){
            console.log('点击了confirm');
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
      approval :function (state) {
        var data = {
          'pojid' : this.poj.id,
          'state' : state
        };
        this.$http.post(global.appCtx + '/projct/updatePojState',data).then(function (response) {
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
    update: function () {}
  }

</script>
<style lang="less" scoped>

</style>
