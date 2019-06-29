package com.example.wxgzh.common.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface TemplateDao<T,ID> {
	
	void insert(T t)throws Exception;
	
	void update(@Param("id") ID id, @Param("map") Map<String, Object> map)throws Exception;
	
	T select(@Param("column") String uniqueColumn, @Param("value") Object value)throws Exception;
	
	int exists(@Param("column") String uniqueColumn, @Param("value") Object value)throws Exception;
	
	T selectById(ID id)throws Exception;
	void delete(@Param("id") ID id)throws Exception;
}

