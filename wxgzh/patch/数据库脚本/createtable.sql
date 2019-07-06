--新建用户wxgzh,密码wxgzh
create user wxgzh identified by wxgzh;

--给用户授权
grant connect, resource to wxgzh;
--授权允许查看sys用户所拥有的数据字典表，不然使用plsql时会报错提示
grant SELECT ANY DICTIONARY to wxgzh

----管理员
create table wx_manager
(
  pk_manager      char(32) not null,--管理员主键
  account    VARCHAR2(50) not null,--账号
  password    char(32) not null,--密码
  vdef1           VARCHAR2(50),
  vdef2           VARCHAR2(50),
  vdef3           VARCHAR2(50),
  constraint      pk_wx_manager primary key (pk_manager),
  ts              CHAR(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);

----楼盘信息
create table wx_builiding
(
  pk_builiding   char(32) not null,--楼盘信息主键
  name     VARCHAR2(120) not null,--楼盘名称
  city     VARCHAR2(100),--城市名称
  region   VARCHAR2(100),--区域名称
  address        VARCHAR2(100),--地址
  daddress   VARCHAR2(200),--详细地址(detail address)
  managecost   VARCHAR2(10),--物管费(元/m2·月)
  floors   VARCHAR2(10),--楼层(层)
  height   VARCHAR2(10),--层高(米)
  parkspace   VARCHAR2(10),--停车位(个)
  elevatorbrand  VARCHAR2(10),--电梯品牌
  elevatorcount  VARCHAR2(10),--停车位数量
  area     VARCHAR2(10),--标准层面积(m2)
  airconditioning VARCHAR2(50),--空调
  developer   VARCHAR2(50),--开发商
  company   VARCHAR2(50),--物管公司
  introduce   VARCHAR2(500),--大楼介绍
  typeimg   VARCHAR2(500),--户型图地址
  buildingimg    VARCHAR2(500),--楼盘照片
  lon     number(10,6),--经度
  lat     number(10,6),--纬度
  vdef1          VARCHAR2(50),
  vdef2          VARCHAR2(50),
  vdef3          VARCHAR2(50),
  constraint     pk_wx_builiding primary key (pk_builiding),
  ts             CHAR(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);


----房间信息
create table wx_house
(
  pk_building   char(32) not null,--楼盘信息主键
  pk_house   char(32) not null,--房间信息主键
  buildingname   VARCHAR2(100),--楼盘名称
  floor     VARCHAR2(100),--楼层
  area     NUMBER(10),--房间面积(m2)
  price     NUMBER(10),--房间单价(元/m2·月)
  rent           NUMBER(10),--租金(元/月)
  trimstyle   varchar2(50),--装修风格
  houseimg       VARCHAR2(500),--房间照片地址
  describe   VARCHAR2(500),--房源描述
  name     VARCHAR2(50),--联系人
  phone     VARCHAR2(20),--联系电话
  rors     char(1),--出租(rent)或者出售(sell);1为出租，2为出售
  isexist   char(1) default 'Y',--是否存在(如果出售或出租，则为N)
  vdef1          VARCHAR2(50),
  vdef2          VARCHAR2(50),
  vdef3          VARCHAR2(50),
  constraint     pk_wx_house primary key (pk_house),
  ts             CHAR(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);

----招商经理
create table wx_leaser
(
  pk_leaser  char(32) not null,--招商经理主键
  name    VARCHAR2(50) not null,--招商经理姓名
  phone    VARCHAR2(20) not null,--招商经理电话
  password  char(32) not null,--招商经理登陆密码
  avatar        VARCHAR2(500),--招商经理头像地址
  vdef1         VARCHAR2(50),
  vdef2         VARCHAR2(50),
  vdef3         VARCHAR2(50),
  constraint  pk_wx_leaser primary key (pk_leaser),
  ts            CHAR(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);


----招商经理关系表
create table wx_leaser_rela
(
  pk_leaser_rela  char(32) not null,--关系表主键
  pk_leaser    char(32) not null,--招商经理主键
  pk_house    char(32) not null,--房间信息主键
  allocdate    char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),--分配时间
  vdef1      VARCHAR2(50),
  vdef2      VARCHAR2(50),
  vdef3      VARCHAR2(50),
  constraint    pk_wx_leaser_rela primary key (pk_leaser_rela),
  ts               char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);
