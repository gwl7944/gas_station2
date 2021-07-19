package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.Oil_price;
import com.gas.pojo.Page;
import com.gas.pojo.ResultCode;
import com.gas.pojo.ResultData;
import com.gas.service.OliInService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:21
 * Description: No Description
 */

@RestController
@CrossOrigin
public class OliInController {

    @Resource
    OliInService oliInService;

    /*----------------------------------  油价 -------------------------------------*/

    /**
     * 根据站点查询全部油价信息(分页)
     */
    @RequestMapping("/AuthorityController/getAllOliInfoBySite")
    public JSON getAllOliInfoBySite(@RequestParam("site_id") Integer site_id, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber){

        Page<Oil_price> oilPricePage = oliInService.findAllOliInfoBySite(site_id,currentpage,currentnumber);
        if (oilPricePage!=null){
            return ResultData.getResponseData(oilPricePage,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(oilPricePage,ResultCode.QUERY_ERROR);
    }

    /**
     * 根据id查询油价信息
     */
    @RequestMapping("/AuthorityController/getOliInfoById")
    public JSON getOliInfoById(@RequestParam("oil_id") Integer oil_id){

        Oil_price oilPrice = oliInService.findOliInfoById(oil_id);
        if (oilPrice!=null){
            return ResultData.getResponseData(oilPrice,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(oilPrice,ResultCode.QUERY_ERROR);
    }

    /**
     * 新增油价
     */
    @PostMapping("/AuthorityController/addOliInfo")
    public JSON addOliInfo(@RequestBody Oil_price oilPrice){

        int i = oliInService.insertOliInfo(oilPrice);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 更新油价
     */
    @PostMapping("/AuthorityController/updateOliInfo")
    public JSON updateOliInfo(@RequestBody Oil_price oilPrice){

        int i = oliInService.updateOliInfo(oilPrice);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /**
     * 删除油价
     */
    @RequestMapping("/AuthorityController/deleteOliInfoById")
    public JSON deleteOliInfoById(@RequestParam("oil_id") Integer oil_id){

        int i = oliInService.deleteOliInfoById(oil_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }
}
