package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.pojo
 * @ClassName: Member_day
 * @Author: gwl
 * @Description:  会员日
 * @Date: 2021/5/10 10:27
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member_day {

    private Integer med_id;  //主键

    private Integer med_weekday;  //星期值

    private Double med_float_value;  //浮动值

    private Integer med_op_id;  //油品ID

    private Integer med_sitecode;  //站点ID

    private Integer med_del;  //删除

    private Site site; //站点

    private Oil_price oilPrice; //油价


}
