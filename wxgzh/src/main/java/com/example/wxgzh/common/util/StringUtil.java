package com.example.wxgzh.common.util;

public class StringUtil {

	public static String blankAsNull(String s) {
		if (s == null || (s = s.trim()).length() == 0) {
			return null;
		}
		return s;
	}
	public static String trim(String s) {
		if (s == null) {
			return null;
		}
		return s.trim();
	}

	public static String blankAsDefault(String s, String defaultValue) {
		if (s == null || (s = s.trim()).length() == 0) {
			return defaultValue;
		}
		return s;
	}

	public static Integer parseInt(String s, Integer defaultValue) {
		s = blankAsNull(s);
		if (s == null) {
			return defaultValue;
		} else {
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}
	}

}
