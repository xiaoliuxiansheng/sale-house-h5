package com.example.wxgzh.house.controller;

import com.alibaba.fastjson.JSON;
import com.example.wxgzh.common.dto.JSONResponse;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.HouseEntity;
import com.example.wxgzh.entity.ManagerEntity;
import com.example.wxgzh.house.dto.HouseAo;
import com.example.wxgzh.house.service.HouseService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
	public JSONResponse queryHousesByB(HttpServletRequest req,String id) throws Exception{
		String ip = req.getServerName();

		int port = req.getServerPort();

		String url = "http://"+ip+":"+port;

		List<HouseEntity> entities = service.query(url,id);

		return JSONResponse.ok(entities);
	}

	@PostMapping("/list")
	@ResponseBody
	public JSONResponse queryHousesByKey(HttpServletRequest req,String name, String region, String price, String area, String pageNo, String pageSize, String rors) throws Exception{
		String ip = req.getServerName();
		String minprice = "";
		String maxprice = "";
		String minarea = "";
		String maxarea = "";
		String[] prices = new String[2];
		String[] ares = new String[2];
		if(price != null && !"".equals(price)){
			prices = price.split("-");
		}
		if(area != null && !"".equals(area)) {
			ares = area.split("-");
		}
		if(prices.length == 1) {
			minprice = prices[0];
		}else{
			minprice = prices[0];
			maxprice = prices[1];
		}
		if(ares.length == 1) {
			minarea = ares[0];
		}else {
			minarea = ares[0];
			maxarea = ares[1];
		}
		int port = req.getServerPort();

		String url = "http://"+ip+":"+port;
		QueryResult<HouseEntity> e = service.selectByKey(name, region, minprice, maxprice, minarea, maxarea, pageNo, pageSize, rors, url);
		return JSONResponse.ok(e);
	}

	/**
	 * oa系统查询
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/oalist")
	@ResponseBody
	public JSONResponse queryHousesOA(HttpServletRequest req,HttpServletResponse resp, String pageNo,String pageSize) throws Exception{

		String ip = req.getServerName();
		int port = req.getServerPort();
		String url = "http://"+ip+":"+port;

		HttpSession session = req.getSession(false);
		if (session == null) {
			throw new WxgzhException("该用户未登录或已超时！");
		}
		ManagerEntity e= (ManagerEntity)session.getAttribute("leaser");
		String id = "";
		if(e != null) {
			id = e.getPk_manager();
		}

		QueryResult<HouseEntity> entities = service.queryOA(id, pageNo, pageSize,url);

		return JSONResponse.ok(entities);
	}



	@GetMapping("/detail")
	@ResponseBody
	public JSONResponse queryHouseDetail(HttpServletRequest req,String id)throws Exception{

		String ip = req.getServerName();

		int port = req.getServerPort();

		String url = "http://"+ip+":"+port;

		HouseEntity e = service.queryDetail(url,id);

		return JSONResponse.ok(e);
	}
}
