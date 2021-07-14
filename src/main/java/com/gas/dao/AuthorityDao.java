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


    int insertRole_Menu(Integer role_id,Integer menu_id);

    List<Menu> findMenuByLevel(Integer menu_level);

    int deleteMenuByRoId(Integer role_id);



}
