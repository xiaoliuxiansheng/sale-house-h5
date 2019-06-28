package com.example.wxgzh.example.dao;

import java.util.List;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.CommodityEntity;
import org.apache.ibatis.annotations.Param;


public interface CommodityDao extends TemplateDao<CommodityEntity, String> {

	List<CommodityEntity> selectByKey(@Param("key") String key) throws Exception;
	int selectMaxId() throws Exception;
	List<CommodityEntity> selectByKeyTo(@Param("key") String key) throws Exception;
}
