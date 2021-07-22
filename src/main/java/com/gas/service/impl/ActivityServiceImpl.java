package com.gas.service.impl;

import com.gas.dao.ActivityDao;
import com.gas.dao.OliInDao;
import com.gas.dao.SiteDao;
import com.gas.pojo.Activity;
import com.gas.pojo.Oil_price;
import com.gas.pojo.Page;
import com.gas.pojo.Site;
import com.gas.service.ActivityService;
import com.gas.util.DateTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:48
 * Description: 活动相关
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    ActivityDao activityDao;
    @Resource
    SiteDao siteDao;
    @Resource
    OliInDao oliInDao;


    @Override
    public int insertActivity(Activity activity) {
        if (activity.getActivity_start_date_str()!=""&&activity.getActivity_start_date_str()!=null){
            activity.setActivity_start_date(DateTO.getDate(activity.getActivity_start_date_str()));
        }
        if (activity.getActivity_end_date_str()!=""&&activity.getActivity_end_date_str()!=null){
            activity.setActivity_end_date(DateTO.getDate(activity.getActivity_end_date_str()));
        }
        System.out.println("activity>>"+activity);
        return activityDao.insertActivity(activity);
    }

    @Override
    public int updateActivity(Activity activity) {
        if (activity.getActivity_end_date_str()!=null){
            activity.setActivity_end_date(DateTO.getDate(activity.getActivity_end_date_str()));
        }
        if (activity.getActivity_start_date_str()!=null){
            activity.setActivity_start_date(DateTO.getDate(activity.getActivity_start_date_str()));
        }
        return activityDao.updateActivity(activity);
    }

    @Override
    public Page<Activity> findAllActivityBySite(Activity activity, Integer currentpage, Integer currentnumber) {

        Page<Activity> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        if (activity.getActivity_end_date_str()!="" && activity.getActivity_end_date_str()!=null){
            activity.setActivity_end_date(DateTO.getDate(activity.getActivity_end_date_str()));
        }

        if (activity.getActivity_start_date_str()!="" && activity.getActivity_start_date_str()!=null){
            activity.setActivity_start_date(DateTO.getDate(activity.getActivity_start_date_str()));
        }

        List<Activity> activityList = activityDao.findAllActivityBySite(activity);
        for (Activity activity1 : activityList) {
            if(activity1.getActivity_start_date()!=null){
                activity1.setActivity_start_date_str(DateTO.getStringDate(activity1.getActivity_start_date()));
            }
            if (activity1.getActivity_end_date()!=null){
                activity1.setActivity_end_date_str(DateTO.getStringDate(activity1.getActivity_end_date()));
            }
            //查询所属站点
            Site site = siteDao.findSiteById(activity1.getActivity_siteid());
            activity1.setSite(site);
            //查询所属油品
            Oil_price oilPrice = oliInDao.findOliInfoById(activity1.getActivity_oil_price());
            activity1.setOilPrice(oilPrice);
        }
        PageInfo<Activity> info = new PageInfo<>(activityList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }

    @Override
    public int deleteActivityById(Integer activity_id) {
        return activityDao.deleteActivityById(activity_id);
    }
}
