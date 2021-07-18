package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.Pointegers_Item;
import com.gas.pojo.ResultCode;
import com.gas.pojo.ResultData;
import com.gas.service.IntegralService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/17
 * Time: 17:42
 * Description: 积分业务  2.0新增
 */
@RestController
public class IntegralController {

    @Resource
    IntegralService integralService;

    /**
     * 积分商品 新增
     */
    @PostMapping("/integral/addIntegralProduct")
    public JSON addIntegralProduct(@RequestBody Pointegers_Item pointegersItem){

        Integer i = integralService.insertIntegralProduct(pointegersItem);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }
}
