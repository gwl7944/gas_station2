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

/**
 * 站点
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Site {

    private Integer site_id;//	主键

    private String  site_name;//	名称

    private String  site_appid;//	appID

    private String  site_grant_type;//	常量

    private String  site_secret;//	秘钥

    private String site_code;

    private String  site_mch_id;//	商户号

    private String  site_org_code;//	机构号

    private String  site_public_key; // 嘉联公钥  正式

    private String  site_private_key; // 接入方系统私钥  正式

    private String  site_state;//	状态（默认启用为1  禁用为2）

    private Integer site_del;//	删除（默认为1   删除为2）

    private String site_address; //站点位置

    private String site_title_url;  //头像地址

}
