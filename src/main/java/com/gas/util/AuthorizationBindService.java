package com.gas.util;

import com.alibaba.fastjson.JSON;
import com.jlpay.ext.qrcode.trans.request.AuthorizationBindRequest;
import com.jlpay.ext.qrcode.trans.response.AuthorizationBindResponse;
import com.jlpay.ext.qrcode.trans.service.TransExecuteService;

/**
 * @author zhaoyang2
 * 绑定支付目录demo 此接口测试环境无法测试,报文仅做参考
 */
public class AuthorizationBindService {

	//线下测试放开
	/*static{
		//设置系统参数
		TransContants.setJlpayProperty();
	}*/
	
	public static void main(String[] args) {
		//组装请求参数
		AuthorizationBindRequest request = componentRequestData();
		//交易请求
		AuthorizationBindResponse response = TransExecuteService.executor(request, AuthorizationBindResponse.class);
		System.out.println("返回参数=========>"+ JSON.toJSON(response));
	}

	private static AuthorizationBindRequest componentRequestData() {
		AuthorizationBindRequest request = new AuthorizationBindRequest();
		//必传字段
		request.setMchId("849177055410018");//嘉联分配的商户号 849177055410007
		request.setOrgCode("2111097218");//嘉联分配的机构号    2110962173
		request.setNonceStr("398E482C39C243C985C3E8");//随机字符串

		request.setPayType("wxpay");//交易类型    wxpay
		request.setMchCreateIp("118.72.8.247");//终端IP
		//非必传字段
		request.setJsapiPath("https://comeon.lslongyu.com/gasStationH5/index/");//JSAPI支付授权目录  https://comeon.lslongyu.com/gasStationH5/ https://comeon.lslongyu.com/gasStationH5/index/

		request.setSubAppid("");//公众账号ID    军港:wxf1096614416a31b7     国信:wx83f243d532c0e911
		request.setVersion("V1.0.1");//版本号
		request.setCharset("UTF-8");//字符集
		request.setSignType("RSA256");//签名方式
		
		return request;
	}
	
}
