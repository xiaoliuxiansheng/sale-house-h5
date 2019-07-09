<template>
  <div class="box">
   <div class="head">
     <span>写字楼出租</span>
   </div>
    <div class="search">
    <van-dropdown-menu>
      <van-dropdown-item v-model="value1" :options="option1" />
      <van-dropdown-item v-model="value2" :options="option2" />
      <van-dropdown-item v-model="value1" :options="option3" />
      <van-dropdown-item v-model="value2" :options="option4" />
    </van-dropdown-menu>
    </div>
    <div class="content"  v-if="users">
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
              <span style="color: #0c84d6;">{{item.price}}</span>㎡
              <span  class="rigth" style="float: right"><span style="color: #0c84d6;">{{item.rent}}</span>元/㎡*月</span>
            </div>
          </van-col>
        </van-row>
      </div>
    </div>
  </div>
</template>
<script>

  export default {
      data(){
        return{
          value1: 0,
          value2: 'a',
          option1: [
            { text: '区域', value: 0 },
            { text: '新款商品', value: 1 },
            { text: '活动商品', value: 2 }
          ],
          option2: [
            { text: '面积', value: 'a' },
            { text: '好评排序', value: 'b' },
            { text: '销量排序', value: 'c' },
          ],
          option3: [
            { text: '价格', value: 0 },
            { text: '新款商品', value: 1 },
            { text: '活动商品', value: 2 }
          ],
          option4: [
            { text: '高级搜索', value: 'a' },
            { text: '好评排序', value: 'b' },
            { text: '销量排序', value: 'c' },
          ],
          parms:{
            name:null,
            region:null,
            price:null,
            area:null,
            pageNo:null,
            rors:1
          },
          users:[]
        }
      },
    created() {
        this.getmsg()
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
      line-height: 2rem;
    }
    span{
      line-height: 50px;
      text-align: center;
      font-size: 18/$sc+rem;
    }
    .search{
      position: fixed;
      width: 100%;
      top: 50/$sc+rem;
      border-bottom: 1px solid #ddd;
      z-index: 6;
      font-size: 10px;
      color: #666;
    }
    /deep/ .van-dropdown-menu{
      width: 100%;
      height: 34/$sc+rem;
      background: #eee;
    }
    .content{
      margin-top: 84/$sc+rem;
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
