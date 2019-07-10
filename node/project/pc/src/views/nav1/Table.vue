<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="reset">重置</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" highlight-current-row v-loading="listLoading" style="width: 100%;">
      <el-table-column prop="name" label="姓名" width="auto" >
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" width="auto" >
      </el-table-column>
      <el-table-column prop="avatar" label="头像" width="auto" >
        <template scope="scope">
          <div class="avatarimg">
            <img :src="scope.row.avatar">
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="账号" min-width="auto" >
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
<!--      <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="10" :total="total"
                     style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-row>
          <el-col :span="10">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="editForm.name" auto-complete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10">
            <el-form-item label="电话"  prop="phone">
              <el-input placeholder="请输入电话" v-model="editForm.phone" clearable ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="头像">
          <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="editbeforeAvatarUpload"
            :file-list="fileList">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-row>
          <el-col :span="10">
            <el-form-item label="密码" prop="repassword">
              <el-input placeholder="请输入密码" v-model="addForm.password" clearable show-password></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <!--新增界面-->
    <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false"  width="40%" >
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-row>
          <el-col :span="10">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="addForm.name" placeholder="请输入名称" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="电话"  prop="phone">
              <el-input placeholder="请输入电话" v-model="addForm.phone" clearable ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="头像">
          <el-upload
            class="avatar-uploader"
            action="http://111.230.43.181:8081/api/o/o"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-row>
          <el-col :span="12">
          <el-form-item label="密码">
            <el-input placeholder="请输入密码" v-model="addForm.password" clearable show-password></el-input>
          </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
import util from '../../common/js/util'
import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api'

export default {
  data () {
    let checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('手机号不能为空'));
      } else {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
        if (reg.test(value)) {
          callback();
        } else {
          return callback(new Error('请输入正确的手机号'));
        }
      }
    };
    let  passwords = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('密码不能为空'));
      } else {
        const reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
        if (reg.test(value)) {
          callback();
        } else {
          return callback(new Error('密码长度要大于6位，由数字和字母组成!'));
        }
      }
    };
    return {
      filters: {
        name: null,
        pageNo:null,
      },
      users: [],
      total: 0,
      page: 1,
      listLoading: false,
      sels: [], // 列表选中列
      editFormVisible: false, // 编辑界面是否显示
      editLoading: false,
      editFormRules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ]
      },
      // 编辑界面数据
      editForm: {
        name: null,
        file:null,
        password: null,
        phone: null,
        file:null
     },
      fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
      addFormVisible: false, // 新增界面是否显示
      addLoading: false,
      addFormRules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        region: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        age: [
          { required: true, message: '输入年龄', trigger: 'blur' }
        ],
        phone: [
          {validator: checkPhone, trigger: 'blur'}
        ],
        num: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          {validator: passwords, trigger: 'blur'}
        ]
      },
      // 新增界面数据
      addForm: {
        name: null,
        file:null,
        password: null,
        phone: null
      },
      imageUrl: '',
      input: '',
      ruleForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    } } ,
  methods: {
    reset(){
      this.filters={
        name: null,
        pageNo:null
      }
      this.getUsers()
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      this.addForm.file=file
      return isJPG && isLt2M
    },
    editbeforeAvatarUpload (file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      this.editForm.file=file
      return isLt2M
    },
    // 性别显示转换
    formatSex: function (row, column) {
      return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
    },
    handleCurrentChange (val) {
      this.filters.pageNo = val
      this.getUsers()
    },
    // 获取用户列表
    getUsers () {
      this.listLoading = true
      this.$axios.get("/leaser/list",{params:this.filters}).then((res) => {
        console.log(res)
        if (res.data.code=="ok"){
          this.total =Number(res.data.data.totalRows)
          this.users = res.data.data.rows
          this.listLoading = false
        }
        console.log(this.users)
      })
    },
    // 删除
    handleDel: function (index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true
        let  formdata;
        formdata =new FormData()
        formdata.append("id",row.pk_leaser)
        this.$axios.post("/leaser/del",formdata).then((res) => {
          this.listLoading = false
          if (res.data.code=="ok"){
            this.$message({
              message: '删除成功',
              type: 'success'
            })
          } else {
            this.$message({
              message: '删除失败，请稍后重试！',
              type: 'warning'
            })
          }
          this.getUsers()
        })
      }).catch(() => {
      })
    },
    // 显示编辑界面
    handleEdit: function (index, row) {
      this.editFormVisible = true
      this.editForm = Object.assign({}, row)
      console.log(row)
      // this.fileList[0].url=row.avatar
    },
    // 显示新增界面
    handleAdd: function () {
      this.addFormVisible = true
      this.addForm = {
        name: null,
        file:null,
        password: null,
        phone: null
      }
    },
    // 编辑
    editSubmit: function () {
      // this.$refs.editForm.validate((valid) => {
      //   if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then((res) => {
            this.editLoading = true
            this.addLoading = true
              let formdata;
              formdata=new FormData()
              for(let k in this.editForm){
                formdata.append(k,this.editForm[k])
              }
              let config = {
                headers:{'Content-Type':'multipart/form-data'}
              }; //添加请求头
              this.$axios.post("/leaser/mod",formdata,config).then((res) => {
                if (res.data.code=="ok"){
                  this.$message({
                    message: '修改成功',
                    type: 'success'
                  })
                } else {
                  this.$message({
                    message: '修改失败，请稍后重试！',
                    type: 'warning'
                  })
                }
                this.editLoading = false
                // this.$refs['editForm'].resetFields()
                this.editFormVisible = false
                this.getUsers()
              })
            // NProgress.start();

          })
        // }
      // })
    },
    // 新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then((res) => {
            this.addLoading = true
            // NProgress.start();
            let formdata;
            formdata=new FormData()
            for(let k in this.addForm){
              formdata.append(k,this.addForm[k])
            }
            let config = {
              headers:{'Content-Type':'multipart/form-data'}
            }; //添加请求头
            this.$axios.post("/leaser/add",formdata,config).then((res) => {
              this.addLoading = false
              // NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.$refs['addForm'].resetFields()
              this.addFormVisible = false
              this.getUsers()
            })
          })
        }
      })
    },
    selsChange: function (sels) {
      this.sels = sels
    },
    // 批量删除
    // batchRemove: function () {
    //   var ids = this.sels.map(item => item.id).toString()
    //   this.$confirm('确认删除选中记录吗？', '提示', {
    //     type: 'warning'
    //   }).then(() => {
    //     this.listLoading = true
    //     let para = { ids: ids }
    //     batchRemoveUser(para).then((res) => {
    //       this.listLoading = false
    //       this.$message({
    //         message: '删除成功',
    //         type: 'success'
    //       })
    //       this.getUsers()
    //     })
    //   }).catch(() => {
    //
    //   })
    // }
  },
  mounted () {
    this.getUsers()
  }
}

</script>

<style lang="scss">
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
  .avatarimg{
    img{
      height: 50px;
      width: 50px;
    }
  }
</style>
