<template>
  <div class="hello">
    <button @click="getAge">提交</button>
    <h1>{{ itmes }}</h1>
  </div>
</template>

<script>
  export default {
    name: 'HelloWorld',
    data() {
      return{
        itmes : [ ]
      }
    },
    methods : {
      getAge: function () {
        var that = this;
        that.$http({
          method:"get",
          url:'http://localhost:8080/findAllUsers'
        }).then(function (response) {
          this.itmes = response.data;
        },function (error) {
          console.log(error);
        })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
