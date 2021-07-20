package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 10:59
 * Description: 轮播位
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carousel {

    private Integer cal_id;//	主键

    private String cal_code;//	编号

    private String cal_name;//	名称

    private String cal_describe;//	描述

    private List<Product_picture> product_pictureList;  //图片集合
}
