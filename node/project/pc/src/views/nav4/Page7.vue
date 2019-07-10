<template>
  <el-form ref="form" :model="parms" label-width="130px" :rules="rules" @submit.prevent="onSubmit"
           style="margin:20px;width:600px;">
    <el-form-item label="选择区域" prop="region">
      <el-select v-model="parms.region" placeholder="请选择所属区域">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="楼盘名称" prop="name">
    <el-input v-model="parms.name" placeholder="请输入楼盘名称"></el-input>
  </el-form-item>
    <el-form-item label="楼盘详细地址" prop="daddress">
      <el-input v-model="parms.daddress" placeholder="请输入楼盘详细地址"></el-input>
    </el-form-item>
    <el-form-item label="楼盘图片资源">
<!--      <input class="file" name="file" type="file" multiple="multiple" accept="image/png,image/gif,image/jpeg" @change="update"/>-->
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
    <el-row>
      <el-col :span="12">
        <el-form-item label="楼层" prop="floors">
          <el-input v-model="parms.floors"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="物管费（元/m²）" prop="managecost">
          <el-input v-model="parms.managecost"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="层高" prop="height">
          <el-input v-model="parms.height"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="停车位（个）" prop="parkspace">
          <el-input v-model="parms.parkspace"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="空调" prop="airconditioning">
          <el-input v-model="parms.airconditioning"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="标准层面积（m²）" prop="area">
          <el-input v-model="parms.area"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="电梯" prop="elevatorbrand">
          <el-input v-model="parms.elevatorbrand"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="地址" prop="address">
          <el-input v-model="parms.address"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-form-item label="开发商" prop="developer">
          <el-input v-model="parms.developer"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="物管公司" prop="company">
          <el-input v-model="parms.company"></el-input>
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
    <el-form-item label="大楼介绍"  prop="introduce">
      <el-input v-model="parms.introduce"
                type="textarea"
                autosize
                placeholder="请输入楼盘描述">
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="addbuild('form')">立即创建</el-button>
      <el-button  @click="cancel()" >取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    data () {
      return {
        uploadHeader: {
          enctype:"multipart/form-data"
        },
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
        options: [
          {
            value: '渝中区',
            label: '渝中区'
          }, {
            value: '江北区',
            label: '江北区'
          }, {
            value: '南岸区',
            label: '南岸区'
          }, {
            value: '九龙坡区',
            label: '九龙坡区'
          }, {
            value: '沙坪坝区',
            label: '沙坪坝区'
          }, {
            value: '大渡口区',
            label: '大渡口区'
          }, {
            value: '北碚区',
            label: '北碚区'
          }, {
            value: '渝北区',
            label: '渝北区'
          }, {
            value: '巴南区',
            label: '巴南区'
          }, {
            value: '两江新区',
            label: '两江新区'
          }
        ],
        value: '',
        parms:{
          name:null,
          region:null,
          address:null,
          daddress:null,
          managecost:null,
          floors:null,
          parkspace:null,
          elevatorbrand:null,
          elevatorcount:null,
          area:null,
          airconditioning:null,
          developer:null,
          company:null,
          introduce:null,
          typeimg:null,
          height:null
        },
        fileList2: [],
        dynamicTags: ['精装', '家具', '桌椅'],
        inputVisible: false,
        inputValue: '',
        formdata:[],
        file:[],
        rules: {
          managecost:[
            { required: true, message: '请输入物管费（元/m²）', trigger: 'blur' }
          ],
          floors:[
            { required: true, message: '请输入楼层', trigger: 'blur' }
          ],
          parkspace:[
            { required: true, message: '请输入停车位个数', trigger: 'blur' }
          ],
          elevatorbrand:[
            { required: true, message: '请输入电梯描述', trigger: 'blur' }
          ],
          company:[
            { required: true, message: '请输入物管公司', trigger: 'blur' }
          ],
          area:[
            { required: true, message: '请输入标准层面积（m²）', trigger: 'blur' }
          ],
          airconditioning: [
            { required: true, message: '请输入空调描述', trigger: 'change' }
          ],
          height:[
            { required: true, message: '请输入楼盘层高', trigger: 'blur' }
          ],
          introduce:[
            { required: true, message: '请输入楼盘描述', trigger: 'blur' }
          ],
          developer: [
            { required: true, message: '请输入开发商', trigger: 'change' }
          ],
          daddress:[
            { required: true, message: '请输入楼盘详细地址', trigger: 'blur' }
          ],
          address:[
            { required: true, message: '请输入楼盘地址', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择所属区域', trigger: 'change' }
          ],
          name:[
            { required: true, message: '请输入楼盘名称', trigger: 'blur' }
          ]
        }
      }
    },
    created(){
      this.formdata = new FormData();
    },
    methods: {
      cancel(){
        this.$refs['form'].resetFields();
        this.parms={
          name:null,
            region:null,
            address:null,
            daddress:null,
            managecost:null,
            floors:null,
            parkspace:null,
            elevatorbrand:null,
            elevatorcount:null,
            area:null,
            airconditioning:null,
            developer:null,
            company:null,
            introduce:null,
            typeimg:null,
            height:null
        }
      },
      submit(){
        this.addbuild()
      },
      addbuild(x){
        this.$refs[x].validate((valid) => {
          if (valid) {
            this.formdata = new FormData()
            this.file.forEach((item, index) => {
              this.formdata.append('photo', item);
            })
            for (let k in this.parms) {
              this.formdata.append(k, this.parms[k])
            }
            let config = {
              headers: {'Content-Type': 'multipart/form-data'}
            }; //添加请求头
            this.$axios.post('/building/add', this.formdata, config)
              .then(res => {
                if (res.data.code == "ok") {
                  this.parms = {
                    name: null,
                    daddress: null,
                    region: null,
                    address: null,
                    daddress: null,
                    managecost: null,
                    floors: null,
                    parkspace: null,
                    elevatorbrand: null,
                    elevatorcount: null,
                    area: null,
                    airconditioning: null,
                    developer: null,
                    company: null,
                    introduce: null,
                    typeimg: null,
                    height: null
                  }
                  this.file = []
                  this.$message({
                    message: '添加楼盘信息成功！',
                    type: 'success'
                  })
                }
              })
            this.$refs[x].resetFields();
          } else {
            return false;
          }});
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
        this.fileList2[this.file.length]=file
      },
      handlePreview(file) {
        console.log(file);
      },
      onSubmit () {
        console.log('submit!')
      },
      handleAvatarSuccess (res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        console.log(this.imageUrl)
      },
      beforeAvatarUpload (file) {
        const isLt2M = file.size / 1024 / 1024 < 2
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        this.file.push(file);
        // return  false
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
