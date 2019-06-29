package com.example.wxgzh.leaser.service;
/**
 *商品信息
 */

import com.example.wxgzh.common.exeption.WxgzhException;
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
		entity.setPk_leaser(UUID.random32());
		entity.setPassword(MD5.encode(entity.getPassword()));
		dao.insert(entity);
		return entity;
	}

	@Override
	public void delLeaser(String id) throws Exception {

		dao.delete(id);

	}

	@Override
	public LeaserEntity modLeaser(LeaserEntity entity) throws Exception {

		//1.先查询该招商经理是否存在
		int count= dao.exists("pk_leaser",entity.getPk_leaser());
		//2.如果不存在，抛出；如果存在，则修改
		if(count<=0){
			throw new WxgzhException("该招商经理不存在或已被删除！");
		}else {
			dao.modify(entity);

			return entity;
		}
	}

	@Override
	public LeaserEntity query(String id) throws Exception {
		return null;
	}
}
