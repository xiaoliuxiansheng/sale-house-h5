package com.example.wxgzh.manager.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.ManagerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDao extends TemplateDao<ManagerEntity, String> {

}
