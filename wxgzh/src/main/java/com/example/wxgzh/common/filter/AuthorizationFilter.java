package com.example.wxgzh.common.filter;

//import com.example.wxgzh.login.ao.UserContext;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/api/*"})
public class AuthorizationFilter implements Filter{
	//白名单
	Set<String> whiteList=new HashSet<>();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		whiteList.add("/api/login");
		
	}
		
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		//1拿出请求的url
		String url=req.getRequestURI();// "/oa/xxx"
		//1.1获取应用名称
		String appName=req.getContextPath();// "/oa"
		//1.2从url中删掉应用名称
		url=url.substring(appName.length());
		//2判断是否是白名单，如果是直接通过
		if(isInWhiteList(url)) {
			chain.doFilter(req, resp);
			return;
		}
		
		//3如果不是，判断是否已登录，如果是直接通过
		if(isLogin(req)) {
			chain.doFilter(req, resp);
			return;
		}
		//4如果未通过，返回未授权(非法访问)
		resp.sendError(403, "非法访问");
	}
	/**
	 * 判断转入url是否在白名单中
	 * @param url
	 * @return
	 */
	private boolean isInWhiteList(String url) {
		return whiteList.contains(url);
	}
	
	/**
	 * 判断此请求是否有权限(是否登录)
	 * @param req
	 * @return
	 */
	private boolean isLogin(HttpServletRequest req) {
		HttpSession session=req.getSession(false);
		if(session==null) {
			return false;
		}
//		Object value=session.getAttribute(UserContext.class.getName());
//		if(value==null) {
//			return false;
//		}
		return true;
	}
	
	

	

	
}
