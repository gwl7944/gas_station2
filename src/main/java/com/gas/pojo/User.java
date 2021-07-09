package com.gas.pojo;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.pojo
 * @version: 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * PC用户
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer user_id;//	主键

    private String user_name;//	用户真实姓名

    private String user_loginname;  //登录名

    private String user_pwd;//	密码

    private String user_phone;//	手机号

    private Integer user_sitecode;//	站点编号

    private Site site;  //站点对象

    private String user_id_number;//	用户身份证号

    private Integer user_gender;//	性别（1男  2女）

    private Integer user_del;//	删除（默认为1   删除为2）

    private Integer user_role_id;//	角色ID

    private String user_role_name;//角色名称

    private Role role;   //角色对象

    private Date user_onduty_time; //用户上班时间

    private String user_onduty_time_str; //用户上班时间str

    private Date user_offduty_time;  //用户下班时间

    private String user_offduty_time_str; //用户下班时间str

    private String user_classes;  //班次

    private String user_site_name;  //用户站点班次
}
