package com.example.wxgzh.common.util;

import java.security.MessageDigest;

public class MD5 {
	static final char[] cs="1234567890abcdef".toCharArray();
	/**
	 * 加密
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		try {
			MessageDigest digest=MessageDigest.getInstance("md5");
			byte[] bs=digest.digest(s.getBytes("utf-8"));
			char[] result=new char[32];
			int j=0;
			for(int i=0;i<bs.length;i++) {
				result[j++]=cs[bs[i]&0xf];
				result[j++]=cs[(bs[i]>>>4)&0xf];
			}
			return new String(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		String s1=encode("123456");
		System.out.println(s1);
	}
}
