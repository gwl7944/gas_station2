package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 11:08
 * Description: 积分抽奖   2.0 版本新增
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Points_lottery {

    private Integer pl_id;//	主键

    private String pl_name;//	名称

    private Integer pl_type;//	类型

    private Double pl_probability;//	中奖概率

    private Integer pl_growth_value;//	成长值

    private Integer pl_coupon;//	优惠券外键

    private Coupon coupon;  //优惠券对象

    private Double pl_balance;//	余额值

    private Integer pl_site_id;  //门店主键

    private Site site; //门店对象

}
