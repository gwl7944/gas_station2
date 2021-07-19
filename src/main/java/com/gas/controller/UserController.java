package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.*;
import com.gas.service.AuthorityService;
import com.gas.service.UserService;
import com.gas.util.DateTO;
import com.gas.util.OOS_Util;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@CrossOrigin
public class UserController {


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


    /*---------------------------  会员等级 2.0新增  ------------------------------*/

    /**
     * 新增会员等级
     */
    @PostMapping("/user/addMembershipLevel")
    public JSON addMembershipLevel(@RequestBody Membership_level membershipLevel){

        int i = userService.insertMembershipLevel(membershipLevel);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     *  修改会员等级
     */
    @PostMapping("/user/editMembershipLevel")
    public JSON editMembershipLevel(@RequestBody Membership_level membershipLevel){

        int i = userService.updateMembershipLevel(membershipLevel);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /**
     * 查询全部会员等级
     */
    @GetMapping("/user/membershipLevel")
    public JSON membershipLevel(@ModelAttribute Membership_level membershipLevel){

        List<Membership_level> membershipLevels =  userService.findMembershipLevel(membershipLevel);
        if (membershipLevels.size()>0){
            return ResultData.getResponseData(membershipLevels,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(membershipLevels,ResultCode.QUERY_ERROR);
    }

    /**
     * 删除会员等级
     */
    @DeleteMapping("/user/membershipLevelById/{ml_id}")
    public JSON membershipLevelById(@PathVariable("ml_id") Integer ml_id){
        int i = userService.deleteMemberLevelById(ml_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }

    /*---------------------------   积分规则   系统设置固定 2.0新增  ------------------------------*/
    /**
     * 根据站点查询积分规则
     */
    @GetMapping("/user/integeregralRule/{lr_siteid}")
    public JSON integeregralRule(@PathVariable("lr_siteid") Integer lr_siteid){

        List<Integeregral_rule> integeregralRules = userService.findIntegeregralRule(lr_siteid);

        if (integeregralRules.size()>0){
            return ResultData.getResponseData(integeregralRules,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(integeregralRules,ResultCode.QUERY_ERROR);
    }

    /**
     * 修改积分规则
     */
    @PostMapping("/user/editIntegeregralRule")
    public JSON editIntegeregralRule(@RequestBody Integeregral_rule integeregralRule){
        int i = userService.updateIntegeregralRule(integeregralRule);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /*---------------------------   会员规则   系统设置固定 2.0新增  ------------------------------*/

    /**
     * 根据会员id查询会员规则
     */
    @GetMapping("/user/membershipRules/{mr_ml_id}")
    public JSON membershipRules(@PathVariable("mr_ml_id") Integer mr_ml_id){

        List<Membership_rules> membershipRules = userService.findMembershipRules(mr_ml_id);

        if (membershipRules.size()>0){
            return ResultData.getResponseData(membershipRules,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(membershipRules,ResultCode.QUERY_ERROR);
    }

    /**
     * 修改会员规则
     */
    @PostMapping("/user/editMembershipRules")
    public JSON editMembershipRules(@RequestBody Membership_rules membershipRules){
        System.out.println("membershipRules>>"+membershipRules);
        int i = userService.updateMembershipRules(membershipRules);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /*---------------------------   图片上传 OSS 2.0新增  ------------------------------*/
    /**
     * 图片上传
     */
    @PostMapping("/user/uploadPic")
    public JSON uploadPic(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("type") Integer type) {
        //System.out.println("file>>>>"+file);
        String fileType="";
        if (type.equals(1)) { //积分商品图
            fileType="gas/sp";
        }else if(type.equals(2)) { //商品详情图
            fileType="gas/spdetails";
        }else {  //宣传图
            fileType="gas/pub";
        }
        if (file!=null){
            try {
                String url = OOS_Util.uploadDocuments(file,fileType);
                return ResultData.getResponseData(url,ResultCode.SYS_SUCCESS);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ResultData.getResponseData("",ResultCode.SYS_ERROR);
    }


    /**
     * 图片删除
     */
    @GetMapping("/user/deletePic")
    public JSON deletePic(String pic_name) {
        try {
            String flag = OOS_Util.deleteimg(pic_name);
            if (flag.equals("success")){
                return ResultData.getResponseData("success",ResultCode.SYS_SUCCESS);
            }else {
                return ResultData.getResponseData("fail",ResultCode.SYS_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultData.getResponseData("fail",ResultCode.SYS_ERROR);
    }

    /*---------------------------   图片相关 2.0新增  ------------------------------*/
    /**
     * 新增图片
     */
    @PostMapping("/user/addProductPicture")
    public JSON addProductPicture(@RequestBody Product_Picture productPicture){
        int i = userService.insertProductPicture(productPicture);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }


    /**
     * 根据类型查看图片
     */
    @GetMapping("/user/getProductPicture/{ppe_type}/{ppe_siteid}")
    public JSON getProductPicture(@PathVariable("ppe_type") Integer ppe_type,@PathVariable("ppe_siteid") Integer ppe_siteid,@Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber){

        Page<Product_Picture> productPicturePage = userService.findProductPicture(ppe_type,ppe_siteid,currentpage,currentnumber);

        if (productPicturePage!=null){
            return ResultData.getResponseData(productPicturePage,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(productPicturePage,ResultCode.QUERY_ERROR);
    }

    /**
     * 查询所有轮播位
     */
    @GetMapping("/user/getCarousel")
    public JSON getCarousel(){
        List<Carousel> carousels = userService.findCarousel();
        if (carousels.size()>0){
            return ResultData.getResponseData(carousels,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(carousels,ResultCode.QUERY_ERROR);
    }

    /**
     * 删除图片
     */
    @DeleteMapping("/user/productPicture")
    public JSON productPicture(@RequestParam("ppe_id") Integer ppe_id,@RequestParam("ppe_url") String ppe_url){
        int i = userService.deleteProductPicture(ppe_id,ppe_url);
        if (i>0){
           return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }
}
