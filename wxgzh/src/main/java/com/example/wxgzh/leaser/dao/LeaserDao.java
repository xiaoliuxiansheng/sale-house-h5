package com.example.wxgzh.leaser.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.BuildingEntity;
import com.example.wxgzh.entity.LeaserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaserDao extends TemplateDao<LeaserEntity, String> {

    void modify(LeaserEntity entity)throws Exception;
    List<LeaserEntity> selectByKey(String key) throws Exception;
}
