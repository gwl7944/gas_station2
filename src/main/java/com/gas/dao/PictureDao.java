package com.gas.dao;

import com.gas.pojo.Carousel;
import com.gas.pojo.Product_Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/18
 * Time: 16:48
 * Description: No Description
 */
@Mapper
public interface PictureDao {

    /**
     * 新增图片
     */
    int insertProductPicture(Product_Picture productPicture);

    /**
     * 查询图片
     */
    List<Product_Picture> findProductPicture(Integer ppe_type, Integer ppe_siteid);

    /**
     * 根据id查询轮播位
     */
    Carousel findCarouselById(Integer cal_id);

    /**
     * 查询轮播位
     */
    List<Carousel> findCarousel();

    /**
     * 删除图片
     */
    int deleteProductPictureById(Integer ppe_id);


}
