package com.example.wxgzh.entity;

public class ManagerEntity {

   private String pk_manager;//管理员主键
   private String account;//账号
   private String password;//密码

   public String getPk_manager() {
      return pk_manager;
   }

   public void setPk_manager(String pk_manager) {
      this.pk_manager = pk_manager;
   }

   public String getAccount() {
      return account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
