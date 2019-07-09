<template>
  <el-form ref="form" :model="form" label-width="130px" @submit.prevent="onSubmit"
           style="margin:20px;width:600px;">
    <el-form-item label="方式"  prop="region">
      <el-radio-group v-model="parms.rors">
        <el-radio class="radio" :label="1">出租</el-radio>
        <el-radio class="radio" :label="2">出售</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-row>
      <el-col :span="12">
        <el-form-item label="业主姓名" prop="name">
          <el-input v-model="parms.name"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="业主电话" prop="name">
          <el-input v-model="parms.phone"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
    <el-form-item label="选择楼盘">
    <el-select v-model="parms.pk_building" placeholder="请选择所属楼盘">
      <el-option
        v-for="item in buildoption"
        :key="item.pkBuiliding"
        :label="item.name"
        :value="item.pkBuiliding">
      </el-option>
    </el-select>
    </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="房屋面积（m²)" prop="name">
          <el-input v-model="parms.area"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="2">
      </el-col>
      <el-col :span="12">
      <el-form-item label="租金/售价（元/m²·月)" prop="name">
        <el-input v-model="parms.price"></el-input>
      </el-form-item>
      </el-col>
    </el-row>
    <el-form-item label="房源标签">
      <el-tag
        :key="tag"
        v-for="tag in dynamicTags"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)" >
        {{tag}}
      </el-tag>
      <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="small"
        @keyup.enter.native="handleInputConfirm"
        @blur="handleInputConfirm"
      >
      </el-input>
      <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
    </el-form-item>
    <el-form-item label="房源描述"  prop="region">
      <el-input v-model="parms.describe"
                type="textarea"
                autosize
                placeholder="请输入房源描述">
      </el-input>
    </el-form-item>
    <el-form-item label="上传房屋图片资源">
      <el-upload
        class="upload-demo"
        action="http://111.230.43.181:8081/api/o/o"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList2"
        :before-upload="beforeAvatarUpload"
        list-type="picture">
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
    </el-form-item >
    <el-form-item label="分配招商经理">
    <el-select v-model="parms.pk_leaser" placeholder="分配所属招商经理">
      <el-option
        v-for="item in mangeroption"
        :key="item.pk_leaser"
        :label="item.name"
        :value="item.pk_leaser">
      </el-option>
    </el-select>
    </el-form-item >
    <el-form-item>
      <el-button type="primary" @click="pushmsg()">立即创建</el-button>
      <el-button @click.native.prevent>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    data () {
      return {
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: '',
          imageUrl: '',
        },
        buildoption:[],
        mangeroption:[],
        value: '',
        parms:{
          pk_leaser:null,
          phone:null,
          price:null,
          describe:null,
          trimstyle:null,
          area:null,
          floor:null,
          pk_building:null,
          rors:1
        },
        fileList2: [],
        dynamicTags: ['精装', '家具', '桌椅'],
        inputVisible: false,
        inputValue: '',
        file:[]
      }
    },
    created(){
      this.getbuilds()
      this.getmanager()
    },
    methods: {
      // 创建信息
      pushmsg(){
        this.parms.trimstyle=this.dynamicTags.join("-");
        let formdata;
        formdata=new FormData()
        this.file.forEach((item,index)=>{
          formdata.append('photo',item);
        })
        for(let k in this.parms){
          formdata.append(k,this.parms[k])
        }
        let config = {
          headers:{'Content-Type':'multipart/form-data'}
        }; //添加请求头
        this.$axios.post("/house/add",formdata,config).then((res)=>{
          if (res.data.code=="ok"){
            this.$message({
              type: "success",
              message:"添加成功！"
            })
            this.parms={
              pk_leaser:null,
                phone:null,
                price:null,
                describe:null,
                trimstyle:null,
                area:null,
                floor:null,
                pk_building:null,
                rors:1
            }
          } else {
            this.$message({
              type: "warning",
              message:res.data.message
            })
          }
        })
      },
      //获取楼盘信息
      getbuilds(){
        this.$axios.get("/building/queryNames").then((res)=>{
          if (res.data.code=="ok"){
            this.buildoption=res.data.data
          }
        })
      },
      // 获取招商经理
      getmanager(){
        this.$axios.get("/leaser/queryAll").then((res)=>{
          if (res.data.code=="ok"){
            this.mangeroption=res.data.data
          }
        })
      },
      handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      },

      showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },

      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      onSubmit () {
        console.log('submit!')
      },
      handleAvatarSuccess (res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload (file) {
        const isLt2M = file.size / 1024 / 1024 < 2
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        this.file.push(file);
        return  isLt2M;
      }
    }
  }
</script>
<style  lang="scss">
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
  .el-form-item__label{
    text-align: left;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
