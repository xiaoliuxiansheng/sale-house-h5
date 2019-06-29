package com.example.wxgzh.manager.service;


import com.example.wxgzh.entity.ManagerEntity;
import com.example.wxgzh.manager.ao.ManagerAO;

public interface ManagerService {
	public ManagerEntity addManager(ManagerAO ao) throws Exception;
	public void delManager(String id) throws Exception;
	public ManagerEntity modManager(String id, ManagerAO ao) throws Exception;
	public ManagerEntity query(String id) throws Exception;
}