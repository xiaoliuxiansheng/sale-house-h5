package com.example.wxgzh.common.util;


import com.example.wxgzh.common.exeption.WxgzhException;

/**
 * 	参数验证
 * @author adminstrator
 *
 */
public class AllegeUtil {
	
	 public static String allegeBetween(String message,String s,int min,int max) {
		if(s.length()<min||s.length()>max) {
			throw new WxgzhException(message+"必须大于"+min+"位且小于"+max+"位");
		}
		return s;
	}
	
	 public static String allegeMatch(String message,String target,String regex) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
		if(!pattern.matcher(allegeNotBlank(message, target)).matches()) {
			throw new WxgzhException(message + "格式不正确");
		}
		return target;
	}
	
	 public static String allegeNotBlank(String message,String s) {
		if(s==null||(s=s.trim()).length()==0) {
			throw new WxgzhException(message+"不能为空");
		}
		return s;
	}
	
	 public static void allegeTrue(String message,boolean b) {
		if(!b) {
			throw new WxgzhException(message);
		}
	}
	 public static void allegeFalse(String message,boolean b) {
		if(b) {
			throw new WxgzhException(message);
		}
	}
}
