package com.example.wxgzh.common.exeption;


public class WxgzhException extends RuntimeException {

	public WxgzhException(String message, Throwable cause) {
		super(message, cause);
	}

	public WxgzhException(String message) {
		super(message);
	}

}
