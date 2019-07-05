package com.example.wxgzh.building.controller;

import com.alibaba.fastjson.JSON;
import com.example.wxgzh.building.service.BuildingService;
import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.BuildingEntity;
import com.example.wxgzh.entity.LeaserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/building")
public class BuildingController {
	@Autowired
	BuildingService service;

	@Value("${all.photo-save-dir}")
	private String savePath;

	/**
	 * 新增楼盘信息
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	@ResponseBody
	public JSONResponse addBuilding(HttpServletRequest req, HttpServletResponse resp, BuildingEntity entity, @RequestParam(value="photo") MultipartFile[] files) throws Exception {
		String id = UUID.random32();
		entity.setPkBuiliding(id);
		List<String> list = new ArrayList<String>();
		BuildingEntity e = new BuildingEntity();
		StringBuilder imgurl = new StringBuilder();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				list = saveFile(req, file, list, id);
			}
			//json数组把，转换为&
			entity.setBuildingimg(JSON.toJSONString(list).replace(",", "&"));
			e = service.addBuilding(entity);
		}
		return JSONResponse.ok(e);
	}
	private List<String> saveFile(HttpServletRequest request,
								  MultipartFile file, List<String> list, String id) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				String uuid = UUID.random32();
				String unit = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
				String filePath = savePath + "/" + id + "/" + uuid + unit;
				list.add(savePath.substring(savePath.indexOf(":")+1) + "/" + id + "/" + uuid + unit);
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists())
					saveDir.getParentFile().mkdirs();
				// 转存文件
				file.transferTo(saveDir);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	/**
	 * 删除楼盘信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/del")
	@ResponseBody
	public JSONResponse delBuilding(String id) throws Exception{
		service.delBuilding(id, savePath);
		return JSONResponse.ok();
	}


	/**
	 * 修改楼盘信息
	 * @param req
	 * @param resp
	 * @param entity 楼盘实体
	 * @param files 图片文件
	 * @param imgIds 修改的图片id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/mod")
	@ResponseBody
	public JSONResponse modBuilding(HttpServletRequest req, HttpServletResponse resp, BuildingEntity entity, @RequestParam(value="photo") MultipartFile[] files, @RequestParam(value="imgId")List<String> imgIds) throws Exception{
		BuildingEntity e = service.modBuilding(entity, savePath, files, imgIds);
		return JSONResponse.ok(e);
	}

	/**
	 * 查询区域信息
	 * @return 区域信息集合
	 * @throws Exception
	 */
	@GetMapping("/queryRegion")
	@ResponseBody
	public JSONResponse queryRegion()throws Exception {
		List result = service.queryRegion();
		return JSONResponse.ok(result);
	}

	@GetMapping("/list")
	@ResponseBody
	public JSONResponse queryBuildingByKey(String key,String pageNo,String pageSize) throws Exception{
		QueryResult<BuildingEntity> e = service.queryAll(key, pageNo, pageSize);
		return JSONResponse.ok(e);
	}
	@GetMapping("/queryById")
	@ResponseBody
	public JSONResponse queryById(String id) throws Exception{
		BuildingEntity e = service.queryById(id);
		return JSONResponse.ok(e);
	}

}
