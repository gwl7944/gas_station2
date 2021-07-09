package com.gas.service;

import com.gas.dao.AuthorityDao;
import com.gas.dao.OilGunDao;
import com.gas.pojo.OilGun;
import com.gas.pojo.Oil_price;
import com.gas.pojo.Page;
import com.gas.pojo.Site;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/4/28
 * Time: 10:21
 * Description: No Description
 */
@Service
public class OilGunServiceImpl implements OilGunService {

    @Autowired
    OilGunDao oilGunDao;
    @Autowired
    AuthorityDao authorityDao;

    @Override
    public int insertOilGun(OilGun oilGun) {

        return oilGunDao.insertOilGun(oilGun);
    }

    @Override
    public int updateOilGun(OilGun oilGun) {
        return oilGunDao.updateOilGun(oilGun);
    }

    @Override
    public Page<OilGun> findAllOilGun(OilGun oilGun, Integer currentpage, Integer currentnumber) {
        Page<OilGun> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<OilGun> oilGunList = oilGunDao.findAllOilGun(oilGun);
        for (OilGun oilGun1 : oilGunList) {
            //查询站点
            Site site = authorityDao.findSiteById(Integer.parseInt(oilGun1.getOil_gun_sitecode()));
            oilGun1.setSite(site);
            //查询油品
            Oil_price oil_price = authorityDao.findOliInfoById(oilGun1.getOil_op_id());
            oilGun1.setOilPrice(oil_price);
        }
        PageInfo<OilGun> info = new PageInfo<>(oilGunList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }

    @Override
    public int deleteOilGun(Integer oil_gun_id) {

        return oilGunDao.deleteOilGun(oil_gun_id);
    }

    @Override
    public List<OilGun> findOilGun(OilGun oilGun) {
        return  oilGunDao.findAllOilGun(oilGun);
    }

}
