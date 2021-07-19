package com.gas.service;

import com.gas.pojo.Pointegers_Item;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/17
 * Time: 17:43
 * Description: No Description
 */
public interface IntegralService {
    /**
     * 新增积分商品
     */
    Integer insertIntegralProduct(Pointegers_Item pointegersItem);
    /**
     * 修改积分商品
     */
    Integer  updateIntegralProduct(Pointegers_Item pointegersItem);
}
