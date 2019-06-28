package com.example.wxgzh.example.service;


import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.entity.CommodityEntity;
import com.example.wxgzh.example.ao.CommodityAO;

public interface CommodityService {
	public CommodityEntity addCommodity(CommodityAO ao) throws Exception;
	public void delCommodity(String id) throws Exception;
	public CommodityEntity modCommodity(String id, CommodityAO ao) throws Exception;
	public CommodityEntity query(String id) throws Exception;
	public QueryResult<CommodityEntity> queryAll(String key, String pageNo, String pageSize) throws Exception;
	public QueryResult<CommodityEntity> queryAllTo(String key, String pageNo, String pageSize) throws Exception;
}