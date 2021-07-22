package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 10:44
 * Description: 开卡活动   2.0 版本新增
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Development_welfare {

    private Integer dwe_id;//	主键

    private Date dwe_startdate;//	开始时间

    private Date dwe_enddate;//	结束时间

    private Integer dwe_growth_value;//	成长值

    private Double dwe_balance;//	余额

    private Integer dwe_coupon_id;//	优惠券

    private Integer dwe_integral;//	积分

    private String dwe_description;//	详细说明

    private Integer dwe_siteid;//	门店ID

    private Coupon coupon; //优惠券
}
