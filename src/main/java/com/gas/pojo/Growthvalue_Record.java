package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/13
 * Time: 10:39
 * Description: 会员成长值变动记录   2.0 版本新增
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Growthvalue_Record {

    private Integer gvr_id;//	主键

    private Integer gvr_valuenum;//	变动成长值

    private Date gvr_date;//	变动日期

    private Integer gvr_type;//	变动类型

    private Integer gvr_wu_id;//	用户外键
}
