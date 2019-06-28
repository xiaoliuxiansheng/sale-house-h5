package com.example.wxgzh.example.support.service;
/**
 *商品信息
 */
import com.example.wxgzh.common.dto.QueryResult;
		import com.example.wxgzh.entity.CommodityEntity;
import com.example.wxgzh.example.ao.CommodityAO;
		import com.example.wxgzh.example.service.CommodityService;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl implements CommodityService {
	//创建一个dao层对象
//	final CommodityDao dao;
//	public CommodityServiceImpl(CommodityDao dao) {
//		this.dao = dao;
//	}

	@Override
	public CommodityEntity addCommodity(CommodityAO ao) throws Exception {
		return null;
	}

	@Override
	public void delCommodity(String id) throws Exception {

	}

	@Override
	public CommodityEntity modCommodity(String id, CommodityAO ao) throws Exception {
		return null;
	}

	@Override
	public CommodityEntity query(String id) throws Exception {
		return null;
	}

	@Override
	public QueryResult<CommodityEntity> queryAll(String key, String pageNo, String pageSize) throws Exception {
		return null;
	}

	@Override
	public QueryResult<CommodityEntity> queryAllTo(String key, String pageNo, String pageSize) throws Exception {
		return null;
	}
	/**
	 * 新增商品信息
	 */

	
	
}
