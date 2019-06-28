package com.example.wxgzh.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SetCharsetFilter
 */
@WebFilter(urlPatterns= {"/api/*","/public-api/*"},initParams={@WebInitParam(name="encoding", value = "UTF-8")})
public class SetCharsetFilter implements Filter {
    private String encoding;
	public void destroy() {
		this.encoding=null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(this.encoding==null)
			this.encoding="UTF-8";
		request.setCharacterEncoding(this.encoding);
		response.setCharacterEncoding(this.encoding);
		response.setContentType("application/json;charset=utf-8");
		HttpServletResponse resp= ((HttpServletResponse)response);
		HttpServletRequest req=(HttpServletRequest) request;
		resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin")); 
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(request, response);  //放行
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding=fConfig.getInitParameter("encoding");//
	}

}
