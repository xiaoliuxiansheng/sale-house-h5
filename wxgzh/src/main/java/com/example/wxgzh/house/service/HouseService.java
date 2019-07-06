package com.example.wxgzh.house.service;


import com.example.wxgzh.entity.BuildingEntity;
import com.example.wxgzh.entity.HouseEntity;
import com.example.wxgzh.house.dto.HouseAo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HouseService {
	public HouseEntity addHouse(HouseAo ao) throws Exception;

	public void delHouse(String id, String path) throws Exception;

	public HouseEntity modHouse(HouseAo ao, String path, MultipartFile[] files, List<String> imgIds) throws Exception;

	public List<HouseEntity> query(String id) throws Exception;
}