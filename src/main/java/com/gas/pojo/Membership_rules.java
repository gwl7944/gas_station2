package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 10:48
 * Description: 会员规则   系统设置固定  2.0 版本新增
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership_rules {

    private Integer mr_id;//	主键

    private Integer mr_ml_id;//	会员外键

    private String mr_name;//	名称

    private Double mr_recharge_discount;//	充值折扣值

    private Double mr_consumption_discount;//	消费抵扣

    private Double mr_recharge_growthvalue;//	充值成长值比

    private Double mr_consumption_growthvalue;//	消费成长值比

    private Membership_level membershipLevel;  //会员等级



}
