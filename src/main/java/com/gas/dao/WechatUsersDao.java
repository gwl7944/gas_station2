package com.gas.dao;

import com.gas.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.dao
 * @ClassName: WechatUsersDao
 * @Author: gwl
 * @Description:
 * @Date: 2021/3/22 11:48
 * @Version: 1.0
 */
@Mapper
public interface WechatUsersDao {

    /**
     * openid查询用户那信息
     * */
    Wechat_users findWechat_usersByOpenId(String wu_openid);

    /**
     * 新增用户信息
     * */
    int insertWechat_users(Wechat_users wechat_users);

    /**
     * 修改用户信息
     * */
    int updateWechat_users(Wechat_users wechat_users);

    /**
     * 查询用户优惠券数量
     * */
    Integer findWci_numberByWci_wu_id(Integer ucs_wu_id);

    /**
     * 查询用户优惠券列表
     * */
    List<Coupon> findCouponByWci_wu_id(Integer wci_wu_id);

    /**
     * 查询用户消费记录
     * */
    List<Records_consumption> findRecords_consumptionByRc_wu_id(Records_consumption records_consumption);

    /**
     * 查询全部活动信息
     * */
    List<Activity> findAllActivity(@Param("activity_sitecode") String activity_sitecode,@Param("date") String date);

    /**
     * 查询没有结束时间的全部活动信息
     * */
    List<Activity> findNoEndTimeActivity(String activity_sitecode);

    /**
     * 查询全部优惠券
     * */
    List<Coupon> findAllCoupon(@Param("now_date") String now_date,@Param("coupon_sitecode") String coupon_sitecode);

    /**
     * 修改优惠券
     * */
    int updateCoupon(Coupon coupon);

    /**
     * 查询用户下是否有该优惠券
     * */
    Integer findWCIcount(@Param("wci_wu_id") Integer wci_wu_id, @Param("wci_coupon_type") Integer wci_coupon_type);

    /**
     * 修改用户优惠券数量
     * */
    int updateWCI(Wu_coupon_information wu_coupon_information);

    /**
     * 新增用户优惠券
     * */
    int insertWCI(Wu_coupon_information wu_coupon_information);

    /**
     * 修改用户当前积分
     * */
    int updateWechat_usersWu_current_points(Wechat_users wechat_users);

    /**
     * 修改优惠券数量
     * */
    int updateCouponCoupon_number(Coupon coupon);

    /**
     * 查询油价价格
     * */
    List<Oil_price> findAllOil_price(String op_sitecode);

    /**
     * 查询站点
     * */
    Site findSiteByAppId(String site_appid);

    /**
     * 查询活动
     * */
    Activity findActivity(Integer rc_activity);

    /**
     * 更新用户充值信息
     * */
    int updateWechatTopUpInfo(Wechat_users wechatUsers);

    /**
     * 新增用户充值信息待支付
     * */
    int insertRecordsConsumptionWait(Records_consumption recordsConsumption);

    /**
     * 增加消费记录
     * */
    int insertRecordsConsumption(Records_consumption recordsConsumption);

    /**
     * 减少账户余额
     * */
    int updateWechatBalanceInfo(Wechat_users wechatUsers);

    /**
     * 减少优惠券数
     * */
    int updateWuCouponNum(Wu_coupon_information wuCouponInformation);


    /**
     * 嘉联支付
     * */
    int insertJlOrder(JlOrder jlOrder);

    /**
     * 根据单号查询待支付消费记录
     * */
    Records_consumption findRecordsByRcNum(String out_trade_no);


    /**
     * 查询全部会员日
     * */
    List<Member_day> findAllMember_day(Integer med_sitecode);

    /**
     * 根据油品ID 星期值 查询会员日
     * */
    Member_day findMember_dayByMed_op_id(Integer med_op_id,Integer med_weekday,Integer med_sitecode);

    /**
     * 新增会员日
     * */
    Integer insertMember_day(Member_day member_day);

    /**
     * 删除会员日
     * */
    Integer deleteMember_day(Integer med_id);

    List<Activity> findActivityBy();

    /**
     * 修改会员日
     * */
    Integer updateMember_day(Member_day member_day);

    /**
     * 查询用户优惠券信息
     * */
    Integer findWCIcountUseNum(Integer wci_wu_id, Integer wci_coupon_type);

    /**
     * 拉取全部无卡消费信息
     * */
    List<Records_consumption> findRecords_consumptionByNocard(Integer rc_sitecode);


    /**-------------------------------- 2.0 新增 ------------------------------------------------*/

    /**
     * 查询轮播图
     * */
    Carousel findCarouselByCal_id(Integer cal_id,Integer ppe_siteid);


    /**
     * 根据ID 查询会员等级
     * */
    Membership_level findMembership_levelById(Integer ml_id);

    /**
     * 查询全部会员权益  站点
     * */
    List<Membership_rules> findAllMembership_rules(Integer site_id);

    /**
     * 根据会员等级ID 查询会员权益
     * */
    Membership_rules findMembership_rulesByMl_id(Integer mr_ml_id);

    /**
     * 领取会员卡
     * */
    Integer getTheCard(Wechat_users wechat_users);

    /**
     * 查询门店开卡福利
     * */
    Development_welfare findDevelopment_welfareById(Integer dwe_siteid);

    /**
     * 查询用户会员成长值变动记录
     * */
    List<Growthvalue_record> findGrowthvalue_recordByGvr_wu_id(Integer gvr_wu_id);

    /**
     * 添加会员成长值变更记录
     * */
    Integer insertGrowthvalue_record(Growthvalue_record growthvalue_record);


    /**
     * 查询全部充值额度
     * */
    List<Recharge> findAllRecharge(Integer rech_site_id);


    /**
     * 查询用户的积分详情
     * */
    List<Pointegers_details> findPoIntegers_detailsByPds_wu_id(Integer pds_wu_id);

    /**
     * 添加用户积分变更记录
     * */
    Integer insertPointegers_details(Pointegers_details pointegers_details);

    /**
     * 扣除用户积分值
     * */
    Integer updateWechat_usersByWu_current_points(Integer wu_id,Integer wu_current_points);

    /**
     * 查询门店积分规则 (消费)
     * */
    Integeregral_rule findIntegeregral_ruleByLr_siteid(Integer lr_siteid);

    /**
     * 查询用户消费记录
     * */
    List<Records_consumption> findRecords_consumptionByRc_wu_id2(Integer rc_wu_id,Integer rc_type);

    /**
     * 查询门店积分商品信息
     * */
    List<Pointegers_item> findAllPointegers_item(Integer pim_site_id);

    /**
     * 查询全部奖池奖品
     * */
    List<Points_lottery> findAllPoints_lottery(Integer pl_site_id);










}


