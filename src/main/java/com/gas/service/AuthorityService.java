package com.gas.service;

import com.gas.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.service
 * @version: 1.0
 */

public interface AuthorityService {


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

/**----------------------------------------------------------------------------*/
    /**
     * 根据角色名查询角色
     * */
    Role getRoleByName(String rolename);

    /**
     * 根据ID查询角色
     * */
    Role getRoleById(Integer id);

    /**
     * 查询全部角色
     * */
    List<Role> getAllRole(Role role);

    /**
     * 查询全部角色(分页)
     * */
    Page<Role> getAllRolePage(Role role, Integer currentpage, Integer currentnumber);

    /**
     * 新增角色
     * */
    int insertRole(Role role);

    /**
     * 修改角色
     * */
    int updateRole(Role role);

    /**
     * 删除角色
     * */
    int deleteRole(Integer role_id);

/**----------------------------------------------------------------------------*/

    /**
     * 根据资源名查询资源
     * */
    Menu getMenuByName(String menuname);

    /**
     * 根据ID查询资源
     * */
    Menu getMenuById(Integer id);

    /**
     * 根据roleID查询资源
     * */
    List<Menu> findMenuByRoleId(Integer role_id);

    /**
     * 查询全部资源
     * */
    List<Menu> getAllMenu();

    /**
     * 新增资源
     * */
    int insertMenu(Menu menu);

    /**
     * 修改资源
     * */
    int updateMenu(Menu menu);

    /**
     * 删除资源
     * */
    int deleteMenu(Integer menu_id);

    /**----------------------------------------------------------------------------*/

    int insertRole_Menu(Integer role_id,Integer menu_id);

    int deleteRole_Menu(Integer role_id);

    Page<Site> findSite(Integer currentpage,Integer currentnumber);

    List<Site> findSiteNoPage();

    List<Menu> findMenuByLevel(Integer menu_level);

    int addSite(Site site);

    int updateSite(Site site);

    int deleteSite(Integer site_id);

    Page<Oil_price> findAllOliInfoBySite(Integer site_id, Integer currentpage, Integer currentnumber);

    Oil_price findOliInfoById(Integer oil_id);

    int insertOliInfo(Oil_price oilPrice);

    int updateOliInfo(Oil_price oilPrice);

    int deleteOliInfoById(Integer oil_id);

    /* ----------------  微信用户管理 ----------------*/
    Page<Wechat_users> findAllWcUser(Wechat_users wechatUsers, Integer currentpage, Integer currentnumber);

    Wechat_users findWcUserById(Integer wu_id);

    int updateWcUserById(Wechat_users wechatUsers);

    int deleteWcUserById(Integer wu_id);
    /* ----------------  消费记录 ----------------*/

    Page<Records_consumption> findRcByUserId(Integer wu_id, Integer currentpage, Integer currentnumber);

    /* ----------------  优惠卷 ----------------*/
    int insertCoupon(Coupon coupon);

    Page<Coupon> findAllCouponBySite(Coupon coupon, Integer currentpage, Integer currentnumber);

    int deleteCouponById(Integer coupon_id);

    int updateCouponById(Coupon coupon);

    /* ----------------  活动 ----------------*/
    int insertActivity(Activity activity);

    int updateActivity(Activity activity);

    Page<Activity> findAllActivityBySite(Activity activity, Integer currentpage, Integer currentnumber);

    int deleteActivityById(Integer activity_id);

    int updateUserOnOrOffDuty(Integer user_id, Integer type);

    Map<String, String> findUserOnOrOffDuty(Integer user_id, Integer user_sitecode);

    Site getSiteById(Integer wu_id);
}
