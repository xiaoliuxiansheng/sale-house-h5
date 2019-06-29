package com.example.wxgzh.entity;

import lombok.Data;

@Data
public class LeaserEntity {
    private String pkLeaser;//招商经理主键
    private String name;//招商经理名称
    private String phone;//招商经理电话
    private String password;//招商经理电话

    public String getPkLeaser() {
        return pkLeaser;
    }

    public void setPkLeaser(String pkLeaser) {
        this.pkLeaser = pkLeaser;
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
