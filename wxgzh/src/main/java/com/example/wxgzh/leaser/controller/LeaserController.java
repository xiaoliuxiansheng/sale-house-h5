package com.example.wxgzh.leaser.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.leaser.ao.LeaserAO;
import com.example.wxgzh.leaser.service.LeaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/leaser")
public class LeaserController {
	@Autowired
	LeaserService service;
	@PostMapping("/add")
	@ResponseBody
	public JSONResponse addLeaser(LeaserAO ao) throws Exception{
		LeaserEntity e = service.addLeaser(ao);
		return JSONResponse.ok(e);
	}
}
