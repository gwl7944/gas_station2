package com.gas.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jlpay.ext.qrcode.trans.utils.RSA256Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/4/16
 * Time: 11:49
 * Description: No Description
 */
@Slf4j
public class Attestation {

    //public static final  String jlPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAss7k8qMrC4Ln35hSlh4uhBFawIDCUdxEknzG43StjkkiJUxXZj18E2PsushB45p1+CWCs4v9DTcRgvgjNqAX96EJccQ+rstaST7u5sBMNkNheAY6cMi1F0wHqDjlGIZ/7Nj+DDD3LzR0RWK42d7+mGj3nodaNzp4lXaoc6imNM9by6NMN0paSKAdRQ9SD4TBe5T9kzB9xExjbQu6CvsmSVSmVevrryrSt53Ivm693MomOBCew7D4FqZG2IliuHcEzJNYdjwCQ0/8+3RUsnhwGeXhfrNwAZx5R+rC7sxYxF9hyA/5vANLwr6C0T9S6hek+wQP2jb1Z4vucK/Y4BHsUwIDAQAB";

    public JSONObject getAttestation(JSONObject json){
        //System.out.println(json);
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
        //System.out.println(pay.toString());
        List<String> keys = new ArrayList<>(json.keySet());
        Collections.sort(keys);
        Map<String, Object> treeMap = new TreeMap<>();
        for (String key : keys) {
            if (!SIGN_KEY.equals(key)) {
                treeMap.put(key, json.get(key));
            }
        }
        boolean flag = RSA256Utils.verify256(JSON.toJSONString(treeMap),   //嘉联公钥
                TransContants.jlPubKey,
                pay.getSign());
        String s = treeMap.toString();
        if (!flag) {
            log.info("验签不通过");
            return json;
        } else {
            log.info("验签成功");
            return json;
        }
    }
}
