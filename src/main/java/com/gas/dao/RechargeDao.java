package com.gas.dao;

import com.gas.pojo.Recharge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/20
 * Time: 15:13
 * Description: No Description
 */
@Mapper
public interface RechargeDao {

    /**
     * 新增充值金额信息
     */
    int insertRecharge(Recharge recharge);

    /**
     * 修改充值金额信息
     */
    int updateRecharge(Recharge recharge);

    /**
     * 查询充值金额信息
     */
    List<Recharge> findRecharge(Integer rech_site_id);

    /**
     * 删除充值金额信息
     */
    int deleteRecharge(Integer rech_id);
}
