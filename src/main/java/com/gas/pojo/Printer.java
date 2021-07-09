package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.pojo
 * @ClassName: Printer
 * @Author: gwl
 * @Description: 打印机
 * @Date: 2021/4/24 18:05
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Printer {

    private Integer printer_id;   //主键

    private String printer_user;  //账号名

    private String printer_Identification_code;  //鹅云后台注册账号后生成的UKEY

    private String printer_code;   //编号  打印机编号

    private String printer_alias;   //别名 备注

    private Integer printer_state;   //状态  1、离线。2、在线，工作状态正常。 3、在线，工作状态不正常。

    private Integer printer_del;   //删除

    private Integer printer_state_id; //所属门店

    private Site site;   //门店

    private String printer_key; //识别码

}
