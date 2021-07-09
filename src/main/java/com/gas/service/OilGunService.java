package com.gas.service;

import com.gas.pojo.OilGun;
import com.gas.pojo.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/4/28
 * Time: 10:21
 * Description: No Description
 */
public interface OilGunService {

    int insertOilGun(OilGun oilGun);

    int updateOilGun(OilGun oilGun);

    Page<OilGun> findAllOilGun(OilGun oilGun, Integer currentpage, Integer currentnumber);

    int deleteOilGun(Integer oil_gun_id);

    List<OilGun> findOilGun(OilGun oilGun);
}
