<template>
  <div class="box">
    <div class="head">
      <span class="left"  @click="$router.go(-1)"><van-icon name="arrow-left" /></span>业主放盘<span class="rigth"  @click="$router.push('/')"><van-icon name="home-o" /></span>
    </div>
    <div class="option" >
      <div :class="{ckecked: flag==1}" @click="checked(1)">房屋出租</div>
      <div :class="{ckecked: flag==2}" @click="checked(2)">房屋出售</div>
    </div>
    <div class="form">
      <van-cell-group>
        <van-field
          v-model="parms.buildingname"
          required
          clearable
          label="大楼名称"
          placeholder="请输入大楼名称"
        />
        <van-field
          v-model="parms.floor"
          required
          clearable
          label="楼层房号"
          placeholder="请输入楼层房号"
        />
        <van-field
          v-model="parms.area"
          required
          clearable
          label="建筑面积"
          placeholder="请输入建筑面积"
        />
        <van-field
          v-model="parms.price"
          required
          clearable
          :label="styletxt"
          placeholder="请输入价格"
        />
        <van-field
          v-model="parms.name"
          required
          clearable
          label="联系人"
          placeholder="请输入姓名"
        />
        <van-field
          v-model="parms.phone"
          required
          clearable
          label="联系电话"
          placeholder="请输入联系电话"
        />
      </van-cell-group>
      <div class="btn" @click="submit()">确定</div>
    </div>
  </div>
</template>
<script>
export default {
    data(){
      return{
          flag:1,
        searchResult: [],
        parms:{
          buildingname:null,
          floor:null,
          area:null,
          price:null,
          name:null,
          phone:null,
          rors:1
        },
        styletxt:"出租价格（元/月·m²）"
      }
    },
    methods:{
      checked(item){
        this.flag=item
        this.parms.rors=item
        if (item=="2"){
          this.styletxt="出售价格（元/·m²）"
        } else {
          this.styletxt="出租价格（元/月·m²）"
        }
      },
      submit(){
        if (this.parms.buildingname&&this.parms.floor&&this.parms.area&&this.parms.price&&this.parms.name&&this.parms.phone){
          let formdata=new FormData()
          for (let k in this.parms){
            formdata.append(k,this.parms[k])
          }
          this.$axios.post("/ower/add",formdata).then((res)=>{
            if (res.data.code=='ok'){
              this.$toast.success('放盘成功！');
              this.parms={
                buildingname:null,
                floor:null,
                area:null,
                price:null,
                name:null,
                phone:null,
                rors:1
              }
            } else {
              Toast.fail('放盘失败！');
            }
          })
        } else {
          this.$toast('请完善所有信息！');
        }

      },
      onSave() {
        Toast('save');
      },
      onDelete() {
        Toast('delete');
      },
      onChangeDetail(val) {
        if (val) {
          this.searchResult = [{
            name: '黄龙万科中心',
            address: '杭州市西湖区'
          }];
        } else {
          this.searchResult = [];
        }
      }
    }
}
</script>
<style scoped lang="scss">
  $sc:25;
.box{
  margin-top: 2.8rem;
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
  .option{
    top: 50/$sc+rem;
    margin-top: 20/$sc+rem;
    width: 80%;
    background: #eee;
    margin: 0 auto;
    display: flex;
    div{
      display: flex;
      flex-direction: column;
      flex: 1;
      width: 50%;
      height: 34/$sc+rem;
      text-align: center;
      line-height: 34/$sc+rem;
      color: #999;
    }
    .ckecked{
      background: #44b1c1;
      color: #fff;
    }
  }
  .form{
    margin: 10/$sc+rem;
    .btn{
      margin-top: 20/$sc+rem;
      padding: 6px 12px;
      font-weight: 400;
      background: #44b1c1;
      font-size: 18px;
      text-align: center;
      color: #fff;
    }
  }
}
</style>

