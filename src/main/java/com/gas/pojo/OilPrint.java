package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/4/1 19:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OilPrint {

    private String oil_name;  //名称

    private Double oil_unit_price;  //单价

    private Double receivable_amount;  //应收金额

    private Double paid_amount;  //实收金额

    private Double coupon_amount;  //优惠金额

    private Double sales_volume;  //销售量
}
