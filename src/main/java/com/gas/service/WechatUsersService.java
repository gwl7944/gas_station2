package com.gas.service;

import com.gas.pojo.*;

import java.util.List;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.service
 * @ClassName: WechatUsersService
 * @Author: gwl
 * @Description:
 * @Date: 2021/3/22 16:18
 * @Version: 1.0
 */
public interface WechatUsersService {

    /**
     * openid查询用户那信息
     * */
    Wechat_users getWechat_usersByOpenId(String wu_openid);

    /**
     * 新增用户信息
     * */
    int insertWechat_users(Wechat_users wechat_users);

    /**
     * 修改用户信息
     * */
    int updateWechat_users(Wechat_users wechat_users);

    /**
     * 查询用户优惠券列表
     * */
    List<Coupon> getCouponByWci_wu_id(Integer wci_wu_id);

    /**
     * 查询用户消费记录
     * */
    RecordsConsumptionInfo getRecords_consumptionByRc_wu_id(Records_consumption records_consumption);

    /**
     * 查询全部活动信息
     * */
    List<Activity> getAllActivity(String activity_sitecode);

    /**
     * 查询全部优惠券
     * */
    List<Coupon> getAllCoupon(String coupon_sitecode);

    /**
     * 用户兑换优惠券
     * */
    int CouponExchange(Wu_coupon_information wu_coupon_information,Integer coupon_integralnum);

    /**
     * 查询油价价格
     * */
    List<Oil_price> getAllOil_price(String op_sitecode);

    /**
     * 通过appId查询站点
     * */
    Site findSiteByAppId(Site site);

    /**
     * 余额充值
     * */
    int insertRecords(Records_consumption recordsConsumption);

    /**
     * 余额充值待支付
     * */
    String insertRecordsWait(Records_consumption recordsConsumption);

    /**
     * 加油消费
     * */
    int insertConsumeInfo(Records_consumption recordsConsumption);

    /**
     * 嘉联支付 订单新增
     * */
    int insertJlOrder(JlOrder jlOrder);

    /**
     * 嘉联异步  消费记录录入
     * */
    int insertConsumeReco(String out_trade_no);


    /**
     * 查询全部会员日
     * */
    List<Member_day> getAllMember_day(Integer med_sitecode);  //, Integer currentpage, Integer currentnumber

    /**
     * 根据油品ID 星期值 查询会员日
     * */
    Member_day getMember_dayByMed_op_id(Integer med_op_id,Integer med_weekday,Integer med_sitecode);

    /**
     * 新增会员日
     * */
    Integer insertMember_day(Member_day member_day);

    /**
     * 删除会员日
     * */
    Integer deleteMember_day(Integer med_id);

    /**
     * 修改会员日
     * */
    Integer updateMember_day(Member_day member_day);

    /**
     *
     */
    List<Oil_price> findMemberDayOilPrice(String op_sitecode,Integer med_weekday);


    /**
     * 拉取全部无卡消费信息
     * */
    Page<Records_consumption> findRecords_consumptionByNocard(Integer currentpage, Integer currentnumber,Integer rc_sitecode);
}
