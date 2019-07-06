package com.example.wxgzh.entity;

public class ManagerRelaEntity {

    private String pk_leaser_rela;//招商经理关系表主键
    private String pk_leaser;//招商经理主键
    private String pk_house;//房间信息主键
    private String allocdate;//分配时间

    public String getPk_leaser_rela() {
        return pk_leaser_rela;
    }

    public void setPk_leaser_rela(String pk_leaser_rela) {
        this.pk_leaser_rela = pk_leaser_rela;
    }

    public String getPk_leaser() {
        return pk_leaser;
    }

    public void setPk_leaser(String pk_leaser) {
        this.pk_leaser = pk_leaser;
    }

    public String getPk_house() {
        return pk_house;
    }

    public void setPk_house(String pk_house) {
        this.pk_house = pk_house;
    }

    public String getAllocdate() {
        return allocdate;
    }

    public void setAllocdate(String allocdate) {
        this.allocdate = allocdate;
    }
}
