package com.gas.dao;

import com.gas.pojo.Pointegers_item;
import com.gas.pojo.Points_lottery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 查询积分商品
     */
    List<Pointegers_item> findIntegralProduct(Pointegers_item pointegersItem);

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
}
