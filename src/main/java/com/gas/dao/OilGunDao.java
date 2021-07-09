package com.gas.dao;

import com.gas.pojo.OilGun;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/4/28
 * Time: 10:22
 * Description: No Description
 */
@Mapper
public interface OilGunDao {

    int insertOilGun(OilGun oilGun);

    int updateOilGun(OilGun oilGun);

    List<OilGun> findAllOilGun(OilGun oilGun);

    int deleteOilGun(Integer oil_gun_id);
}
