package com.gas.service;

import com.gas.pojo.Page;
import com.gas.pojo.Pointegers_item;
import com.gas.pojo.Points_lottery;

import java.util.List;

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
    /**
     * 查询积分商品
     */
    Page<Pointegers_item> findIntegralProduct(Pointegers_item pointegersItem, Integer currentpage, Integer currentnumber);

    /**
     * 新增积分抽奖
     */
    int insertPointsLottery(Points_lottery pointsLottery);
    /**
     * 更新积分抽奖
     */
    int updatePointsLottery(Points_lottery pointsLottery);
    /**
     * 查询积分抽奖
     */
    List<Points_lottery> findPointsLottery(Points_lottery pointsLottery);

    /**
     * 奖品兑换
     */
    int updateintegralConversion(Integer pds_id);
}
