<template>
    <div class="content" :style="{backgroundImage: 'url(' + bg2 + ')' }">
       <div class="login">欢迎登录oa系统</div>
      <van-cell-group>
        <van-field
          v-model="parms.username"
          required
          clearable
          label="用户名"
          right-icon="question-o"
          placeholder="请输入用户名"
          @click-right-icon="$toast('用户名为您的手机号码')"
          :error-message="nametext"
        />

        <van-field
          v-model="parms.password"
          type="password"
          label="密码"
          placeholder="请输入密码"
          required
          right-icon="question-o"
          :error-message="pastext"
          @click-right-icon="$toast('如忘记密码 请联系管理员')"
        />
      </van-cell-group>
      <div class="btn" @click="login"><van-button type="primary" size="large">登录</van-button></div>
    </div>
</template>
<script>
  import Bg2 from "@/assets/timg.jpg"
export default {
    data(){
      return{
        parms:{
          password:null,
          username:null,
        },
        bg2:Bg2,
        nametext:null,
        pastext:null
      }
    },
  methods:{
    login(){
      this.nametext=null,
        this.pastext=null
        if (this.parms.password&&this.parms.username){
          let formdata=new FormData()
          formdata.append("phone",this.parms.password)
          formdata.append("password",this.parms.username)
          this.$axios.post("/leaser/login",formdata).then((res)=>{
            console.log(res)
          })
        } else {
          if (this.parms.username==null){
            this.nametext="账号不能为空！"
          } else {
            this.pastext="密码不能为空！"
          }
        }
    }
  }
}
</script>
<style scoped lang="scss">
.content{
  position:absolute;
  left:0;
  top:0px;
  width:100%;
  height:100%;
  .login{
    margin-top: 4rem;
    text-align: center;
    margin-bottom: 2rem;
  }
  .btn{
    margin-top: 2rem;
  }
}
</style>
