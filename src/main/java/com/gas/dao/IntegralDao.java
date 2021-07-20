package com.gas.dao;

import com.gas.pojo.Pointegers_item;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/17
 * Time: 17:44
 * Description: No Description
 */
@Mapper
public interface IntegralDao {

    /**
     * 新增积分商品
     */
    int insertIntegralProduct(Pointegers_item pointegersItem);

    /**
     * 修改积分商品
     */
    int updateIntegralProduct(Pointegers_item pointegersItem);
}
