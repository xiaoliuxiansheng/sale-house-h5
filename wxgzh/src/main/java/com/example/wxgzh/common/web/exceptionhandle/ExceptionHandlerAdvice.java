package com.example.wxgzh.common.web.exceptionhandle;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.exeption.WxgzhException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class ExceptionHandlerAdvice {
	@ExceptionHandler
	@ResponseBody
	public JSONResponse handleSystemException(WxgzhException e) {
		e.printStackTrace();
		return JSONResponse.fail(e);
	}
	@ExceptionHandler
	@ResponseBody
	public JSONResponse handleSystemException(Exception e) {
		e.printStackTrace();
		return JSONResponse.fail(e);
	}
}
