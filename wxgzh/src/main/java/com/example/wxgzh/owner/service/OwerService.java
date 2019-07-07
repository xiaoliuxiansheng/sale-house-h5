package com.example.wxgzh.owner.service;


import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.entity.OwerEntity;
import com.example.wxgzh.owner.dto.OwerAo;

public interface OwerService {
	public OwerEntity addOwer(OwerAo ao) throws Exception;

	public void delOwer(String id) throws Exception;

	public QueryResult<OwerEntity> selectByKey(String pageNo, String pageSize, String rors)throws Exception;
}