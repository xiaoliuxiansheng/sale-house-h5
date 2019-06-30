package com.example.wxgzh.building.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.BuildingEntity;
import com.example.wxgzh.entity.LeaserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingDao extends TemplateDao<BuildingEntity, String> {

    void modify(BuildingEntity entity)throws Exception;
}
