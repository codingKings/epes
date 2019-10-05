<template>
  <div class="login-page">

    <div class="login-form">

      <div class="login-content">

        <div class="form-login-error">
          <h3>Invalid login</h3>
          <p>Enter <strong>demo</strong>/<strong>demo</strong> as login and password.</p>
        </div>
        <form method="post" action="/login" role="form" id="loginForm">
          <div class="form-group">

            <div class="input-group">
              <div class="input-group-addon">
                <i class="fa fa-user"></i>
              </div>
              <input type="text" class="form-control" name="loginname" id="loginname" placeholder="请输入账号"
                     autocomplete="off" v-model="loginname"/>
            </div>
          </div>

          <div class="form-group">
            <div class="input-group">
              <div class="input-group-addon">
                <i class="fa fa-lock"></i>
              </div>

              <input type="password" class="form-control" name="password" id="password" placeholder="请输密码"
                     autocomplete="off" v-model="password"/>
            </div>

          </div>

          <div class="form-group">
            <button type="button" id="btnlogin" class="btn btn-primary btn-block btn-login" @click="btnFrom">
              <i class="fa fa-sign-in"></i>
              登陆
            </button>
          </div>

          <!-- Implemented in v1.1.4 -->
          <div class="form-group">
            <em>- or -</em>
          </div>

          <div class="form-group">

            <button type="button" class="btn btn-default btn-lg btn-block facebook-button" @click="sendParams">
              &#160;&#160;
              <i class="fa fa-facebook"></i>
              | Login with Facebook
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Test',
    data() {
      return {
        msg: "跳转成功",
        loginname: '',
        password: ''
      }
    },
    methods: {
      btnFrom() {
        alert("11111")
      },
      sendParams() {
        this.$router.push({
          path: '/hello',
          name: 'HelloWord',
          params: {
            name: 'name',
            dataObj: this.msg
          }
        })
      }
    }
  }
</script>

<style>
  @import "../../static/css/style.css";
</style>
