package com.example.wxgzh.leaser.service;


import com.example.wxgzh.entity.LeaserEntity;

public interface LeaserService {
	public LeaserEntity addLeaser(LeaserEntity entity) throws Exception;
	public void delLeaser(String id) throws Exception;
	public LeaserEntity modLeaser(String id, LeaserEntity entity) throws Exception;
	public LeaserEntity query(String id) throws Exception;
}