package com.example.wxgzh.example.web.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.entity.CommodityEntity;
import com.example.wxgzh.example.ao.CommodityAO;
import com.example.wxgzh.example.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/commodity")
public class CommodityController {
	@Autowired
	CommodityService service;
	@PostMapping("/add")
	@ResponseBody
	public JSONResponse addCommodity(CommodityAO ao) throws Exception{
		System.out.println("进入");
		CommodityEntity e = service.addCommodity(ao);
		return JSONResponse.ok(e);
	}
	@PostMapping("/del")
	@ResponseBody
	public JSONResponse delCommodity(String id) throws Exception{
		System.out.println("glsfkjg"+id);
		service.delCommodity(id);
		return JSONResponse.ok();
	}
	@PostMapping("/mod")
	@ResponseBody
	public JSONResponse modCommodity(String id,CommodityAO ao) throws Exception{
		CommodityEntity e = service.modCommodity(id, ao);
		return JSONResponse.ok(e);
	}
	@GetMapping("/query")
	@ResponseBody
	public JSONResponse queryConsumer(String id) throws Exception{
		CommodityEntity e = service.query(id);
		return JSONResponse.ok(e);
	}
	
	@GetMapping("/list")
	@ResponseBody
	public JSONResponse selectCommodityByKey(String key,String pageNo,String pageSize) throws Exception{
		QueryResult<CommodityEntity> e = service.queryAll(key, pageNo, pageSize);
		return JSONResponse.ok(e);
	}
	@GetMapping("/listTo")
	@ResponseBody
	public JSONResponse selectCommodityByKeyTo(String key,String pageNo,String pageSize) throws Exception{
		QueryResult<CommodityEntity> e = service.queryAllTo(key, pageNo, pageSize);
		return JSONResponse.ok(e);
	}
	
}
