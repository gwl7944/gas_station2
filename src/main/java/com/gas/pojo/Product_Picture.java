package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 10:58
 * Description: 积分商品产品图片  2.0 版本新增
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product_Picture {

    private Integer ppe_id;//	主键

    private Integer ppe_type;//	类型

    private String ppe_url;//

    private Integer ppe_pim_id;//	商品外键

    private Integer ppe_carousel_id;//	轮播位外键

    private Integer ppe_siteid;  //门店外键

    private Integer ppe_del; //删除

    private Site site; //门店id

    private Carousel carousel; //轮播位
}
