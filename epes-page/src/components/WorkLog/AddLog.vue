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
            <div class="form-group col-lg-12 col-xs-12">
              <!-- 分割线 -->
              <div class="fenge" style="width: 100%;height: 10px;border-bottom: #707070 solid 2px;"></div>
            </div>

            <div v-if="poj.type == 0">

                <div class="panel-group col-xs-12 col-lg-12" id="accordion">
                  <div class="panel form-group">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion"
                           href="#collapseOne">
                          2019年10月（第 1 月）
                        </a>
                      </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                      <div class="panel-body">
                        <header class="main-box-header clearfix">
                          <h2>日志填报</h2>
                        </header>
                        <div class="form-group col-lg-12 col-xs-12">
                          <label
                            class="col-lg-1 control-label">进展情况：</label>
                          <div class="col-lg-11">
                            <textarea v-model="progress" value="${progress}" class="form-control" rows="4"></textarea>
                          </div>
                        </div>

                        <div class="form-group col-lg-12 col-xs-12">
                          <label
                            class="col-lg-1 control-label">问题及原因分析：</label>
                          <div class="col-lg-11">
                            <textarea v-model="question" value="${question}" class="form-control" rows="4"></textarea>
                          </div>
                        </div>

                        <header class="main-box-header clearfix">
                          <h2>任务自评</h2>
                        </header>
                        <div class="form-group col-lg-6 col-xs-12">
                          <label
                            class="col-lg-2 control-label">进度评价：</label>
                          <div class="col-lg-10">
                            <label class="radio-inline col-lg-4">
                              <input type="radio" name="progressScore" v-model="progressScore" value="0"> 提前
                            </label>
                            <label class="radio-inline col-lg-4">
                              <input type="radio" name="progressScore" v-model="progressScore" value="1"> 正常
                            </label>
                            <label class="radio-inline col-lg-4">
                              <input type="radio" name="progressScore" v-model="progressScore" value="2"> 滞后
                            </label>
                          </div>
                        </div>

                        <div class="form-group col-lg-6 col-xs-12">
                          <label
                            class="col-lg-2 control-label">质量评价：</label>
                          <div class="col-lg-10">
                            <label class="radio-inline col-lg-4">
                              <input type="radio" name="qualityScore" v-model="qualityScore" value="0"> 好
                            </label>
                            <label class="radio-inline col-lg-4">
                              <input type="radio" name="qualityScore" v-model="qualityScore" value="1"> 中
                            </label>
                            <label class="radio-inline col-lg-4">
                              <input type="radio" name="qualityScore" v-model="qualityScore" value="2"> 差
                            </label>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="panel">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion"
                           href="#collapseTwo">
                          2019年11月（第 2 月）
                        </a>
                      </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                      <div class="panel-body">
                        Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                        cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                        vice lomo.
                      </div>
                    </div>
                  </div>
                  <div class="panel">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion"
                           href="#collapseThree">
                          2019年12月（第 3 月）
                        </a>
                      </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                      <div class="panel-body">
                        Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                        cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                        vice lomo.
                      </div>
                    </div>
                  </div>
                </div>
              <!--</div>-->
              </div>

            <div v-else>
              <header class="main-box-header clearfix">
                <h2>日志填报</h2>
              </header>
              <div class="form-group col-lg-12 col-xs-12">
                <label
                  class="col-lg-1 control-label">进展情况：</label>
                <div class="col-lg-11">
                  <textarea v-model="progress" class="form-control" rows="4"></textarea>
                </div>
              </div>

              <div class="form-group col-lg-12 col-xs-12">
                <label
                  class="col-lg-1 control-label">问题及原因分析：</label>
                <div class="col-lg-11">
                  <textarea v-model="question" class="form-control" rows="4"></textarea>
                </div>
              </div>

              <header class="main-box-header clearfix">
                <h2>任务自评</h2>
              </header>
              <div class="form-group col-lg-6 col-xs-12">
                <label
                  class="col-lg-2 control-label">进度评价：</label>
                <div class="col-lg-10">
                  <div class="radio radio-inline">
                    <input type="radio" id="optionsRadios1" v-model="progressScore" value="0" name="progressScore" checked>
                    <label for="optionsRadios1"> 提前 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" id="optionsRadios2" v-model="progressScore" value="1" name="progressScore">
                    <label for="optionsRadios2"> 正常 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" id="optionsRadios3" v-model="progressScore" value="2" name="progressScore">
                    <label for="optionsRadios3"> 滞后 </label>
                  </div>
                </div>
              </div>

              <div class="form-group col-lg-6 col-xs-12">
                <label class="col-lg-2 control-label">质量评价：</label>
                <div class="col-lg-10">
                  <div class="radio radio-inline">
                    <input type="radio" id="inlineRadio1" v-model="qualityScore" value="0" name="radioInline" checked>
                    <label for="inlineRadio1"> 好 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" id="inlineRadio2" v-model="qualityScore" value="1" name="radioInline">
                    <label for="inlineRadio2"> 中 </label>
                  </div>
                  <div class="radio radio-inline">
                    <input type="radio" id="inlineRadio3" v-model="qualityScore" value="2" name="radioInline">
                    <label for="inlineRadio3"> 差 </label>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group col-lg-12 col-xs-12 text-center">
              <button type="button" class="btn btn-success " @click="submit">保存</button>
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
        dept: [],
        deptModel: [],
        userInfo: [],
        poj: {},
        pojid: "",
        progress: "",//进展情况
        question: "",//问题及原因
        progressScore: "", //进度评分
        qualityScore: "",//质量评分
        userid: '',
        logs: [],
        nowDate: ''
      }
    },
    mounted: function () {
      this.getLog();
      this.userid = sessionStorage.getItem("userid");
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.getPoj();
      },
      getPoj: function () {
        const that = this;
        this.pojid = this.$route.query.pojid;
        const data = {
          'id': this.pojid
        };
        that.$http.post(global.appCtx + '/projct/d', data).then(function (response) {
          this.poj = response.data;
        }, function (error) {
          console.log(error);
        });
      },
      getLog: function () {
        const data = {
          'pojid': this.pojid,
          'userid': this.userid
        };
        that.$http.post(global.appCtx + '/worklog/findLogByPojOfUser', data).then(function (response) {
          this.logs = response.data;
        }, function (error) {
          console.log(error);
        });
      },
      goBack: function () {
        this.$router.push({
          path: '/WorkLog'
        });
      },
      submit: function () {
        const that = this;
        //校验
        const data = {
          'pojid': this.poj.id,
          'userid': this.userid,
          'pojname': this.poj.name,
          'progress': this.progress,
          'question': this.question,
          'progressScore': this.progressScore,
          'qualityScore': this.qualityScore

        };
        that.$http.post(global.appCtx + '/worklog/addLog', data).then(function (response) {
          if (response.data.success === "success") {
            alert("保存成功！");
          }
        }, function (error) {
          console.log(error);
        });
      }
    },
    update: function () {
    }
  }
</script>
