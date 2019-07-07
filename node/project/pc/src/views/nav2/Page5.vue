<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="大楼名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-select v-model="value" placeholder="选择区域">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-select v-model="parms.area" placeholder="选择面积">
            <el-option
              v-for="item in options2"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-select v-model="parms.price" placeholder="选择价格">
            <el-option
              v-for="item in options3"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" highlight-current-row v-loading="listLoading" style="width: 100%;">
      <el-table-column prop="subordinate" label="所属楼盘" width="auto" >
      </el-table-column>
      <el-table-column prop="area" label="面积（m²）" width="auto" >
      </el-table-column>
      <el-table-column prop="price" label="价格元/m²·月" width="auto" >
      </el-table-column>
      <el-table-column prop="describe" label="描述" min-width="auto" >
      </el-table-column>
      <el-table-column prop="addr" label="房屋照片" min-width="auto" >
        <template scope="scope">
          <div class="avatarimg"  v-if="scope.row.houseimg.length>0" v-for="(item,index) in scope.row.houseimg"  style="display: inline-block" >
            <img :src="item" v-if="index<1">
          </div>
          <div v-else>
            <img src="@/assets/default.png">
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="业主姓名" min-width="auto" >
      </el-table-column>
      <el-table-column prop="phone" label="业主联系方式" min-width="auto" >
      </el-table-column>
      <el-table-column prop="isexist" label="是否出租" min-width="auto" >
        <template scope="scope">
          <span v-if="scope.row.isexist=='Y'">否</span>
          <span v-else>是</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="auto">
        <template scope="scope">
          <!--          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
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
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-row>
          <el-col :span="10">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="addForm.name" auto-complete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别"  prop="region">
              <el-radio-group v-model="addForm.sex">
                <el-radio class="radio" :label="1">男</el-radio>
                <el-radio class="radio" :label="0">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10">
            <el-form-item label="年龄"  prop="age">
              <el-input-number v-model="addForm.age" :min="0" :max="200"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话"  prop="phone">
              <el-input placeholder="请输入电话" v-model="addForm.phone" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="头像">
          <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-row>
          <el-col :span="10">
            <el-form-item label="账号" prop="num">
              <el-input placeholder="请输入账号" v-model="addForm.num" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="密码" prop="pwd">
              <el-input placeholder="请输入密码" v-model="addForm.pwd" clearable></el-input>
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
              <el-input v-model="addForm.name" auto-complete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别"  prop="region">
              <el-radio-group v-model="addForm.sex">
                <el-radio class="radio" :label="1">男</el-radio>
                <el-radio class="radio" :label="0">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10">
            <el-form-item label="年龄"  prop="age">
              <el-input-number v-model="addForm.age" :min="0" :max="200"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话"  prop="phone">
              <el-input placeholder="请输入电话" v-model="addForm.phone" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="头像">
          <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-row>
          <el-col :span="10">
            <el-form-item label="账号" prop="num">
              <el-input placeholder="请输入账号" v-model="addForm.num" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="密码" prop="pwd">
              <el-input placeholder="请输入密码" v-model="addForm.pwd" clearable></el-input>
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
      return {
        filters: {
          name: ''
        },
        options: [
          {
            value: '',
            label: '不限'
          }, {
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
        }],
        options3: [
          {
            value: '',
            label: '不限'
          },{
            value: '0-50',
            label: '50元/m²·月以下'
          }, {
            value: '50-80',
            label: '50-80元/m²·月'
          }, {
            value: '80-100',
            label: '80-100元/m²·月'
          }, {
            value: '100',
            label: '100元以上'
          }],
        options2: [
          {
            value: '',
            label: '不限'
          },{
            value: '0-100',
            label: '100m²以下'
          }, {
            value: '100-200',
            label: '100-200m²'
          }, {
            value: '200-300',
            label: '200-300m²'
          }, {
            value: '300-500',
            label: '300-500m²'
          }, {
            value: '500-800',
            label: '500-800m²'
          }, {
            value: '800-1500',
            label: '800-1500m²'
          }, {
            value: '1500',
            label: '1500以上'
          }],
        value: '',
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
          id: 0,
          name: '',
          sex: -1,
          age: 0,
          num: null,
          phone: null,
          pwd: null
        },
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
            { required: true, message: '请输入联系方式', trigger: 'blur' }
          ],
          num: [
            { required: true, message: '请输入账号', trigger: 'blur' }
          ],
          pwd: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ]
        },
        // 新增界面数据
        addForm: {
          name: '',
          sex: -1,
          age: 0,
          birth: '',
          addr: ''
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
        },
        parms:{
          name:null,
          region:null,
          price:null,
          area:null,
          pageNo:null,
          rors:2
        }
      } } ,
    methods: {
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
        return isJPG && isLt2M
      },
      // 性别显示转换
      formatSex: function (row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      handleCurrentChange (val) {
        this.parms.pageNo = val
        this.getUsers()
      },
      // 获取用户列表
      getUsers () {
        let para = {
          page: this.page,
          name: this.filters.name
        }
        this.listLoading = true
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
            this.total = res.data.data.totalRows
            this.users = res.data.data.rows
            this.users.forEach((item,index)=>{
              this.users[index].houseimg=item.houseimg.split(",")
            })
            console.log(this.users)
            this.listLoading = false
          }
        })
      },
      // 删除
      handleDel: function (index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        }).then(() => {
          this.listLoading = true
          let formdata=new FormData()
          formdata.append("id",row.pk_house)
          this.$axios.post("/house/del",formdata).then((res) => {
            this.listLoading = false
            if(res.data.code=="ok"){
              this.$message({
                message: '删除成功',
                type: 'success'
              })
            } else {
              this.$message({
                message: res.data.message,
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
      },
      // 显示新增界面
      handleAdd: function () {
        this.addFormVisible = true
        this.addForm = {
          name: '',
          sex: -1,
          age: 0,
          birth: '',
          addr: ''
        }
      },
      // 编辑
      editSubmit: function () {
        this.$refs.editForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let para = Object.assign({}, this.editForm)
              para.birth = (!para.birth || para.birth === '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')
              editUser(para).then((res) => {
                this.editLoading = false
                this.$message({
                  message: '提交成功',
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.editFormVisible = false
                this.getUsers()
              })
            })
          }
        })
      },
      // 新增
      addSubmit: function () {
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.addLoading = true
              // NProgress.start();
              let para = Object.assign({}, this.addForm)
              para.birth = (!para.birth || para.birth === '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')
              addUser(para).then((res) => {
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
      batchRemove: function () {
        var ids = this.sels.map(item => item.id).toString()
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.listLoading = true
          let para = { ids: ids }
          batchRemoveUser(para).then((res) => {
            this.listLoading = false
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.getUsers()
          })
        }).catch(() => {

        })
      }
    },
    mounted () {
      this.getUsers()
    }
  }

</script>

<style lang="scss">
  .avatarimg{
    img{
      width: 50px;
      height: 50px;
    }
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
