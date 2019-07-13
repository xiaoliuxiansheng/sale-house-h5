package com.example.wxgzh.building.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.BuiHouEntity;
import com.example.wxgzh.entity.BuildingEntity;
import com.example.wxgzh.entity.LeaserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingDao extends TemplateDao<BuildingEntity, String> {

    List<String> selectRegion () throws Exception;
    List<BuildingEntity> selectByKey(String name, String region) throws Exception;

    List<BuildingEntity> queryByNames()throws  Exception;

    BuiHouEntity selectDetail(String id)throws  Exception;
}
