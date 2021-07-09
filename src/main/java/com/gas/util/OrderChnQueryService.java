package com.gas.util;

import com.alibaba.fastjson.JSON;
import com.jlpay.ext.qrcode.trans.request.OrderChnQueryRequest;
import com.jlpay.ext.qrcode.trans.response.OrderChnQueryResponse;
import com.jlpay.ext.qrcode.trans.service.TransExecuteService;

/**
 * @author zhaoyang2
 * 订单查询demo
 */
public class OrderChnQueryService {

	//线下测试放开
	/*static{
		//设置系统参数
		TransContants.setJlpayProperty();
	}*/
	
	public static void main(String[] args) {
		//组装请求参数
		OrderChnQueryRequest request = componentRequestData();
		//交易请求
		OrderChnQueryResponse response = TransExecuteService.executor(request, OrderChnQueryResponse.class);
		System.out.println("返回参数=========>"+ JSON.toJSON(response));
		
	}

	private static OrderChnQueryRequest componentRequestData() {
		OrderChnQueryRequest request = new OrderChnQueryRequest();
		//必传字段
		request.setMchId("849177055410007");//嘉联分配的商户号
		request.setOrgCode("2110962173");//嘉联分配的机构号
		request.setNonceStr("123789456");//随机字符串
		request.setOutTradeNo("GZH0137806435");//商家系统内部订单号   机构下唯一
		//非必传字段
		request.setVersion("V1.0.1");//版本号
		request.setCharset("UTF-8");//字符集
		request.setSignType("RSA256");//签名方式
		
		return request;
	}
	
}
