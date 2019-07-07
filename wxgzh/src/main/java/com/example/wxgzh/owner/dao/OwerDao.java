package com.example.wxgzh.owner.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.HouseEntity;
import com.example.wxgzh.entity.OwerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwerDao extends TemplateDao<OwerEntity, String> {

    void delete(String id)throws Exception;

    List<OwerEntity> query(String rors)throws Exception;

}
