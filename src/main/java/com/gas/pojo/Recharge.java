package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: gas_station2
 * @Package: com.gas.pojo
 * @ClassName: Recharge
 * @Author: gwl
 * @Description: 充值额度设置
 * @Date: 2021/7/15 11:37
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recharge {

    private Integer rech_id;    //主键

    private Integer rech_quota;    //充值额度

    private Integer rech_Integral_value;  //积分值

    private Integer rech_balance_value;    //余额值

    private Integer rech_coupons_id;    //赠送优惠券

    private Coupon coupon;  /*优惠券对象*/

    private Integer rech_site_id;    //门店外键

    private Site site;  /*门店对象*/

    private Integer rech_del;    //删除

}
