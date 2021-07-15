package com.gas.service.impl;

import com.gas.dao.AuthorityDao;
import com.gas.dao.OliInDao;
import com.gas.dao.SiteDao;
import com.gas.dao.UserDao;
import com.gas.pojo.*;
import com.gas.service.UserService;
import com.gas.util.DateTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 17:09
 * Description: No Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    AuthorityDao authorityDao;
    @Resource
    SiteDao siteDao;
    @Resource
    OliInDao oliInDao;

    @Override
    public User login(User user) {
        System.out.println("user》》"+user);
        User userByName = userDao.findUserByName(user.getUser_loginname());
        if (userByName != null && user.getUser_pwd()!="" && user.getUser_pwd()!=null) {
            if (userByName.getUser_pwd().equals(user.getUser_pwd())) {
                Role roleById = authorityDao.findRoleById(userByName.getUser_role_id());
                List<Menu> menuByRoleId = authorityDao.findMenuByRoleId(userByName.getUser_role_id());
                roleById.setMenuList(menuByRoleId);
                userByName.setRole(roleById);
                //查询站点信息
                Site site = siteDao.findSiteById(userByName.getUser_sitecode());
                userByName.setSite(site);
                return userByName;
            }
        }
        return null;
    }

    @Override
    public List<User> getUserByName(User user) {
        List<User> userList = userDao.findUserByNs(user);
        //根据id查询角色名称
        if (user != null) {
            for (User user1 : userList) {
                Role role = authorityDao.findRoleById(user1.getUser_role_id());
                user.setUser_role_name(role.getRole_name());
            }
        }
        return userList;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userDao.findUserById(id);
        //根据id查询角色名称
        Role role = authorityDao.findRoleById(user.getUser_role_id());
        if (role!=null){
            user.setUser_role_name(role.getRole_name());
        }
        return user;
    }

    @Override
    public Page<User> getAllUser(User user, Integer currentpage, Integer currentnumber) {
        Page<User> page = new Page<>();
        if (currentpage < 0) {
            List<User> allUser = userDao.findAllUser(user);
            for (User user1 : allUser) {
                //根据id查询角色名称
                Role role = authorityDao.findRoleById(user1.getUser_role_id());
                if (role != null) {
                    user1.setUser_role_name(role.getRole_name());
                }
                //查询所属站点
                Site site = siteDao.findSiteById(user1.getUser_sitecode());
                user1.setSite(site);
            }
            page.setDatalist(allUser);
            return page;
        } else {
            PageHelper.startPage(currentpage, currentnumber);
            List<User> allUser = userDao.findAllUser(user);
            for (User user1 : allUser) {
                //根据id查询角色名称
                Role role = authorityDao.findRoleById(user1.getUser_role_id());
                if (role != null) {
                    user1.setUser_role_name(role.getRole_name());
                }
                //查询所属站点
                Site site = siteDao.findSiteById(user1.getUser_sitecode());
                user1.setSite(site);
            }
            PageInfo<User> info = new PageInfo<>(allUser);
            page.setCurrentnumber(info.getPageNum());
            page.setCurrentpage(currentpage);
            page.setPagecount(info.getPages());
            page.setTotalnumber((int) info.getTotal());
            page.setDatalist(info.getList());
            return page;
        }
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }


    @Override
    public Page<Wechat_users> findAllWcUser(Wechat_users wechatUsers, Integer currentpage, Integer currentnumber) {
        Page<Wechat_users> page = new Page<>();
        if (currentpage < 0) {
            page.setDatalist(findAllWcUserPu(wechatUsers));
            return page;
        } else {
            PageHelper.startPage(currentpage, currentnumber);
            List<Wechat_users> allWcUserPu = findAllWcUserPu(wechatUsers);
            PageInfo<Wechat_users> info = new PageInfo<>(allWcUserPu);
            page.setCurrentnumber(info.getPageNum());
            page.setCurrentpage(currentpage);
            page.setPagecount(info.getPages());
            page.setTotalnumber((int) info.getTotal());
            page.setDatalist(info.getList());
            return page;
        }
    }

    @Override
    public Wechat_users findWcUserById(Integer wu_id) {
        Wechat_users wcUserById = userDao.findWcUserById(wu_id);
        //wcUserById.setWu_birthday_str(DateTO.getStringDate(wcUserById.getWu_birthday()));
        //查询所属站点
        Site site = siteDao.findSiteById(wcUserById.getWu_sitecode());
        wcUserById.setSite(site);
        return wcUserById;
    }

    @Override
    public int updateWcUserById(Wechat_users wechatUsers) {
        //wechatUsers.setWu_birthday(DateTO.getDate(wechatUsers.getWu_birthday_str()));
        return userDao.updateWcUserById(wechatUsers);
    }

    @Override
    public int deleteWcUserById(Integer wu_id) {
        return userDao.deleteWcUserById(wu_id);
    }


    @Override
    public Page<Records_consumption> findRcByUserId(Integer wu_id, Integer currentpage, Integer currentnumber) {
        Page<Records_consumption> page = new Page<>();
        DecimalFormat df = new DecimalFormat("0.00");
        if (currentpage < 0) {
            List<Records_consumption> rcByUserId = userDao.findRcByUserId(wu_id);
            for (Records_consumption records_consumption : rcByUserId) {

                Site site = siteDao.findSiteById(records_consumption.getRc_sitecode());
                records_consumption.setRc_sitecode_name(site.getSite_name());
                records_consumption.setRc_Date_str(DateTO.getStringDateTime(records_consumption.getRc_datetime()));
                records_consumption.setSite(site);

                Oil_price oilPrice = oliInDao.findOliInfoById(records_consumption.getRc_consumer_projects_code());
                records_consumption.setOilPrice(oilPrice);
            }
            page.setDatalist(rcByUserId);
            return page;
        } else {
            PageHelper.startPage(currentpage, currentnumber);
            List<Records_consumption> rcByUserId = userDao.findRcByUserId(wu_id);

            for (Records_consumption records_consumption : rcByUserId) {
                Site site = siteDao.findSiteById(records_consumption.getRc_sitecode());
                records_consumption.setSite(site);
                records_consumption.setRc_sitecode_name(site.getSite_name());
                records_consumption.setRc_Date_str(DateTO.getStringDateTime(records_consumption.getRc_datetime()));
                Oil_price oilPrice = oliInDao.findOliInfoById(records_consumption.getRc_consumer_projects_code());
                records_consumption.setOilPrice(oilPrice);
            }
            PageInfo<Records_consumption> info = new PageInfo<>(rcByUserId);
            page.setCurrentnumber(info.getPageNum());
            page.setCurrentpage(currentpage);
            page.setPagecount(info.getPages());
            page.setTotalnumber((int) info.getTotal());
            page.setDatalist(info.getList());
            return page;
        }
    }





    @Override
    public int updateUserOnOrOffDuty(Integer user_id, Integer type) {
        Date time = new Date();
        User user = new User();
        user.setUser_id(user_id);
        User user1 = userDao.findUserById(user_id);
        if (user1!=null){
            user.setUser_sitecode(user1.getUser_sitecode());
            user.setUser_classes(user1.getUser_classes());
        }
        int i=0;
        if (type==1){ //上班
            user.setUser_onduty_time(time);
            i= userDao.updateUserOnOrOffDuty(user);
        }else {
            user.setUser_offduty_time(time);
            i= userDao.updateUserOnOrOffDuty(user);
        }
        return i;
    }

    @Override
    public Map<String, String> findUserOnOrOffDuty(Integer user_id, Integer user_sitecode) {
        User user =  userDao.findUserOnOrOffDuty(user_id,user_sitecode);
        Map<String, String> map = new HashMap<>();
        if (user!=null){
            if (user.getUser_onduty_time()==null){
                map.put("onduty","1"); //未开班
                map.put("user_onduty_time","");
            }else {
                map.put("onduty","2");//已开班
                map.put("user_onduty_time", DateTO.getStringDateTime(user.getUser_onduty_time()));
            }
            if (user.getUser_offduty_time()==null){
                map.put("offduty","3");  //未结班
                map.put("user_offduty_time","");
            }else{
                map.put("offduty","4");  //已结班
                map.put("user_offduty_time",DateTO.getStringDateTime(user.getUser_offduty_time()));
            }
        }
        return map;
    }


    // ------------------------- 公共方法 -------------------------------------------
    public List<Wechat_users> findAllWcUserPu(Wechat_users wechatUsers) {
        List<Wechat_users> wechat_users = userDao.findWcUser(wechatUsers);
        System.out.println("wechat_users1>>"+wechat_users);
        for (Wechat_users wechat_user : wechat_users) {
            /*if (wechat_user.getWu_birthday()!=null){
                wechat_user.setWu_birthday_str(DateTO.getStringDate(wechat_user.getWu_birthday()));
            }*/
            //查询所属站点
            Site site = siteDao.findSiteById(wechat_user.getWu_sitecode());
            wechat_user.setSite(site);
        }
        System.out.println("wechat_users>>"+wechat_users);
        return wechat_users;
    }
}
