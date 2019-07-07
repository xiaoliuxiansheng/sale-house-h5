package com.example.wxgzh.manager.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.entity.ManagerEntity;
import com.example.wxgzh.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/api/manager")
public class ManagerController {
	@Autowired
	ManagerService service;


    /**
     * 新增管理员账号
     * @param entity
     * @return
     * @throws Exception
     */
    @GetMapping("/add")
	@ResponseBody
	public JSONResponse addManager(ManagerEntity entity) throws Exception{

		ManagerEntity e = service.addManager(entity);

		return JSONResponse.ok(e);
	}


    /**
     * 删除管理员账号
     * @param id
     * @return
     * @throws Exception
     */
	@GetMapping("/del")
	@ResponseBody
	public JSONResponse delManager(String id) throws Exception{

	    service.delManager(id);

		return JSONResponse.ok();
	}

	/**
	 * 管理员登录功能
	 * @param account 账号
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	@ResponseBody
	public JSONResponse queryManager(HttpServletRequest request,String account, String password) throws Exception{

		ManagerEntity entity = service.login(account,password);

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		session = request.getSession(true);
		session.setAttribute("manager", entity);
		session.setAttribute("LOGIN", true);

		return JSONResponse.ok(entity);
	}
	
}
