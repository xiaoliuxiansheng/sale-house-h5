package com.example.wxgzh.entity;

public class LeaserEntity {
    private String pk_leaser;//招商经理主键
    private String name;//招商经理名称
    private String phone;//招商经理电话
    private String password;//招商经理电话

    public String getPk_leaser() {
        return pk_leaser;
    }

    public void setPk_leaser(String pk_leaser) {
        this.pk_leaser = pk_leaser;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
