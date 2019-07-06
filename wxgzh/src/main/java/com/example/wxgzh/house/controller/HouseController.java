package com.example.wxgzh.house.controller;

import com.alibaba.fastjson.JSON;
import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.HouseEntity;
import com.example.wxgzh.house.dto.HouseAo;
import com.example.wxgzh.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/house")
public class HouseController {
	@Autowired
	HouseService service;

	@Value("${all.photo-save-dir}")
	private String savePath;

	/**
	 * 新增房间信息
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	@ResponseBody
	public JSONResponse addHouse(HttpServletRequest req, HttpServletResponse resp, HouseAo ao, @RequestParam(value="photo") MultipartFile[] files) throws Exception {
		String id = UUID.random32();
		ao.setPk_house(id);
		List<String> list = new ArrayList<String>();
		StringBuilder imgurl = new StringBuilder();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				list = saveFile(req, file, list, id);
			}
			//json数组把，转换为&
			ao.setHouseimg(JSON.toJSONString(list).replace(",", "&"));
			HouseEntity e = service.addHouse(ao);
			return JSONResponse.ok(e);
		}else {
			throw new WxgzhException("请上传房间图片！");
		}

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
		service.delHouse(id, savePath);
		return JSONResponse.ok();
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
	 * 修改房间信息
	 * @param req
	 * @param resp
	 * @param ao 楼盘ao
	 * @param files 图片文件
	 * @param imgIds 修改的图片id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/mod")
	@ResponseBody
	public JSONResponse modBuilding(HttpServletRequest req, HttpServletResponse resp, HouseAo ao, @RequestParam(value="photo") MultipartFile[] files, @RequestParam(value="imgId")List<String> imgIds) throws Exception{
		HouseEntity e = service.modHouse(ao, savePath, files, imgIds);
		return JSONResponse.ok(e);
	}


	/**
	 * 通过楼盘主键，查询该楼盘下面所有的房间
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/que")
	@ResponseBody
	public JSONResponse queryHousesByB(String id) throws Exception{
		List<HouseEntity> entities = service.query(id);
		return JSONResponse.ok(entities);
	}

}
