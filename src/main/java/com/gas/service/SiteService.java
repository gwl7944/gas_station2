package com.gas.service;

import com.gas.pojo.Page;
import com.gas.pojo.Site;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:05
 * Description: No Description
 */
public interface SiteService {

    Page<Site> findSite(Integer currentpage, Integer currentnumber);

    List<Site> findSiteNoPage();

    int addSite(Site site);

    int updateSite(Site site);

    int deleteSite(Integer site_id);

}
