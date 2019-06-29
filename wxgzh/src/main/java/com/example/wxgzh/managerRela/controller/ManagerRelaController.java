package com.example.wxgzh.managerRela.controller;

import com.example.wxgzh.managerRela.ao.ManagerRelaAO;
import com.example.wxgzh.managerRela.service.ManagerRelaService;
import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/managerRela")
public class ManagerRelaController {
	@Autowired
	ManagerRelaService service;
	@PostMapping("/add")
	@ResponseBody
	public JSONResponse addManagerRela(ManagerRelaAO ao) throws Exception{

		return JSONResponse.ok();
	}
	@PostMapping("/del")
	@ResponseBody
	public JSONResponse delManagerRela(String id) throws Exception{

		return JSONResponse.ok();
	}
	@PostMapping("/mod")
	@ResponseBody
	public JSONResponse modManagerRela(String id, ManagerRelaAO ao) throws Exception{
		return JSONResponse.ok();
	}
	@GetMapping("/query")
	@ResponseBody
	public JSONResponse queryManagerRela(String id) throws Exception{

		return JSONResponse.ok();
	}
	
}
