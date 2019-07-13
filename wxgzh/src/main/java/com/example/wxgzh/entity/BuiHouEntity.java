package com.example.wxgzh.entity;

import java.util.List;

public class BuiHouEntity {

    private String pk_build;//楼盘主键
    private String buildname;
    private String city;
    private String region;
    private String address;
    private String daddress;
    private String cost;
    private String floorcount;
    private String buildheight;
    private String park;
    private String ebrand;
    private String ecount;
    private String buildarea;
    private String airconditioning;
    private String developer;
    private String company;
    private String introduce;
    private String typeimg;
    private String buildimg;
    private Double lon;
    private Double lat;

    private List<HouseEntity> housesInfo;

    public String getPk_build() {
        return pk_build;
    }

    public void setPk_build(String pk_build) {
        this.pk_build = pk_build;
    }

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDaddress() {
        return daddress;
    }

    public void setDaddress(String daddress) {
        this.daddress = daddress;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFloorcount() {
        return floorcount;
    }

    public void setFloorcount(String floorcount) {
        this.floorcount = floorcount;
    }

    public String getBuildheight() {
        return buildheight;
    }

    public void setBuildheight(String buildheight) {
        this.buildheight = buildheight;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public String getEbrand() {
        return ebrand;
    }

    public void setEbrand(String ebrand) {
        this.ebrand = ebrand;
    }

    public String getEcount() {
        return ecount;
    }

    public void setEcount(String ecount) {
        this.ecount = ecount;
    }

    public String getBuildarea() {
        return buildarea;
    }

    public void setBuildarea(String buildarea) {
        this.buildarea = buildarea;
    }

    public String getAirconditioning() {
        return airconditioning;
    }

    public void setAirconditioning(String airconditioning) {
        this.airconditioning = airconditioning;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTypeimg() {
        return typeimg;
    }

    public void setTypeimg(String typeimg) {
        this.typeimg = typeimg;
    }

    public String getBuildimg() {
        return buildimg;
    }

    public void setBuildimg(String buildimg) {
        this.buildimg = buildimg;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public List<HouseEntity> getHousesInfo() {
        return housesInfo;
    }

    public void setHousesInfo(List<HouseEntity> housesInfo) {
        this.housesInfo = housesInfo;
    }
}
