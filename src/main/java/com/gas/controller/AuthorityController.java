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

}
