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
    Site findSiteByAppId(String site_appid);

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


    /**----------------------------------------------- 2.0 新增 --------------------------------------------*/

    /**
     * 查询轮播图
     * */
    Carousel getCarouselByCal_id(Integer cal_id,Integer ppe_siteid);

    /**
     * 查询全部会员权益  站点
     * */
    List<Membership_rules> getAllMembership_rules(Integer site_id);

    /**
     * 领取会员卡
     * */
    Integer getTheCard(Wechat_users wechat_users);

    /**
     * 查询门店开卡福利
     * */
    Development_welfare getDevelopment_welfareById(Integer dwe_siteid);

    /**
     * 查询用户会员成长值变动记录
     * */
    List<Growthvalue_record> getGrowthvalue_recordByGvr_wu_id(Integer gvr_wu_id);

    /**
     * 查询全部充值额度
     * */
    List<Recharge> getAllRecharge(Integer rech_site_id);

    /**
     * 查询用户的积分详情
     * */
    List<Pointegers_details> getPoIntegers_detailsByPds_wu_id(Integer pds_wu_id);

    /**
     * 添加用户积分变更记录
     * */
    Integer insertPointegers_details(Pointegers_details pointegers_details);

    /**
     * 查询门店积分规则 (消费)
     * */
    Integeregral_rule getIntegeregral_ruleByLr_siteid(Integer lr_siteid);

    /**
     * 查询用户消费记录
     * */
    List<Records_consumption> getRecords_consumptionByRc_wu_id2(Integer rc_wu_id,Integer rc_type);

    /**
     * 查询门店积分商品信息
     * */
    List<Pointegers_item> getAllPointegers_item(Integer pim_site_id);

    /**
     * 查询全部奖池奖品
     * */
    List<Points_lottery> getAllPoints_lottery(Integer pl_site_id);

    /**
     * 点击抽奖
     * */
    Integer getLuck_Draw(Integer pl_site_id);

    /**
     * 充值成功
     * */
    Integer Recharge_success(Records_consumption records_consumption);
}
