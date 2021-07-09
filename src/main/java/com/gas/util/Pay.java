package com.gas.util;

import java.io.Serializable;
import com.jlpay.ext.qrcode.trans.request.TransBaseRequest;


import com.jlpay.ext.qrcode.trans.request.TransBaseRequest;

import java.io.Serializable;

public class Pay extends TransBaseRequest implements Serializable  {
	/**   
	 * @Fields         serialVersionUID : 支付异步返回值 
	 * @author:        嘉联支付
	 * @date:          2020年5月18日 下午12:00:25  
	 */   
	private static final long serialVersionUID = 1L;
	//状态
	private String status;
	//机构号
	private String org_code;
	//商户号
	private String mch_id;
	//平台订单号
	private String transaction_id;
	//外部订单号
	private String out_trade_no;
	//渠道订单号
	private String chn_transaction_id;
	//交易金额
	private String total_fee;
	//订单时间
	private String order_time;
	//交易时间
	private String trans_time;
	//终端号
	private String term_no;
	//终端设备号
	private String device_info;
	//备注
	private String remark;
	//操作员
	private String op_user_id;
	//门店号
	private String op_shop_id;
	//实际付款金额
	private String finnal_amount;
	//优惠总金额
	private String discount_amount;
	//优惠活动名称
	private String discount_name;
	//优惠信息
	private String coupon_Info;
	//交易类型
	private String pay_type;
	//用户openid
	private String sub_openid;
	//签名
	private String sign;


	public Pay() {
		super();
		
	}

	public Pay(String status, String org_code, String mch_id, String transaction_id, String out_trade_no,
			String chn_transaction_id, String total_fee, String order_time, String trans_time, String term_no,
			String device_info, String remark, String op_user_id, String op_shop_id, String finnal_amount,
			String discount_amount, String discount_name, String coupon_Info, String pay_type, String sub_openid,
			String sign) {
		super();
		this.status = status;
		this.org_code = org_code;
		this.mch_id = mch_id;
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;
		this.chn_transaction_id = chn_transaction_id;
		this.total_fee = total_fee;
		this.order_time = order_time;
		this.trans_time = trans_time;
		this.term_no = term_no;
		this.device_info = device_info;
		this.remark = remark;
		this.op_user_id = op_user_id;
		this.op_shop_id = op_shop_id;
		this.finnal_amount = finnal_amount;
		this.discount_amount = discount_amount;
		this.discount_name = discount_name;
		this.coupon_Info = coupon_Info;
		this.pay_type = pay_type;
		this.sub_openid = sub_openid;
		this.sign = sign;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public String getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	

	public String getTerm_no() {
		return term_no;
	}

	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

	public String getOp_shop_id() {
		return op_shop_id;
	}

	public void setOp_shop_id(String op_shop_id) {
		this.op_shop_id = op_shop_id;
	}

	public String getFinnal_amount() {
		return finnal_amount;
	}

	public void setFinnal_amount(String finnal_amount) {
		this.finnal_amount = finnal_amount;
	}

	public String getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(String discount_amount) {
		this.discount_amount = discount_amount;
	}

	public String getDiscount_name() {
		return discount_name;
	}

	public void setDiscount_name(String discount_name) {
		this.discount_name = discount_name;
	}

	public String getCoupon_Info() {
		return coupon_Info;
	}

	public void setCoupon_Info(String coupon_Info) {
		this.coupon_Info = coupon_Info;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getSub_openid() {
		return sub_openid;
	}

	public void setSub_openid(String sub_openid) {
		this.sub_openid = sub_openid;
	}

	
	
	public String getChn_transaction_id() {
		return chn_transaction_id;
	}

	public void setChn_transaction_id(String chn_transaction_id) {
		this.chn_transaction_id = chn_transaction_id;
	}

	@Override
	public String toString() {
		return "Pay [status=" + status + ", org_code=" + org_code + ", mch_id=" + mch_id + ", transaction_id="
				+ transaction_id + ", out_trade_no=" + out_trade_no + ", chn_transaction_id=" + chn_transaction_id
				+ ", total_fee=" + total_fee + ", order_time=" + order_time + ", trans_time=" + trans_time
				+ ", term_no=" + term_no + ", device_info=" + device_info + ", remark=" + remark + ", op_user_id="
				+ op_user_id + ", op_shop_id=" + op_shop_id + ", finnal_amount=" + finnal_amount + ", discount_amount="
				+ discount_amount + ", discount_name=" + discount_name + ", coupon_Info=" + coupon_Info + ", pay_type="
				+ pay_type + ", sub_openid=" + sub_openid + ", sign=" + sign + "]";
	}





	

	
	
	
	

}
