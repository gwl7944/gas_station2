package com.gas.dao;

import com.gas.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.dao
 * @version: 1.0
 */
@Mapper
public interface AuthorityDao {

    /**
     * 根据用户名查询用户
     * */
    User findUserByName(String loginname);

    /**
     * 根据ID查询用户
     * */
    User findUserById(Integer id);

    /**
     * 查询全部用户
     * */
    List<User> findAllUser(User user);

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
    Role findRoleByName(String rolename);

    /**
     * 根据ID查询角色
     * */
    Role findRoleById(Integer id);

    /**
     * 查询全部角色
     * */
    List<Role> findAllRole(Role role);

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
    Menu findMenuByName(String menuname);

    /**
     * 根据ID查询资源
     * */
    Menu findMenuById(Integer id);

    /**
     * 根据roleID查询资源
     * */
    List<Menu> findMenuByRoleId(Integer role_id);

    /**
     * 查询全部资源
     * */
    List<Menu> findAllMenu();

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


    List<Records_consumption> findRcByUserId(Integer wu_id);

    Site findSiteById(Integer rc_sitecode);

    List<Wechat_users> findWcUser(Wechat_users wechatUsers);

    List<Site> findSite();

    List<Menu> findMenuByLevel(Integer menu_level);

    List<User> findUserByNs(User user);

    int addSite(Site site);

    int updateSite(Site site);

    int deleteSite(Integer site_id);

    int deleteMenuByRoId(Integer role_id);

    List<Oil_price> findAllOliInfoBySite(Integer site_id);

    Oil_price findOliInfoById(Integer oil_id);

    int insertOliInfo(Oil_price oilPrice);

    int updateOliInfo(Oil_price oilPrice);

    int deleteOliInfoById(Integer oil_id);

    Wechat_users findWcUserById(Integer wu_id);

    int updateWcUserById(Wechat_users wechatUsers);

    int deleteWcUserById(Integer wu_id);

    int insertCoupon(Coupon coupon);

    List<Coupon> findAllCouponBySite(Coupon coupon);

    int deleteCouponById(Integer coupon_id);

    int updateCouponById(Coupon coupon);

    int insertActivity(Activity activity);

    int updateActivity(Activity activity);

    List<Activity> findAllActivityBySite(Activity activity);

    int deleteActivityById(Integer activity_id);

    int updateUserOnOrOffDuty(User user);

    Activity findActivityById(Integer rc_activity);

    Coupon findCouponById(Integer rc_coupon);

    User findUserOnOrOffDuty(@Param("user_id") Integer user_id, @Param("user_sitecode") Integer user_sitecode);

    int updateReByRcNum(String rc_number, String data);
}
