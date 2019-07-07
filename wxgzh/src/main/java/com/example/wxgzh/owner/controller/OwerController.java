package com.example.wxgzh.owner.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.entity.HouseEntity;
import com.example.wxgzh.entity.OwerEntity;
import com.example.wxgzh.house.dto.HouseAo;
import com.example.wxgzh.owner.dto.OwerAo;
import com.example.wxgzh.owner.service.OwerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;


@Controller
@RequestMapping("/api/ower")
public class OwerController {
	@Autowired
	OwerService service;

	/**
	 * 新增房间信息
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	@ResponseBody
	public JSONResponse addHouse(HttpServletRequest req, HttpServletResponse resp, OwerAo ao) throws Exception {
		service.addOwer(ao);
		return JSONResponse.ok();
	}

	/**
	 * 删除房间信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/del")
	@ResponseBody
	public JSONResponse delHouse(String id) throws Exception{
		service.delOwer(id);

		return JSONResponse.ok();
	}

	@PostMapping("/list")
	@ResponseBody
	public JSONResponse queryOwerEntityByKey(String pageNo, String pageSize, String rors) throws Exception{
		QueryResult<OwerEntity> e = service.selectByKey(pageNo, pageSize, rors);
		return JSONResponse.ok(e);
	}

}
