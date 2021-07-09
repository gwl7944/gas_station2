package com.gas.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jlpay.ext.qrcode.trans.utils.ErrorConstants;
import com.jlpay.ext.qrcode.trans.utils.JlpayException;
import com.jlpay.ext.qrcode.trans.utils.RSA256Utils;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * 验签
 */
@Slf4j
public class DeviceThread implements Runnable{
	//MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxyXKMr/ABvEeDWzI4va2/kO+XRpIQfdoN/xDL2umCNPF7NKP4zn3h7E1bFRHBCcPdpDHq+Tu0BImJwRE1pNJKEdaelG0VPivo6F+wRzf70DhwfrahJrPbPwjPdUdVMp8KQAhwGyG4Nz7WuKF3+xKucGZQyli5ClIXwkzeGMF8Yi9CwE6wVPgu7+i2tZbPKIEzvbtJCYgDPJjXNxS2YKyzXmnXw507b1+zJkONuYTzH5iAbxAkFNBUY+6i33QvUwOzDt4NPXNmUeXR0bCh8SfGM8HJ14/NrD8NcLynxvjiUF0wCQDRzHL9gIv9C1mP/YYde3oIACku2lS8cOUgmhSNQIDAQAB
	public static final  String jlPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAss7k8qMrC4Ln35hSlh4uhBFawIDCUdxEknzG43StjkkiJUxXZj18E2PsushB45p1+CWCs4v9DTcRgvgjNqAX96EJccQ+rstaST7u5sBMNkNheAY6cMi1F0wHqDjlGIZ/7Nj+DDD3LzR0RWK42d7+mGj3nodaNzp4lXaoc6imNM9by6NMN0paSKAdRQ9SD4TBe5T9kzB9xExjbQu6CvsmSVSmVevrryrSt53Ivm693MomOBCew7D4FqZG2IliuHcEzJNYdjwCQ0/8+3RUsnhwGeXhfrNwAZx5R+rC7sxYxF9hyA/5vANLwr6C0T9S6hek+wQP2jb1Z4vucK/Y4BHsUwIDAQAB";

    public DeviceThread(JSONObject json){
    	System.out.println(json);
    	final String SIGN_KEY = "sign";

		Pay pay = new Pay();
		pay.setTransaction_id(json.getString("transaction_id"));
		pay.setStatus(json.getString("status"));
		pay.setOrg_code(json.getString("org_code"));
		pay.setMch_id(json.getString("mch_id"));
		pay.setOut_trade_no(json.getString("out_trade_no"));
		pay.setChn_transaction_id(json.getString("chn_transaction_id"));
		pay.setTotal_fee(json.getString("transaction_id"));
		pay.setOrder_time(json.getString("order_time"));
		pay.setTrans_time(json.getString("trans_time"));
		pay.setTerm_no(json.getString("term_no"));
		pay.setDevice_info(json.getString("device_info"));
		pay.setRemark(json.getString("remark"));
		pay.setOp_user_id(json.getString("op_user_id"));
		pay.setOp_shop_id(json.getString("op_shop_id"));
		pay.setFinnal_amount(json.getString("finnal_amount"));
		pay.setDiscount_amount(json.getString("discount_amount"));
		pay.setDiscount_name(json.getString("discount_name"));
		pay.setCoupon_Info(json.getString("coupon_Info"));
		pay.setPay_type(json.getString("pay_type"));
		pay.setSub_openid(json.getString("sub_openid"));
		pay.setSign(json.getString("sign"));
		System.out.println(pay.toString());
		List<String> keys = new ArrayList<>(json.keySet());
		Collections.sort(keys);
		Map<String, Object> treeMap = new TreeMap<>();
		for (String key : keys) {
			if (!SIGN_KEY.equals(key)) {
				treeMap.put(key, json.get(key));
			}
		}
		boolean flag = RSA256Utils.verify256(JSON.toJSONString(treeMap),   //嘉联公钥
				jlPubKey,
				pay.getSign());
		if (!flag) {
			log.info("验签不通过");
			throw new JlpayException(ErrorConstants.VALIDATE_ERROR_1, "验签不通过");
		} else {
			log.info("验签成功");
		}
    }
    
    
    @Override
    public void run() {
        try {
            for (int i = 0; i <=0; i++) {
                Thread.sleep(1000);
            }
        }catch (Exception e){

        }
    }
}