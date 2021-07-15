package com.gas.service.impl;

import com.gas.dao.SiteDao;
import com.gas.pojo.Page;
import com.gas.pojo.Site;
import com.gas.service.SiteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:11
 * Description: No Description
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Resource
    SiteDao siteDao;

    @Override
    public Page<Site> findSite(Integer currentpage, Integer currentnumber) {
        Page<Site> page = new Page<>();
        if (currentpage < 0) {
            page.setDatalist(siteDao.findSite());
            return page;
        } else {
            PageHelper.startPage(currentpage, currentnumber);
            List<Site> allSite = siteDao.findSite();
            PageInfo<Site> info = new PageInfo<>(allSite);
            page.setCurrentnumber(info.getPageNum());
            page.setCurrentpage(currentpage);
            page.setPagecount(info.getPages());
            page.setTotalnumber((int) info.getTotal());
            page.setDatalist(info.getList());
            return page;
        }
    }

    @Override
    public List<Site> findSiteNoPage() {
        return siteDao.findSite();
    }


    @Override
    public int addSite(Site site) {
        int i = siteDao.addSite(site);
        return i;
    }

    @Override
    public int updateSite(Site site) {
        int i = siteDao.updateSite(site);
        return i;
    }

    @Override
    public int deleteSite(Integer site_id) {
        int i = siteDao.deleteSite(site_id);
        return i;
    }
}
