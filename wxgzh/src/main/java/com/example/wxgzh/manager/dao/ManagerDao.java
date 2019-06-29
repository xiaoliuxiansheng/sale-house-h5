package com.example.wxgzh.manager.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.ManagerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDao extends TemplateDao<ManagerEntity, String> {

    ManagerEntity selectByAccount(@Param("account") String account)throws Exception;

}
