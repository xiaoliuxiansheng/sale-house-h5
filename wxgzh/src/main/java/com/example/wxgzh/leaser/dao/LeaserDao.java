package com.example.wxgzh.leaser.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.LeaserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaserDao extends TemplateDao<LeaserEntity, String> {
}
