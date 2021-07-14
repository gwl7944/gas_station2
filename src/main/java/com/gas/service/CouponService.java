package com.gas.service;

import com.gas.pojo.Coupon;
import com.gas.pojo.Page;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:36
 * Description: No Description
 */
public interface CouponService {

    int insertCoupon(Coupon coupon);

    Page<Coupon> findAllCouponBySite(Coupon coupon, Integer currentpage, Integer currentnumber);

    int deleteCouponById(Integer coupon_id);

    int updateCouponById(Coupon coupon);
}
