<template>
   <Header v-bind:title="title" v-bind:leftoptionsshowBack="true" left-options.backtext=true   v-on:cancel="alert(1)"/>
</template>

<script>
import Header from "../components/common/header"

export default {
  components:{
    Header
  },
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      title:"主界面"
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header{
  background:#333;
  color:#fff;
  height:40px;
  line-height:40px;
  text-align:center
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
