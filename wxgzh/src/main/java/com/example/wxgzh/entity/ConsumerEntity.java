package com.example.wxgzh.entity;

import java.util.Date;
import lombok.Data;
/**
 * 
 * @author chenfangxu
 *	vip用户表
 */
@Data
public class ConsumerEntity {
	private int id;//vip用户编号
	private String name;//vip用户姓名
	private int age;//年龄
	private String profession;//职业
	private	 Date joinTime;//加入vip时间
	private Date birthTime;//出生日期
	private String phone;//电话号码
	private String avatar;//头像
	private Date ctModified;//修改时间
}
