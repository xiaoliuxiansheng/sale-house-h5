package com.example.wxgzh.leaser.service;
/**
 *商品信息
 */

import com.example.wxgzh.common.util.MD5;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.leaser.dao.LeaserDao;
import org.springframework.stereotype.Service;

@Service
public class LeaserServiceImpl implements LeaserService {
	//创建一个dao层对象
	final LeaserDao dao;
	public LeaserServiceImpl(LeaserDao dao) {
		this.dao = dao;
	}


	@Override
	public LeaserEntity addLeaser(LeaserEntity entity) throws Exception {
		String id = UUID.random32();
		String name = entity.getName();
		String phone = entity.getPhone();
		String password = MD5.encode(entity.getPassword());
		LeaserEntity e = new LeaserEntity();
		e.setName(name);
		e.setPassword(password);
		e.setPhone(phone);
		e.setPk_leaser(id);
		dao.insert(e);
		return e;
	}

	@Override
	public void delLeaser(String id) throws Exception {

		dao.delete(id);

	}

	@Override
	public LeaserEntity modLeaser(String id, LeaserEntity entity) throws Exception {
		return null;
	}

	@Override
	public LeaserEntity query(String id) throws Exception {
		return null;
	}
}
