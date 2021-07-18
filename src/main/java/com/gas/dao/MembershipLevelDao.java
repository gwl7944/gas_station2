package com.gas.dao;

import com.gas.pojo.Integeregral_rule;
import com.gas.pojo.Membership_level;
import com.gas.pojo.Membership_rules;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/16
 * Time: 9:42
 * Description: 会员等级  2.0新增
 */
@Mapper
public interface MembershipLevelDao {

    /**
     * 新增会员等级
     */
    public int insertMembershipLevel(Membership_level membershipLevel);

    /**
     * 修改会员等级
     */
    int updateMembershipLevel(Membership_level membershipLevel);

    /**
     * 查询会员等级
     */
    List<Membership_level> findMembershipLevel(Membership_level membershipLevel);

    /**
     * 删除会员等级
     */
    int deleteMemberLevelById(Integer ml_id);

    /**
     * 查询积分规则
     */
    List<Integeregral_rule> findIntegeregralRule(Integer lr_siteid);

    /**
     * 修改积分规则
     */
    int updateIntegeregralRule(Integeregral_rule integeregralRule);

    /**
     * 查询会员积分规则
     */
    List<Membership_rules> findMembershipRules(Integer mr_ml_id);

    /**
     * 修改会员积分规则
     */
    int updateMembershipRules(Membership_rules membershipRules);

    /**
     * 根据id查询会员
     */
    Membership_level findMembershipLevelById(Integer mr_ml_id);
}
