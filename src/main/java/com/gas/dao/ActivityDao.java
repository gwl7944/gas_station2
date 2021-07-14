package com.gas.dao;

import com.gas.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:50
 * Description: No Description
 */
@Mapper
public interface ActivityDao {

    int insertActivity(Activity activity);

    int updateActivity(Activity activity);

    List<Activity> findAllActivityBySite(Activity activity);

    int deleteActivityById(Integer activity_id);

    Activity findActivityById(Integer rc_activity);
}
