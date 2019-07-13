<template>
  <div class="box">
    <div class="head">
      <span>所属房源</span>
    </div>
    <div id="list-content">
      <div class="content"  v-if="users&&users.length>0" >
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
          <van-list
            v-model="loading"
            :finished="finished"
            @load="onLoad"
            :offset="10"
          >
            <div class="item" v-for="(item,index) in users" >
              <van-row>
                <van-col span="8"><img :src="item.houseimg[0]"></van-col>
                <van-col span="16">
                  <div class="name">
                      {{item.subordinate}}
                    <span @click="deleteitem(item)" class="delete">删除</span>
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
              <div class="house">
                <span>房东姓名：{{item.name}}</span>
                <span>联系电话：{{item.phone}}</span>
              </div>
            </div>
          </van-list>
        </van-pull-refresh>
      </div>
    <div v-else class="none">
      <img src="@/assets/none.jpg">
    </div>
    </div>
  </div>
</template>
<script>

  export default {
    data(){
      return{
        value1: 0,
        value2: 'Login.vue',
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
        totalpage:null,
        id:this.$route.query.id,
      }
    },
    created() {
      this.getmsg()
    },
    mounted(){
      let winHeight = document.documentElement.clientHeight                          //视口大小
      document.getElementById('list-content').style.height = (winHeight-20) +'px'  //调整上拉加载框高度
    },
    methods:{
      //删除
      deleteitem(item){
       this.$dialog.confirm({
          message: '是否删除！'
        }).then(() => {
         let formdata=new FormData()
         formdata.append("id",item.pk_house)
          this.$axios.post("/house/del",formdata).then((res)=>{
            if (res.data.code=="ok"){
              this.getmsg()
              this.$toast.success('删除成功');
              this.parms.pageNo=1
            } else {
              this.$toast.fail('删除失败，请稍后重试！');
            }
          })
        }).catch(() => {
          // on cancel
        });
      },
      changeoption(){
        this.users=[]
        this.parms.pageNo=1
        this.getmsg()
      },
      onLoad() {      //上拉加载
        setTimeout(() => {
          this.loading = false;
          if (this.parms.pageNo<=this.totalpage){
            if (this.parms.pageNo!==1){
              this.getmsg()
            }
            this.parms.pageNo++
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
        formdata.append("pageno",this.parms.pageNo)
        formdata.append("id",this.id)
        this.$axios.post("/house/oalist",formdata).then((res) => {
          console.log(res)
          if (res.data.code=="error"){
            this.$toast.fail(res.data.message);
          } else {
            this.totalpage=res.data.data.totalPages
            res.data.data.rows.forEach((item)=>{
              item.houseimg=item.houseimg.split(",")
              this.users.push(item)
            })
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
    .none{
      position:absolute;
      left:0;
      top:0px;
      width:100%;
      height:100%;
      img{
        width: 100%;
        height: 100%;
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
        height: 125/$sc+rem;
        .delete{
          display: inline-block;
          width: 40/$sc+rem;
          height: 20/$sc+rem;
          font-size: 15/$sc+rem;
          line-height:20/$sc+rem;
          color: white;
          background: red;
        }
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
        .house{
          padding: 0;
          span{
            display: inline-block;
            line-height: 0;
            font-size: 0.6rem;
            color: #666;
          }
        }
      }
    }
  }
</style>
