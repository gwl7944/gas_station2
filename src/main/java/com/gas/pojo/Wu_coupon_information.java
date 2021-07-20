package com.gas.pojo;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.pojo
 * @version: 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信用户优惠券信息
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wu_coupon_information {

    private Integer wci_id;//	主键

    private Integer wci_wu_id;//	微信用户外键

    private Wechat_users wechat_users;  //微信用户对象

    private Integer wci_coupon_id;//	优惠券外键

    private Coupon coupon;   //优惠券对象

    private Integer wci_state;  //状态


}
