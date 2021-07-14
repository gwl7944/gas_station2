package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.ResultCode;
import com.gas.pojo.ResultData;
import com.gas.pojo.User;
import com.gas.pojo.Wechat_users;
import com.gas.service.AuthorityService;
import com.gas.service.UserService;
import com.gas.util.DateTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:58
 * Description: No Description
 */
@RestController
public class UserController {

    @Resource
    AuthorityService authorityService;
    @Resource
    UserService userService;

    /**
     * 登录
     */
    @PostMapping("/AuthorityController/Login")
    public JSON Login(@ModelAttribute User user) {
        User user1 = userService.login(user);
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
        return ResultData.getResponseData(userService.getUserByName(user), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/AuthorityController/findUserById/{id}")
    public JSON findUserById(@PathVariable("id") Integer id) {
        return ResultData.getResponseData(userService.getUserById(id), ResultCode.SYS_SUCCESS);
    }

    /**
     * 查询全部用户
     */
    @PostMapping("/AuthorityController/findAllUser")
    public JSON findAllUser(@ModelAttribute User user, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {
        return ResultData.getResponseData(userService.getAllUser(user, currentpage, currentnumber), ResultCode.SYS_SUCCESS);
    }

    /**
     * 新增用户
     */
    @PostMapping("/AuthorityController/insertUser")
    public JSON insertUser(@RequestBody User user) {

        //查询用户名是否重复
        List<User> user1 = userService.getUserByName(user);
        if (user1.size() > 0) {
            return ResultData.getResponseData(user, ResultCode.USER_ALREADY_REG);
        }
        return ResultData.getResponseData(userService.insertUser(user), ResultCode.SYS_SUCCESS);
    }

    /**
     * 修改用户
     */
    @PostMapping("/AuthorityController/updateUser")
    public JSON updateUser(@RequestBody User user) {
        System.out.println("user----->>>>"+user);
        return ResultData.getResponseData(userService.updateUser(user), ResultCode.SYS_SUCCESS);
    }

    /**
     * 删除用户
     */
    @GetMapping("/AuthorityController/updateUser/{id}")
    public JSON deleteUser(@PathVariable("id") Integer id) {
        return ResultData.getResponseData(userService.deleteUser(id), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据用户id查询用户消费记录
     */
    @PostMapping("/AuthorityController/findRcByUserId")
    public JSON findRcByUserId(@Param("wu_id") Integer wu_id, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {
        return ResultData.getResponseData(userService.findRcByUserId(wu_id, currentpage, currentnumber), ResultCode.SYS_SUCCESS);
    }

    /**
     * 无卡消费记录
     */
    @GetMapping("/AuthorityController/getUserOnOrOffDuty/{user_id}/{user_sitecode}")
    public JSON getUserOnOrOffDuty(@PathVariable("user_id") Integer user_id, @PathVariable("user_sitecode") Integer user_sitecode){
        Map<String,String> map= userService.findUserOnOrOffDuty(user_id,user_sitecode);
        if (map!=null){
            return ResultData.getResponseData(map,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData("",ResultCode.QUERY_ERROR);
    }

    /*---------------------------  微信用户 ------------------------------*/

    /**
     * 查询当前站点下的所有微信用户
     */
    @PostMapping("/AuthorityController/findAllWcUser")
    public JSON findAllWcUser(@ModelAttribute Wechat_users wechatUsers, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {

        return ResultData.getResponseData(userService.findAllWcUser(wechatUsers, currentpage, currentnumber), ResultCode.SYS_SUCCESS);
    }

    /**
     * 根据id查询微信用户
     */
    @GetMapping("/AuthorityController/getWcUserById/{wu_id}")
    public JSON getWcUserById(@PathVariable("wu_id") Integer wu_id){

        Wechat_users wechatUsers = userService.findWcUserById(wu_id);
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
        int i = userService.updateWcUserById(wechatUsers);
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
        int i = userService.deleteWcUserById(wu_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }


    /**
     * 用户上班 下班时间 时间创建
     */
    @GetMapping("/AuthorityController/userOnOrOffDuty/{user_id}/{type}")
    public JSON userOnOrOffDuty(@PathVariable("user_id") Integer user_id,@PathVariable("type") Integer type){
        //System.out.println("user_id>>"+user_id);
        int i = userService.updateUserOnOrOffDuty(user_id,type);
        if (i>0){
            //查询当前用户信息
            User user = userService.getUserById(user_id);
            user.setUser_onduty_time_str(DateTO.getStringDateTime(user.getUser_onduty_time()));
            if (user.getUser_offduty_time()!=null){
                user.setUser_offduty_time_str(DateTO.getStringDateTime(user.getUser_offduty_time()));
            }
            return ResultData.getResponseData(user,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }


}