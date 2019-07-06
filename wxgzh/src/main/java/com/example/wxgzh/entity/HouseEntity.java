package com.example.wxgzh.entity;

public class HouseEntity {

    private String pk_building;//楼盘信息主键
    private String pk_house;//房间信息主键
    private String buildingname;//楼盘名称
    private String floor;//楼层
    private int area;//房间面积(m3)
    private int price;//房间单价(元/m2·月)
    private int rent;//租金(元/月)
    private String trimstyle;//装修风格
    private String houseimg;//房间照片地址
    private String describe;//房源描述
    private String name;//联系人
    private String phone;//联系电话
    private String rors;//出租(rent)或者出售(sell);1为出租，2为出售
    private String isexist;//是否存在(如果出售或出租，则为N)
    private String leasername;//招商经理名称
    private String leaserphone;//招商经理电话
    private String avatar;//招商经理头像地址


    public String getLeasername() {
        return leasername;
    }

    public void setLeasername(String leasername) {
        this.leasername = leasername;
    }

    public String getLeaserphone() {
        return leaserphone;
    }

    public void setLeaserphone(String leaserphone) {
        this.leaserphone = leaserphone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getPk_building() {
        return pk_building;
    }

    public void setPk_building(String pk_building) {
        this.pk_building = pk_building;
    }

    public String getPk_house() {
        return pk_house;
    }

    public void setPk_house(String pk_house) {
        this.pk_house = pk_house;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getTrimstyle() {
        return trimstyle;
    }

    public void setTrimstyle(String trimstyle) {
        this.trimstyle = trimstyle;
    }

    public String getHouseimg() {
        return houseimg;
    }

    public void setHouseimg(String houseimg) {
        this.houseimg = houseimg;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRors() {
        return rors;
    }

    public void setRors(String rors) {
        this.rors = rors;
    }

    public String getIsexist() {
        return isexist;
    }

    public void setIsexist(String isexist) {
        this.isexist = isexist;
    }
}
