package com.example.wxgzh.manager.service;
/**
 *商品信息
 */

import com.example.wxgzh.entity.ManagerEntity;
import com.example.wxgzh.manager.ao.ManagerAO;
import com.example.wxgzh.manager.dao.ManagerDao;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
	//创建一个dao层对象
	final ManagerDao dao;
	public ManagerServiceImpl(ManagerDao dao) {
		this.dao = dao;
	}

	@Override
	public ManagerEntity addManager(ManagerAO ao) throws Exception {
		return null;
	}

	@Override
	public void delManager(String id) throws Exception {

	}

	@Override
	public ManagerEntity modManager(String id, ManagerAO ao) throws Exception {
		return null;
	}

	@Override
	public ManagerEntity query(String id) throws Exception {
		return null;
	}
}
