package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 11:03
 * Description: 积分商品  2.0 版本新增
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pointegers_Item {

    private Integer pim_id;//	主键

    private String pim_name;//	名称

    private String pim_describe;//	描述

    private Integer pim_poIntegers_number;//	所需积分

    private Integer pim_default_picture;//	缺省产品图

    private Integer pim_stock;//	库存

    private Integer pim_site_id;//	门店

    private Integer pim_state;//	状态

    private Integer pim_del;//	删除

    private List<Product_Picture> pictureList;  //商品图片
}
