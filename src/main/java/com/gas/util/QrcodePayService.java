package com.gas.util;

import com.alibaba.fastjson.JSON;
import com.jlpay.ext.qrcode.trans.request.QrcodePayRequest;
import com.jlpay.ext.qrcode.trans.response.QrcodePayResponse;
import com.jlpay.ext.qrcode.trans.service.TransExecuteService;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author zhaoyang2
 * C2B扫码支付demo
 */
public class QrcodePayService {
	//线下测试放开
	/*static{
		//设置系统参数
		TransContants.setJlpayProperty();
	}*/
	
	public static void main(String[] args) {
		//组装请求参数
		QrcodePayRequest request = componentRequestData();
		//交易请求
		QrcodePayResponse response = TransExecuteService.executor(request, QrcodePayResponse.class);
		System.out.println("返回参数=========>"+ JSON.toJSON(response));
	}

	private static QrcodePayRequest componentRequestData() {
		QrcodePayRequest request = new QrcodePayRequest();
		//必传字段
		request.setMchId("84944035812A01P");//嘉联分配的商户号
		request.setOrgCode("50265462");//嘉联分配的机构号
		request.setNonceStr("123456789abcdefg");//随机字符串
		request.setPayType("jlpay");//交易类型    wxpay、alipay、unionpay, jlpay
		request.setOutTradeNo("ZS"+ RandomStringUtils.randomNumeric(10));//商家系统内部订单号   机构下唯一
		request.setTotalFee("1");//交易金额
		request.setBody("主扫测试0319");//商品名
		request.setTermNo("12345678");//终端号   unionpay时必须8位
		request.setDeviceInfo("80005611");//终端设备号 
		request.setMchCreateIp("172.20.6.21");//终端IP  生产环境需要真实的公网IP
		//request.setNotifyUrl("http://127.0.0.1/qrcode/notify/");//回调地址
		//https://305w1a4671.imdo.co/mutong-wx/WxUser/async.do
		request.setNotifyUrl("https://305w1a4671.imdo.co/applets/async");//回调地址
		request.setAttach("主扫测试0319");//商品描述
		//非必传字段
		request.setVersion("V1.0.1");//版本号
		request.setCharset("UTF-8");//字符集
		request.setSignType("RSA256");//签名方式
		request.setRemark("主扫备注");//备注
		request.setLongitude("113.571558");//经度
		request.setLatitude("22.144889");//纬度
		request.setOpUserId("1001");//操作员
		request.setOpShopId("100001");//门店号
		//request.setPaymentValidTime("20");//订单支付有效时间,默认20分钟

		return request;
	}

}
