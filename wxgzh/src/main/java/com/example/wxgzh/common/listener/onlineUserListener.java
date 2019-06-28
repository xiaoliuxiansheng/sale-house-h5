package com.example.wxgzh.common.listener;

import com.example.wxgzh.WxgzhApplication;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;



@WebListener
public class onlineUserListener implements HttpSessionAttributeListener {
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		if("LOGIN".equals(name)){
//			WxgzhApplication.ONLINE_USER_COUNT++;
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		if("LOGIN".equals(name)){
//			WxgzhApplication.ONLINE_USER_COUNT--;
		}
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
		
	}
}
