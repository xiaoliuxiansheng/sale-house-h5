(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1f027c52"],{"0857":function(e,t,a){"use strict";a("a0e0");var r=a("a013"),o=a("f425"),l=a("dad2"),n="toString",i=/./[n],s=function(e){a("e5ef")(RegExp.prototype,n,e,!0)};a("b6f1")(function(){return"/a/b"!=i.call({source:"a",flags:"b"})})?s(function(){var e=r(this);return"/".concat(e.source,"/","flags"in e?e.flags:!l&&e instanceof RegExp?o.call(e):void 0)}):i.name!=n&&s(function(){return i.call(this)})},"0e44":function(e,t,a){var r=a("88dd"),o=a("a013"),l=function(e,t){if(o(e),!r(t)&&null!==t)throw TypeError(t+": can't set as prototype!")};e.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(e,t,r){try{r=a("01f5")(Function.call,a("acb9").f(Object.prototype,"__proto__").set,2),r(e,[]),t=!(e instanceof Array)}catch(o){t=!0}return function(e,a){return l(e,a),t?e.__proto__=a:r(e,a),e}}({},!1):void 0),check:l}},"1d71":function(e,t,a){"use strict";var r=a("379d"),o=a.n(r);o.a},"379d":function(e,t,a){},"44de":function(e,t,a){var r=a("88dd"),o=a("0e44").set;e.exports=function(e,t,a){var l,n=t.constructor;return n!==a&&"function"==typeof n&&(l=n.prototype)!==a.prototype&&r(l)&&o&&o(e,l),e}},"539d":function(e,t,a){var r=a("b2f5"),o=a("f01a"),l=a("b6f1"),n=a("c9ea"),i="["+n+"]",s="​",d=RegExp("^"+i+i+"*"),c=RegExp(i+i+"*$"),u=function(e,t,a){var o={},i=l(function(){return!!n[e]()||s[e]()!=s}),d=o[e]=i?t(m):n[e];a&&(o[a]=d),r(r.P+r.F*i,"String",o)},m=u.trim=function(e,t){return e=String(o(e)),1&t&&(e=e.replace(d,"")),2&t&&(e=e.replace(c,"")),e};e.exports=u},7364:function(e,t,a){var r=a("ddf7").f,o=Function.prototype,l=/^\s*function ([^ (]*)/,n="name";n in o||a("dad2")&&r(o,n,{configurable:!0,get:function(){try{return(""+this).match(l)[1]}catch(e){return""}}})},a0e0:function(e,t,a){a("dad2")&&"g"!=/./g.flags&&a("ddf7").f(RegExp.prototype,"flags",{configurable:!0,get:a("f425")})},a891:function(e,t,a){var r=a("fb6d"),o=a("b4e0").concat("length","prototype");t.f=Object.getOwnPropertyNames||function(e){return r(e,o)}},acb9:function(e,t,a){var r=a("d217"),o=a("7dea"),l=a("3a68"),n=a("5325"),i=a("03b3"),s=a("568a"),d=Object.getOwnPropertyDescriptor;t.f=a("dad2")?d:function(e,t){if(e=l(e),t=n(t,!0),s)try{return d(e,t)}catch(a){}if(i(e,t))return o(!r.f.call(e,t),e[t])}},be24:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("section",[a("el-col",{staticClass:"toolbar",staticStyle:{"padding-bottom":"0px"},attrs:{span:24}},[a("el-form",{attrs:{inline:!0,model:e.filters}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"楼盘名称"},model:{value:e.parms.name,callback:function(t){e.$set(e.parms,"name",t)},expression:"parms.name"}})],1),a("el-form-item",[a("el-select",{attrs:{placeholder:"选择区域"},model:{value:e.parms.region,callback:function(t){e.$set(e.parms,"region",t)},expression:"parms.region"}},e._l(e.options,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.search()}}},[e._v("查询")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.reset()}}},[e._v("重置")])],1)],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticStyle:{width:"100%"},attrs:{data:e.users,"highlight-current-row":""}},[a("el-table-column",{attrs:{prop:"name",label:"楼盘名称",width:"auto"}}),a("el-table-column",{attrs:{prop:"floors",label:"楼层",width:"auto"}}),a("el-table-column",{attrs:{prop:"managecost",label:"物管费","min-width":"auto"}}),a("el-table-column",{attrs:{prop:"area",label:"标准层面积","min-width":"auto"}}),a("el-table-column",{attrs:{prop:"address",label:"地址",width:"auto"}}),a("el-table-column",{attrs:{prop:"company",label:"物管公司",width:"auto"}}),a("el-table-column",{attrs:{prop:"developer",label:"开发商","min-width":"auto"}}),a("el-table-column",{attrs:{prop:"parkspace",label:"房源数量","min-width":"auto"}}),a("el-table-column",{attrs:{label:"操作",width:"150"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(a){return e.handleDel(t.$index,t.row)}}},[e._v("删除")])]}}])})],1),a("el-col",{staticClass:"toolbar",attrs:{span:24}},[a("el-pagination",{staticStyle:{float:"right"},attrs:{layout:"prev, pager, next","page-size":10,total:e.total},on:{"current-change":e.handleCurrentChange}})],1),a("el-dialog",{attrs:{title:"编辑",visible:e.editFormVisible,"close-on-click-modal":!1},on:{"update:visible":function(t){e.editFormVisible=t}}},[a("el-form",{ref:"addForm",attrs:{model:e.addForm,"label-width":"80px",rules:e.addFormRules}},[a("el-row",[a("el-col",{attrs:{span:10}},[a("el-form-item",{attrs:{label:"姓名",prop:"name"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.addForm.name,callback:function(t){e.$set(e.addForm,"name",t)},expression:"addForm.name"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"性别",prop:"region"}},[a("el-radio-group",{model:{value:e.addForm.sex,callback:function(t){e.$set(e.addForm,"sex",t)},expression:"addForm.sex"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("男")]),a("el-radio",{staticClass:"radio",attrs:{label:0}},[e._v("女")])],1)],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:10}},[a("el-form-item",{attrs:{label:"年龄",prop:"age"}},[a("el-input-number",{attrs:{min:0,max:200},model:{value:e.addForm.age,callback:function(t){e.$set(e.addForm,"age",t)},expression:"addForm.age"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"电话",prop:"phone"}},[a("el-input",{attrs:{placeholder:"请输入电话",clearable:""},model:{value:e.addForm.phone,callback:function(t){e.$set(e.addForm,"phone",t)},expression:"addForm.phone"}})],1)],1)],1),a("el-form-item",{attrs:{label:"头像"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"https://jsonplaceholder.typicode.com/posts/","show-file-list":!1,"on-success":e.handleAvatarSuccess,"before-upload":e.beforeAvatarUpload}},[e.imageUrl?a("img",{staticClass:"avatar",attrs:{src:e.imageUrl}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),a("el-row",[a("el-col",{attrs:{span:10}},[a("el-form-item",{attrs:{label:"账号",prop:"num"}},[a("el-input",{attrs:{placeholder:"请输入账号",clearable:""},model:{value:e.addForm.num,callback:function(t){e.$set(e.addForm,"num",t)},expression:"addForm.num"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"密码",prop:"pwd"}},[a("el-input",{attrs:{placeholder:"请输入密码",clearable:""},model:{value:e.addForm.pwd,callback:function(t){e.$set(e.addForm,"pwd",t)},expression:"addForm.pwd"}})],1)],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{nativeOn:{click:function(t){e.editFormVisible=!1}}},[e._v("取消")]),a("el-button",{attrs:{type:"primary",loading:e.editLoading},nativeOn:{click:function(t){return e.editSubmit(t)}}},[e._v("提交")])],1)],1),a("el-dialog",{attrs:{title:"新增",visible:e.addFormVisible,"close-on-click-modal":!1,width:"40%"},on:{"update:visible":function(t){e.addFormVisible=t}}},[a("el-form",{ref:"addForm",attrs:{model:e.addForm,"label-width":"80px",rules:e.addFormRules}},[a("el-row",[a("el-col",{attrs:{span:10}},[a("el-form-item",{attrs:{label:"姓名",prop:"name"}},[a("el-input",{attrs:{"auto-complete":"off"},model:{value:e.addForm.name,callback:function(t){e.$set(e.addForm,"name",t)},expression:"addForm.name"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"性别",prop:"region"}},[a("el-radio-group",{model:{value:e.addForm.sex,callback:function(t){e.$set(e.addForm,"sex",t)},expression:"addForm.sex"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("男")]),a("el-radio",{staticClass:"radio",attrs:{label:0}},[e._v("女")])],1)],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:10}},[a("el-form-item",{attrs:{label:"年龄",prop:"age"}},[a("el-input-number",{attrs:{min:0,max:200},model:{value:e.addForm.age,callback:function(t){e.$set(e.addForm,"age",t)},expression:"addForm.age"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"电话",prop:"phone"}},[a("el-input",{attrs:{placeholder:"请输入电话",clearable:""},model:{value:e.addForm.phone,callback:function(t){e.$set(e.addForm,"phone",t)},expression:"addForm.phone"}})],1)],1)],1),a("el-form-item",{attrs:{label:"头像"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"https://jsonplaceholder.typicode.com/posts/","show-file-list":!1,"on-success":e.handleAvatarSuccess,"before-upload":e.beforeAvatarUpload}},[e.imageUrl?a("img",{staticClass:"avatar",attrs:{src:e.imageUrl}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),a("el-row",[a("el-col",{attrs:{span:10}},[a("el-form-item",{attrs:{label:"账号",prop:"num"}},[a("el-input",{attrs:{placeholder:"请输入账号",clearable:""},model:{value:e.addForm.num,callback:function(t){e.$set(e.addForm,"num",t)},expression:"addForm.num"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"密码",prop:"pwd"}},[a("el-input",{attrs:{placeholder:"请输入密码",clearable:""},model:{value:e.addForm.pwd,callback:function(t){e.$set(e.addForm,"pwd",t)},expression:"addForm.pwd"}})],1)],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{nativeOn:{click:function(t){e.addFormVisible=!1}}},[e._v("取消")]),a("el-button",{attrs:{type:"primary",loading:e.addLoading},nativeOn:{click:function(t){return e.addSubmit(t)}}},[e._v("提交")])],1)],1)],1)},o=[],l=(a("0857"),a("d4d5"),a("7364"),{data:function(){return{filters:{name:""},parms:{pageNo:1,name:null,region:null},options:[{value:"渝中区",label:"渝中区"},{value:"江北区",label:"江北区"},{value:"南岸区",label:"南岸区"},{value:"九龙坡区",label:"九龙坡区"},{value:"沙坪坝区",label:"沙坪坝区"},{value:"大渡口区",label:"大渡口区"},{value:"北碚区",label:"北碚区"},{value:"渝北区",label:"渝北区"},{value:"巴南区",label:"巴南区"},{value:"两江新区",label:"两江新区"}],value:"",users:[],total:0,page:1,listLoading:!1,sels:[],editFormVisible:!1,editLoading:!1,editFormRules:{name:[{required:!0,message:"请输入姓名",trigger:"blur"}]},editForm:{id:0,name:"",sex:-1,age:0,num:null,phone:null,pwd:null},addFormVisible:!1,addLoading:!1,addFormRules:{name:[{required:!0,message:"请输入姓名",trigger:"blur"},{min:2,max:5,message:"长度在 2 到 5 个字符",trigger:"blur"}],region:[{required:!0,message:"请选择性别",trigger:"change"}],age:[{required:!0,message:"输入年龄",trigger:"blur"}],phone:[{required:!0,message:"请输入联系方式",trigger:"blur"}],num:[{required:!0,message:"请输入账号",trigger:"blur"}],pwd:[{required:!0,message:"请输入密码",trigger:"blur"}]},addForm:{name:"",sex:-1,age:0,birth:"",addr:""},imageUrl:"",input:"",ruleForm:{name:"",region:"",date1:"",date2:"",delivery:!1,type:[],resource:"",desc:""}}},created:function(){this.getlist()},methods:{search:function(){this.parms.pageNo=1,this.getlist()},reset:function(){this.parms={pageNo:1,name:null,region:null},this.getlist()},getlist:function(){var e,t=this;for(var a in e=new FormData,this.parms)console.log(a,this.parms[a]),e.append(a,this.parms[a]);console.log(e);this.$axios.get("/building/list",{params:{name:this.parms.name,pageNo:this.parms.pageNo,region:this.parms.region}}).then(function(e){"ok"==e.data.code&&(console.log(e),t.users=e.data.data.rows,t.total=Number(e.data.data.totalRows))})},submitForm:function(e){this.$refs[e].validate(function(e){if(!e)return console.log("error submit!!"),!1;alert("submit!")})},resetForm:function(e){this.$refs[e].resetFields()},handleAvatarSuccess:function(e,t){this.imageUrl=URL.createObjectURL(t.raw)},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type,a=e.size/1024/1024<2;return t||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),t&&a},formatSex:function(e,t){return 1===e.sex?"男":0===e.sex?"女":"未知"},handleCurrentChange:function(e){this.parms.pageNo=e,this.getlist()},getUsers:function(){var e=this,t={page:this.page,name:this.filters.name};this.listLoading=!0,getUserListPage(t).then(function(t){e.total=t.data.total,e.users=t.data.users,e.listLoading=!1})},handleDel:function(e,t){var a=this;console.log(t),this.$confirm("确认删除该记录吗?","提示",{type:"warning"}).then(function(){var e;a.listLoading=!0,e=new FormData,e.append("id",t.pkBuiliding);t.pkBuiliding;a.$axios.post("/building/del",e).then(function(e){a.listLoading=!1,"ok"==e.data.code?a.$message({message:"删除成功",type:"success"}):a.$message({message:"删除失败，请稍后再试！",type:"warning"}),a.getlist()})}).catch(function(){})},handleEdit:function(e,t){this.editFormVisible=!0,this.editForm=Object.assign({},t)},handleAdd:function(){this.addFormVisible=!0,this.addForm={name:"",sex:-1,age:0,birth:"",addr:""}},editSubmit:function(){var e=this;this.$refs.editForm.validate(function(t){t&&e.$confirm("确认提交吗？","提示",{}).then(function(){e.editLoading=!0;var t=Object.assign({},e.editForm);t.birth=t.birth&&""!==t.birth?util.formatDate.format(new Date(t.birth),"yyyy-MM-dd"):"",editUser(t).then(function(t){e.editLoading=!1,e.$message({message:"提交成功",type:"success"}),e.$refs["editForm"].resetFields(),e.editFormVisible=!1,e.getUsers()})})})},addSubmit:function(){var e=this;this.$refs.addForm.validate(function(t){t&&e.$confirm("确认提交吗？","提示",{}).then(function(){e.addLoading=!0;var t=Object.assign({},e.addForm);t.birth=t.birth&&""!==t.birth?util.formatDate.format(new Date(t.birth),"yyyy-MM-dd"):"",addUser(t).then(function(t){e.addLoading=!1,e.$message({message:"提交成功",type:"success"}),e.$refs["addForm"].resetFields(),e.addFormVisible=!1,e.getUsers()})})})},selsChange:function(e){this.sels=e},batchRemove:function(){var e=this,t=this.sels.map(function(e){return e.id}).toString();this.$confirm("确认删除选中记录吗？","提示",{type:"warning"}).then(function(){e.listLoading=!0;var a={ids:t};batchRemoveUser(a).then(function(t){e.listLoading=!1,e.$message({message:"删除成功",type:"success"}),e.getUsers()})}).catch(function(){})}}}),n=l,i=(a("1d71"),a("17cc")),s=Object(i["a"])(n,r,o,!1,null,null,null);t["default"]=s.exports},c9ea:function(e,t){e.exports="\t\n\v\f\r   ᠎             　\u2028\u2029\ufeff"},d4d5:function(e,t,a){"use strict";var r=a("3754"),o=a("03b3"),l=a("94ac"),n=a("44de"),i=a("5325"),s=a("b6f1"),d=a("a891").f,c=a("acb9").f,u=a("ddf7").f,m=a("539d").trim,p="Number",f=r[p],g=f,b=f.prototype,h=l(a("a7b8")(b))==p,v="trim"in String.prototype,F=function(e){var t=i(e,!1);if("string"==typeof t&&t.length>2){t=v?t.trim():m(t,3);var a,r,o,l=t.charCodeAt(0);if(43===l||45===l){if(a=t.charCodeAt(2),88===a||120===a)return NaN}else if(48===l){switch(t.charCodeAt(1)){case 66:case 98:r=2,o=49;break;case 79:case 111:r=8,o=55;break;default:return+t}for(var n,s=t.slice(2),d=0,c=s.length;d<c;d++)if(n=s.charCodeAt(d),n<48||n>o)return NaN;return parseInt(s,r)}}return+t};if(!f(" 0o1")||!f("0b1")||f("+0x1")){f=function(e){var t=arguments.length<1?0:e,a=this;return a instanceof f&&(h?s(function(){b.valueOf.call(a)}):l(a)!=p)?n(new g(F(t)),a,f):F(t)};for(var y,w=a("dad2")?d(g):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),x=0;w.length>x;x++)o(g,y=w[x])&&!o(f,y)&&u(f,y,c(g,y));f.prototype=b,b.constructor=f,a("e5ef")(r,p,f)}},f425:function(e,t,a){"use strict";var r=a("a013");e.exports=function(){var e=r(this),t="";return e.global&&(t+="g"),e.ignoreCase&&(t+="i"),e.multiline&&(t+="m"),e.unicode&&(t+="u"),e.sticky&&(t+="y"),t}}}]);
//# sourceMappingURL=chunk-1f027c52.0ac38ed8.js.map