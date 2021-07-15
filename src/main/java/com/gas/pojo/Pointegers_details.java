package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 10:54
 * Description: 用户积分详情  2.0 版本新增
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pointegers_details {

    private Integer pds_id;//	主键

    private Integer pds_type;//	类型

    private Integer pds_operation;//	操作

    private String pds_project;//	项目

    private Date pds_date;//	日期

    private Integer pds_num;//	变化数值

    private Integer pds_wu_id;//	用户外键
}
