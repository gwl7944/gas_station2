package com.gas.service;

import com.gas.pojo.Page;
import com.gas.pojo.Pointegers_item;

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
    Integer insertIntegralProduct(Pointegers_item pointegersItem);
    /**
     * 修改积分商品
     */
    Integer  updateIntegralProduct(Pointegers_item pointegersItem);

    Page<Pointegers_item> findIntegralProduct(Pointegers_item pointegersItem, Integer currentpage, Integer currentnumber);
}
