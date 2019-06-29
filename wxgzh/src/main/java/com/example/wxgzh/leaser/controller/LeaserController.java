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
	@GetMapping("/add")
	@ResponseBody
	public JSONResponse addLeaser(LeaserEntity entity) throws Exception{
		LeaserEntity e = service.addLeaser(entity);
		return JSONResponse.ok(e);
	}
}
