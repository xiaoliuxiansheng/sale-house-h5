package com.example.wxgzh.leaser.service;
/**
 *商品信息
 */

import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.MD5;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.entity.ManagerRelaEntity;
import com.example.wxgzh.leaser.dao.LeaserDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.wxgzh.common.util.AssertUtil.$;

@Service
public class LeaserServiceImpl implements LeaserService {
	//创建一个dao层对象
	final LeaserDao dao;
	public LeaserServiceImpl(LeaserDao dao) {
		this.dao = dao;
	}

	@Value("${all.photo-save-dir}")
	private String savePath;

	@Override
	public LeaserEntity addLeaser(LeaserEntity entity, MultipartFile file) throws Exception {
		String id = UUID.random32();
		if(file.isEmpty()) {
			throw new WxgzhException("头像文件不能为空");
		}
		String unit = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
		String filePath = savePath + "/" + "avatar" + "/" + id + unit;
		File saveDir = new File(filePath);
		if (!saveDir.getParentFile().exists())
			saveDir.getParentFile().mkdirs();
		// 转存文件
		file.transferTo(saveDir);

		entity.setAvatar("/" + "avatar" + "/" + id + unit);

		entity.setPk_leaser(UUID.random32());
		entity.setPassword(MD5.encode(entity.getPassword()));

		dao.insert(entity);
		return entity;
	}

	@Override
	public void delLeaser(String id) throws Exception {
		id = $(id, "招商经理id不能为空");
		LeaserEntity old = dao.selectById(id);
		if(old == null) {
			throw new WxgzhException("该招商经理不存在或已被删除！");
		}
		dao.delete(id);
		String filePath = savePath + old.getAvatar();
		//删除本地头像
		File oldfile = new File(filePath);
		if(oldfile.exists()){
			oldfile.delete();
		}
	}

	@Override
	public LeaserEntity modLeaser(LeaserEntity entity, MultipartFile file) throws Exception {
		String id = $(entity.getPk_leaser(), "招商经理id不能为空");
		String name = entity.getName();
		String phone = entity.getPhone();
		String password = entity.getPassword();
		LeaserEntity old = dao.selectById(id);
		if(name != null) {
			if(name.equals(old.getName())) {
				old.setName(name);
			}
		}
		if(phone != null) {
			if(phone.equals(old.getPhone())) {
				old.setPhone(phone);
			}
		}
		if(password != null) {
			if(MD5.encode(password).equals(old.getPassword())) {
				old.setPassword(MD5.encode(password));
			}
		}
		if(old == null) {
			throw new WxgzhException("该招商经理不存在或已被删除！");
		}
		dao.modify(entity);
		//判断是否修改头像
		if(!file.isEmpty()) {
			String filePath = savePath + old.getAvatar();
			//删除本地头像
			File oldfile = new File(filePath);
			if(oldfile.exists()){
				oldfile.delete();
			}
			String unit = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
			String newPath = savePath + "/" + "avatar" + "/" + id + unit;
			File saveDir = new File(newPath);
			if (!saveDir.getParentFile().exists())
				saveDir.getParentFile().mkdirs();
			// 转存文件
			file.transferTo(saveDir);
			old.setAvatar("/" + "avatar" + "/" + id + unit);
		}
		return old;
	}

	@Override
	public LeaserEntity query(String id) throws Exception {
		id = $(id, "招商经理id不能为空");
		LeaserEntity e = dao.selectById(id);
		return e;
	}

	@Override
	public QueryResult<LeaserEntity> queryAll(String name, String pageNo, String pageSize) throws Exception {
		if(name != null) {
			name = "%"+name+"%";
		}
		int no = 1;
		if(pageNo!=null) {
			no = Integer.parseInt(pageNo);
		}
		int size = 10;
		if(pageSize != null) {
			size = Integer.parseInt(pageSize);
		}

		PageHelper.startPage(no, size);
		List<LeaserEntity> e = dao.selectByKey(name);
		PageInfo<LeaserEntity> pageinfo= new PageInfo<>(e);
		List<LeaserEntity> rows = new ArrayList<>();
		for (LeaserEntity leaserEntity : pageinfo.getList()) {
			rows.add(leaserEntity);
		}
		QueryResult<LeaserEntity> m = new QueryResult<>();
		m.setPageNo(no);
		m.setPageSize(size);
		m.setTotalRows((int)pageinfo.getTotal());
		m.setRows(rows);
		return m;
	}

	@Override
	public LeaserEntity login(String phone, String password) throws Exception {
		LeaserEntity entity = dao.select("phone", $("电话号码不能为空", phone));
		password = $("密码不能为空", password);
		if (entity == null){
			throw new WxgzhException("该招商经理不存在或已被删除！");
		}
		if (!entity.getPassword().equals(MD5.encode(password))){
			throw new WxgzhException("密码错误，请重试！");
		}else{
			return entity;
		}
	}

	@Override
	public void allocate(ManagerRelaEntity entity) throws Exception {

		String pk_leaser_rela = UUID.random32();

		entity.setPk_leaser_rela(pk_leaser_rela);

		dao.allocate(entity);

	}

}
