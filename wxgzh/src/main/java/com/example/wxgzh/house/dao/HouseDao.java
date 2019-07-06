package com.example.wxgzh.house.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.HouseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDao extends TemplateDao<HouseEntity, String> {

    void modify(HouseEntity entity)throws Exception;
}
