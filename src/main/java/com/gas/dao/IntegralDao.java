package com.gas.dao;

import com.gas.pojo.Pointegers_details;
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

    /**
     * 奖品兑换
     */
    int updateintegralConversion(Integer pds_id);

    /**
     * 奖品兑换
     */
    Pointegers_details findPointegersDetailsById(Integer pds_id);

    /**
     * 更新商品库存
     */
    int updateIntegralProductNum(Integer pds_pim_id);

    /**
     * 根据id查询积分商品信息
     */
    Pointegers_item findIntegralProductById(Integer pds_pim_id);
}
