package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: gas_station2
 * @Package: com.gas.pojo
 * @ClassName: Property_change
 * @Author: gwl
 * @Description:
 * @Date: 2021/7/22 15:16
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property_change {

    private Integer pce_id; //主键

    private String pce_code; //单号

    private Integer pce_integral; //积分值

    private Integer pce_integral_num; //积分值  实体

    private Integer pce_coupon; //优惠券

    private Integer pce_growth_value; //成长值

    private Double pce_balance; //余额值

    private Integer pce_wu_id; //用户主键

    private Integer pce_site_id; //门店主键

    private Integer pce_type; //类型

    private Double pce_money; //支付金额

    private Integer pce_pay_type; //支付类型 实体字段
}
