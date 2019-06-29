package com.example.wxgzh.leaser.service;


import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.leaser.ao.LeaserAO;

public interface LeaserService {
	public LeaserEntity addLeaser(LeaserAO ao) throws Exception;
	public void delLeaser(String id) throws Exception;
	public LeaserEntity modLeaser(String id, LeaserAO ao) throws Exception;
	public LeaserEntity query(String id) throws Exception;
}