<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="row">
        <div class="col-lg-12">
          <ol class="breadcrumb">
            <li><a href="/">首页</a></li>
            <li class="active"><span>绩效考评</span></li>
          </ol>
        </div>
      </div>
      <div class="col-lg-12 col-xs-12" v-if="pojs.length <= 0">
        <label class="col-lg-2 control-label" style="color: red;">未查询到该月的任务列表</label>
      </div>
      <div class="col-lg-12" v-for="(poj,index) in pojs" :key="poj">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>任务详情</h2>
          </header>
          <div class="main-box-body clearfix">
            <div class="form-group col-lg-12 col-xs-12">
              <label class="col-lg-2 control-label">任务名称：</label>
              <div class="col-lg-10">
                <h4 style="font-weight: bolder;">{{poj.name}}</h4>
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
              <label class="col-lg-2 control-label">日期：</label>
              <div class="col-lg-10">
                <label>1~{{lastMonth}}月份</label>
              </div>
            </div>
            <!-- <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">结束时间：</label>
              <div class="col-lg-10">
                <label>{{ poj.enddate }}</label>
              </div>
            </div> -->
            <div class="form-group col-lg-6 col-xs-12">
              <label
                class="col-lg-2 control-label">任务类型：</label>
              <div class="col-lg-10">
                <label class="radio-inline" v-if="poj.type == 0">
                  年度任务
                </label>
                <label class="radio-inline" v-if="poj.type == 1">
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
            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务描述：</label>
              <div class="col-lg-11">
                <textarea v-model="poj.content" class="form-control" rows="3" disabled></textarea>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务要求：</label>
              <div class="col-lg-11">
                <textarea v-model="poj.demand" class="form-control" rows="4" disabled></textarea>
              </div>
            </div>

            <div>
              <input type="text" name="pojId" :id="poj.id" v-model="pojid[index]=poj.id" hidden>
              <input type="text" name="pojName" v-model="pojname[index]=poj.name" hidden>
              <div class="form-group col-lg-12 col-xs-12">
                <label
                  class="col-lg-1 control-label">进展情况：</label>
                <div class="col-lg-11">
                  <textarea v-model="poj.progress" class="form-control" rows="4" disabled></textarea>
                </div>
              </div>

              <div class="form-group col-lg-12 col-xs-12">
                <label
                  class="col-lg-1 control-label">问题及原因分析：</label>
                <div class="col-lg-11">
                  <textarea v-model="poj.question" class="form-control" rows="4" disabled></textarea>
                </div>
              </div>
              <div class="form-group col-lg-12 col-xs-12">
                <!-- 分割线 -->
                <div class="fenge" style="width: 100%;height: 10px;border-bottom: #707070 solid 2px;"></div>
              </div>
              <!--任务评价-->
              <header class="main-box-header clearfix">
                <h2>任务评价</h2>
              </header>
              <input type="text" id="pojId" v-model="pojid[index]=poj.id" hidden>
              <div class="form-group col-lg-6 col-xs-12">
                <label
                  class="col-lg-2 control-label text-center">进度评价：</label>
                <div class="col-lg-10">
                  <div class="radio radio-inline" style="margin-top: 0;">
                    <input type="radio" :id="poj.id+'progressScore'+1" v-model="progressScore[index]" value="0"
                           :name="poj.id+'progressScore'"
                           checked >
                    <label :for="poj.id+'progressScore'+1"> 提前 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" :id="poj.id+'progressScore'+2" v-model="progressScore[index]" value="1"
                           :name="poj.id+'progressScore'">
                    <label :for="poj.id+'progressScore'+2"> 正常 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" :id="poj.id+'progressScore'+3" v-model="progressScore[index]" value="2"
                           :name="poj.id+'progressScore'">
                    <label :for="poj.id+'progressScore'+3"> 滞后 </label>
                  </div>
                </div>
              </div>

              <div class="form-group col-lg-6 col-xs-12">
                <label class="col-lg-2 control-label text-center">质量评价：</label>
                <div class="col-lg-10">
                  <div class="radio radio-inline" style="margin-top: 0;">
                    <input type="radio" :id="poj.id+'qualityScore'+1" v-model="qualityScore[index]" value="0"
                           :name="poj.id+'qualityScore'" checked>
                    <label :for="poj.id+'qualityScore'+1"> &nbsp;好&nbsp;&nbsp;&nbsp; </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" :id="poj.id+'qualityScore'+2" v-model="qualityScore[index]" value="1"
                           :name="poj.id+'qualityScore'">
                    <label :for="poj.id+'qualityScore'+2"> &nbsp;中&nbsp;&nbsp; </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" :id="poj.id+'qualityScore'+3" v-model="qualityScore[index]" value="2"
                           :name="poj.id+'qualityScore'">
                    <label :for="poj.id+'qualityScore'+3"> &nbsp;差 </label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>


      <!--综合评价-->

      <div class="col-lg-12 main-box"  v-if="pojs.length > 0">
        <header class=" main-box-header clearfix">
          <h2>综合评价</h2>
        </header>
        <div class="form-group col-lg-6 col-xs-12">
          <label
            class="col-lg-2 control-label text-center">专业素质：</label>
          <div class="col-lg-10">
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="professionScore1" value="0" v-model="professionScore" name="professionScore">
              <label for="professionScore1"> 优 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="professionScore2" value="1"  v-model="professionScore" name="professionScore">
              <label for="professionScore2"> 良 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="professionScore3" value="2"  v-model="professionScore" name="professionScore">
              <label for="professionScore3"> 中 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="professionScore4" value="3"  v-model="professionScore" name="professionScore">
              <label for="professionScore4"> 差 </label>
            </div>
          </div>
        </div>

        <div class="form-group col-lg-6 col-xs-12">
          <label
            class="col-lg-2 control-label text-center">敬业精神：</label>
          <div class="col-lg-10">
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="dedicationScore1" value="0" v-model="dedicationScore" name="dedicationScore">
              <label for="dedicationScore1"> 优 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="dedicationScore2" value="1" v-model="dedicationScore" name="dedicationScore">
              <label for="dedicationScore2"> 良 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="dedicationScore3" value="2" v-model="dedicationScore" name="dedicationScore">
              <label for="dedicationScore3"> 中 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="dedicationScore4" value="3" v-model="dedicationScore" name="dedicationScore">
              <label for="dedicationScore4"> 差 </label>
            </div>
          </div>
        </div>

        <div class="form-group col-lg-6 col-xs-12">
          <label
            class="col-lg-2 control-label text-center">主动担当：</label>
          <div class="col-lg-10">
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="dutyScore1" value="0" v-model="dutyScore" name="dutyScore">
              <label for="dutyScore1"> 优 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="dutyScore2" value="1" v-model="dutyScore" name="dutyScore">
              <label for="dutyScore2"> 良 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="dutyScore3" value="2" v-model="dutyScore" name="dutyScore">
              <label for="dutyScore3"> 中 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="dutyScore4" value="3" v-model="dutyScore" name="dutyScore">
              <label for="dutyScore4"> 差 </label>
            </div>
          </div>
        </div>

        <div class="form-group col-lg-6 col-xs-12">
          <label
            class="col-lg-2 control-label text-center">工作成效：</label>
          <div class="col-lg-10">
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="businessScore1" value="0" v-model="businessScore" name="businessScore">
              <label for="businessScore1"> 优 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="businessScore2" value="1" v-model="businessScore" name="businessScore">
              <label for="businessScore2"> 良 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="businessScore3" value="2" v-model="businessScore" name="businessScore">
              <label for="businessScore3"> 中 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="businessScore4" value="3" v-model="businessScore" name="businessScore">
              <label for="businessScore4"> 差 </label>
            </div>
          </div>
        </div>

        <div class="form-group col-lg-6 col-xs-12">
          <label
            class="col-lg-2 control-label text-center">创新能力：</label>
          <div class="col-lg-10">
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="innovationScore1" value="0" v-model="innovationScore" name="innovationScore">
              <label for="innovationScore1"> 优 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="innovationScore2" value="1" v-model="innovationScore" name="innovationScore">
              <label for="innovationScore2"> 良 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="innovationScore3" value="2" v-model="innovationScore" name="innovationScore">
              <label for="innovationScore3"> 中 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="innovationScore4" value="3" v-model="innovationScore" name="innovationScore">
              <label for="innovationScore4"> 差 </label>
            </div>
          </div>
        </div>

        <div class="form-group col-lg-6 col-xs-12">
          <label
            class="col-lg-2 control-label text-center">团结协作：</label>
          <div class="col-lg-10">
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="teamScore1" value="0" v-model="teamScore" name="teamScore">
              <label for="teamScore1"> 优 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="teamScore2" value="1" v-model="teamScore" name="teamScore">
              <label for="teamScore2"> 良 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="teamScore3" value="2" v-model="teamScore" name="teamScore">
              <label for="teamScore3"> 中 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="teamScore4" value="3" v-model="teamScore" name="teamScore">
              <label for="teamScore4"> 差 </label>
            </div>
          </div>
        </div>
      </div>


      
        <div  v-if="pojs.length > 0">
       
       
        <header class=" main-box-header clearfix" >
          <h2>综合评价定级</h2>
        </header>
        <div class="form-group col-lg-6 col-xs-12" >
          <label
            class="col-lg-2 control-label text-center"></label>
          <div class="col-lg-10" style="centre">
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="evaluate1" value="0" v-model="evaluate" name="evaluate">
              <label for="evaluate1"> 优 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="evaluate2" value="1"  v-model="evaluate" name="evaluate">
              <label for="evaluate2"> 良 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="evaluate3" value="2"  v-model="evaluate" name="evaluate">
              <label for="evaluate3"> 中 </label>
            </div>
            <div class="radio radio-inline" style="margin-top: 0;">
              <input type="radio" id="evaluate4" value="3"  v-model="evaluate" name="evaluate">
              <label for="evaluate4"> 差 </label>
            </div>
          </div>
        </div>
      

        <div class="col-lg-12 col-xs-12 text-center " style="padding: 10px;margin:40px" >
          <label class="col-lg-4 control-label" style="color: red;" v-if="limitDateState === 1">绩效评价时间为每月1号至{{limitDate}}号</label>
          <button type="button" class="btn btn-success " v-if="pojs.length > 0" @click="openMask()" :disabled="limitDateState === 1 || isScored ==='Y'" >提交</button>
          <dialog-bar v-model="sendVal" type="danger" title="确认提交吗 ? 确认后不能再提交了！"  v-on:cancel="clickCancel()" 
                v-on:danger="clickDanger"  dangerText="确定">
          </dialog-bar>
          <button type="button" class="btn btn-info" @click="goBack">返回</button>
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
  import dialogBar from './Dialog.vue'

  var len;
  var pojList;
  export default {
    components:{
        'dialog-bar': dialogBar,
    },
    name: "workLogIndex",
    data() {
      return {
        pojs: [],
        pojid: [],
        pojname: [],
        // progress: [],//进展情况
        // question: [],//问题及原因
        progressScore: [], //进度评分
        qualityScore: [],//质量评分
        userid: '',
        logUserid: '',
        logs: [],
        nowDate: '',
        limitDateState: 0, //是否在日期限制，0为可编辑日志，1为不可编辑日志
        limitDate: '',
        professionScore: '',//专业素质
        dedicationScore: '',//敬业精神
        dutyScore: '', //主动担当
        businessScore:'', //工作成交
        innovationScore:'',//创新能力
        teamScore:'',//团结协作
        evaluate:'',//综合评价定级
        scoreDate:'',
        lastMonth:new Date().getMonth(),
        perSc:[],
        respondents:[],//被考评的人
        sendVal: false,
      }
    },
    mounted: function () {
      
      this.getScore();
  
      this.userid = sessionStorage.getItem("userid");

      var date = new Date();
      var d = date.getDate();
      var m = date.getMonth();
      var y = date.getFullYear();
      if (m === 0 ){
        m = 12;
        y = y-1;
      }
      if (m<10){
        this.scoreDate = y+'-0'+m;
      } else {
        this.scoreDate = y+'-'+m;
      }

      //时间校验，判断是否到达日志填报日期
      this.limitDate = global.score_time;
      if (parseInt(this.limitDate) >= date.getDate()) {
        this.limitDateState = 0;
      } else {
        this.limitDateState = 1;
      }
      this.$nextTick(function () {
        this.init();
      });
    },
    
    watch: {
      'progress': function () {
        console.log(this.progress);
      }
    },
    methods: {
      init: function () {
        this.logUserid = this.$route.query.id;
        this.isScored = this.$route.query.isScore;
        this.getPoj();
        this.getScore();
      },

      openMask(index){
        for(var i=0 ; i<len;i++){
          
          if(this.progressScore[i]===null || this.progressScore[i] ===undefined||this.progressScore[i]===''){
              alert("第"+(i+1)+"个的任务进度评价没点选，请点选！");
              return;
          }
        }
        for(var i=0; i<len;i++){
          if(this.qualityScore[i]===""|| this.qualityScore[i]===null || this.qualityScore[i]===undefined){
            alert("第"+(i+1)+"个的任务质量评价没点选，请点选");
            return;
          }
        }
        if(this.professionScore ===""){
          
          alert("请评价专业素质！");
          return;
        }
        if(this.dedicationScore===""){
          alert("请评价敬业精神！");
          return ;
        }
        if(this.dutyScore ===""){
          alert("请评价主动担当！");
          return;
        }
        if(this.businessScore===""){
          alert("请评价工作成效！");
          return;
        }
        if(this.innovationScore===""){
          alert("请评价创新能力!");
          return;
        }
        if(this.teamScore===""){
          alert("请评价团结协作！");
          return;
        }
        if(this.evaluate ===""){
          alert("请综合评价定级！");
          return;
        }
        this.sendVal=true;
        
      },
      clickCancel(){
        console.log('点击了取消');
      },
      clickDanger:function(){
       this.submit();
      },
      getPoj: function () {
        const that = this;
        const data = {
          'userid': this.logUserid
        };
        // data.date = '2019-10';
        data.date = this.scoreDate;

        that.$http.post(global.appCtx + '/score/findPojToScore', data).then(function (response) {
          this.pojs = response.data;
          len = this.pojs.length;
          for (var i = 0; i < this.pojs.length; i++) {
            this.pojid[i] = this.pojs[i].id;
            
          }
          this.getScore();
          
        }, function (error) {
          console.log(error);
          alert("网络连接失败");
        });
      },

      
      getScore:function(){
        const data = {
          'userid': this.logUserid
          
        };
        data.date=this.scoreDate;
        data.user = this.userid;
        this.$http.post(global.appCtx + '/score/findScoreById', data).then(function(response){
          
          console.log("--------->pojs:"+this.pojs.length)
          if(response.data.length !==0 && this.pojs.length !==0){
            for(var i = 0;i<response.data.length;i++){
              for(var j=0;j<this.pojs.length;j++){
                if(this.pojs[j].id ===response.data[i].pojid){
                  this.progressScore[j]=response.data[i].progressScore;
                  this.qualityScore[j]=response.data[i].qualityScore;
                }
              }
              if(response.data[i].pojid===undefined || response.data[i].pojid===null||response.data[i].pojid===''){
                this.teamScore = response.data[i].teamScore;
                this.professionScore=response.data[i].professionScore;
                this.dedicationScore=response.data[i].dedicationScore;
                this.dutyScore=response.data[i].dutyScore;
                this.businessScore=response.data[i].businessScore;
                this.innovationScore=response.data[i].innovationScore;
                this.evaluate=response.data[i].evaluate;
              }
            }

          }
          
          
        },function (error) {
          console.log(error);
          alert("网络连接失败");
        });
      },

      goBack: function () {
        this.$router.push({
          path: '/Performance'
        });
      },
      submit: function () {
        const that = this;
        

        const data = {
          'pojids': this.pojid,
          'userid': this.userid,
          'logUserid' : this.logUserid,
          'pojnames': this.pojname,
          'progressScore': this.progressScore,
          'qualityScore': this.qualityScore,
          'professionScore' : this.professionScore,
          'dedicationScore' : this.dedicationScore,
          'dutyScore' : this.dutyScore,
          'businessScore' : this.businessScore,
          'innovationScore' : this.innovationScore,
          'teamScore' : this.teamScore,
          'evaluate' : this.evaluate,
          'logDate' : this.scoreDate
        };
        that.$http.post(global.appCtx + '/score/addScore', data).then(function (response) {
          if (response.data.state === "200") {
            alert("保存成功！");
          } else {
            console.log(response.data.error);
            alert("保存失败！");
          }
        }, function (error) {
          console.log(error);
          alert("提交失败！");
        });
      }
    },
    update: function () {
    }
  }
</script>
