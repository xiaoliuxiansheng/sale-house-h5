(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-77de852c"],{"9fe7":function(e,t,a){"use strict";var l=a("bca0"),n=a.n(l);n.a},b4cd:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-form",{ref:"form",staticStyle:{margin:"20px",width:"600px"},attrs:{model:e.form,"label-width":"130px"},on:{submit:function(t){return t.preventDefault(),e.onSubmit(t)}}},[a("el-form-item",{attrs:{label:"方式",prop:"region"}},[a("el-radio-group",{model:{value:e.parms.rors,callback:function(t){e.$set(e.parms,"rors",t)},expression:"parms.rors"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("出租")]),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("出售")])],1)],1),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"业主姓名",prop:"name"}},[a("el-input",{model:{value:e.parms.name,callback:function(t){e.$set(e.parms,"name",t)},expression:"parms.name"}})],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"业主电话",prop:"name"}},[a("el-input",{model:{value:e.parms.phone,callback:function(t){e.$set(e.parms,"phone",t)},expression:"parms.phone"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"选择楼盘"}},[a("el-select",{attrs:{placeholder:"请选择所属楼盘"},model:{value:e.parms.pk_building,callback:function(t){e.$set(e.parms,"pk_building",t)},expression:"parms.pk_building"}},e._l(e.buildoption,function(e){return a("el-option",{key:e.pkBuiliding,attrs:{label:e.name,value:e.pkBuiliding}})}),1)],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"房屋面积（m²)",prop:"name"}},[a("el-input",{model:{value:e.parms.area,callback:function(t){e.$set(e.parms,"area",t)},expression:"parms.area"}})],1)],1),a("el-col",{attrs:{span:2}}),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"租金/售价（元/m²·月)",prop:"name"}},[a("el-input",{model:{value:e.parms.price,callback:function(t){e.$set(e.parms,"price",t)},expression:"parms.price"}})],1)],1)],1),a("el-form-item",{attrs:{label:"房源标签"}},[e._l(e.dynamicTags,function(t){return a("el-tag",{key:t,attrs:{closable:"","disable-transitions":!1},on:{close:function(a){return e.handleClose(t)}}},[e._v("\n      "+e._s(t)+"\n    ")])}),e.inputVisible?a("el-input",{ref:"saveTagInput",staticClass:"input-new-tag",attrs:{size:"small"},on:{blur:e.handleInputConfirm},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleInputConfirm(t)}},model:{value:e.inputValue,callback:function(t){e.inputValue=t},expression:"inputValue"}}):a("el-button",{staticClass:"button-new-tag",attrs:{size:"small"},on:{click:e.showInput}},[e._v("+ New Tag")])],2),a("el-form-item",{attrs:{label:"房源描述",prop:"region"}},[a("el-input",{attrs:{type:"textarea",autosize:"",placeholder:"请输入房源描述"},model:{value:e.parms.describe,callback:function(t){e.$set(e.parms,"describe",t)},expression:"parms.describe"}})],1),a("el-form-item",{attrs:{label:"上传房屋图片资源"}},[a("el-upload",{staticClass:"upload-demo",attrs:{action:"http://192.168.1.4:8081/api/o/o","on-preview":e.handlePreview,"on-remove":e.handleRemove,"file-list":e.fileList2,"before-upload":e.beforeAvatarUpload,"list-type":"picture"}},[a("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")]),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传jpg/png文件，且不超过500kb")])],1)],1),a("el-form-item",{attrs:{label:"分配招商经理"}},[a("el-select",{attrs:{placeholder:"分配所属招商经理"},model:{value:e.parms.pk_leaser,callback:function(t){e.$set(e.parms,"pk_leaser",t)},expression:"parms.pk_leaser"}},e._l(e.mangeroption,function(e){return a("el-option",{key:e.pk_leaser,attrs:{label:e.name,value:e.pk_leaser}})}),1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.pushmsg()}}},[e._v("立即创建")]),a("el-button",{nativeOn:{click:function(e){e.preventDefault()}}},[e._v("取消")])],1)],1)},n=[],i=(a("f763"),{data:function(){return{form:{name:"",region:"",date1:"",date2:"",delivery:!1,type:[],resource:"",desc:"",imageUrl:""},buildoption:[],mangeroption:[],value:"",parms:{pk_leaser:null,phone:null,price:null,describe:null,trimstyle:null,area:null,floor:null,pk_building:null,rors:1},fileList2:[],dynamicTags:["精装","家具","桌椅"],inputVisible:!1,inputValue:"",file:[]}},created:function(){this.getbuilds(),this.getmanager()},methods:{pushmsg:function(){var e,t=this;for(var a in this.parms.trimstyle=this.dynamicTags.join("-"),e=new FormData,this.file.forEach(function(t,a){e.append("photo",t)}),this.parms)e.append(a,this.parms[a]);var l={headers:{"Content-Type":"multipart/form-data"}};this.$axios.post("/house/add",e,l).then(function(e){"ok"==e.data.code?(t.$message({type:"success",message:"添加成功！"}),t.parms={pk_leaser:null,phone:null,price:null,describe:null,trimstyle:null,area:null,floor:null,pk_building:null,rors:1}):t.$message({type:"warning",message:e.data.message})})},getbuilds:function(){var e=this;this.$axios.get("/building/queryNames").then(function(t){"ok"==t.data.code&&(e.buildoption=t.data.data)})},getmanager:function(){var e=this;this.$axios.get("/leaser/queryAll").then(function(t){"ok"==t.data.code&&(e.mangeroption=t.data.data)})},handleClose:function(e){this.dynamicTags.splice(this.dynamicTags.indexOf(e),1)},showInput:function(){var e=this;this.inputVisible=!0,this.$nextTick(function(t){e.$refs.saveTagInput.$refs.input.focus()})},handleInputConfirm:function(){var e=this.inputValue;e&&this.dynamicTags.push(e),this.inputVisible=!1,this.inputValue=""},handleRemove:function(e,t){console.log(e,t)},handlePreview:function(e){console.log(e)},onSubmit:function(){console.log("submit!")},handleAvatarSuccess:function(e,t){this.imageUrl=URL.createObjectURL(t.raw)},beforeAvatarUpload:function(e){var t=e.size/1024/1024<2;return t||this.$message.error("上传头像图片大小不能超过 2MB!"),this.file.push(e),t}}}),s=i,r=(a("9fe7"),a("17cc")),o=Object(r["a"])(s,l,n,!1,null,null,null);t["default"]=o.exports},bca0:function(e,t,a){},f763:function(e,t,a){for(var l=a("dac5"),n=a("cfc7"),i=a("e5ef"),s=a("3754"),r=a("743d"),o=a("14fc"),u=a("8b37"),p=u("iterator"),c=u("toStringTag"),m=o.Array,d={CSSRuleList:!0,CSSStyleDeclaration:!1,CSSValueList:!1,ClientRectList:!1,DOMRectList:!1,DOMStringList:!1,DOMTokenList:!0,DataTransferItemList:!1,FileList:!1,HTMLAllCollection:!1,HTMLCollection:!1,HTMLFormElement:!1,HTMLSelectElement:!1,MediaList:!0,MimeTypeArray:!1,NamedNodeMap:!1,NodeList:!0,PaintRequestList:!1,Plugin:!1,PluginArray:!1,SVGLengthList:!1,SVGNumberList:!1,SVGPathSegList:!1,SVGPointList:!1,SVGStringList:!1,SVGTransformList:!1,SourceBufferList:!1,StyleSheetList:!0,TextTrackCueList:!1,TextTrackList:!1,TouchList:!1},f=n(d),b=0;b<f.length;b++){var h,g=f[b],v=d[g],k=s[g],y=k&&k.prototype;if(y&&(y[p]||r(y,p,m),y[c]||r(y,c,g),o[g]=m,v))for(h in l)y[h]||i(y,h,l[h],!0)}}}]);
//# sourceMappingURL=chunk-77de852c.d2dfb600.js.map