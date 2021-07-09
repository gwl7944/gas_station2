package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.pojo
 * @version: 1.0
 */

/**
 * 活动
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    private Integer activity_id;    //主键

    private String activity_name;   //名称

    private Date activity_start_date;   //开始日期

    private String activity_start_date_str;  //开始日期

    private Date activity_end_date;  //结束日期

    private String activity_end_date_str;   //结束日期

    private Double activity_discount_value;  //折扣值

    private Integer activity_sitecode;  //站点编号

    private Site site;   //站点对象

    private Integer activity_state;  //状态

    private Integer activity_del;  //删除（默认为1   删除为2）

    private String activity_remarks;  //备注

    private Double activity_condition;  //活动条件


}
