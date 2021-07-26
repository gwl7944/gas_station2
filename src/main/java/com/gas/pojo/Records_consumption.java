package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.pojo
 * @version: 1.0
 */

/**
 * 消费记录
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Records_consumption {

    private Integer rc_id;//	主键

    private String rc_consumer_projects; //消费项目  油价名称

    private Integer rc_consumer_projects_code;  //消费项目编号  油价id

    private Double rc_oil_price; //油品单价

    private Oil_price oilPrice;  //油价信息

    private Date rc_datetime;//	消费时间

    private String rc_Date_str; //消费时间

    private Integer rc_type;  //消费类型


    private Double rc_actual_amount;//	应收金额

    private Double rc_amount_payable;//	实付金额

    private Integer rc_wu_id;//	用户主键

    private Wechat_users wechat_users;  //用户对象

    private String rc_wu_name;//	用户名称

    private Integer rc_sitecode;//	站点编号

    private String rc_sitecode_name;//	站点名称

    private Site site;   //站点对象





    private Double rc_activity;  //活动优惠金额

    private Double rc_coupon;  //优惠券优惠金额

    private Integer rc_coupon_id;  //优惠券外键  实体

    private Double rc_integral;  //积分抵扣金额

    private Integer rc_integral_num;  //积分值  实体

    private Double rc_member;  //会员优惠金额

    private Double rc_balance_deduction;  //余额抵扣金额


    private Integer rc_user_id;  //打印员id

    private User user;

    private String rc_number; //单号

    private Integer rc_print;  //是否首次打印

    private String rc_oil_gun;  //油枪编号

    private Double rc_oil_num;  //加油数量

    private String mchCreateIp;  //客户支付终端ip

    private String openId;  //openId

    private String rc_nocard_phone; //无卡支付手机号

    private String mch_id; //商户号

    private String org_code;//机构号

    private String appid;//	appID

    private Integer rc_pay_type;  //实体字段  支付类型
}
