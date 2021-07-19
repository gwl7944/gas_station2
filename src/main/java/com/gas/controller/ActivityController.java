package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.Activity;
import com.gas.pojo.Page;
import com.gas.pojo.ResultCode;
import com.gas.pojo.ResultData;
import com.gas.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:46
 * Description: No Description
 */
@RestController
@CrossOrigin
public class ActivityController {

    @Resource
    ActivityService activityService;

    /**
     * 新增活动
     */
    @PostMapping("/AuthorityController/insertActivity")
    public JSON insertActivity(@RequestBody Activity activity){

        int i = activityService.insertActivity(activity);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 修改活动
     */
    @PostMapping("/AuthorityController/updateActivity")
    public JSON updateActivity(@RequestBody Activity activity){

        int i = activityService.updateActivity(activity);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /**
     * 查询当前站点下的所有活动
     */
    @PostMapping("/AuthorityController/getAllActivityBySite")
    public JSON getAllActivityBySite(@ModelAttribute Activity activity, @RequestParam("currentpage") Integer currentpage, @RequestParam("currentnumber") Integer currentnumber){

        Page<Activity> activityPage = activityService.findAllActivityBySite(activity,currentpage,currentnumber);
        if (activityPage!=null){
            return ResultData.getResponseData(activityPage,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(activityPage,ResultCode.QUERY_ERROR);
    }

    /**
     * 删除活动
     */
    @GetMapping("/AuthorityController/deleteActivity/{activity_id}")
    public JSON deleteActivity(@PathVariable("activity_id") Integer activity_id){

        int i = activityService.deleteActivityById(activity_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }
}
