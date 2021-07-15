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
 * 优惠券
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    private Integer coupon_id;//	主键

    private String coupon_name;//	名称

    /*private String coupon_describe;*/   //描述

    private Double coupon_discount;  //折扣

    private Double coupon_quota;//	金额

    private Integer coupon_number;//数量

    private Date coupon_term_validity;//	开始日期

    private String coupon_term_validity_str;//	开始日期

    private Date coupon_enddate;   //截止日期

    private String coupon_enddate_str;  //截止日期

    private Integer coupon_threshold;   //门槛

    private Integer coupon_site_id;   //站点编号

    private Site site;  //站点对象

    private String coupon_exchange_code;  //兑换码

    /*private Integer coupon_integralnum;*/  //所需积分

    private Integer coupon_state;//	状态（默认启用为1  禁用为2）

    private Integer coupon_del;//	删除（默认为1   删除为2）

   /* private String coupon_remarks;*///	备注

    /*private Integer coupon_wxuser_number;*/  //用户优惠券持有数量


}
