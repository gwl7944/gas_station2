package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.pojo
 * @ClassName: JlOrder
 * @Author: ywj
 * @Description:
 * @Date: 2021/3/1 16:00
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JlOrder {

    private Integer id;

    private String ret_code; //返回码

    private String ret_msg; //描述信息

    private String sign;//签名

    private String status;//状态

    private String mch_id;//商户号

    private String org_code;//机构号

    private String term_no;//终端号

    private String device_info;//终端设备号

    private String transaction_id;//平台订单号

    private String out_trade_no;//外部订单号

    private String total_fee;//交易金额

    private String pay_info;//支付信息

    private String order_time;//订单时间

    private String pay_type;//交易类型

    private String trans_time;//交易时间
}
