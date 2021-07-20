package com.gas.dao;

import com.gas.pojo.Product_picture;
import com.gas.pojo.Records_consumption;
import com.gas.pojo.User;
import com.gas.pojo.Wechat_users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 17:10
 * Description: No Description
 */
@Mapper
public interface UserDao {

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

    List<Records_consumption> findRcByUserId(Integer wu_id);

    List<Wechat_users> findWcUser(Wechat_users wechatUsers);

    List<User> findUserByNs(User user);

    int deleteWcUserById(Integer wu_id);

    int updateUserOnOrOffDuty(User user);

    User findUserOnOrOffDuty(@Param("user_id") Integer user_id, @Param("user_sitecode") Integer user_sitecode);

    Wechat_users findWcUserById(Integer wu_id);

    int updateWcUserById(Wechat_users wechatUsers);

    int updateReByRcNum(String rc_number, String data);

    int insertProductPicture(Product_picture product_picture);






}
