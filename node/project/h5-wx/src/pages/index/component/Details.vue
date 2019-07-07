<template>
  <div class="body">
    <div class="head">
      <span class="left"><van-icon name="arrow-left" /></span>{{title}}<span class="rigth"><van-icon name="home-o" /></span>
    </div>
    <div class="content" >
      <div class="swiper">
      <van-swipe :autoplay="3000" indicator-color="red">
        <van-swipe-item v-for="(item,index) in msg.houseimg">
          <img :src="item"/>
        </van-swipe-item>
      </van-swipe>
      </div>
      <div class="txt">
        <div class="title">
          <span>{{msg.subordinate}}</span>
          <span class="position"><van-icon name="location-o" /></span>
        </div>
        <div class="two">
          <div><span class="left" style="color: darkcyan">{{msg.area}}</span>m²<span class="rigth" ><span style="color: darkcyan">租金 {{msg.price}}元</span>/m²*月</span></div>
          <div><span class="left">{{msg.trimstyle}}</span><span class="rigth" ><span style="color: darkcyan">{{msg.rent}}元</span>/月</span></div>
        </div>
        <div class="three">
          <div class="names">房源描述</div>
          <span >
            {{msg.describe}}
          </span>
        </div>
        <div class="four">
          <div class="names">大楼参数</div>
          <div class="box">
            <div class="img">
              <div class="left">
                <img :src="msg.buildingimg[0]"/>
              </div>
              <div class="right">
                <div class="top" v-if="msg.buildingimg[1]">
                  <img :src="msg.buildingimg[1]"/>
                </div>
                <div class="bottom" v-if="msg.buildingimg[2]">>
                  <img :src="msg.buildingimg[2]"/>
                </div>
              </div>
            </div>
            <div class="txtss">
              <div class="perss" >
                <span class="left"><span style="color: #999">楼层：</span>{{msg.floors}}层</span>
                <span class="rigth"><span style="color: #999">物管费：</span>{{msg.managecost}}元/m²·月</span>
              </div>
              <div class="perss" >
                <span class="left"><span style="color: #999">层高：</span>{{msg.height}}米</span>
                <span class="rigth"><span style="color: #999">停车位：</span>{{msg.parkspace}}个</span>
              </div>
              <div class="perss" >
                <span class="left"><span style="color: #999">电梯：</span>{{msg.elevatorbrand}}</span>
                <span class="rigth"><span style="color: #999">标准层面积：</span>{{msg.standardarea}}m²</span>
              </div>
              <div class="btn" @click="pushlist()">查看大楼详情</div>
            </div>
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
    <div class="footer">
      <div class="pers left" >
        <img src="@/assets/images/test.jpg">
      </div>
      <div class="pers center">
        <div class="top">
          <span class="namess">陈方旭</span>
          <span class="spanss">招商经理</span>
        </div>
        <div class="phone">
          18875150394
        </div>
      </div>
      <div class="pers right">
        <van-icon name="phone-o" />
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
          id:this.$route.query.id,
          title:null,
          msg:{},
          buildid:null
        }
      },
    created() {
      this.getmsg()
    },
    methods:{
      getmsg(){
        this.$axios.get("/house/detail",{params:{
          id:this.$route.query.id
          }}).then((res)=>{
            if (res.data.code=="ok"){
              console.log(res)
              this.title=res.data.data.subordinate
              this.msg=res.data.data
              console.log(this.msg.houseimg)
                this.msg.houseimg=this.msg.houseimg.split(",")
              this.msg.buildingimg=this.msg.buildingimg.split(",")
            }
        })
      },
      pushlist(){
        this.$router.push({
          path:"/list",
          query:{
            buildid:this.msg.pk_building
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
    padding-bottom: 3.2rem;
  }
  .footer{
    position: fixed;
    height: 80/$sc+rem;
    bottom: 0;
    background: #eee;
    width: 100%;
    z-index: 99;
    display: flex;
    .pers{
      display: flex;
    }
    .left{
      flex: 1;
      vertical-align: middle;
      padding: 5/$sc+rem;
      img{
        width: 70px;
        height: 70px;
        border-radius: 50%;
      }
    }
    .center{
      flex: 2;
      padding-top: 8/$sc+rem;
      flex-direction: column;
      font-size: 18/$sc+rem;
      .div{
        display: block;
      }
      .spanss{
        display: inline-block;
        margin-left: 12/$sc+rem;
      }
      .phone{
        margin-top: 8/$sc+rem;
      }
    }
    .right{
      flex: 1;
      font-size: 2.8rem;
      color: #0c84d6;
      text-align: center;
      line-height: 90/$sc+rem;
    }
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
      padding: 10/$sc+rem 15/$sc+rem 0 15/$sc+rem;
      border-bottom: 2px solid #ddd;
      overflow: hidden;
      div{
        margin-bottom: 10/$sc+rem;
        font-size: 18/$sc+rem;
      }
      .left{
        float: left;
        display: inline-block;
      }
      .rigth{
        float: right;
        display: inline-block;
        margin-bottom: 10/$sc+rem;
      }
    }
    .three{
      padding-bottom: 15/$sc+rem;
      border-bottom: 2px solid #ddd;
      span{
        display: block;
        color: #666;
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
