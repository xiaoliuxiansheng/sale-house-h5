package com.example.wxgzh.leaser.service;
/**
 *商品信息
 */

import com.example.wxgzh.common.util.MD5;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.leaser.ao.LeaserAO;
import com.example.wxgzh.leaser.dao.LeaserDao;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LeaserServiceImpl implements LeaserService {
	//创建一个dao层对象
	final LeaserDao dao;
	public LeaserServiceImpl(LeaserDao dao) {
		this.dao = dao;
	}


	@Override
	public LeaserEntity addLeaser(LeaserAO ao) throws Exception {
		String id = UUID.random32();
		String name = ao.getName();
		String phone = ao.getPhone();
		String password = MD5.encode(ao.getPassword());
		LeaserEntity e = new LeaserEntity();
		e.setName(name);
		e.setPassword(password);
		e.setPhone(phone);
		e.setPkLeaser(id);
		dao.insert(e);
		return e;
	}

	@Override
	public void delLeaser(String id) throws Exception {

	}

	@Override
	public LeaserEntity modLeaser(String id, LeaserAO ao) throws Exception {
		return null;
	}

	@Override
	public LeaserEntity query(String id) throws Exception {
		return null;
	}
}
