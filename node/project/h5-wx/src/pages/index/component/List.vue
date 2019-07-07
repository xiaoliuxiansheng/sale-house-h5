<template>
  <div class="body">
    <div class="head">
      <span class="left"><van-icon name="arrow-left" /></span>{{title}}<span class="rigth"><van-icon name="home-o" /></span>
    </div>
    <div class="content" >
      <div class="swiper">
        <van-swipe :autoplay="3000" indicator-color="red">
          <van-swipe-item v-for="item in 5">
            <img src="@/assets/images/test.jpg"/>
          </van-swipe-item>
        </van-swipe>
      </div>
      <div class="txt">
        <div class="title">
          <span>中渝都会首站1号<br>渝北区-加州新牌坊</span>
          <span class="position" style="margin-top: -11px;"><van-icon name="location-o" /></span>
        </div>
        <div class="two">
          <div v-for="(item,index) in 16" class="per"  v-if="index<flag">
            <div class="left">
              <img src="@/assets/images/test.jpg">
            </div>
            <div class="rigth">
              <div class="top">
                <span class="frist">
                  <span style="color: #43b1c0">200</span>m²
                </span>
                <span class="second">
                 <span style="color: #43b1c0">63</span>元/m²·月
                </span>
              </div>
              <div class="bottom">
                简装+家具
              </div>
            </div>
          </div>
          <div class="nums" v-if="flag=='2'" @click="flag='1000'">全部房源16套</div>
        </div>
        <div class="three">
          <div class="names">楼盘参数</div>
          <div class="pers">
            <span class="name"><span style="color: #999;">楼层：</span>：29层</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">物管费：</span>：4元/m²·月</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">层高：</span>：3.3米</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">停车位：</span>：660个</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">电梯：</span>：格里</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">标准层面积：</span>：830m²</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">空调：</span>：分体空调</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">地址：</span>：渝北区某某正</span>
          </div>
          <div class="pers">
          <span class="name"><span style="color: #999;">开发商：</span>：重庆某某地产公司</span>
        </div>
          <div class="pers">
            <span class="name"><span  style="color: #999;">武官公司：</span>：重庆某某地产公司</span>
          </div>
        </div>
        <div class="four">
          <div class="names">大楼介绍</div>
          <div class="txt">
            有道翻译提供即时免费的中文、英语、日语、韩语、法语、德语、俄语、西班牙语、葡萄牙语、越南语、印尼语、意大利语全文翻译、网页翻译、文档翻译服务。
          </div>
        </div>
<!--        <div class="five">-->
<!--          <div class="names">大楼参数</div>-->
<!--          <div class="btns">-->
<!--            <span v-for="(item,index) in 5" :class="{'checked':'index>1'}">地铁</span>-->
<!--          </div>-->
<!--        </div>-->
      </div>
    </div>
  </div>
</template>
<script>
  import XImg from "vux/src/components/x-img/index";
  export default {
    components: {XImg},
    data(){
      return{
        title:this.$route.query.name,
        flag:2,
      }
    },
    created() {
      // this.getmsg()
    },
    methods:{
      getmsg(){
        let formdata;
        formdata=new FormData()
        for (let k in this.parms){
          if(this.parms[k]){
            formdata.append(k,this.parms[k])
          }
        }
        this.$axios.post("/house/list",formdata).then((res) => {
          if (res.data.code=="error"){
            this.$message({
              message:res.data.message,
              type:"warning"
            })
          } else {
            console.log(res)
            this.users = res.data.data.rows
            this.users.forEach((item,index)=>{
              this.users[index].houseimg=item.houseimg.split(",")
            })
            console.log(this.users)
          }
        })
      },
      pushlist(){
        this.$router.push({
          path:"/list",
          query:{
            name:"中渝都会首战1号"
          }
        })
      }
    }

  }
</script>
<style  scoped lang="scss">
  $sc:25;
  .body{
    padding: 0;
    margin: 0;
    overflow: hidden;
  }
  .head{
    width: 100%;
    height: 50/$sc+rem;
    background: #43b1c0;
    color: #fff;
    position: fixed;
    top: 0px;
    z-index: 99;
    line-height: 50/$sc+rem;
    text-align: center;
    font-size: 18/$sc+rem;
    .left{
      display: inline-block;
      font-size: 22/$sc+rem;
      margin-left: 10/$sc+rem;
      float: left;
    }
    .rigth{
      display: inline-block;
      font-size: 22/$sc+rem;
      margin-right: 10/$sc+rem;
      float: right;
    }
  }
  .content{
    margin-top: 50/$sc+rem;
    /deep/ .van-swipe{
      height: 200/$sc+rem;
    }
    .swiper{
      img{
        width: 100%;
        height: 200/$sc+rem;
      }
    }
    .title{
      margin: 0 15/$sc+rem 0 15/$sc+rem;
      border-bottom: 1px solid #ddd;
      padding: 10/$sc+rem 0/$sc+rem;
      font-size: 18/$sc+rem;
      color: #333;
      .position{
        display: inline-block;
        float: right;
        font-size: 20/$sc+rem;
        cursor: pointer;
      }
    }
    .two{
      border-bottom: 2px solid #ddd;
      padding-bottom: 10px;
      overflow: hidden;
      .per{
        margin:10/$sc+rem 15/$sc+rem;
        border-bottom: 1px solid #ddd;
        font-size: 14/$sc+rem;
        color: #333;
        display: flex;
        .left .right{
          display: inline-block;
          float: left;
        }
        .left{
          flex: 2;
          img{
            width: 100%;
            height: 3.8rem;
            border-radius: 4/$sc+rem;
          }
        }
        .rigth{
          flex: 3;
          font-size: 18/$sc+rem;
          padding-left: 10/$sc+rem;
          .top{
            line-height: 30/$sc+rem;
            .second{
              margin-left: 20/$sc+rem;
            }
          }
          .bottom{
            margin-top: 10/$sc+rem;
          }
        }
      }
      .nums{
        padding: 5/$sc+rem 10/$sc+rem;
        margin: auto;
        margin-top: 10/$sc+rem;
        width: 180/$sc+rem;
        text-align: center;
        border: 1px solid #ddd;
        border-radius: 4px/$sc+rem;
        font-size: 14/$sc+rem;
      }
    }
    .three{
      padding-bottom: 15/$sc+rem;
      border-bottom: 2px solid #ddd;
      span{
        color: #333;;
        font-size: 14/$sc+rem;
        padding: 0px 15/$sc+rem;
        line-height: 25/$sc+rem;
      }
      .names{
        font-size: 16/$sc+rem;
        color: #333;
        padding: 15/$sc+rem;
      }
    }
    .four{
      border-bottom: 2px solid #ddd;
      overflow: hidden;
      .txt{
        padding: 0 15/$sc+rem;
        display: inline-block;
        font-size: 14/$sc+rem;
        color: #333;
      }
      .names{
        font-size: 16/$sc+rem;
        color: #333;
        padding: 15/$sc+rem;
      }
      .box{
        padding:0/$sc+rem 10/$sc+rem;
        .img{
          display: flex;
          height: 120/$sc+rem;
          .left .rigth{
            display: flex;
          }
          .left{
            display: flex;
            flex: 2;
            padding-right: 15/$sc+rem;
            img{
              width: 100%;
              height: 100%;
            }
          }
          .right{
            flex: 1;
            img{
              width: 100%;
            }
            div{
              width: 100/$sc+rem;
            }
          }
        }
        .txtss{
          padding:10/$sc+rem 5/$sc+rem;
          overflow: hidden;
          .perss{
            font-size: 14/$sc+rem;
            color: #333;
            margin-bottom: 5/$sc+rem;
            .rigth{
              display: inline-block;
              margin-left: 100/$sc+rem;
            }
          }
          .btn{
            width: 120/$sc+rem;
            margin: auto;
            border-radius: 4/$sc+rem;
            color: #44b1c1;
            padding: 5/$sc+rem 10/$sc+rem;
            border: 1px solid #ddd;
            font-size: 14/$sc+rem;
            line-height: 1.42857143;
            text-align: center;
            margin-top:20/$sc+rem ;
          }
        }

      }
    }
    .five{
      padding: 0 15/$sc+rem;
      .names{
        font-size: 16/$sc+rem;
        color: #333;
        padding: 15/$sc+rem 0;
      }
      .btns{
        margin-top:5/$sc+rem;
        span{
          display: inline-block;
          height: 28/$sc+rem;
          padding: 4/$sc+rem 10/$sc+rem;
          font-size: 14/$sc+rem;
          background: #fff;
          border: 1px solid #ddd;
          margin-right: 5/$sc+rem;
          font-weight: 400;
          line-height: 28/$sc+rem;
          text-align: center;
          white-space: nowrap;
          vertical-align: middle;
          cursor: pointer;
          color: #337ab7;
        }
        .checked{
          color: #fff !important;
          background: #44b1c1;
        }
      }
    }
  }
</style>
