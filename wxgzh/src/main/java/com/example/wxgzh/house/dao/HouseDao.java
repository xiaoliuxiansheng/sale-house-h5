package com.example.wxgzh.house.dao;

import com.example.wxgzh.common.dao.TemplateDao;
import com.example.wxgzh.entity.HouseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseDao extends TemplateDao<HouseEntity, String> {

    void modify(HouseEntity entity)throws Exception;

    List<HouseEntity> query(String id)throws Exception;
    List<HouseEntity> queryOA(String id) throws Exception;

    List<HouseEntity> selectByKey(@Param("name")String name, @Param("region")String region, @Param("minprice")String minprice, @Param("maxprice")String maxprice, @Param("minarea")String minarea, @Param("maxarea")String maxarea, @Param("rors") String rors)throws  Exception;


    HouseEntity queryDetail(String id)throws Exception;
}
