package com.example.wxgzh.leaser.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.leaser.service.LeaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/api/leaser")
public class LeaserController {
	@Autowired
	LeaserService service;

	/**
	 * 新增招商经理账号
	 * @param entity
	 * @param file 头像文件
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/add")
	@ResponseBody
	public JSONResponse addLeaser(LeaserEntity entity, @RequestParam(value="avatar") MultipartFile file) throws Exception{
		LeaserEntity e = service.addLeaser(entity, file);

		return JSONResponse.ok(e);
	}

	/**
	 * 删除招商经理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/del")
	@ResponseBody
	public JSONResponse delLeaser(String id) throws Exception{
		service.delLeaser(id);
		return JSONResponse.ok();
	}


	/**
	 * 修改招商经理信息
	 * @param entity
	 * @param file 头像文件
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/mod")
	@ResponseBody
	public JSONResponse modLeaser(LeaserEntity entity, MultipartFile file) throws Exception{
		LeaserEntity e = service.modLeaser(entity, file);
		return JSONResponse.ok(e);
	}

	/**
	 * 查询招商经理列表
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	@ResponseBody
	public JSONResponse selectByName(String name,String pageNo,String pageSize) throws Exception{
		QueryResult<LeaserEntity> e = service.queryAll(name, pageNo, pageSize);
		return JSONResponse.ok(e);
	}

	/**
	 * 管理员登录功能
	 * @param account 账号
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/login")
	@ResponseBody
	public JSONResponse queryManager(HttpServletRequest request, String account, String password) throws Exception{

		LeaserEntity entity = service.login(account,password);

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
