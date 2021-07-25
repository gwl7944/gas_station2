package com.gas.util;


import com.alibaba.fastjson.JSON;
import com.gas.pojo.Records_consumption;
import com.jlpay.ext.qrcode.trans.request.OfficialAccPayRequest;
import com.jlpay.ext.qrcode.trans.response.OfficialAccPayResponse;
import com.jlpay.ext.qrcode.trans.service.TransExecuteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import java.math.BigDecimal;


/**
 * @author zhaoyang2  嘉联支付
 * 公众号支付demo
 */
@Slf4j
public class OfficialAccPayService {

	//线下测试放开
	/*static{
		//设置系统参数
		TransContants.setJlpayProperty();
	}*/

	/**
	 * 本地测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		//组装请求参数
		OfficialAccPayRequest request = componentRequestData();
		//交易请求
		OfficialAccPayResponse response = TransExecuteService.executor(request, OfficialAccPayResponse.class);
		System.out.println("返回参数=========>"+ JSON.toJSON(response));
	}

	private static OfficialAccPayRequest componentRequestData() {
		OfficialAccPayRequest request = new OfficialAccPayRequest();
		//必传字段
		request.setMchId("849177055410018");//嘉联分配的商户号
		request.setOrgCode("2111097218");//嘉联分配的机构号
		request.setNonceStr("123456789abcdefg");//随机字符串
		request.setPayType("wxpay");//交易类型    wxpay
		request.setOutTradeNo("GZH"+RandomStringUtils.randomNumeric(10));//商家系统内部订单号   机构下唯一
		request.setTotalFee("1");//交易金额
		request.setBody("公众号支付测试");//商品名
		request.setTermNo("800056");//终端号
		request.setDeviceInfo("800056");//终端设备号
		request.setMchCreateIp("127.0.0.1");//终端IP 小程序获取  //183.189.150.45
		request.setNotifyUrl("http://127.0.0.1/qrcode/notify/");//回调地址  https://xr.lykj-tech.com/gas/applets/async
		request.setAttach("公众号支付商品描述");//商品描述
		request.setOpenId("oWK_W6RvJJ0B2wvfvfzevjsfD8II");//用户标识,此标识需要动态获取
		request.setSubAppid("wx83f243d532c0e911");//公众账号ID
		//非必传字段
		request.setVersion("V1.0.1");//版本号
		request.setCharset("UTF-8");//字符集
		request.setSignType("RSA256");//签名方式
		request.setRemark("公众号支付备注");//备注
		request.setLongitude("113.571558");//经度
		request.setLatitude("22.144889");//纬度
		request.setOpUserId("1001");//操作员
		request.setOpShopId("100001");//门店号
		//request.setPaymentValidTime("20");//订单支付有效时间,默认20分钟
		return request;
	}

	public OfficialAccPayRequest gasRequestData(Records_consumption recordsConsumption){
		return gasRequestData2(recordsConsumption);
	}

	private static OfficialAccPayRequest gasRequestData2(Records_consumption recordsConsumption) {
		log.info("【商家】入参信息-->"+recordsConsumption);
		OfficialAccPayRequest request = new OfficialAccPayRequest();
		//必传字段
		request.setMchId(recordsConsumption.getMch_id());//嘉联分配的商户号 849177055410007
		request.setOrgCode(recordsConsumption.getOrg_code());//嘉联分配的机构号  2110962173
		request.setNonceStr("123456789abcdefg");//随机字符串
		request.setPayType("wxpay");//交易类型    wxpay
		request.setOutTradeNo(recordsConsumption.getRc_number());//商家系统内部订单号   机构下唯一

		BigDecimal b = new BigDecimal(String.valueOf(recordsConsumption.getRc_amount_payable()));
		BigDecimal c = new BigDecimal(String.valueOf(100));
		Integer money = b.multiply(c).intValue();

		request.setTotalFee(money.toString());//交易金额
		request.setBody(recordsConsumption.getRc_consumer_projects());//商品名
		request.setTermNo("800056");//终端号
		request.setDeviceInfo("800056");//终端设备号
		request.setMchCreateIp(recordsConsumption.getMchCreateIp());//终端IP 小程序获取 recordsConsumption.getMchCreateIp()
		request.setNotifyUrl("https://comeon.lslongyu.com/gas2/applets/async");//回调地址  https://xr.lykj-tech.com/gas/applets/async   http://305ct14671.zicp.vip/applets/async
		request.setAttach("公众号支付商品描述");//商品描述
		request.setOpenId(recordsConsumption.getOpenId());//用户标识,此标识需要动态获取 oMOjl51FtsCh1vEa39tE0qaw2uqU
		//非必传字段
		request.setSubAppid(recordsConsumption.getAppid());//公众账号ID  wxf1096614416a31b7

		request.setVersion("V1.0.1");//版本号
		request.setCharset("UTF-8");//字符集
		request.setSignType("RSA256");//签名方式
		request.setRemark("公众号支付备注");//备注
		request.setLongitude("113.571558");//经度
		request.setLatitude("22.144889");//纬度
		request.setOpUserId("1001");//操作员
		request.setOpShopId("100001");//门店号
		//request.setPaymentValidTime("20");//订单支付有效时间,默认20分钟
		return request;
	}
}