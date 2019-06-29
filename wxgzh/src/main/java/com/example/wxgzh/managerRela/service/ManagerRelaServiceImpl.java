package com.example.wxgzh.managerRela.service;
/**
 *商品信息
 */

import com.example.wxgzh.managerRela.ao.ManagerRelaAO;
import com.example.wxgzh.managerRela.dao.ManagerRelaDao;
import com.example.wxgzh.entity.ManagerEntity;
import org.springframework.stereotype.Service;

@Service
public class ManagerRelaServiceImpl implements ManagerRelaService {
	//创建一个dao层对象
	final ManagerRelaDao dao;
	public ManagerRelaServiceImpl(ManagerRelaDao dao) {
		this.dao = dao;
	}

	@Override
	public ManagerEntity addManager(ManagerRelaAO ao) throws Exception {
		return null;
	}

	@Override
	public void delManager(String id) throws Exception {

	}

	@Override
	public ManagerEntity modManager(String id, ManagerRelaAO ao) throws Exception {
		return null;
	}

	@Override
	public ManagerEntity query(String id) throws Exception {
		return null;
	}
}
