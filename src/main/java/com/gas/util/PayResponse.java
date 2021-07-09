package com.gas.util;

import com.jlpay.ext.qrcode.trans.response.TransBaseResponse;

import java.io.Serializable;



public class PayResponse extends TransBaseResponse implements Serializable{

	/**   
	 * @Fields         serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 * @author:        嘉联支付
	 * @date:          2020年5月19日 上午9:42:45  
	 */   
	private static final long serialVersionUID = 1L;
	
	private String retCode;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public PayResponse(String retCode) {
		super();
		this.retCode = retCode;
	}

	public PayResponse() {
		super();
		
	}
	
	

}
