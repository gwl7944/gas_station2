package com.gas.service.impl;

import com.gas.dao.CouponDao;
import com.gas.dao.SiteDao;
import com.gas.pojo.Coupon;
import com.gas.pojo.Page;
import com.gas.pojo.Site;
import com.gas.service.CouponService;
import com.gas.util.DateTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:37
 * Description: No Description
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    CouponDao couponDao;
    @Resource
    SiteDao siteDao;

    @Override
    public int insertCoupon(Coupon coupon) {
        coupon.setCoupon_term_validity(DateTO.getDate(coupon.getCoupon_term_validity_str())); //开始日期
        coupon.setCoupon_enddate(DateTO.getDate(coupon.getCoupon_enddate_str()));  //截至日期
        return couponDao.insertCoupon(coupon);
    }

    @Override
    public Page<Coupon> findAllCouponBySite(Coupon coupon, Integer currentpage, Integer currentnumber) {
        Page<Coupon> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<Coupon> couponList = couponDao.findAllCouponBySite(coupon);
        for (Coupon coupon1 : couponList) {
            coupon1.setCoupon_term_validity_str(DateTO.getStringDate(coupon1.getCoupon_term_validity()));
            //查询所属站点
            Site site = siteDao.findSiteById(coupon1.getCoupon_site_id());
            coupon1.setSite(site);

            if (coupon1.getCoupon_term_validity()!=null){
                coupon1.setCoupon_term_validity_str(DateTO.getStringDate(coupon1.getCoupon_term_validity()));
            }
            if (coupon1.getCoupon_enddate()!=null){
                coupon1.setCoupon_enddate_str(DateTO.getStringDate(coupon1.getCoupon_enddate()));
            }
        }
        PageInfo<Coupon> info = new PageInfo<>(couponList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }


    @Override
    public int deleteCouponById(Integer coupon_id) {

        return couponDao.deleteCouponById(coupon_id);
    }

    @Override
    public int updateCouponById(Coupon coupon) {
        if (coupon.getCoupon_term_validity_str()!=null){
            coupon.setCoupon_term_validity(DateTO.getDate(coupon.getCoupon_term_validity_str()));
            coupon.setCoupon_enddate(DateTO.getDate(coupon.getCoupon_enddate_str()));
        }
        return couponDao.updateCouponById(coupon);
    }

}
