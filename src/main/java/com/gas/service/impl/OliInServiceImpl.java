package com.gas.service.impl;

import com.gas.dao.OliInDao;
import com.gas.dao.SiteDao;
import com.gas.pojo.Oil_price;
import com.gas.pojo.Page;
import com.gas.pojo.Site;
import com.gas.service.OliInService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:23
 * Description: No Description
 */
@Service
public class OliInServiceImpl implements OliInService {

    @Resource
    OliInDao oliInDao;
    @Resource
    SiteDao siteDao;

    @Override
    public Page<Oil_price> findAllOliInfoBySite(Integer site_id, Integer currentpage, Integer currentnumber) {
        Page<Oil_price> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<Oil_price> oilPriceList = oliInDao.findAllOliInfoBySite(site_id);
        for (Oil_price oil_price : oilPriceList) {
            //查询站点
            Site site = siteDao.findSiteById(oil_price.getOp_sitecode());
            oil_price.setSite(site);
        }
        PageInfo<Oil_price> info = new PageInfo<>(oilPriceList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }

    @Override
    public Oil_price findOliInfoById(Integer oil_id) {
        Oil_price oilPrice = oliInDao.findOliInfoById(oil_id);
        return oilPrice;
    }

    @Override
    public int insertOliInfo(Oil_price oilPrice) {

        return oliInDao.insertOliInfo(oilPrice);
    }

    @Override
    public int updateOliInfo(Oil_price oilPrice) {

        return oliInDao.updateOliInfo(oilPrice);
    }

    @Override
    public int deleteOliInfoById(Integer oil_id) {
        return oliInDao.deleteOliInfoById(oil_id);
    }
}
