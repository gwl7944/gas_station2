package com.gas.dao;

import com.gas.pojo.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:38
 * Description: No Description
 */

@Mapper
public interface CouponDao {

    int insertCoupon(Coupon coupon);

    List<Coupon> findAllCouponBySite(Coupon coupon);

    int deleteCouponById(Integer coupon_id);

    int updateCouponById(Coupon coupon);

    Coupon findCouponById(Integer rc_coupon);
}
