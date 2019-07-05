package com.example.wxgzh.building.service;
/**
 * 楼盘信息
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.wxgzh.building.dao.BuildingDao;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.CoordinatesUtil;
import com.example.wxgzh.common.util.PictureUtil;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.BuildingEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.wxgzh.common.util.AssertUtil.$;

@Service
public class BuildingServiceImpl implements BuildingService {
    //创建一个dao层对象
    final BuildingDao dao;

    public BuildingServiceImpl(BuildingDao dao) {
        this.dao = dao;
    }

    @Override
    public BuildingEntity addBuilding(BuildingEntity entity) throws Exception {
        //获取楼盘名称
        String name = $("楼盘名称不能为空", entity.getName());
        //获取城市名称
        String city = entity.getCity();
        //获取区域名称
        String region = entity.getRegion();
        //获取详细地址
        String address = entity.getDaddress();
        Map<String, Double> map = CoordinatesUtil.getLngAndLat($("详细地址不能为空", address));
        entity.setLon(map.get("lon"));
        entity.setLat(map.get("lat"));
        dao.insert(entity);
        return entity;
    }

    @Override
    public void delBuilding(String id, String path) throws Exception {
        BuildingEntity b = dao.selectById(id);
        if(b == null) {
            throw new WxgzhException("楼盘不存在或已被删除！");
        }
        dao.delete(id);
        String str = path + "/" + id;
        //删除图片
        PictureUtil.deleteDir(str);
    }

    @Override
    public BuildingEntity modBuilding(BuildingEntity entity, String path, MultipartFile[] files, List<String> imgs) throws Exception {
        //验证参数
        if (entity == null) {
            throw new WxgzhException("非法操作！");
        }
        String id = $("id不能为空！", entity.getPkBuiliding());

        String name = entity.getName();
        String city = entity.getCity();
        String region = entity.getRegion();
        String address = entity.getAddress();
        String daddress = entity.getDaddress();
        String managecost = entity.getManagecost();
        String floors = entity.getFloors();
        String height = entity.getHeight();
        String parkspace = entity.getParkspace();
        String elevatorbrand = entity.getElevatorbrand();
        String elevatorcount = entity.getElevatorcount();
        String area = entity.getArea();
        String airconditioning = entity.getAirconditioning();
        String developer = entity.getDeveloper();
        String company = entity.getCompany();
        String introduce = entity.getIntroduce();
        String typeimg = entity.getTypeimg();
        BuildingEntity old = dao.selectById(id);
        if (old == null) {
            throw new WxgzhException("修改楼盘不存在！");
        }
        Map<String, Object> needUpdateMap = new HashMap<>();
        if (name != null) {
            if (!name.equals(old.getName())) {
                needUpdateMap.put("name", name);
                old.setName(name);
            }
        }
        if (city != null) {
            if (!city.equals(old.getCity())) {
                needUpdateMap.put("city", city);
                old.setCity(city);
            }
        }
        if (region != null) {
            if (!region.equals(old.getRegion())) {
                needUpdateMap.put("region", region);
                old.setRegion(region);
            }
        }
        if (address != null) {
            if (!address.equals(old.getAddress())) {
                needUpdateMap.put("address", address);
                old.setAddress(address);
            }
        }
        if (daddress != null) {
            if (!daddress.equals(old.getDaddress())) {
                needUpdateMap.put("daddress", daddress);
                Map<String, Double> m = CoordinatesUtil.getLngAndLat($("详细地址不能为空", daddress));
                entity.setLon(m.get("lon"));
                entity.setLat(m.get("lat"));
                needUpdateMap.put("lon", m.get("lon"));
                needUpdateMap.put("lat", m.get("lat"));
                old.setDaddress(daddress);
            }
        }if (managecost != null) {
            if (!managecost.equals(old.getManagecost())) {
                needUpdateMap.put("managecost", managecost);
                old.setManagecost(managecost);
            }
        }if (floors != null) {
            if (!floors.equals(old.getFloors())) {
                needUpdateMap.put("floors", floors);
                old.setFloors(floors);
            }
        }if (height != null) {
            if (!height.equals(old.getHeight())) {
                needUpdateMap.put("height", height);
                old.setHeight(height);
            }
        }if (parkspace != null) {
            if (!parkspace.equals(old.getParkspace())) {
                needUpdateMap.put("parkspace", parkspace);
                old.setParkspace(parkspace);
            }
        }if (elevatorbrand != null) {
            if (!elevatorbrand.equals(old.getElevatorbrand())) {
                needUpdateMap.put("elevatorbrand", elevatorbrand);
                old.setElevatorbrand(elevatorbrand);
            }
        }if (elevatorcount != null) {
            if (!elevatorcount.equals(old.getElevatorcount())) {
                needUpdateMap.put("elevatorcount", elevatorcount);
                old.setElevatorcount(elevatorcount);
            }
        }if (area != null) {
            if (!area.equals(old.getArea())) {
                needUpdateMap.put("area", area);
                old.setArea(area);
            }
        }if (airconditioning != null) {
            if (!airconditioning.equals(old.getAirconditioning())) {
                needUpdateMap.put("airconditioning", airconditioning);
                old.setAirconditioning(airconditioning);
            }
        }if (developer != null) {
            if (!developer.equals(old.getDeveloper())) {
                needUpdateMap.put("developer", developer);
                old.setDeveloper(developer);
            }
        }if (company != null) {
            if (!company.equals(old.getCompany())) {
                needUpdateMap.put("company", company);
                old.setCompany(company);
            }
        }if (introduce != null) {
            if (!introduce.equals(old.getIntroduce())) {
                needUpdateMap.put("introduce", introduce);
                old.setIntroduce(introduce);
            }
        }if (typeimg != null) {
            if (!typeimg.equals(old.getTypeimg())) {
                needUpdateMap.put("typeimg", typeimg);
                old.setTypeimg(typeimg);
            }
        }
        String buildingimgOld = old.getBuildingimg();
        List<String> list = new ArrayList<>();
        if(buildingimgOld.length()>0) {
            buildingimgOld = buildingimgOld.replaceAll("&",",");
             list = JSONArray.parseArray(buildingimgOld, String.class);
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
        String buildingimg = JSON.toJSONString(list).replace(",", "&");
        if (buildingimg != null) {
            if (!buildingimg.equals(old.getBuildingimg())) {
                needUpdateMap.put("buildingimg", buildingimg);
                old.setBuildingimg(buildingimg);
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
    public BuildingEntity query(String id) throws Exception {
        return null;
    }

    @Override
    public List<String> queryRegion() throws Exception {
        List<String> result = dao.selectRegion();
        return result;
    }

    @Override
    public BuildingEntity queryById(String id) throws Exception {
        BuildingEntity b = dao.selectById(id);
        return b;
    }

    @Override
    public QueryResult<BuildingEntity> queryAll(String key, String pageNo, String pageSize) throws Exception {
        if(key != null) {
            key = "%"+key+"%";
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
        List<BuildingEntity> e = dao.selectByKey(key);
        PageInfo<BuildingEntity> pageinfo= new PageInfo<>(e);
        List<BuildingEntity> rows = new ArrayList<>();
        for (BuildingEntity buildingEntity : pageinfo.getList()) {
            rows.add(buildingEntity);
        }
        QueryResult<BuildingEntity> m = new QueryResult<>();
        m.setPageNo(no);
        m.setPageSize(size);
        m.setTotalRows((int)pageinfo.getTotal());
        m.setRows(rows);

        return m;
    }
}
