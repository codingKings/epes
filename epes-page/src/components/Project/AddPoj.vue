<template>
  <div id="content-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box">
          <header class="main-box-header clearfix">
            <h2>任务新增</h2>
          </header>
          <div class="main-box-body clearfix">

            <div class="form-group col-lg-6 col-xs-12" id="pojTypeBox">
              <label class="col-lg-2 control-label">任务分类：</label>
              <div class="col-lg-10" v-if="pojTypes.length > 5">
                <select class="form-control" id="pojType" v-model="pojTypeCode">
                  <option v-for="pojType in pojTypes" :value='pojType.code' :key="pojType.code">{{ pojType.name }}
                  </option>
                </select>
              </div>
              <div class="col-lg-10" v-else>
                <div class="radio" v-for="pojType in pojTypes">
                  <input type="radio" :id="pojType.id" v-model="pojTypeCode" :value='pojType.code' name="pojType">
                  <label :for="pojType.id"> {{ pojType.name }} </label>
                </div>
              </div>
            </div>

            <div class="form-group col-lg-6 col-xs-12" id="subTypeBox">
              <label class="col-lg-2 control-label">任务子类：</label>
              <div class="col-lg-10" v-if="subTypes.length > 5">
                <select class="form-control" id="subType" v-model="subTypeCode">
                  <option v-if="pojTypeCode === '' || pojTypeCode === null || pojTypeCode === undefined"
                          style="color: red;" checked>请先选择任务类型
                  </option>
                  <option
                    v-if="(pojTypeCode !== '' && pojTypeCode !== null && pojTypeCode !== undefined ) && subTypes.length <= 0"
                    style="color: red;" checked>该类型未配置子类，请联系管理员添加
                  </option>
                  <option v-for="subType in subTypes" :value='subType.code' :key="subType.code">{{ subType.name }}
                  </option>
                </select>
              </div>
              <div class="col-lg-10" v-else>
                <label v-if="pojTypeCode === '' || pojTypeCode === null || pojTypeCode === undefined"
                       style="color: red;">请先选择任务类型 </label>
                <label
                  v-if="(pojTypeCode !== '' && pojTypeCode !== null && pojTypeCode !== undefined ) && subTypes.length <= 0"
                  style="color: red;">该类型未配置子类，请联系管理员添加 </label>

                <div class="radio" v-for="subType in subTypes">
                  <!--@click="setPojName(subType.name)"-->
                  <input type="radio" :id="subType.id" v-model="subTypeCode" :value='subType.code' name="subType">
                  <label :for="subType.id"> {{ subType.name }} </label>
                </div>
              </div>
            </div>

            <div class="form-group col-lg-6 col-xs-12">
              <label for="inputEmail1" class="col-lg-2 control-label">任务名称：</label>
              <div class="col-lg-10">
                <input type="email" class="form-control" id="inputEmail1"
                       placeholder="任务名称" v-model="name">
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">所属科室：</label>
              <div class="col-lg-10" v-if="role==1">
                <select class="form-control" id="deptSel" v-model="deptid">
                  <option v-for="dept in depts" :value='dept.id' :key="dept.deptid">{{ dept.name }}</option>
                </select>
              </div>
              <div class="col-lg-10" v-else>
                <label>{{ deptname }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">执行人：</label>
              <div class="col-lg-10" v-if="role==1">
                <select class="form-control" id="user" v-model="bindUser">
                  <option v-if="deptid == '' || deptid == null || deptid == undefined" style="color: red;" checked>
                    请先选择部门
                  </option>
                  <option v-if="(deptid != '' && deptid != null && deptid != undefined ) && deptUsers.size <= 0"
                          style="color: red;" checked>该部门未配置人员
                  </option>
                  <option v-for="user in deptUsers" :value='user.id' :key="user.id">{{ user.name }}</option>
                </select>
              </div>
              <div class="col-lg-10" v-else>
                <label>{{ bindUserName }}</label>
              </div>
            </div>
            <div class="form-group col-lg-6 col-xs-12">
              <label class="col-lg-2 control-label">任务状态：</label>
              <div class="col-lg-4">
                <select class="form-control" v-model="dr">
                  <option value=0 selected>启用状态</option>
                  <option value=1>结束状态</option>
                </select>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">年度任务：</label>
              <div class="col-lg-11">
                <textarea v-model="content" class="form-control" placeholder="请填写任务描述..." rows="3"></textarea>
              </div>
            </div>

            <div class="form-group col-lg-12 col-xs-12">
              <label
                class="col-lg-1 control-label">任务要求：</label>
              <div class="col-lg-11">
                <textarea v-model="demand" class="form-control" placeholder="请填写任务要求..." rows="4"></textarea>
              </div>
            </div>
            <div class="form-group text-center col-lg-12 col-xs-12">
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
    name: "projectAdd",
    data() {
      return {
        depts: [],
        deptUsers: [],//选择部门后的部门职员
        name: "",
        code: "",
        type: "0",
        dr: 0,
        deptname: "",
        deptid: "",//所属科室
        content: "",//任务描述
        bindUser: "",//执行人
        bindUserName: "",//执行人名称
        demand: "",//任务要求
        role: 0, //权限
        pojTypes: [],//任务种类
        subTypes: [],//任务子种类
        pojTypeCode: '',//选择的一级任务种类编号
        subTypeCode: '',//选择的子任务种类编号
        subTypeName: ''
        // startDate : null,
        // endDate : null
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
      //查找是否有新增权限
      var roles = sessionStorage.getItem("user_role");
      if (roles.indexOf("01") !== -1) {
        this.role = 1;
      } else {
        this.bindUser = sessionStorage.getItem("userid");
        this.deptid = sessionStorage.getItem("user_dept");

        //获取科室名称
        this.$http.post(global.appCtx + '/dept/findDeptById?id=' + this.deptid).then(function (response) {
          this.deptname = response.data.name;
        }, function (error) {
          console.log(error);
        });

        //获取当前职工名称
        this.$http.post(global.appCtx + '/UserInfo/findUserById?id=' + this.bindUser).then(function (response) {
          this.bindUserName = response.data.name;
        }, function (error) {
          console.log(error);
        });
      }
    },
    watch: {
      //选择部门后显示部门职员
      'deptid': function () {
        this.$http.get(global.appCtx + '/dept/findDeptUsers?id=' + this.deptid).then(function (response) {
          this.deptUsers = response.data;
        }, function (error) {
          console.log(error);
        });
        //获取科室名称
        this.$http.post(global.appCtx + '/dept/findDeptById?id=' + this.deptid).then(function (response) {
          this.deptname = response.data.name;
        }, function (error) {
          console.log(error);
        });
      },
      'bindUser': function () {
        this.$http.post(global.appCtx + '/UserInfo/findUserById?id=' + this.bindUser).then(function (response) {
          this.bindUserName = response.data.name;
        }, function (error) {
          console.log(error);
        });
      },
      // 'type' :function () {
      //   if (this.type == '0') {
      //     document.getElementById("pojTypeBox").removeAttribute('hidden');
      //     document.getElementById("subTypeBox").removeAttribute('hidden');
      //   } else{
      //     document.getElementById("pojTypeBox").setAttribute('hidden','hidden');
      //     document.getElementById("subTypeBox").setAttribute('hidden','hidden');
      //
      //   }
      // },
      'pojTypeCode': function () {
        this.$http.get(global.appCtx + '/projct/findSubPojType?code=' + this.pojTypeCode).then(function (response) {
          this.subTypes = response.data;
        }, function (error) {
          console.log(error);
        });
      },
      'subTypeCode': function () {
        console.log(this.subTypeCode);
        for (var i = 0; i < this.subTypes.length; i++) {
          console.log(this.subTypes[i].code);
          if (this.subTypeCode === this.subTypes[i].code) {
            this.name = this.subTypes[i].name;
            this.subTypeName = this.subTypes[i].name;
          }
        }
      }
    },
    methods: {
      init: function () {
        this.getDept();
        this.getPojType();
      },
      getDept: function () {

        this.$http.get(global.appCtx + '/dept/findAllDept').then(function (response) {
          this.depts = response.data;
        }, function (error) {
          console.log(error);
        });
      },
      setPojName: function (name) {
        this.name = name;
      },
      goBack: function () {
        this.$router.push({
          path: '/Project'
        });
      },
      getPojType: function () {
        this.$http.get(global.appCtx + '/projct/findAllTopPojType').then(function (response) {
          this.pojTypes = response.data;
        }, function (error) {
          console.log(error);
        });
      },
      submit: function () {
        const that = this;
        if (this.subTypeCode === "") {
          alert("请选择任务种类");
          return;
        }
        if (this.name === "") {
          alert("请填写任务名称");
          return;
        }
        if (this.bindUser === "") {
          alert("请选择执行人");
          return;
        }
        if (this.content === "") {
          alert("请填写年度任务");
          return;
        }
        if (this.demand === "") {
          alert("请填写任务要求");
          return;
        }
        const data = {
          "name": this.name,
          "code": this.code,
          "deptid": this.deptid,
          "dr": this.dr,
          "type": this.type,
          "userId": this.bindUser,
          "content": this.content,
          "demand": this.demand,
          "userName": this.bindUserName,
          "deptname": this.deptname,
          "sub_type_code": this.subTypeCode,
          "sub_type_name": this.subTypeName
        };

        if (this.role === 1) {
          //管理员新增任务 不需要审核
          data.state = '01';
        } else {
          //普通用户新增任务，需要审核
          data.state = '00';
        }

        that.$http.post(global.appCtx + '/projct/addPoj', data).then(function (response) {
          alert(response.data.msg);
          if (response.data.success === "success") {
            this.goBack();
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

<style>

</style>
