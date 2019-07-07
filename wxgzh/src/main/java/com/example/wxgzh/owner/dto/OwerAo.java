package com.example.wxgzh.owner.dto;

public class OwerAo {

    private String pk_ower;//业主放盘主键
    private String buildingname;//楼盘名称
    private String floor;//楼层
    private String area;//房间面积(m3)
    private String price;//房间单价(元/m2·月)
    private String name;//联系人
    private String phone;//联系电话
    private String rors;//出租还是出售1出租，2出售

    public String getRors() {
        return rors;
    }

    public void setRors(String rors) {
        this.rors = rors;
    }

    public String getPk_ower() {
        return pk_ower;
    }

    public void setPk_ower(String pk_ower) {
        this.pk_ower = pk_ower;
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
}
