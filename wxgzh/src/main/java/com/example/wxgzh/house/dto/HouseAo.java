package com.example.wxgzh.house.dto;

public class HouseAo {

    private String pk_house;//房间信息主键
    private String buildingname;//楼盘名称
    private String floor;//楼层
    private String area;//房间面积(m3)
    private String price;//房间单价(元/m2·月)
    private String rent;//租金(元/月)
    private String trimstyle;//装修风格
    private String houseimg;//房间照片地址
    private String describe;//房源描述
    private String name;//联系人
    private String phone;//联系电话
    private String rors;//出租(rent)或者出售(sell);1为出租，2为出售
    private String isexist;//是否存在(如果出售或出租，则为N)


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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
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

    public String getRors() { return rors;  }

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
