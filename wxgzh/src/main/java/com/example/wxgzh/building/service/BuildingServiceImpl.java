package com.example.wxgzh.building.service;
/**
 * 楼盘信息
 */

import com.example.wxgzh.building.dao.BuildingDao;
import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.CoordinatesUtil;
import com.example.wxgzh.common.util.PictureUtil;
import com.example.wxgzh.entity.BuildingEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        if (b != null) {
            dao.delete(id);
            String str = path + "/" + id;
            //删除图片
            PictureUtil.deleteDir(str);
        }

    }

    @Override
    public BuildingEntity modBuilding(BuildingEntity entity, String path, MultipartFile[] files, List<String> imgIds) throws Exception {
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
        String buildingimg = entity.getBuildingimg();
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
//        String buildingimg = old.getBuildingimg()
//        //是否修改图片
//        if(imgIds.size() > 0) {
//            for (String img: imgIds) {
//                String id =
//            }
//        }



        if (!needUpdateMap.isEmpty()) {
            dao.update(id, needUpdateMap);
        }

        return old;
    }

    @Override
    public BuildingEntity query(String id) throws Exception {
        return null;
    }
}
