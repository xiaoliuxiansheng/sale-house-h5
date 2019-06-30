package com.example.wxgzh.building.service;


import com.example.wxgzh.entity.BuildingEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BuildingService {
	public BuildingEntity addBuilding(BuildingEntity entity) throws Exception;

	public void delBuilding(String id, String path) throws Exception;

	public BuildingEntity modBuilding(BuildingEntity entity, String path, MultipartFile[] files, List<String> imgIds) throws Exception;

	public BuildingEntity query(String id) throws Exception;
}