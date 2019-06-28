package com.example.wxgzh.common.dto;

import com.example.wxgzh.common.exeption.WxgzhException;

/**
 * 一个json的应答
 */
public class JSONResponse {
	private String code;
	private String message;
	private Object data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	public static final JSONResponse ok(Object data) {
		JSONResponse result= new JSONResponse();
		result.setCode("ok");
		result.setData(data);
		return result;
	}
	public static final JSONResponse fail(WxgzhException e) {
		JSONResponse result= new JSONResponse();
		result.setCode("error");
		result.setMessage(e.getMessage());
		return result;
	}
	public static final JSONResponse fail(Exception e) {
		JSONResponse result= new JSONResponse();
		result.setCode("error");
		result.setMessage("系统繁忙，请稍候再试!");
		return result;
	}
	public static final JSONResponse ok() {
		JSONResponse result= new JSONResponse();
		result.setCode("ok");
		return result;
	}
	
}
