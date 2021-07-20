package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: gas_station2
 * @Package: com.gas.pojo
 * @ClassName: Product_picture
 * @Author: gwl
 * @Description:
 * @Date: 2021/7/20 15:01
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product_picture {

    private Integer ppe_id; //主键

    private Integer ppe_type; //类型

    private String ppe_url; //地址

    private Integer ppe_pim_id; //商品外键

    private Carousel carousel;  //轮播为外键

    private Integer ppe_carousel_id; //轮播位外键

    private Site site; //门店对象

    private Integer ppe_siteid; //门店外键

    private Integer ppe_del; //删除

    private Integer ppe_default; //是否默认
}
