package com.example.wxgzh.owner.service;
/**
 * 楼盘放盘
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.PictureUtil;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.BuildingEntity;
import com.example.wxgzh.entity.HouseEntity;
import com.example.wxgzh.entity.ManagerRelaEntity;
import com.example.wxgzh.entity.OwerEntity;
import com.example.wxgzh.house.dto.HouseAo;
import com.example.wxgzh.leaser.dao.LeaserDao;
import com.example.wxgzh.owner.dao.OwerDao;
import com.example.wxgzh.owner.dto.OwerAo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.wxgzh.common.util.AssertUtil.$;

@Service
public class OwerServiceImpl implements OwerService {
    //创建一个dao层对象
    final OwerDao dao;

    public OwerServiceImpl(OwerDao dao) {
        this.dao = dao;
    }

    @Override
    public OwerEntity addOwer(OwerAo ao) throws Exception {

        if (ao == null) {
            throw new WxgzhException("非法操作！");
        }

        OwerEntity enrity = new OwerEntity();

        String pk_ower = UUID.random32();
        String buildingname = ao.getBuildingname();//楼盘名称
        String floor = ao.getFloor();//楼层
        int area = Integer.parseInt(ao.getArea(),10);//房间面积(m3)
        int price = Integer.parseInt(ao.getPrice(),10);//房间单价(元/m2·月)
        String name = ao.getName();//联系人
        String phone = ao.getPhone();//联系电话
        String rors = ao.getRors();//出租还是出售1出租，2出售

        enrity.setPk_ower(pk_ower);
        enrity.setBuildingname(buildingname);
        enrity.setFloor(floor);
        enrity.setArea(area);
        enrity.setPrice(price);
        enrity.setName(name);
        enrity.setPhone(phone);
        enrity.setRors(rors);

        dao.insert(enrity);

        return enrity;
    }

    @Override
    public void delOwer(String id) throws Exception {

        OwerEntity entity = dao.selectById(id);

        if(entity == null) {
            throw new WxgzhException("放盘信息不存在或已被删除！");
        }
        dao.delete(id);
    }

    @Override
    public QueryResult<OwerEntity> selectByKey(String pageNo, String pageSize, String rors) throws Exception {

        int no = 1;
        if(pageNo!=null) {
            no = Integer.parseInt(pageNo);
        }
        int size = 10;
        if(pageSize != null) {
            size = Integer.parseInt(pageSize);
        }

        PageHelper.startPage(no, size);
        List<OwerEntity> e = dao.query(rors);
        PageInfo<OwerEntity> pageinfo= new PageInfo<>(e);
        List<OwerEntity> rows = new ArrayList<>();
        for (OwerEntity owerEntity : pageinfo.getList()) {
            rows.add(owerEntity);
        }
        QueryResult<OwerEntity> m = new QueryResult<>();
        m.setPageNo(no);
        m.setPageSize(size);
        m.setTotalRows((int)pageinfo.getTotal());
        m.setRows(rows);

        return m;
    }
}
