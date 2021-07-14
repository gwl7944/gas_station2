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

    List<Menu> findMenuByLevel(Integer menu_level);

    Site getSiteById(Integer wu_id);
}
