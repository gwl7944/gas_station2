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
public class Menu {

    private Integer menu_id;//	资源主键

    private String menu_name;//	资源名称

    private String menu_url;//	接口地址

    private Integer menu_state;//	状态

    private Integer menu_del;//	删除标识

    private String menu_remark;//	备注

    private Integer menu_level;//	级别

    private Integer menu_fid;//	父资源ID

    private Menu menu;  //资源对象

    private Integer menu_category;//	菜单类型

    private List<Role> roleList;


}
