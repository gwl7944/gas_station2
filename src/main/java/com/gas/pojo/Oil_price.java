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
 * 油价
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oil_price {

    private Integer op_id;//	主键

    private String op_name;//	名称

    private Double op_price;//	价格

    private Integer op_sitecode;//	站点编号

    private Site site;  //站点对象

    private Integer op_state;//	状态（默认启用为1  禁用为2）

    private Integer op_del;//	删除（默认为1   删除为2）

    private Double op_price_member; //会员价格

    private Integer member_day; //是否为会员日
}
