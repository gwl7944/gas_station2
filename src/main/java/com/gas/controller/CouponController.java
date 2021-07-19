package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.Coupon;
import com.gas.pojo.Page;
import com.gas.pojo.ResultCode;
import com.gas.pojo.ResultData;
import com.gas.service.CouponService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:35
 * Description: No Description
 */
@RestController
@CrossOrigin
public class CouponController {

    @Resource
    CouponService couponService;


    /**
     * 新增优惠卷
     */
    @PostMapping("/AuthorityController/addCoupon")
    public JSON addCoupon(@RequestBody Coupon coupon){

        int i = couponService.insertCoupon(coupon);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 查询当前站点下的优惠卷
     */
    @PostMapping("/AuthorityController/getAllCouponBySite")
    public JSON getAllCouponBySite(@ModelAttribute Coupon coupon, @RequestParam("currentpage") Integer currentpage, @RequestParam("currentnumber") Integer currentnumber){

        Page<Coupon> couponPage = couponService.findAllCouponBySite(coupon,currentpage,currentnumber);
        if (couponPage!=null){
            return ResultData.getResponseData(couponPage, ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(couponPage,ResultCode.QUERY_ERROR);
    }

    /**
     * 删除优惠卷
     */
    @GetMapping("/AuthorityController/deleteCoupon/{coupon_id}")
    public JSON deleteCoupon(@PathVariable("coupon_id") Integer coupon_id){

        int i = couponService.deleteCouponById(coupon_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }

    /**
     * 修改优惠卷
     */
    @PostMapping("/AuthorityController/updateCoupon")
    public JSON updateCoupon(@RequestBody Coupon coupon){

        int i = couponService.updateCouponById(coupon);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }
}
