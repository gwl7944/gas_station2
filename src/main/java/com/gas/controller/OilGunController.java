package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.*;
import com.gas.service.OilGunService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/4/28
 * Time: 9:55
 * Description: No Description
 */

@RestController
@CrossOrigin
public class OilGunController {

    @Autowired
    private OilGunService oilGunService;

    /**
     * 新增油枪
     */
    @PostMapping("/oilGun/addOilGun")
    public JSON addOilGun(@RequestBody OilGun oilGun){
        int i = oilGunService.insertOilGun(oilGun);
        if (i>0){
           return ResultData.getResponseData(i, ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i, ResultCode.INSERT_ERROR);
    }

    /**
     * 修改油枪
     */
    @PostMapping("/oilGun/editOilGun")
    public JSON editOilGun(@RequestBody OilGun oilGun){
        int i = oilGunService.updateOilGun(oilGun);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i, ResultCode.UPDATE_ERROR);
    }

    /**
     * 全查油枪
     */
    @GetMapping("/oilGun/getAllOilGun")
    public JSON getAllOilGun(@ModelAttribute OilGun oilGun, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber){
        Page<OilGun> oilGunList = oilGunService.findAllOilGun(oilGun,currentpage,currentnumber);
        if (oilGunList!=null){
            return ResultData.getResponseData(oilGunList, ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(oilGunList, ResultCode.UPDATE_ERROR);
    }

    /**
     * 删除油枪
     */
    @PostMapping("/oilGun/deleteOilGun/{oil_gun_id}")
    public JSON deleteOilGun(@PathVariable("oil_gun_id") Integer oil_gun_id){
        int i = oilGunService.deleteOilGun(oil_gun_id);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i, ResultCode.DELETE_ERROR);
    }

    /**
     * 全查油枪无分页
     */
    @PostMapping("/oilGun/getOilGun")
    public JSON getOilGun(@ModelAttribute OilGun oilGun){
        List<OilGun> oilGunList = oilGunService.findOilGun(oilGun);
        if (oilGunList.size()>0){
            return ResultData.getResponseData(oilGunList, ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(oilGunList, ResultCode.QUERY_ERROR);
    }
}
