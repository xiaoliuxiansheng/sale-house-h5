<template>
  <div class="body">
    <div class="head">
      <span class="left" @click="$router.go(-1)"><van-icon name="arrow-left" />
      </span>{{title}}
      <span class="rigth">
      <van-icon name="home-o" />
      </span>
    </div>
    <div class="content"  v-if="users!==null">
      <div class="swiper">
        <van-swipe :autoplay="3000" indicator-color="red">
          <van-swipe-item v-for="item in users.buildimg">
            <img :src="item"/>
          </van-swipe-item>
        </van-swipe>
      </div>
      <div class="txt" v-if="users!==null">
        <div class="title">
          <span>{{users.buildname}}<br>{{users.address}}</span>
          <span class="position" style="margin-top: -11px;"><van-icon name="location-o" /></span>
        </div>
        <div class="two">
          <div v-for="(item,index) in users.housesInfo" class="per"  v-if="index<flag" @click="checkitem(item)">
            <div class="left">
              <img :src="item.houseimg[0]">
            </div>
            <div class="rigth">
              <div class="top">
                <span class="frist">
                  <span style="color: #43b1c0">{{item.area}}</span>m²
                </span>
                <span class="second">
                 <span style="color: #43b1c0">{{item.price}}</span>元/m²·月
                </span>
              </div>
              <div class="bottom">
                {{item.trimstyle}}
              </div>
            </div>
          </div>
          <div class="nums" v-if="flag=='2'" @click="flag='1000'">全部房源{{users.housesInfo.length}}套</div>
        </div>
        <div class="three">
          <div class="names">楼盘参数</div>
          <div class="pers">
            <span class="name"><span style="color: #999;">楼层：</span>：{{users.floorcount}}层</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">物管费：</span>：{{users.cost}}元/m²·月</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">层高：</span>：{{users.buildheight}}米</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">停车位：</span>：{{users.park}}个</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">电梯：</span>：{{users.ebrand}}</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">标准层面积：</span>：{{users.buildarea}}m²</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">空调：</span>：{{users.airconditioning}}</span>
          </div>
          <div class="pers">
            <span class="name"><span style="color: #999;">地址：</span>：{{users.daddress}}</span>
          </div>
          <div class="pers">
          <span class="name"><span style="color: #999;">开发商：</span>：{{users.developer}}</span>
        </div>
          <div class="pers">
            <span class="name"><span  style="color: #999;">物管公司：</span>：{{users.company}}</span>
          </div>
        </div>
        <div class="four">
          <div class="names">大楼介绍</div>
          <div class="txt">
            {{users.introduce}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import XImg from "vux/src/components/x-img/index";
  // import BmScale from 'vue-baidu-map/components/controls/Scale'
  // import BmNavigation from 'vue-baidu-map/components/controls/Navigation'
  // import BmMarkerClusterer from  'vue-baidu-map/components/extra/MarkerClusterer'
  // import BmMarker from 'vue-baidu-map/components/overlays/Marker'
  // import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
  export default {
    components: {XImg},
    data(){
      return{
        title:this.$route.query.title,
        buildid:this.$route.query.buildid,
        flag:2,
        users:null,
        buildname:null
      }
    },
    created() {
      this.getmsg()
    },
    methods:{
      getmsg(){
        this.$axios.get("/building/detail",{
          params:{
            id:this.buildid
          }
        }).then((res) => {
          if (res.data.code=="error"){
            this.$toast.fail(res.data.message);
          } else {
            this.users = res.data.data
            this.users.buildimg=this.users.buildimg.split(",")
            this.users.housesInfo.forEach((item,index)=>{
              this.users.housesInfo[index].houseimg.split(",")
            })
          }
        })
      },
      checkitem(item){
        console.log(item)
        this.$router.push({
          path:"/details",
          query:{
            id:item.pk_house
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
    span{
      cursor: pointer;
    }
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
