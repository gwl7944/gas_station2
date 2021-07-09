package com.gas.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.pojo
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer role_id;//	主键

    private String role_name;//	角色名称

    private Integer role_state;//	状态

    private String role_remark;//	备注

    private Integer role_del;//	删除标识

    private Integer role_type; //角色标识

    private List<Menu> menuList; //菜单集合

    private Integer role_sitecode;  //站点外键

    private Site site;   //站点对象


}
