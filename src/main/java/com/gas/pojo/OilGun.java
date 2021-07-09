package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/4/28
 * Time: 9:58
 * Description: No Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OilGun {

    private Integer oil_gun_id;  //主键

    private Integer oil_gun_num; //编号

    private String oil_gun_sitecode; //所属站点

    private Integer oil_gun_del; //删除

    private Integer oil_op_id;  //油品id

    private Oil_price oilPrice;  //油品信息

    private Site site;
}
