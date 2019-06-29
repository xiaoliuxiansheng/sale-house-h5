package com.example.wxgzh.manager.service;


import com.example.wxgzh.entity.ManagerEntity;

public interface ManagerService {

	public ManagerEntity addManager(ManagerEntity entity) throws Exception;

	public void delManager(String id) throws Exception;

	public ManagerEntity login(String account,String password)throws Exception;
}