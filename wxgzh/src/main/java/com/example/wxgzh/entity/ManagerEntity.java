package com.example.wxgzh.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ManagerEntity {
   private String pkManage;
   private String account;
   private String password;
   private Date ts;
}
