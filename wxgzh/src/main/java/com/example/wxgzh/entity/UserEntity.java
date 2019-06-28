package com.example.wxgzh.entity;

import java.util.Date;

import lombok.Data;
/**
 * 
 * @author chenfangxu
 *	管理员
 */
@Data
public class UserEntity {
	private String userId;//用户编号
	private String userNum;//用户账号
	private String userName;//用户名称
	private String password;//用户密码
	private int jurisdiction;//用户权限
	private Date ctCreate;//创建时间
	private Date ctModified;//修改时间
}
