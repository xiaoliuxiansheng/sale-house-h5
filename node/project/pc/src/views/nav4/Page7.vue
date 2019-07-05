<template>
  <el-form ref="form" :model="form" label-width="130px" @submit.prevent="onSubmit"
           style="margin:20px;width:600px;">
    <el-form-item label="选择区域">
      <el-select v-model="parms.plot" placeholder="请选择所属区域">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="楼盘名称">
      <el-input v-model="parms.name" placeholder="请输入楼盘名称"></el-input>
    </el-form-item>
    <el-row>
      <el-col :span="12">
        <el-form-item label="楼层" prop="name">
          <el-input v-model="parms.area"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="物管费（元/m²）" prop="name">
          <el-input v-model="parms.price"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="层高" prop="name">
          <el-input v-model="parms.area"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="停车位（个）" prop="name">
          <el-input v-model="parms.price"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="空调" prop="name">
          <el-input v-model="parms.area"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="标准层面积（m²）" prop="name">
          <el-input v-model="parms.price"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="电梯" prop="name">
          <el-input v-model="parms.area"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="地址" prop="name">
          <el-input v-model="parms.price"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="开发商" prop="name">
          <el-input v-model="parms.area"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="物管公司" prop="name">
          <el-input v-model="parms.price"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-form-item label="区位交通">
      <el-tag
        :key="tag"
        v-for="tag in dynamicTags"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)">
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
    <el-form-item label="大楼介绍"  prop="region">
      <el-input v-model="parms.text"
                type="textarea"
                autosize
                placeholder="请输入房源描述">
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary">立即创建</el-button>
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
        options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
        value: '',
        parms:{
          name:null,
          plot:null,
          area:null,
          price:null,
          options:1,
          Manager:null,
          text:null
        },
        fileList2: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
        dynamicTags: ['精装', '家具', '桌椅'],
        inputVisible: false,
        inputValue: ''

      }
    },
    methods: {
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
        const isJPG = file.type === 'image/jpeg' ;
        const isLt2M = file.size / 1024 / 1024 < 2

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
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
