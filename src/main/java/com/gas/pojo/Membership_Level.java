package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 10:36
 * Description: 会员等级  2.0 版本新增
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership_Level {

    private Integer ml_id;//	主键

    private String ml_name;//	名称

    private Integer ml_upper_limit;//	积分上限

    private Integer ml_level;//	级别

    private Integer ml_sitecode;//	门店外键

    private Integer ml_del;//	删除
}
