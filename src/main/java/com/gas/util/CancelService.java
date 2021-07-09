package com.gas.util;

import com.alibaba.fastjson.JSON;
import com.jlpay.ext.qrcode.trans.request.CancelRequest;
import com.jlpay.ext.qrcode.trans.response.CancelResponse;
import com.jlpay.ext.qrcode.trans.service.TransExecuteService;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author zhaoyang2
 * 撤销、关单demo
 */
public class CancelService {
	
	/*static{
		//设置系统参数
		TransContants.setJlpayProperty();
	}*/
	
	public static void main(String[] args) {
		//组装请求参数
		CancelRequest request = componentRequestData();
		//交易请求
		CancelResponse response = TransExecuteService.executor(request, CancelResponse.class);
		System.out.println("返回参数=========>"+ JSON.toJSON(response));
		
	}

	private static CancelRequest componentRequestData() {
		CancelRequest request = new CancelRequest();
		//必传字段
		request.setMchId("84944035812A01P");//嘉联分配的商户号
		request.setOrgCode("50265462");//嘉联分配的机构号
		request.setNonceStr("123456789abcdefg");//随机字符串
		request.setOutTradeNo("CX"+RandomStringUtils.randomNumeric(10));//商家系统内部订单号   机构下唯一
		request.setOriOutTradeNo("ZS8512054501");//商家系统原内部订单号   机构下唯一
		request.setMchCreateIp("172.20.6.21");//终端IP
		//非必传字段
		request.setPayType("alipay");//交易类型    wxpay、alipay、unionpay
		request.setVersion("V1.0.1");//版本号
		request.setCharset("UTF-8");//字符集
		request.setSignType("RSA256");//签名方式
		request.setLongitude("113.571558");//经度
		request.setLatitude("22.144889");//纬度
		
		return request;
	}
	
}
