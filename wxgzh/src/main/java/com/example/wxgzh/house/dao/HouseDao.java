package com.example.wxgzh.house.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.HouseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseDao extends TemplateDao<HouseEntity, String> {

    void modify(HouseEntity entity)throws Exception;

    List<HouseEntity> query(String id)throws Exception;

}
