package com.gas.service;


import com.gas.dao.AuthorityDao;
import com.gas.pojo.*;
import com.gas.util.DateTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.service
 * @version: 1.0
 */

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Resource
    AuthorityDao authorityDao;


    @Override
    public User login(User user) {
        System.out.println("user》》"+user);
        User userByName = authorityDao.findUserByName(user.getUser_loginname());
        if (userByName != null && user.getUser_pwd()!="" && user.getUser_pwd()!=null) {
            if (userByName.getUser_pwd().equals(user.getUser_pwd())) {
                Role roleById = authorityDao.findRoleById(userByName.getUser_role_id());
                List<Menu> menuByRoleId = authorityDao.findMenuByRoleId(userByName.getUser_role_id());
                roleById.setMenuList(menuByRoleId);
                userByName.setRole(roleById);
                //查询站点信息
                Site site = authorityDao.findSiteById(userByName.getUser_sitecode());
                userByName.setSite(site);
                return userByName;
            }
        }
        return null;
    }

    @Override
    public List<User> getUserByName(User user) {
        List<User> userList = authorityDao.findUserByNs(user);
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
        User user = authorityDao.findUserById(id);
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
            List<User> allUser = authorityDao.findAllUser(user);
            for (User user1 : allUser) {
                //根据id查询角色名称
                Role role = authorityDao.findRoleById(user1.getUser_role_id());
                if (role != null) {
                    user1.setUser_role_name(role.getRole_name());
                }
                //查询所属站点
                Site site = authorityDao.findSiteById(user1.getUser_sitecode());
                user1.setSite(site);
            }
            page.setDatalist(allUser);
            return page;
        } else {
            PageHelper.startPage(currentpage, currentnumber);
            List<User> allUser = authorityDao.findAllUser(user);
            for (User user1 : allUser) {
                //根据id查询角色名称
                Role role = authorityDao.findRoleById(user1.getUser_role_id());
                if (role != null) {
                    user1.setUser_role_name(role.getRole_name());
                }
                //查询所属站点
                Site site = authorityDao.findSiteById(user1.getUser_sitecode());
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
        return authorityDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return authorityDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return authorityDao.deleteUser(id);
    }

    @Override
    public Role getRoleByName(String rolename) {
        return authorityDao.findRoleByName(rolename);
    }

    @Override
    public Role getRoleById(Integer id) {
        return authorityDao.findRoleById(id);
    }

    @Override
    public List<Role> getAllRole(Role role) {


        List<Role> allRole = authorityDao.findAllRole(role);
        for (Role role1 : allRole) {
            List<Menu> list = authorityDao.findMenuByRoleId(role1.getRole_id());
            role1.setMenuList(list);
            //查询站点
            Site site = authorityDao.findSiteById(role1.getRole_sitecode());
            role1.setSite(site);
        }
        return allRole;
    }

    @Override
    public Page<Role> getAllRolePage(Role role, Integer currentpage, Integer currentnumber) {
        Page<Role> page = new Page<>();
        if (currentpage < 0) {
            List<Role> allRole = authorityDao.findAllRole(role);
            for (Role role1 : allRole) {
                List<Menu> list = authorityDao.findMenuByRoleId(role1.getRole_id());
                role1.setMenuList(list);
                //查询站点
                Site site = authorityDao.findSiteById(role1.getRole_sitecode());
                role1.setSite(site);
            }
            page.setDatalist(allRole);
            return page;
        } else {
            PageHelper.startPage(currentpage, currentnumber);
            List<Role> allRole = authorityDao.findAllRole(role);
            for (Role role1 : allRole) {
                List<Menu> list = authorityDao.findMenuByRoleId(role1.getRole_id());
                role1.setMenuList(list);
                //查询站点
                Site site = authorityDao.findSiteById(role1.getRole_sitecode());
                role1.setSite(site);
            }
            PageInfo<Role> info = new PageInfo<>(allRole);
            page.setCurrentnumber(info.getPageNum());
            page.setCurrentpage(currentpage);
            page.setPagecount(info.getPages());
            page.setTotalnumber((int) info.getTotal());
            page.setDatalist(info.getList());
            return page;
        }
    }

    @Override
    public int insertRole(Role role) {
        return authorityDao.insertRole(role);
    }

    @Override
    @Transactional
    public int updateRole(Role role) {
        int i = authorityDao.updateRole(role);

        //删除当前角色下的 的所有菜单
        int i1 = authorityDao.deleteMenuByRoId(role.getRole_id());

        //新增新的菜单
        for (Menu menu : role.getMenuList()) {
            authorityDao.insertRole_Menu(role.getRole_id(), menu.getMenu_id());
        }
        return i;
    }

    @Override
    public int deleteRole(Integer role_id) {
        return authorityDao.deleteRole(role_id);
    }

    @Override
    public Menu getMenuByName(String menuname) {
        return authorityDao.findMenuByName(menuname);
    }

    @Override
    public Menu getMenuById(Integer id) {
        return authorityDao.findMenuById(id);
    }

    @Override
    public List<Menu> findMenuByRoleId(Integer role_id) {
        return authorityDao.findMenuByRoleId(role_id);
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> allMenu = authorityDao.findAllMenu();
        return allMenu;
    }


    @Override
    public int insertMenu(Menu menu) {
        return authorityDao.insertMenu(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return authorityDao.updateMenu(menu);
    }

    @Override
    public int deleteMenu(Integer menu_id) {
        return authorityDao.deleteMenu(menu_id);
    }

    @Override
    public int insertRole_Menu(Integer role_id, Integer menu_id) {
        return authorityDao.insertRole_Menu(role_id, menu_id);
    }

    @Override
    public int deleteRole_Menu(Integer role_id) {
        return authorityDao.deleteRole_Menu(role_id);
    }


    @Override
    public Page<Site> findSite(Integer currentpage, Integer currentnumber) {
        Page<Site> page = new Page<>();
        if (currentpage < 0) {
            page.setDatalist(authorityDao.findSite());
            return page;
        } else {
            PageHelper.startPage(currentpage, currentnumber);
            List<Site> allSite = authorityDao.findSite();
            PageInfo<Site> info = new PageInfo<>(allSite);
            page.setCurrentnumber(info.getPageNum());
            page.setCurrentpage(currentpage);
            page.setPagecount(info.getPages());
            page.setTotalnumber((int) info.getTotal());
            page.setDatalist(info.getList());
            return page;
        }
    }

    @Override
    public List<Site> findSiteNoPage() {
        return authorityDao.findSite();
    }

    @Override
    public List<Menu> findMenuByLevel(Integer menu_level) {

        return authorityDao.findMenuByLevel(menu_level);
    }

    @Override
    public int addSite(Site site) {
        int i = authorityDao.addSite(site);
        return i;
    }

    @Override
    public int updateSite(Site site) {
        int i = authorityDao.updateSite(site);
        return i;
    }

    @Override
    public int deleteSite(Integer site_id) {
        int i = authorityDao.deleteSite(site_id);
        return i;
    }


    @Override
    public Page<Oil_price> findAllOliInfoBySite(Integer site_id, Integer currentpage, Integer currentnumber) {
        Page<Oil_price> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<Oil_price> oilPriceList = authorityDao.findAllOliInfoBySite(site_id);
        for (Oil_price oil_price : oilPriceList) {
            //查询站点
            Site site = authorityDao.findSiteById(oil_price.getOp_sitecode());
            oil_price.setSite(site);
        }
        PageInfo<Oil_price> info = new PageInfo<>(oilPriceList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }

    @Override
    public Oil_price findOliInfoById(Integer oil_id) {
        Oil_price oilPrice = authorityDao.findOliInfoById(oil_id);
        return oilPrice;
    }

    @Override
    public int insertOliInfo(Oil_price oilPrice) {

        return authorityDao.insertOliInfo(oilPrice);
    }

    @Override
    public int updateOliInfo(Oil_price oilPrice) {

        return authorityDao.updateOliInfo(oilPrice);
    }

    @Override
    public int deleteOliInfoById(Integer oil_id) {
        return authorityDao.deleteOliInfoById(oil_id);
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
        Wechat_users wcUserById = authorityDao.findWcUserById(wu_id);
        wcUserById.setWu_birthday_str(DateTO.getStringDate(wcUserById.getWu_birthday()));
        //查询所属站点
        Site site = authorityDao.findSiteById(wcUserById.getWu_sitecode());
        wcUserById.setSite(site);
        return wcUserById;
    }

    @Override
    public int updateWcUserById(Wechat_users wechatUsers) {
        wechatUsers.setWu_birthday(DateTO.getDate(wechatUsers.getWu_birthday_str()));
        return authorityDao.updateWcUserById(wechatUsers);
    }

    @Override
    public int deleteWcUserById(Integer wu_id) {
        return authorityDao.deleteWcUserById(wu_id);
    }


    @Override
    public Page<Records_consumption> findRcByUserId(Integer wu_id, Integer currentpage, Integer currentnumber) {
        Page<Records_consumption> page = new Page<>();
        DecimalFormat df = new DecimalFormat("0.00");
        if (currentpage < 0) {
            List<Records_consumption> rcByUserId = authorityDao.findRcByUserId(wu_id);
            for (Records_consumption records_consumption : rcByUserId) {

                Site site = authorityDao.findSiteById(records_consumption.getRc_sitecode());
                records_consumption.setRc_sitecode_name(site.getSite_name());
                records_consumption.setRc_Date_str(DateTO.getStringDateTime(records_consumption.getRc_datetime()));
                records_consumption.setSite(site);

                Oil_price oilPrice = authorityDao.findOliInfoById(records_consumption.getRc_consumer_projects_code());
                records_consumption.setOilPrice(oilPrice);
            }
            page.setDatalist(rcByUserId);
            return page;
        } else {
            PageHelper.startPage(currentpage, currentnumber);
            List<Records_consumption> rcByUserId = authorityDao.findRcByUserId(wu_id);

            for (Records_consumption records_consumption : rcByUserId) {
                Site site = authorityDao.findSiteById(records_consumption.getRc_sitecode());
                records_consumption.setSite(site);
                records_consumption.setRc_sitecode_name(site.getSite_name());
                records_consumption.setRc_Date_str(DateTO.getStringDateTime(records_consumption.getRc_datetime()));
                Oil_price oilPrice = authorityDao.findOliInfoById(records_consumption.getRc_consumer_projects_code());
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
    public int insertCoupon(Coupon coupon) {
        System.out.println("coupon>>>>"+coupon);
        coupon.setCoupon_term_validity(DateTO.getDate(coupon.getCoupon_term_validity_str())); //开始日期
        coupon.setCoupon_enddate(DateTO.getDate(coupon.getCoupon_enddate_str()));  //截至日期
        return authorityDao.insertCoupon(coupon);
    }

    @Override
    public Page<Coupon> findAllCouponBySite(Coupon coupon,Integer currentpage, Integer currentnumber) {
        Page<Coupon> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<Coupon> couponList = authorityDao.findAllCouponBySite(coupon);
        for (Coupon coupon1 : couponList) {
           coupon1.setCoupon_term_validity_str(DateTO.getStringDate(coupon1.getCoupon_term_validity()));
            //查询所属站点
            Site site = authorityDao.findSiteById(coupon1.getCoupon_sitecode());
            coupon1.setSite(site);

            if (coupon1.getCoupon_term_validity()!=null){
                coupon1.setCoupon_term_validity_str(DateTO.getStringDate(coupon1.getCoupon_term_validity()));
            }
            if (coupon1.getCoupon_enddate()!=null){
                coupon1.setCoupon_enddate_str(DateTO.getStringDate(coupon1.getCoupon_enddate()));
            }
        }
        PageInfo<Coupon> info = new PageInfo<>(couponList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }


    @Override
    public int deleteCouponById(Integer coupon_id) {

        return authorityDao.deleteCouponById(coupon_id);
    }

    @Override
    public int updateCouponById(Coupon coupon) {
        if (coupon.getCoupon_term_validity_str()!=null){
            coupon.setCoupon_term_validity(DateTO.getDate(coupon.getCoupon_term_validity_str()));
            coupon.setCoupon_enddate(DateTO.getDate(coupon.getCoupon_enddate_str()));
        }
        return authorityDao.updateCouponById(coupon);
    }

    @Override
    public int insertActivity(Activity activity) {
        if (activity.getActivity_start_date_str()!=""&&activity.getActivity_start_date_str()!=null){
            activity.setActivity_start_date(DateTO.getDate(activity.getActivity_start_date_str()));
        }
        if (activity.getActivity_end_date_str()!=""&&activity.getActivity_end_date_str()!=null){
            activity.setActivity_end_date(DateTO.getDate(activity.getActivity_end_date_str()));
        }

        return authorityDao.insertActivity(activity);
    }

    @Override
    public int updateActivity(Activity activity) {
        if (activity.getActivity_end_date_str()!=null){
            activity.setActivity_end_date(DateTO.getDate(activity.getActivity_end_date_str()));
        }
        if (activity.getActivity_start_date_str()!=null){
            activity.setActivity_start_date(DateTO.getDate(activity.getActivity_start_date_str()));
        }
        return authorityDao.updateActivity(activity);
    }

    @Override
    public Page<Activity> findAllActivityBySite(Activity activity, Integer currentpage, Integer currentnumber) {

        Page<Activity> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        if (activity.getActivity_end_date_str()!="" && activity.getActivity_end_date_str()!=null){
            activity.setActivity_end_date(DateTO.getDate(activity.getActivity_end_date_str()));
        }

        if (activity.getActivity_start_date_str()!="" && activity.getActivity_start_date_str()!=null){
            activity.setActivity_start_date(DateTO.getDate(activity.getActivity_start_date_str()));
        }

        List<Activity> activityList = authorityDao.findAllActivityBySite(activity);
        for (Activity activity1 : activityList) {
            if(activity1.getActivity_start_date()!=null){
                activity1.setActivity_start_date_str(DateTO.getStringDate(activity1.getActivity_start_date()));
            }
            if (activity1.getActivity_end_date()!=null){
                activity1.setActivity_end_date_str(DateTO.getStringDate(activity1.getActivity_end_date()));
            }
            //查询所属站点
            Site site = authorityDao.findSiteById(activity1.getActivity_sitecode());
            activity1.setSite(site);
        }
        PageInfo<Activity> info = new PageInfo<>(activityList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }

    @Override
    public int deleteActivityById(Integer activity_id) {
        return authorityDao.deleteActivityById(activity_id);
    }

    @Override
    public int updateUserOnOrOffDuty(Integer user_id, Integer type) {
        Date time = new Date();
        User user = new User();
        user.setUser_id(user_id);
        User user1 = authorityDao.findUserById(user_id);
        if (user1!=null){
            user.setUser_sitecode(user1.getUser_sitecode());
            user.setUser_classes(user1.getUser_classes());
        }
        int i=0;
        if (type==1){ //上班
            user.setUser_onduty_time(time);
            i= authorityDao.updateUserOnOrOffDuty(user);
        }else {
            user.setUser_offduty_time(time);
            i= authorityDao.updateUserOnOrOffDuty(user);
        }
        return i;
    }

    @Override
    public Map<String, String> findUserOnOrOffDuty(Integer user_id, Integer user_sitecode) {
        User user =  authorityDao.findUserOnOrOffDuty(user_id,user_sitecode);
        Map<String, String> map = new HashMap<>();
        if (user!=null){
            if (user.getUser_onduty_time()==null){
                map.put("onduty","1"); //未开班
                map.put("user_onduty_time","");
            }else {
                map.put("onduty","2");//已开班
                map.put("user_onduty_time",DateTO.getStringDateTime(user.getUser_onduty_time()));
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

    @Override
    public Site getSiteById(Integer wu_id){
        return authorityDao.findSiteById(wu_id);
    }

    // ------------------------- 公共方法 -------------------------------------------
    public List<Wechat_users> findAllWcUserPu(Wechat_users wechatUsers) {
        List<Wechat_users> wechat_users = authorityDao.findWcUser(wechatUsers);
        for (Wechat_users wechat_user : wechat_users) {
            wechat_user.setWu_birthday_str(DateTO.getStringDate(wechat_user.getWu_birthday()));
            //查询所属站点
            Site site = authorityDao.findSiteById(wechat_user.getWu_sitecode());
            wechat_user.setSite(site);
        }
        return wechat_users;
    }



}
