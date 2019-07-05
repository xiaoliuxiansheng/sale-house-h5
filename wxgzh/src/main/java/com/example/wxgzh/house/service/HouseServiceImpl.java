package com.example.wxgzh.house.service;
/**
 * 楼盘信息
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.CoordinatesUtil;
import com.example.wxgzh.common.util.PictureUtil;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.BuildingEntity;
import com.example.wxgzh.entity.HouseEntity;
import com.example.wxgzh.house.dao.HouseDao;
import com.example.wxgzh.house.dto.HouseAo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.wxgzh.common.util.AssertUtil.$;

@Service
public class HouseServiceImpl implements HouseService {
    //创建一个dao层对象
    final HouseDao dao;

    public HouseServiceImpl(HouseDao dao) {
        this.dao = dao;
    }

    @Override
    public HouseEntity addHouse(HouseAo ao) throws Exception {
        HouseEntity entity = new HouseEntity();
        String pk_house = ao.getPk_house();//房间信息主键
        String buildingname = ao.getBuildingname();//楼盘名称
        String floor = ao.getFloor();//楼层
        int area = Integer.parseInt(ao.getArea(),10);//房间面积(m3)
        int price = Integer.parseInt(ao.getPrice(),10);//房间单价(元/m2·月)
        int rent = Integer.parseInt(ao.getRent(),10);//租金(元/月)
        String trimstyle = ao.getTrimstyle();//装修风格
        String houseimg = ao.getHouseimg();//房间照片地址
        String describe = ao.getDescribe();//房源描述
        String name = ao.getName();//联系人
        String phone = ao.getPhone();//联系电话
        String rors = ao.getRors();//出租(rent)或者出售(sell);1为出租，2为出售
        String isexist = ao.getIsexist();//是否存在(如果出售或出租，则为N)

        entity.setPk_house(pk_house);
        entity.setBuildingname(buildingname);
        entity.setFloor(floor);
        entity.setArea(area);
        entity.setPrice(price);
        entity.setRent(rent);
        entity.setTrimstyle(trimstyle);
        entity.setHouseimg(houseimg);
        entity.setDescribe(describe);
        entity.setName(name);
        entity.setPhone(phone);
        entity.setRors(rors);
        entity.setIsexist(isexist);

        dao.insert(entity);
        return entity;
    }

    @Override
    public void delHouse(String id, String path) throws Exception {
        HouseEntity entity = dao.selectById(id);
        if (entity != null) {
            dao.delete(id);
            String str = path + "/" + id;
            //删除图片
            PictureUtil.deleteDir(str);
        }

    }

    @Override
    public HouseEntity modHouse(HouseAo ao, String path, MultipartFile[] files, List<String> imgs) throws Exception {

        if (ao == null) {
            throw new WxgzhException("非法操作！");
        }
        String id = $("id不能为空！", ao.getPk_house());

        String buildingname = ao.getBuildingname();//楼盘名称
        String floor = ao.getFloor();//楼层
        int area = Integer.parseInt(ao.getArea(),10);//房间面积(m3)
        int price = Integer.parseInt(ao.getPrice(),10);//房间单价(元/m2·月)
        int rent = Integer.parseInt(ao.getRent(),10);//租金(元/月)
        String trimstyle = ao.getTrimstyle();//装修风格
        //String houseimg = ao.getHouseimg();//房间照片地址
        String describe = ao.getDescribe();//房源描述
        String name = ao.getName();//联系人
        String phone = ao.getPhone();//联系电话
        String rors = ao.getRors();//出租(rent)或者出售(sell);1为出租，2为出售
        String isexist = ao.getIsexist();//是否存在(如果出售或出租，则为N)

        HouseEntity old = dao.selectById(id);
        if (old == null) {
            throw new WxgzhException("修改房间不存在！");
        }
        Map<String, Object> needUpdateMap = new HashMap<>();
        if (buildingname != null) {
            if (!buildingname.equals(old.getBuildingname())) {
                needUpdateMap.put("buildingname", buildingname);
                old.setBuildingname(buildingname);
            }
        }
        if (floor != null) {
            if (!floor.equals(old.getFloor())) {
                needUpdateMap.put("floor", floor);
                old.setFloor(floor);
            }
        }
        if (trimstyle != null) {
            if (!trimstyle.equals(old.getTrimstyle())) {
                needUpdateMap.put("trimstyle", trimstyle);
                old.setTrimstyle(trimstyle);
            }
        }
        if (describe != null) {
            if (!describe.equals(old.getDescribe())) {
                needUpdateMap.put("describe", describe);
                old.setDescribe(describe);
            }
        }

        if (name != null) {
            if (!name.equals(old.getName())) {
                needUpdateMap.put("name", name);
                old.setName(name);
            }
        }
        if (phone != null) {
            if (!phone.equals(old.getPhone())) {
                needUpdateMap.put("phone", phone);
                old.setPhone(phone);
            }
        }
        if (rors != null) {
            if (!rors.equals(old.getRors())) {
                needUpdateMap.put("rors", rors);
                old.setRors(rors);
            }
        }
        if (isexist != null) {
            if (!isexist.equals(old.getIsexist())) {
                needUpdateMap.put("isexist", isexist);
                old.setIsexist(isexist);
            }
        }
        if (rent!=old.getRent()) {
            needUpdateMap.put("rent", rent);
            old.setRent(rent);
        }
        if (price!=old.getPrice()) {
            needUpdateMap.put("price", price);
            old.setPrice(price);
        }
        if (area!=old.getArea()) {
            needUpdateMap.put("area", area);
            old.setArea(area);
        }

        String houseimgOld = old.getHouseimg();
        List<String> list = new ArrayList<>();
        if(houseimgOld.length()>0) {
            houseimgOld = houseimgOld.replaceAll("&",",");
            list = JSONArray.parseArray(houseimgOld, String.class);
            //是否修改图片
            if(imgs.size() > 0) {
                for (String img: imgs) {
                    list.remove(path.substring(path.indexOf(":") + 1) + "/" + id + "/" + img);
                }
            }
        }
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 判断文件是否为空
                if (!file.isEmpty()) {
                    String uuid = UUID.random32();
                    String unit = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                    String filePath = path + "/" + id + "/" + uuid + unit;
                    list.add(path.substring(path.indexOf(":") + 1) + "/" + id + "/" + uuid + unit);
                    File saveDir = new File(filePath);
                    if (!saveDir.getParentFile().exists()) {
                        saveDir.getParentFile().mkdirs();
                    }
                    // 转存文件
                    file.transferTo(saveDir);
                }
            }
        }
        //json数组把，转换为&
        String houseimg = JSON.toJSONString(list).replace(",", "&");
        if (houseimg != null) {
            if (!houseimg.equals(old.getHouseimg())) {
                needUpdateMap.put("houseimg", houseimg);
                old.setHouseimg(houseimg);
            }
        }
        if (!needUpdateMap.isEmpty()) {
            dao.update(id, needUpdateMap);
        }
        //删除本地文件
        for (String img: imgs) {
            //获取文件路径
            String filePath = path + "/" + id + "/" + img;
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
        }
        return old;

    }

    @Override
    public HouseEntity query(String id) throws Exception {
        return null;
    }
}
