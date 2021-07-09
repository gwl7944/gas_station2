package com.gas.controller;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.controller
 * @version: 1.0
 */

import com.alibaba.fastjson.JSON;
import com.gas.pojo.*;
import com.gas.service.AuthorityService;
import com.gas.util.DateTO;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * PC 权限部分接口
 */
@RestController
@CrossOrigin
public class AuthorityController {

    @Resource
    AuthorityService authorityService;

    /**
     * 登录
     */
    @PostMapping("/AuthorityController/Login")
    public JSON Login(@ModelAttribute User user) {
        User user1 = authorityService.login(user);
        if (user1 != null) {
            return ResultData.getResponseData(user1, ResultCode.LOGIN_SUCCESS);
        }
        return ResultData.getResponseData(user, ResultCode.USER_PWD_ERROR);
    }

    /* ---------------------------  用户 -----------------------------*/
    /**
     * 根据用户名查询用户
     */
    @PostMapping("/AuthorityController/findUserByName")
    public JSON findUserByName(@ModelAttribute User user) {
        return ResultData.getResponseData(authorityService.getUserByName(user), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/AuthorityController/findUserById/{id}")
    public JSON findUserById(@PathVariable("id") Integer id) {
        return ResultData.getResponseData(authorityService.getUserById(id), ResultCode.SYS_SUCCESS);
    }

    /**
     * 查询全部用户
     */
    @PostMapping("/AuthorityController/findAllUser")
    public JSON findAllUser(@ModelAttribute User user, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {
        return ResultData.getResponseData(authorityService.getAllUser(user, currentpage, currentnumber), ResultCode.SYS_SUCCESS);
    }

    /**
     * 新增用户
     */
    @PostMapping("/AuthorityController/insertUser")
    public JSON insertUser(@RequestBody User user) {

        //查询用户名是否重复
        List<User> user1 = authorityService.getUserByName(user);
        if (user1.size() > 0) {
            return ResultData.getResponseData(user, ResultCode.USER_ALREADY_REG);
        }
        return ResultData.getResponseData(authorityService.insertUser(user), ResultCode.SYS_SUCCESS);
    }

    /**
     * 修改用户
     */
    @PostMapping("/AuthorityController/updateUser")
    public JSON updateUser(@RequestBody User user) {
        System.out.println("user----->>>>"+user);
        return ResultData.getResponseData(authorityService.updateUser(user), ResultCode.SYS_SUCCESS);
    }

    /**
     * 删除用户
     */
    @GetMapping("/AuthorityController/updateUser/{id}")
    public JSON deleteUser(@PathVariable("id") Integer id) {
        return ResultData.getResponseData(authorityService.deleteUser(id), ResultCode.SYS_SUCCESS);
    }

    /* ---------------------------  角色  -----------------------------*/
    /**
     * 根据角色名查询角色
     */
    @GetMapping("/AuthorityController/findRoleByName/{loginname}")
    public JSON findRoleByName(@PathVariable("loginname") String loginname) {
        return ResultData.getResponseData(authorityService.getRoleByName(loginname), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据ID查询角色
     */
    @GetMapping("/AuthorityController/findRoleById/{id}")
    public JSON findRoleById(@PathVariable("id") Integer id) {
        return ResultData.getResponseData(authorityService.getRoleById(id), ResultCode.SYS_SUCCESS);
    }

    /**
     * 查询全部角色
     */
    @PostMapping("/AuthorityController/findAllRole")
    public JSON findAllRole(@ModelAttribute Role role) {
        return ResultData.getResponseData(authorityService.getAllRole(role), ResultCode.SYS_SUCCESS);
    }

    /**
     * 查询全部角色(分页)
     */
    @PostMapping("/AuthorityController/findAllRolePage")
    public JSON findAllRolePage(@ModelAttribute Role role, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {
        return ResultData.getResponseData(authorityService.getAllRolePage(role, currentpage, currentnumber), ResultCode.SYS_SUCCESS);
    }

    /**
     * 新增角色
     */
    @PostMapping("/AuthorityController/insertRole")
    public JSON insertRole(@RequestBody Role role) {
        if (authorityService.insertRole(role) > 0) {
            int num = 0;
            for (Menu menu : role.getMenuList()) {
                if (authorityService.insertRole_Menu(role.getRole_id(), menu.getMenu_id()) < 1) {
                    num++;
                }
            }
            if (num == 0) {
                return ResultData.getResponseData(num, ResultCode.INSERT_SUCCESS);
            }
        }
        return ResultData.getResponseData(null, ResultCode.INSERT_ERROR);
    }

    /**
     * 修改角色
     */
    @PostMapping("/AuthorityController/updateRole")
    public JSON updateRole(@RequestBody Role role) {
        return ResultData.getResponseData(authorityService.updateRole(role), ResultCode.SYS_SUCCESS);
    }

    /**
     * 删除角色
     */
    @GetMapping("/AuthorityController/updateRole/{id}")
    public JSON deleteRole(@PathVariable("id") Integer id) {
        return ResultData.getResponseData(authorityService.deleteRole(id), ResultCode.SYS_SUCCESS);
    }

    /* ---------------------------  菜单 -----------------------------*/
    /**
     * 根据资源名查询资源
     */
    @GetMapping("/AuthorityController/findMenuByName/{loginname}")
    public JSON findMenuByName(@PathVariable("loginname") String loginname) {
        return ResultData.getResponseData(authorityService.getMenuByName(loginname), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据ID查询资源
     */
    @GetMapping("/AuthorityController/findMenuById/{id}")
    public JSON findMenuById(@PathVariable("id") Integer id) {
        return ResultData.getResponseData(authorityService.getMenuById(id), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据roleID查询资源
     */
    @GetMapping("/AuthorityController/findMenuByRoleId/{role_id}")
    public JSON findMenuByRoleId(@PathVariable("role_id") Integer role_id) {
        return ResultData.getResponseData(authorityService.findMenuByRoleId(role_id), ResultCode.SYS_SUCCESS);
    }

    /**
     * 查询全部资源
     */
    @PostMapping("/AuthorityController/findAllMenu")
    public JSON findAllMenu() {
        return ResultData.getResponseData(authorityService.getAllMenu(), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据级别查询
     */
    @GetMapping("/AuthorityController/findMenuByLevel/{menu_level}")
    public JSON findMenuByLevel(@PathVariable("menu_level") Integer menu_level) {
        return ResultData.getResponseData(authorityService.findMenuByLevel(menu_level), ResultCode.SYS_SUCCESS);
    }

    /**
     * 新增资源
     */
    @PostMapping("/AuthorityController/insertMenu")
    public JSON insertMenu(@RequestBody Menu menu) {
        return ResultData.getResponseData(authorityService.insertMenu(menu), ResultCode.SYS_SUCCESS);
    }

    /**
     * 修改资源
     */
    @PostMapping("/AuthorityController/updateMenu")
    public JSON updateMenu(@RequestBody Menu menu) {
        return ResultData.getResponseData(authorityService.updateMenu(menu), ResultCode.SYS_SUCCESS);
    }

    /**
     * 删除资源
     */
    @GetMapping("/AuthorityController/updateMenu/{id}")
    public JSON deleteMenu(@PathVariable("id") Integer id) {
        return ResultData.getResponseData(authorityService.deleteMenu(id), ResultCode.SYS_SUCCESS);
    }


    /* ---------------------------  站点(超管)-----------------------------*/

    /**
     * 查询所有站点
     */
    @GetMapping("/AuthorityController/findSitePage")
    public JSON findSitePage(@Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {
        Page<Site> page = authorityService.findSite(currentpage, currentnumber);
        return ResultData.getResponseData(page, ResultCode.SYS_SUCCESS);
    }

    /**
     * 查询所有站点
     */
    @GetMapping("/AuthorityController/findSite")
    public JSON findSite() {
        List<Site> siteList = authorityService.findSiteNoPage();
        return ResultData.getResponseData(siteList, ResultCode.SYS_SUCCESS);
    }

    /**
     * 新增站点
     */
    @PostMapping("/AuthorityController/addSite")
    public JSON addSite(@RequestBody Site site) {
        return ResultData.getResponseData(authorityService.addSite(site), ResultCode.SYS_SUCCESS);
    }

    /**
     * 修改站点
     */
    @PostMapping("/AuthorityController/updateSite")
    public JSON updateSite(@RequestBody Site site) {
        return ResultData.getResponseData(authorityService.updateSite(site), ResultCode.SYS_SUCCESS);
    }

    /**
     * 删除站点
     */
    @GetMapping("/AuthorityController/deleteSite/{site_id}")
    public JSON updateSite(@PathVariable("site_id") Integer site_id) {
        return ResultData.getResponseData(authorityService.deleteSite(site_id), ResultCode.SYS_SUCCESS);
    }

    /*----------------------------------  油价 -------------------------------------*/

    /**
    * 根据站点查询全部油价信息(分页)
    */
    @RequestMapping("/AuthorityController/getAllOliInfoBySite")
    public JSON getAllOliInfoBySite(@RequestParam("site_id") Integer site_id,@Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber){

        Page<Oil_price> oilPricePage = authorityService.findAllOliInfoBySite(site_id,currentpage,currentnumber);
        if (oilPricePage!=null){
            return ResultData.getResponseData(oilPricePage,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(oilPricePage,ResultCode.QUERY_ERROR);
    }

    /**
     * 根据id查询油价信息
     */
    @RequestMapping("/AuthorityController/getOliInfoById")
    public JSON getOliInfoById(@RequestParam("oil_id") Integer oil_id){

        Oil_price oilPrice = authorityService.findOliInfoById(oil_id);
        if (oilPrice!=null){
            return ResultData.getResponseData(oilPrice,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(oilPrice,ResultCode.QUERY_ERROR);
    }

    /**
     * 新增油价
     */
    @PostMapping("/AuthorityController/addOliInfo")
    public JSON addOliInfo(@RequestBody Oil_price oilPrice){

        int i = authorityService.insertOliInfo(oilPrice);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 更新油价
     */
    @PostMapping("/AuthorityController/updateOliInfo")
    public JSON updateOliInfo(@RequestBody Oil_price oilPrice){

        int i = authorityService.updateOliInfo(oilPrice);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /**
     * 删除油价
     */
    @RequestMapping("/AuthorityController/deleteOliInfoById")
    public JSON deleteOliInfoById(@RequestParam("oil_id") Integer oil_id){

        int i = authorityService.deleteOliInfoById(oil_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }



    /*---------------------------  微信用户 ------------------------------*/

    /**
     * 查询当前站点下的所有微信用户
     */
    @PostMapping("/AuthorityController/findAllWcUser")
    public JSON findAllWcUser(@ModelAttribute Wechat_users wechatUsers, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {

        return ResultData.getResponseData(authorityService.findAllWcUser(wechatUsers, currentpage, currentnumber), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据id查询微信用户
     */
    @GetMapping("/AuthorityController/getWcUserById/{wu_id}")
    public JSON getWcUserById(@PathVariable("wu_id") Integer wu_id){

        Wechat_users wechatUsers = authorityService.findWcUserById(wu_id);
        if (wechatUsers!=null){
            return ResultData.getResponseData(wechatUsers,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(wechatUsers,ResultCode.QUERY_ERROR);
    }

    /**
     * 修改微信用户
     */
    @PostMapping("/AuthorityController/updateWcUserById")
    public JSON updateWcUserById(@RequestBody Wechat_users wechatUsers){
        int i = authorityService.updateWcUserById(wechatUsers);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /**
     * 删除微信用户
     */
    @GetMapping("/AuthorityController/deleteWcUserById/{wu_id}")
    public JSON deleteWcUserById(@PathVariable("wu_id") Integer wu_id){
        int i = authorityService.deleteWcUserById(wu_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }

    /*---------------------------  消费记录 ------------------------------*/

    /**
     * 根据用户id查询用户消费记录
     */
    @PostMapping("/AuthorityController/findRcByUserId")
    public JSON findRcByUserId(@Param("wu_id") Integer wu_id, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {
        return ResultData.getResponseData(authorityService.findRcByUserId(wu_id, currentpage, currentnumber), ResultCode.SYS_SUCCESS);
    }

    /**
     * 无卡消费记录
     */
    @GetMapping("/AuthorityController/getUserOnOrOffDuty/{user_id}/{user_sitecode}")
    public JSON getUserOnOrOffDuty(@PathVariable("user_id") Integer user_id,@PathVariable("user_sitecode") Integer user_sitecode){
        Map<String,String> map= authorityService.findUserOnOrOffDuty(user_id,user_sitecode);
        if (map!=null){
            return ResultData.getResponseData(map,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData("",ResultCode.QUERY_ERROR);
    }

    /*---------------------------  优惠卷 ------------------------------*/
    /**
     * 新增优惠卷
     */
    @PostMapping("/AuthorityController/addCoupon")
    public JSON addCoupon(@RequestBody Coupon coupon){

        int i = authorityService.insertCoupon(coupon);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 查询当前站点下的优惠卷
     */
    @PostMapping("/AuthorityController/getAllCouponBySite")
    public JSON getAllCouponBySite(@ModelAttribute Coupon coupon,@RequestParam("currentpage") Integer currentpage,@RequestParam("currentnumber") Integer currentnumber){

        Page<Coupon> couponPage = authorityService.findAllCouponBySite(coupon,currentpage,currentnumber);
        if (couponPage!=null){
            return ResultData.getResponseData(couponPage,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(couponPage,ResultCode.QUERY_ERROR);
    }

    /**
     * 删除优惠卷
     */
    @GetMapping("/AuthorityController/deleteCoupon/{coupon_id}")
    public JSON deleteCoupon(@PathVariable("coupon_id") Integer coupon_id){

        int i = authorityService.deleteCouponById(coupon_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }

    /**
     * 修改优惠卷
     */
    @PostMapping("/AuthorityController/updateCoupon")
    public JSON updateCoupon(@RequestBody Coupon coupon){

        int i = authorityService.updateCouponById(coupon);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /*---------------------------  活动 ------------------------------*/

    /**
     * 新增活动
     */
    @PostMapping("/AuthorityController/insertActivity")
    public JSON insertActivity(@RequestBody Activity activity){

        int i = authorityService.insertActivity(activity);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 修改活动
     */
    @PostMapping("/AuthorityController/updateActivity")
    public JSON updateActivity(@RequestBody Activity activity){

        int i = authorityService.updateActivity(activity);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /**
     * 查询当前站点下的所有活动
     */
    @PostMapping("/AuthorityController/getAllActivityBySite")
    public JSON getAllActivityBySite(@ModelAttribute Activity activity,@RequestParam("currentpage") Integer currentpage,@RequestParam("currentnumber") Integer currentnumber){

        Page<Activity> activityPage = authorityService.findAllActivityBySite(activity,currentpage,currentnumber);
        if (activityPage!=null){
            return ResultData.getResponseData(activityPage,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(activityPage,ResultCode.QUERY_ERROR);
    }

    /**
     * 删除活动
     */
    @GetMapping("/AuthorityController/deleteActivity/{activity_id}")
    public JSON deleteActivity(@PathVariable("activity_id") Integer activity_id){

        int i = authorityService.deleteActivityById(activity_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }

    /*---------------------------  用户上下班 ------------------------------*/

    /**
     * 用户上班 下班时间 时间创建
     */
    @GetMapping("/AuthorityController/userOnOrOffDuty/{user_id}/{type}")
    public JSON userOnOrOffDuty(@PathVariable("user_id") Integer user_id,@PathVariable("type") Integer type){
        //System.out.println("user_id>>"+user_id);
        int i = authorityService.updateUserOnOrOffDuty(user_id,type);
        if (i>0){
            //查询当前用户信息
            User user = authorityService.getUserById(user_id);
            user.setUser_onduty_time_str(DateTO.getStringDateTime(user.getUser_onduty_time()));
            if (user.getUser_offduty_time()!=null){
                user.setUser_offduty_time_str(DateTO.getStringDateTime(user.getUser_offduty_time()));
            }
            return ResultData.getResponseData(user,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }





}
