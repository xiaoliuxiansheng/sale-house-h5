package com.example.wxgzh.entity;

public class BuildingEntity {
    private String pkBuiliding;
    private String name;
    private String city;
    private String region;
    private String address;
    private String daddress;
    private String managecost;
    private String floors;
    private String height;
    private String parkspace;
    private String elevatorbrand;
    private String elevatorcount;
    private String area;
    private String airconditioning;
    private String developer;
    private String company;
    private String introduce;
    private String typeimg;
    private String buildingimg;
    private Double lon;
    private Double lat;

    public String getPkBuiliding() {
        return pkBuiliding;
    }

    public void setPkBuiliding(String pkBuiliding) {
        this.pkBuiliding = pkBuiliding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getManagecost() {
        return managecost;
    }

    public void setManagecost(String managecost) {
        this.managecost = managecost;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getParkspace() {
        return parkspace;
    }

    public void setParkspace(String parkspace) {
        this.parkspace = parkspace;
    }

    public String getElevatorbrand() {
        return elevatorbrand;
    }

    public void setElevatorbrand(String elevatorbrand) {
        this.elevatorbrand = elevatorbrand;
    }

    public String getElevatorcount() {
        return elevatorcount;
    }

    public void setElevatorcount(String elevatorcount) {
        this.elevatorcount = elevatorcount;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getBuildingimg() {
        return buildingimg;
    }

    public void setBuildingimg(String buildingimg) {
        this.buildingimg = buildingimg;
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

    @Override
    public String toString() {
        return "BuildingEntity{" +
                "pkBuiliding='" + pkBuiliding + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", daddress='" + daddress + '\'' +
                ", managecost='" + managecost + '\'' +
                ", floors='" + floors + '\'' +
                ", height='" + height + '\'' +
                ", parkspace='" + parkspace + '\'' +
                ", elevatorbrand='" + elevatorbrand + '\'' +
                ", elevatorcount='" + elevatorcount + '\'' +
                ", area='" + area + '\'' +
                ", airconditioning='" + airconditioning + '\'' +
                ", developer='" + developer + '\'' +
                ", company='" + company + '\'' +
                ", introduce='" + introduce + '\'' +
                ", typeimg='" + typeimg + '\'' +
                ", buildingimg='" + buildingimg + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
