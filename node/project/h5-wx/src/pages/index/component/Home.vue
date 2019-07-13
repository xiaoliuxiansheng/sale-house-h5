<template>
  <div class="box">
   <div class="head">
     <van-dropdown-menu>
       <van-dropdown-item v-model="parms.rors" :options="checkstyle" @change="changeoption()"/>
     </van-dropdown-menu>
   </div>
    <div class="search">
    <van-dropdown-menu>
      <van-dropdown-item v-model="parms.region" :options="option1"  @change="changeoption()"/>
      <van-dropdown-item v-model="parms.area" :options="option2" @change="changeoption()"/>
      <van-dropdown-item v-model="parms.price" :options="option3" @change="changeoption()"/>
    </van-dropdown-menu>
    </div>
    <div class="content"  v-if="users" id="list-content">
      <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
        <van-list
          v-model="loading"
          :finished="finished"
          @load="onLoad"
          :offset="10"
        >
      <div class="item" v-for="(item,index) in users" @click="checkitem(item)">
        <van-row>
          <van-col span="8"><img :src="item.houseimg[0]"></van-col>
          <van-col span="16">
            <div class="name">
              {{item.subordinate}}
            </div>
            <div class="adress">
              {{item.address}}
            </div>
            <div class="price">
              <span style="color: #0c84d6;">{{item.area}}</span>㎡
              <span  class="rigth" style="float: right"><span style="color: #0c84d6;">{{item.rent}}</span>元/㎡*月</span>
            </div>
          </van-col>
        </van-row>
      </div>
        </van-list>
      </van-pull-refresh>
    </div>
  </div>
</template>
<script>

  export default {
      data(){
        return{
          value1: 0,
          value2: 'Login.vue',
          checkstyle:[
            { text: '写字楼出租', value: 1 },
            { text: '写字楼出售', value: 2 },],
          option1: [
            {text:"区域",value:null},
            { text: '渝中区', value: '渝中区' },
            { text: '江北区', value: "江北区"},
            { text: '南岸区', value: "南岸区" },
            { text: '九龙坡区', value: '九龙坡区' },
            { text: '沙坪坝区', value:"沙坪坝区" },
            { text: '大渡口区', value: "大渡口区" },
            { text: '北碚区', value: '北碚区' },
            { text: '渝北区', value: "渝北区"},
            { text: '巴南区', value: "巴南区" },
            { text: '两江新区', value: "两江新区" }
          ],
          option2: [
            { text: '面积', value: null },
            {
              value: null,
              text: '不限'
            },{
              value: '0-100',
              text: '100m²以下'
            }, {
              value: '100-200',
              text: '100-200m²'
            }, {
              value: '200-300',
              text: '200-300m²'
            }, {
              value: '300-500',
              text: '300-500m²'
            }, {
              value: '500-800',
              text: '500-800m²'
            }, {
              value: '800-1500',
              text: '800-1500m²'
            }, {
              value: '1500',
              text: '1500以上'
            }
          ],
          option3: [
            { text: '价格', value: null },
            {
              value: '',
              text: '不限'
            },{
              value: '0-50',
              text: '50元/m²·月以下'
            }, {
              value: '50-80',
              text: '50-80元/m²·月'
            }, {
              value: '80-100',
              text: '80-100元/m²·月'
            }, {
              value: '100',
              text: '100元以上'
            }
          ],
          option4: [
            { text: '高级搜索', value: 'Login.vue' },
            { text: '好评排序', value: 'b' },
            { text: '销量排序', value: 'c' },
          ],
          parms:{
            name:null,
            region:null,
            price:null,
            area:null,
            pageNo:1,
            rors:1
          },
          users:[],
          loading: false,   //是否处于加载状态
          finished: false,  //是否已加载完所有数据
          isLoading: false,   //是否处于下拉刷新状态
          totalpage:null
        }
      },
    created() {
        this.getmsg()
    },
    mounted(){
      let winHeight = document.documentElement.clientHeight                          //视口大小
      document.getElementById('list-content').style.height = (winHeight - 80) +'px'  //调整上拉加载框高度
    },
    methods:{
      changeoption(){
        this.users=[]
        this.parms.pageNo=1
        this.getmsg()
      },
      onLoad() {      //上拉加载
        setTimeout(() => {
          this.loading = false;
          if (this.parms.pageNo<this.totalpage){
            this.parms.pageNo++
            this.getmsg()
          } else {
            this.finished = true;
            this.$toast('已经加载完毕!');
          }
          //
        }, 500);
      },
      onRefresh() {       //下拉刷新
        setTimeout(() => {
          this.finished = false;
          this.isLoading = false;
          this.parms.pageNo=1
          this.users=[]
          this.getmsg()
        }, 500);
      },
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
            this.$toast.fail(res.data.message);
          } else {
            this.totalpage=res.data.data.totalpage
            res.data.data.rows.forEach((item)=>{
              this.users.push(item)
            })
            console.log(this.users)
            this.users.forEach((item,index)=>{
              this.users[index].houseimg=item.houseimg.split(",")
            })
            console.log(this.users)
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
<style scoped lang="scss">
  $sc:25;
  .box{
    width: 100%;
    .head{
      height: 2rem;
      width: 100%;
      background: #43b1c0;
      color: #fff;
      position: fixed;
      top: 0px;
      z-index: 99;
      text-align: center;
      .van-dropdown-menu{
        background:#43b1c0 ;
        border: none;
        margin-top: 10/$sc+rem;
        border: none;
      }
    }
    span{
      line-height: 50px;
      text-align: center;
      font-size: 18/$sc+rem;
    }
    .search{
      position: fixed;
      width: 100%;
      top: 50px;
      border-bottom: 1px solid #ddd;
      z-index: 6;
      font-size: 10px;
      color: #666;
    }
    /deep/ .van-dropdown-menu{
      width: 100%;
      height: 34px;
      background: #eee;
    }
    .content{
      margin-top: 84px;
      overflow:scroll;
      padding-right: 15/$sc+rem;
      padding-left: 15/$sc+rem;
      .item{
        padding-top: 15/$sc+rem;
        border-bottom: 1px solid #ddd;
        height: 108/$sc+rem;
        img{
          width: 100%;
          height: 70/$sc+rem;
        }
        .name{
          margin-top: 5/$sc+rem;
          font-size: 18/$sc+rem;
          color: #333;
          padding-left:15/$sc+rem;
        }
        .adress{
          color: #666;
          font-size: 12/$sc+rem;
          padding-left:15/$sc+rem;
        }
        .price{
          padding-left:15/$sc+rem;
          font-size: 18/$sc+rem;
          color: #666;
          .rigth{
            display: inline-block;
          }
        }
      }
    }
  }
</style>
