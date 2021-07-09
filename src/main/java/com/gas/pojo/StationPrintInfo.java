package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/4/1 19:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationPrintInfo {

    private Integer sta_pri_id;  //主键id

    private String site_name;  //站点名称

    private String classes;  //班次

    private String business_day;  //营业日

    private String user_onduty_time_str; //用户开班时间str

    private String user_offduty_time_str; //用户结班时间str

    private String operator;  //操作人

    private Integer site_id;  //站点id

    private String start_date; //开始日期

    private String end_date; //结束日期

    /*-------------------------- 充值  -----------------------------*/

    private Double top_up_amounp;  //充值应收总金额

    private Double paid_amount;  //充值实收总金额

    private Double coupon_amount;  //充值活动优惠总金额

    private Double refund_amount;  //退款金额

    /*-------------------------- 消费  -----------------------------*/
    private Double sales_amount; //消费应收总金额

    private Double sales_official_amount; //消费实收总金额

    private Double sales_coupon_amount; //消费优惠卷优惠总金额

    private Double sales_nocard_amount; //消费无卡支付总金额

    private Double sales_volume;  //石油总销售量

    /*-------------------------- 充值+消费=总成交数 -----------------------------*/
    private Integer fixture_number; //成交数

    /*-------------------------- 明细 -----------------------------*/
    private List<OilPrint> oilPrintList;  //单种石油统计

    private Integer print_num;  //打印次数

}
