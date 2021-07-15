package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 10:45
 * Description: 积分规则   系统设置固定  2.0 版本新增
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Integeregral_rule {

    private Integer lr_id;//	主键

    private String lr_name;//	名称

    private Double lr_recharge_ratio;//	充值比

    private Double lr_consumption_ratio;//	消费比

    private Integer lr_siteid;//	门店ID
}
