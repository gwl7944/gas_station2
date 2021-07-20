package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.Pointegers_item;
import com.gas.pojo.ResultCode;
import com.gas.pojo.ResultData;
import com.gas.service.IntegralService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/17
 * Time: 17:42
 * Description: 积分业务  2.0新增
 */
@RestController
@CrossOrigin
public class IntegralController {

    @Resource
    IntegralService integralService;

    /**
     * 积分商品 新增
     */
    @PostMapping("/integral/addIntegralProduct")
    public JSON addIntegralProduct(@RequestBody Pointegers_item pointegersItem){

        Integer i = integralService.insertIntegralProduct(pointegersItem);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 积分商品修改
     */
    @RequestMapping(value = "/integral/editIntegralProduct",method = RequestMethod.POST)
    public String editIntegralProduct(@RequestBody Pointegers_item pointegersItem){
        if (integralService.updateIntegralProduct(pointegersItem)>0){
            return ResultData.getResponseData(null,ResultCode.UPDATE_SUCCESS).toString();
        }else {
            return ResultData.getResponseData(null,ResultCode.UPDATE_ERROR).toString();
        }
    }


}
