package com.example.wxgzh.manager.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.entity.CommodityEntity;
import com.example.wxgzh.entity.ManagerEntity;
import com.example.wxgzh.manager.ao.ManagerAO;
import com.example.wxgzh.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/manager")
public class ManagerController {
	@Autowired
	ManagerService service;
	@PostMapping("/add")
	@ResponseBody
	public JSONResponse addManager(ManagerAO ao) throws Exception{
//		System.out.println("进入");
		ManagerEntity e = service.addManager(ao);
		return JSONResponse.ok(e);
	}
	@PostMapping("/del")
	@ResponseBody
	public JSONResponse delManager(String id) throws Exception{

		return JSONResponse.ok();
	}
	@PostMapping("/mod")
	@ResponseBody
	public JSONResponse modManager(String id, ManagerAO ao) throws Exception{
		return JSONResponse.ok();
	}
	@GetMapping("/query")
	@ResponseBody
	public JSONResponse queryManager(String id) throws Exception{

		return JSONResponse.ok();
	}
	
}
