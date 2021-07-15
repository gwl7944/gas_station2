package com.gas.service.impl;


import com.gas.dao.AuthorityDao;
import com.gas.dao.OliInDao;
import com.gas.dao.SiteDao;
import com.gas.pojo.*;
import com.gas.service.AuthorityService;
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
    @Resource
    SiteDao siteDao;





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
            Site site = siteDao.findSiteById(role1.getRole_sitecode());
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
                Site site = siteDao.findSiteById(role1.getRole_sitecode());
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
                Site site = siteDao.findSiteById(role1.getRole_sitecode());
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

    /*@Override
    public int deleteRole_Menu(Integer role_id) {
        return authorityDao.deleteRole_Menu(role_id);
    }*/


    @Override
    public List<Menu> findMenuByLevel(Integer menu_level) {

        return authorityDao.findMenuByLevel(menu_level);
    }



    @Override
    public Site getSiteById(Integer wu_id){
        return siteDao.findSiteById(wu_id);
    }





}
