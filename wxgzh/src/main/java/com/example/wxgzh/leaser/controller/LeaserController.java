package com.example.wxgzh.leaser.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.entity.ManagerRelaEntity;
import com.example.wxgzh.leaser.service.LeaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


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
	@PostMapping("/add")
	@ResponseBody
	public JSONResponse addLeaser(LeaserEntity entity, @RequestParam(value="file") MultipartFile file) throws Exception{
		LeaserEntity e = service.addLeaser(entity, file);

		return JSONResponse.ok(e);
	}

	/**
	 * 删除招商经理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/del")
	@ResponseBody
	public JSONResponse delLeaser(String id) throws Exception{
		service.delLeaser(id);
		System.out.println(JSONResponse.ok());
		return JSONResponse.ok();
	}


	/**
	 * 修改招商经理信息
	 * @param entity
	 * @param file 头像文件
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/mod")
	@ResponseBody
	public JSONResponse modLeaser(LeaserEntity entity, @RequestParam(value="file")MultipartFile file) throws Exception{
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
	public JSONResponse selectByName(HttpServletRequest req,String name,String pageNo,String pageSize) throws Exception{
		String ip = req.getServerName();

		int port = req.getServerPort();

		//String apppath = req.getContextPath();

		String url = "http://"+ip+":"+port;

		QueryResult<LeaserEntity> e = service.queryAll(url,name, pageNo, pageSize);
		return JSONResponse.ok(e);
	}

	/**
	 * 招商经理登录功能
	 * @param phone 手机号码
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	@ResponseBody
	public JSONResponse queryManager(HttpServletRequest request, String phone, String password) throws Exception{

		LeaserEntity entity = service.login(phone,password);

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		session = request.getSession(true);
		session.setAttribute("leaser", entity);
		session.setAttribute("LOGIN", true);

		return JSONResponse.ok(entity);
	}


	//allocate分配
	@GetMapping("/allocate")
	@ResponseBody
	public JSONResponse queryManager(ManagerRelaEntity entity) throws Exception{

		service.allocate(entity);

		return JSONResponse.ok();
	}

	@GetMapping("/query")
	@ResponseBody
	public JSONResponse selectByName(String id) throws Exception{
		LeaserEntity e = service.query(id);
		return JSONResponse.ok(e);
	}


	@GetMapping("/queryAll")
	@ResponseBody
	public JSONResponse selectAll() throws Exception{
		List<LeaserEntity> e = service.queryLeasers();
		return JSONResponse.ok(e);
	}
}
