package com.example.wxgzh.leaser.service;


import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.entity.ManagerRelaEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LeaserService {
	public LeaserEntity addLeaser(LeaserEntity entity, MultipartFile file) throws Exception;

	public void delLeaser(String id) throws Exception;

	public LeaserEntity modLeaser(LeaserEntity entity, MultipartFile file) throws Exception;

	public LeaserEntity query(String id) throws Exception;

	public QueryResult<LeaserEntity> queryAll(String url,String name, String pageNo, String pageSize) throws Exception;

	public LeaserEntity login(String phone,String password)throws Exception;

	public void allocate(ManagerRelaEntity entity)throws Exception;

	public List<LeaserEntity> queryLeasers()throws Exception;
}