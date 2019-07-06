
<template>
   <div class="head" v-bind:title="title" v-on:click="cancel">
     <span class="return">返回</span>
     <span class="title">{{title}}</span></div>
</template>
<style lang="less" >
   @import "../../css/common";

</style>

<script>
export default {
  data(){
   return{}
  },
  props:["title","leftoptionsshowBack"],
  methods:{
    cancel(){
      debugger
      this.$emit("back");
    }
  }

}
</script>
