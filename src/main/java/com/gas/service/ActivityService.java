package com.gas.service;

import com.gas.pojo.Activity;
import com.gas.pojo.Page;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:46
 * Description: No Description
 */
public interface ActivityService {

    int insertActivity(Activity activity);

    int updateActivity(Activity activity);

    Page<Activity> findAllActivityBySite(Activity activity, Integer currentpage, Integer currentnumber);

    int deleteActivityById(Integer activity_id);
}
