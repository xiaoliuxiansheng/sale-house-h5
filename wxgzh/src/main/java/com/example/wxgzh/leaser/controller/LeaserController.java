package com.example.wxgzh.leaser.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.leaser.service.LeaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/leaser")
public class LeaserController {
	@Autowired
	LeaserService service;

	/**
	 * 新增招商经理账号
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/add")
	@ResponseBody
	public JSONResponse addLeaser(LeaserEntity entity) throws Exception{
		LeaserEntity e = service.addLeaser(entity);
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
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/mod")
	@ResponseBody
	public JSONResponse modLeaser(LeaserEntity entity) throws Exception{
		LeaserEntity e = service.modLeaser(entity);
		return JSONResponse.ok(e);
	}

}
