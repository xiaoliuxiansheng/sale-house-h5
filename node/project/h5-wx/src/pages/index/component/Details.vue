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
          <span>中渝都会首站1号</span>
          <span class="position"><van-icon name="location-o" /></span>
        </div>
        <div class="two">
          <div><span class="left" style="color: darkcyan">200</span>m²<span class="rigth" ><span style="color: darkcyan">租金 63元</span>/m²*月</span></div>
          <div><span class="left">简装-家具</span><span class="rigth" ><span style="color: darkcyan">12600元</span>/月</span></div>
        </div>
        <div class="three">
          <div class="names">房源描述</div>
          <span v-for="item in 6">
            1、装修与布局：精装修交付，3个隔间，1个大通间，可自行设计装修
          </span>
        </div>
        <div class="four">
          <div class="names">大楼参数</div>
          <div class="box">
            <div class="img">
              <div class="left">
                <img src="@/assets/images/test.jpg"/>
              </div>
              <div class="right">
                <div class="top">
                  <img src="@/assets/images/test.jpg"/>
                </div>
                <div class="bottom">
                  <img src="@/assets/images/test.jpg"/>
                </div>
              </div>
            </div>
            <div class="txtss">
              <div class="perss" >
                <span class="left"><span style="color: #999">楼层：</span>29层</span>
                <span class="rigth"><span style="color: #999">物管费：</span>660个</span>
              </div>
              <div class="perss" >
                <span class="left"><span style="color: #999">楼层：</span>29层</span>
                <span class="rigth"><span style="color: #999">物管费：</span>660个</span>
              </div>
              <div class="perss" >
                <span class="left"><span style="color: #999">楼层：</span>29层</span>
                <span class="rigth"><span style="color: #999">物管费：</span>660个</span>
              </div>
              <div class="btn">查看大楼详情</div>
            </div>
          </div>
        </div>
        <div class="five">
          <div class="names">大楼参数</div>
          <div class="btns">
            <span v-for="(item,index) in 5" :class="{'checked':'index>1'}">地铁</span>
          </div>
        </div>
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
          title:this.$route.query.name
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
