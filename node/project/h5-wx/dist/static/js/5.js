webpackJsonp([5],{"/Fja":function(n,e,a){var t=a("kiVn");"string"==typeof t&&(t=[[n.i,t,""]]),t.locals&&(n.exports=t.locals);a("rjj0")("5e879ec5",t,!0,{})},"9ri4":function(n,e,a){"use strict";function t(n){a("/Fja")}Object.defineProperty(e,"__esModule",{value:!0});var o={data:function(){return{flag:1,searchResult:[],parms:{buildingname:null,floor:null,area:null,price:null,name:null,phone:null,rors:1}}},methods:{checked:function(n){this.flag=n,this.parms.rors=n},submit:function(){var n=this,e=new FormData;for(var a in this.parms)e.append(a,this.parms[a]);this.$axios.post("/ower/add",e).then(function(e){"ok"==e.data.code?(Toast.success("放盘成功！"),n.parms={buildingname:null,floor:null,area:null,price:null,name:null,phone:null,rors:1}):Toast.fail("放盘失败！")})},onSave:function(){Toast("save")},onDelete:function(){Toast("delete")},onChangeDetail:function(n){this.searchResult=n?[{name:"黄龙万科中心",address:"杭州市西湖区"}]:[]}}},i=function(){var n=this,e=n.$createElement,a=n._self._c||e;return a("div",{staticClass:"box"},[a("div",{staticClass:"head"},[a("span",{staticClass:"left",on:{click:function(e){n.$router.go(-1)}}},[a("van-icon",{attrs:{name:"arrow-left"}})],1),n._v("业主放盘"),a("span",{staticClass:"rigth",on:{click:function(e){n.$router.push("/")}}},[a("van-icon",{attrs:{name:"home-o"}})],1)]),n._v(" "),a("div",{staticClass:"option"},[a("div",{class:{checked:1==n.flag},on:{click:function(e){n.checked(1)}}},[n._v("房屋出租")]),n._v(" "),a("div",{class:{ckecked:2==n.flag},on:{click:function(e){n.checked(2)}}},[n._v("房屋出售")])]),n._v(" "),a("div",{staticClass:"form"},[a("van-cell-group",[a("van-field",{attrs:{required:"",clearable:"",label:"大楼名称",placeholder:"请输入大楼名称"},model:{value:n.parms.buildingname,callback:function(e){n.$set(n.parms,"buildingname",e)},expression:"parms.buildingname"}}),n._v(" "),a("van-field",{attrs:{required:"",clearable:"",label:"楼层房号",placeholder:"请输入楼层房号"},model:{value:n.parms.floor,callback:function(e){n.$set(n.parms,"floor",e)},expression:"parms.floor"}}),n._v(" "),a("van-field",{attrs:{required:"",clearable:"",label:"建筑面积",placeholder:"请输入建筑面积"},model:{value:n.parms.area,callback:function(e){n.$set(n.parms,"area",e)},expression:"parms.area"}}),n._v(" "),a("van-field",{attrs:{required:"",clearable:"",label:"出租价格（元/月·m²）",placeholder:"请输入价格"},model:{value:n.parms.price,callback:function(e){n.$set(n.parms,"price",e)},expression:"parms.price"}}),n._v(" "),a("van-field",{attrs:{required:"",clearable:"",label:"联系人",placeholder:"请输入姓名"},model:{value:n.parms.name,callback:function(e){n.$set(n.parms,"name",e)},expression:"parms.name"}}),n._v(" "),a("van-field",{attrs:{required:"",clearable:"",label:"联系电话",placeholder:"请输入联系电话"},model:{value:n.parms.phone,callback:function(e){n.$set(n.parms,"phone",e)},expression:"parms.phone"}})],1),n._v(" "),a("div",{staticClass:"btn",on:{click:function(e){n.submit()}}},[n._v("确定")])],1)])},r=[],l={render:i,staticRenderFns:r},c=l,A=a("VU/8"),s=t,d=A(o,c,!1,s,"data-v-2cd1aa0c",null);e.default=d.exports},kiVn:function(n,e,a){e=n.exports=a("FZ+f")(!0),e.push([n.i,"\n.box[data-v-2cd1aa0c] {\n  margin-top: 2.8rem;\n}\n.box .head[data-v-2cd1aa0c] {\n    width: 100%;\n    height: 2rem;\n    background: #43b1c0;\n    color: #fff;\n    position: fixed;\n    top: 0px;\n    z-index: 99;\n    line-height: 2rem;\n    text-align: center;\n    font-size: 0.72rem;\n}\n.box .head .left[data-v-2cd1aa0c] {\n      display: inline-block;\n      font-size: 0.88rem;\n      margin-left: 0.4rem;\n      float: left;\n}\n.box .head .rigth[data-v-2cd1aa0c] {\n      display: inline-block;\n      font-size: 0.88rem;\n      margin-right: 0.4rem;\n      float: right;\n}\n.box .option[data-v-2cd1aa0c] {\n    top: 2rem;\n    margin-top: 0.8rem;\n    width: 80%;\n    background: #eee;\n    margin: 0 auto;\n    display: flex;\n}\n.box .option div[data-v-2cd1aa0c] {\n      display: flex;\n      flex-direction: column;\n      flex: 1;\n      width: 50%;\n      height: 1.36rem;\n      text-align: center;\n      line-height: 1.36rem;\n      color: #999;\n}\n.box .option .ckecked[data-v-2cd1aa0c] {\n      background: #44b1c1;\n      color: #fff;\n}\n.box .form[data-v-2cd1aa0c] {\n    margin: 0.4rem;\n}\n.box .form .btn[data-v-2cd1aa0c] {\n      margin-top: 0.8rem;\n      padding: 6px 12px;\n      font-weight: 400;\n      background: #44b1c1;\n      font-size: 18px;\n      text-align: center;\n      color: #fff;\n}\n","",{version:3,sources:["D:/liuliu/wechat/wxproject/node/project/h5-wx/src/pages/index/component/new/add.vue"],names:[],mappings:";AACA;EACE,mBAAmB;CACpB;AACD;IACI,YAAY;IACZ,aAAa;IACb,oBAAoB;IACpB,YAAY;IACZ,gBAAgB;IAChB,SAAS;IACT,YAAY;IACZ,kBAAkB;IAClB,mBAAmB;IACnB,mBAAmB;CACtB;AACD;MACM,sBAAsB;MACtB,mBAAmB;MACnB,oBAAoB;MACpB,YAAY;CACjB;AACD;MACM,sBAAsB;MACtB,mBAAmB;MACnB,qBAAqB;MACrB,aAAa;CAClB;AACD;IACI,UAAU;IACV,mBAAmB;IACnB,WAAW;IACX,iBAAiB;IACjB,eAAe;IACf,cAAc;CACjB;AACD;MACM,cAAc;MACd,uBAAuB;MACvB,QAAQ;MACR,WAAW;MACX,gBAAgB;MAChB,mBAAmB;MACnB,qBAAqB;MACrB,YAAY;CACjB;AACD;MACM,oBAAoB;MACpB,YAAY;CACjB;AACD;IACI,eAAe;CAClB;AACD;MACM,mBAAmB;MACnB,kBAAkB;MAClB,iBAAiB;MACjB,oBAAoB;MACpB,gBAAgB;MAChB,mBAAmB;MACnB,YAAY;CACjB",file:"add.vue",sourcesContent:["\n.box[data-v-2cd1aa0c] {\n  margin-top: 2.8rem;\n}\n.box .head[data-v-2cd1aa0c] {\n    width: 100%;\n    height: 2rem;\n    background: #43b1c0;\n    color: #fff;\n    position: fixed;\n    top: 0px;\n    z-index: 99;\n    line-height: 2rem;\n    text-align: center;\n    font-size: 0.72rem;\n}\n.box .head .left[data-v-2cd1aa0c] {\n      display: inline-block;\n      font-size: 0.88rem;\n      margin-left: 0.4rem;\n      float: left;\n}\n.box .head .rigth[data-v-2cd1aa0c] {\n      display: inline-block;\n      font-size: 0.88rem;\n      margin-right: 0.4rem;\n      float: right;\n}\n.box .option[data-v-2cd1aa0c] {\n    top: 2rem;\n    margin-top: 0.8rem;\n    width: 80%;\n    background: #eee;\n    margin: 0 auto;\n    display: flex;\n}\n.box .option div[data-v-2cd1aa0c] {\n      display: flex;\n      flex-direction: column;\n      flex: 1;\n      width: 50%;\n      height: 1.36rem;\n      text-align: center;\n      line-height: 1.36rem;\n      color: #999;\n}\n.box .option .ckecked[data-v-2cd1aa0c] {\n      background: #44b1c1;\n      color: #fff;\n}\n.box .form[data-v-2cd1aa0c] {\n    margin: 0.4rem;\n}\n.box .form .btn[data-v-2cd1aa0c] {\n      margin-top: 0.8rem;\n      padding: 6px 12px;\n      font-weight: 400;\n      background: #44b1c1;\n      font-size: 18px;\n      text-align: center;\n      color: #fff;\n}\n"],sourceRoot:""}])}});
//# sourceMappingURL=5.js.map