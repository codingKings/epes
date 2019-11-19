<template>
  <div class="table1" id="content-wrapper">
    <input type="button" class="button" value="提交" id="get" @click="getAge"/>

    <ul>
      <li v-for="itme in itmes" :key="itme.name">{{ itme.name }}</li>
    </ul>
  </div>
</template>

<script>

  import $ from "jquery"

  export default {
    name: "table1",
    data() {
      return {
        itmes: {}
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.init();
      });
    },
    methods: {
      init: function () {
        this.getAge();
      },
      getAge: function () {
        const that = this;
        that.$http.post('http://localhost:8080/findUserBypage',{'pageIndex':0,'size':20}).then(function (response) {
          this.itmes = response.data;
        },function (error) {
          console.log(error);
        });
      }
    }
  }
</script>

<style>

</style>
