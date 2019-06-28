package com.example.wxgzh.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.wxgzh.common.exeption.WxgzhException;

public class ToDate {
	public static Date changeTime(String time) {
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = d.parse(time);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("时间格式传入错误！");
			throw new WxgzhException("时间格式传入错误！");
		}
	}
	
	public static Date time(String time) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = d.parse(time);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("时间格式传入错误！");
			throw new WxgzhException("时间格式传入错误！");
		}
	}
	public static void main(String[] args) {
//		String str = "19960917";
//		System.out.println(changeTime(str));
		String str = "1996-09-16";
		System.out.println(time(str));
	}
}
