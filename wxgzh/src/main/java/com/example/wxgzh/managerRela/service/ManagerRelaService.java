package com.example.wxgzh.managerRela.service;


import com.example.wxgzh.managerRela.ao.ManagerRelaAO;
import com.example.wxgzh.entity.ManagerEntity;

public interface ManagerRelaService {
	public ManagerEntity addManager(ManagerRelaAO ao) throws Exception;
	public void delManager(String id) throws Exception;
	public ManagerEntity modManager(String id, ManagerRelaAO ao) throws Exception;
	public ManagerEntity query(String id) throws Exception;
}