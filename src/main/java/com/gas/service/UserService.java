package com.gas.service;

import com.gas.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 17:05
 * Description: No Description
 */
public interface UserService {


    User login(User user);


    /**
     * 根据用户名查询用户
     * */
    List<User> getUserByName(User user);

    /**
     * 根据ID查询用户
     * */
    User getUserById(Integer id);

    /**
     * 查询全部用户
     * */
    Page<User> getAllUser(User user, Integer currentpage, Integer currentnumber);

    /**
     * 新增用户
     * */
    int insertUser(User user);

    /**
     * 修改用户
     * */
    int updateUser(User user);

    /**
     * 删除用户
     * */
    int deleteUser(Integer id);


    /* ----------------  微信用户管理 ----------------*/
    Page<Wechat_users> findAllWcUser(Wechat_users wechatUsers, Integer currentpage, Integer currentnumber);

    Wechat_users findWcUserById(Integer wu_id);

    int updateWcUserById(Wechat_users wechatUsers);

    int deleteWcUserById(Integer wu_id);

    /* ----------------  消费记录 ----------------*/
    Page<Records_consumption> findRcByUserId(Integer wu_id, Integer currentpage, Integer currentnumber);

    int updateUserOnOrOffDuty(Integer user_id, Integer type);

    /**
     * 用户上班 下班时间 时间创建
     */
    Map<String, String> findUserOnOrOffDuty(Integer user_id, Integer user_sitecode);

    /* ----------------  消费记录 2.0新增 ----------------*/

    /**
     * 新增会员等级
     */
    int insertMembershipLevel(Membership_level membershipLevel);

    /**
     * 更新会员等级
     */
    int updateMembershipLevel(Membership_level membershipLevel);

    /**
     * 查找会员等级
     */
    List<Membership_level> findMembershipLevel(Membership_level membershipLevel);

    /**
     * 删除会员等级
     */
    int deleteMemberLevelById(Integer ml_id);

    /* ----------------  积分规则 2.0新增 ----------------*/
    /**
     * 查询积分规则 系统设置固定
     */
    List<Integeregral_rule> findIntegeregralRule(Integer lr_siteid);

    /**
     * 更新积分规则 系统设置固定
     */
    int updateIntegeregralRule(Integeregral_rule integeregralRule);

    /**
     * 查询会员规则 系统设置固定
     */
    List<Membership_rules> findMembershipRules(Integer mr_ml_id);

    /**
     * 更新会员规则 系统设置固定
     */
    int updateMembershipRules(Membership_rules membershipRules);

    /**
     * 新增图片
     */
    int insertProductPicture(Product_picture productPicture);

    /**
     * 查询图片
     */
    Page<Product_picture> findProductPicture(Integer ppe_type, Integer ppe_siteid, Integer currentpage, Integer currentnumber);

    /**
     * 查询轮播位
     */
    List<Carousel> findCarousel();

    /**
     * 删除图片
     */
    int deleteProductPicture(Integer ppe_id, String ppe_url);

    /**
     * 新增充值金额信息
     */
    int insertRecharge(Recharge recharge);

    /**
     * 更新充值金额信息
     */
    int updateRecharge(Recharge recharge);

    /**
     * 查询充值金额信息
     */
    Page<Recharge> findRecharge(Integer rech_site_id, Integer currentpage, Integer currentnumber);

    /**
     * 删除充值金额信息
     */
    int deleteRecharge(Integer rech_id);
}
