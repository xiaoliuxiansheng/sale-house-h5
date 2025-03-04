package com.example.wxgzh.house.service;
/**
 * 楼盘信息
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.wxgzh.common.dto.QueryResult;
import com.example.wxgzh.common.exeption.WxgzhException;
import com.example.wxgzh.common.util.PictureUtil;
import com.example.wxgzh.common.util.UUID;
import com.example.wxgzh.entity.BuildingEntity;
import com.example.wxgzh.entity.HouseEntity;
import com.example.wxgzh.entity.LeaserEntity;
import com.example.wxgzh.entity.ManagerRelaEntity;
import com.example.wxgzh.house.dao.HouseDao;
import com.example.wxgzh.house.dto.HouseAo;
import com.example.wxgzh.leaser.dao.LeaserDao;
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
public class HouseServiceImpl implements HouseService {
    //创建一个dao层对象
    final HouseDao dao;

    //创建一个dao层对象
    final LeaserDao leaserDao;

    public HouseServiceImpl(HouseDao dao,LeaserDao leaserDao) {
        this.dao = dao;
        this.leaserDao = leaserDao;
    }

    @Override
    @Transactional
    public HouseEntity addHouse(HouseAo ao) throws Exception {

        HouseEntity entity = new HouseEntity();
        ManagerRelaEntity mentity = new ManagerRelaEntity();

        String pk_building = $("楼盘主键不能为空！",ao.getPk_building());//楼盘信息主键
        String pk_house = ao.getPk_house();//房间信息主键
        String buildingname = ao.getBuildingname();//楼盘名称
        String floor = ao.getFloor();//楼层
        int area = Integer.parseInt(ao.getArea(),10);//房间面积(m3)
        int price = Integer.parseInt(ao.getPrice(),10);//房间单价(元/m2·月)
        int rent = -1;//租金(元/月)设置租金初始值
        rent = area * price;
        String trimstyle = ao.getTrimstyle();//装修风格
        String houseimg = ao.getHouseimg();//房间照片地址
        String describe = ao.getDescribe();//房源描述
        String name = ao.getName();//联系人
        String phone = ao.getPhone();//联系电话
        String rors = ao.getRors();//出租(rent)或者出售(sell);1为出租，2为出售
        //String isexist = ao.getIsexist();//是否存在(如果出售或出租，则为N)
        String pk_leaser = ao.getPk_leaser();

        entity.setPk_building(pk_building);
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
        //entity.setIsexist(isexist);

        dao.insert(entity);

        String pk_leaser_rela = UUID.random32();
        mentity.setPk_leaser_rela(pk_leaser_rela);
        mentity.setPk_leaser(pk_leaser);
        mentity.setPk_house(pk_house);
        leaserDao.allocate(mentity);
        return entity;
    }

    @Override
    public void delHouse(String id, String path) throws Exception {
        HouseEntity entity = dao.selectById(id);

        if(entity == null) {
            throw new WxgzhException("楼盘不存在或已被删除！");
        }
        dao.delete(id);
        String str = path + "/" + id;
        //删除图片
        PictureUtil.deleteDir(str);

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
    public List<HouseEntity> query(String url,String id) throws Exception {

        List<HouseEntity> houseEntities =dao.query(id);

        if (!houseEntities.isEmpty()){
            for (HouseEntity e:houseEntities
                 ) {
                if(e.getHouseimg() != null) {
                    String img = e.getHouseimg().replaceAll("&",",");
                    List<String> lists = JSONArray.parseArray(img, String.class);
                    for(int i=0; i< lists.size(); i++) {
                        lists.set(i,url + lists.get(i));
                    }
                    e.setHouseimg(lists.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                }
                if (e.getAvatar()!=null){
                    e.setAvatar(url+e.getAvatar());
                }
            }
        }

        return houseEntities;
    }

    @Override
    public QueryResult<HouseEntity> selectByKey(String name, String region, String minprice, String maxprice, String minarea, String maxarea, String pageNo, String pageSize, String rors, String url) throws Exception {
        if(rors == null || "".equals(rors)) {
            rors = "1";
        }
        int no = 1;
        if(pageNo != null || "".equals(pageNo)) {
            no = Integer.parseInt(pageNo);
        }
        int size = 10;
        if(pageSize != null || "".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }

        PageHelper.startPage(no, size);
        int xarea = -1;
        int iarea = -1;
        int xprice = -1;
        int iprice = -1;
        try {
            if(maxarea!=null && maxarea.length()!=0){
                xarea = Integer.parseInt(maxarea);
            }
            if(minarea!=null && minarea.length()!=0){
                xarea = Integer.parseInt(minarea);
            }
            if(maxprice!=null && maxprice.length()!=0){
                xprice = Integer.parseInt(maxprice);
            }
            if(minprice!=null && minprice.length()!=0){
                iprice = Integer.parseInt(minprice);
            }
        }catch (WxgzhException e){
            throw new WxgzhException("请输入整数！");
        }
        if(name != null && !"".equals(name)) {
            name = "%" + name +"%";
        }

        List<HouseEntity> entities = dao.selectByKey(name,region,minprice,maxprice,minarea,maxarea, rors);
        PageInfo<HouseEntity> pageinfo= new PageInfo<>(entities);
        List<HouseEntity> rows = new ArrayList<>();
        for (HouseEntity houseEntity : pageinfo.getList()) {
            rows.add(houseEntity);
        }
        for (HouseEntity b: rows
        ) {
            String img = b.getHouseimg().replaceAll("&",",");
            List<String> lists = JSONArray.parseArray(img, String.class);
            for(int i=0; i< lists.size(); i++) {
                lists.set(i,url + lists.get(i));
            }
            //json数组把，转换为&
            b.setHouseimg(lists.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        }
        QueryResult<HouseEntity> m = new QueryResult<>();
        m.setPageNo(no);
        m.setPageSize(size);
        m.setTotalRows((int)pageinfo.getTotal());
        m.setRows(rows);
        return m;
    }

    @Override
    public QueryResult<HouseEntity> queryOA(String id, String pageNo,String pageSize,String url) throws Exception {
        LeaserEntity u = leaserDao.selectById(id);
        if(u == null) {
            id = null;
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
        List<HouseEntity> e = dao.queryOA(id);
        PageInfo<HouseEntity> pageinfo= new PageInfo<>(e);
        List<HouseEntity> rows = new ArrayList<>();
        for (HouseEntity houseEntity : pageinfo.getList()) {
            String img = houseEntity.getHouseimg().replaceAll("&",",");
            List<String> lists = JSONArray.parseArray(img, String.class);
            for(int i=0; i< lists.size(); i++) {
                lists.set(i,url + lists.get(i));
            }
            //json数组把，转换为&
            houseEntity.setHouseimg(lists.toString().replaceAll("\\[", "").replaceAll("\\]", ""));

            rows.add(houseEntity);
        }
        QueryResult<HouseEntity> m = new QueryResult<>();
        m.setPageNo(no);
        m.setPageSize(size);
        m.setTotalRows((int)pageinfo.getTotal());
        m.setRows(rows);

        return m;
    }

    @Override
    public HouseEntity queryDetail(String url, String id) throws Exception {

        HouseEntity e = dao.queryDetail(id);

        if(e == null){
            throw new WxgzhException("该房间不存在或已被删除！");
        }

        String img = e.getHouseimg().replaceAll("&",",");
        String imgs = e.getBuildingimg().replaceAll("&",",");
        List<String> lists = JSONArray.parseArray(img, String.class);
        List<String> lists2 = JSONArray.parseArray(imgs, String.class);
        for(int i=0; i< lists.size(); i++) {
            lists.set(i,url + lists.get(i));
        }
        //json数组把，转换为&
        e.setHouseimg(lists.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        for(int j=0; j< lists2.size(); j++) {
            lists2.set(j,url + lists2.get(j));
        }
        //json数组把，转换为&
        e.setBuildingimg(lists2.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        return e;
    }

}
