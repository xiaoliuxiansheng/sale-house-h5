package com.example.wxgzh.manager.service;
/**
 *商品信息
 */

import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.MD5;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.ManagerEntity;
import com.example.wxgzh.manager.dao.ManagerDao;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
	//创建一个dao层对象
	final ManagerDao dao;
	public ManagerServiceImpl(ManagerDao dao) {
		this.dao = dao;
	}


	/**
	 * 新增管理员账号业务
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public ManagerEntity addManager(ManagerEntity entity) throws Exception {

		//1.拿出新增的账号
		String account = entity.getAccount();

		//2.通过账号查询管理员实体，验证账号是否存在
		ManagerEntity managerEntity = dao.selectByAccount(account);

		if (managerEntity!=null){
			throw new WxgzhException("该账号已被使用，请更换账号再试");
		}else{
			ManagerEntity ManagerEntity = new ManagerEntity();
			ManagerEntity.setAccount(entity.getAccount());
			ManagerEntity.setPassword(MD5.encode(entity.getPassword()));
			ManagerEntity.setPk_manager(UUID.random32());
			dao.insert(ManagerEntity);
			return  ManagerEntity;
		}
	}


	/**
	 * 删除管理员账号业务
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void delManager(String id) throws Exception {

		dao.delete(id);

	}

	/**
	 * 登录业务
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Override
	public ManagerEntity login(String account, String password) throws Exception {

		ManagerEntity entity = dao.selectByAccount(account);

		if (entity == null){
			throw new WxgzhException("该账号不存在或已被删除！");
		}else if (password==null||password.length()==0){
			throw new WxgzhException("登录密码不能为空！");
		}else if (!entity.getPassword().equals(MD5.encode(password))){
			throw new WxgzhException("密码错误，请重试！");
		}else{
			return entity;
		}
	}
}
