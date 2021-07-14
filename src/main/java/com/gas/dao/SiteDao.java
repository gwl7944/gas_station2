package com.gas.dao;

import com.gas.pojo.Site;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.dao
 * @ClassName: SiteDao
 * @Author: gwl
 * @Description:  站点DAO
 * @Date: 2021/3/22 16:00
 * @Version: 1.0
 */

@Mapper
public interface SiteDao {

    List<Site> findSite();

    int addSite(Site site);

    int updateSite(Site site);

    int deleteSite(Integer site_id);

    Site findSiteById(Integer rc_sitecode);

}
