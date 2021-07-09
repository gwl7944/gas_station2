package com.gas.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.util
 * @ClassName: WechatPayTest
 * @Author: gwl
 * @Description: 微信支付测试
 * @Date: 2021/4/27 15:27
 * @Version: 1.0
 */


public class WechatPayTest {

    private static String mchId = "1608918873"; // 商户号
    private static String mchSerialNo = "741A24774325DCD6C583D908F10CA3D6FC1E5E16"; // 商户证书序列号
    private static String apiV3Key = "yH3e2IBpQfE6AwihN4gUptxlC1x6IG5o"; // api密钥
    // 你的商户私钥
    private static String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQClAYJjxCtFWJZCcftChIv358NJt+irs0OSlxi+h/nuR/9H2sYhXfZwkCXCz2Ot6VAFB+b99YBGZacY9Ikm4awwhUVYH6kMfsrXMRTtEpsG/9M5bORwlR7dFU20aZQaOD6UsE/Jcq+oqUFFZOlXQAr5ekKCCWBhoWxVNeHiwpC6/jwxZFRugxDkzYLZK91UfrPY44R6nAnA9emK+M1Rh3R39kXh9CQCrig82LMOf+Qyf72s70o038QySkd3vCANGiU0+PFiU3oL3cw0TmJfutFWmj3cYOKTCWe04yRxWaydhGKB6vyFdIEI6Lr1kboiZQtJjnLfvrjqkIgYcwjxh/J3AgMBAAECggEBAKJWSs8Wp6+xCRvUAewedYpvtR0o17tdQaTODOUe6FB6+vfZkEYxqIxz9QKlgR8PxM8+SA8g4pPf3LbblrfvHLYb1+5sL6gAclWmjzzyqh0HwerkbBcrTtMZ63oMyU3GeiNVFxpNP+7CnlnEfyF8+48Yrqg6y10iMG/g9BxhaXzdoKvRzQQLPbFYV2jCCUm5mmKirj/Pj/jYstlpVnGNSHIbllyrRTZq74mPOwdFbQUk0/HrKq8hoCKwTMpqWmfGHGClVfXPJcZk9fOGdwIb9dvdJXEprMNeSmmFmMZU3al19qx45G3vTDzCZGg/5OFMLg9aChNmei5r8qLPHBW2KDkCgYEA2HiA2QUqQmjLpRQCpUdcTcDlRDpwrUFgUtuFGQILo+mWTlVRgLuwN8m0hpCu1AgH7aY9EYnETzoHNIOU7ekn8nPO5NrpTFf7uUT5JFfwVhuaslkmfo/yZnMCDkfIBw553dGsIxPpWKtUH/MfGEA1U+rxIxnXsNhWVKpNBMUyEK0CgYEAwyMlkfbLrvYEjiOmpuLdp5vFT9MaX3BW2njBQD51qh349PSmSHmuesrukinnfKKa8D44zcu1AOG5Z/ssFo63XDTiCAGPWeQi8vWSo3nea78fGLoVMTTg6basOCtRPSC2fvovJZWw9pWXrpG4ALhOaPvSiYZdpSkp9CmsAIp9IDMCgYEAsndOyx0uBQg+9L81myvlFpl0qzZ/FOf0UPt4lPCRxjZbXAONKVQT6TqbYeHAsUBZwP0wFdh+B09xB0OAMlDqRUJjDVzJwM480zbhLKxvkZ46gNVtHiTxFLO2uy1E5URzBoTJpTXSSO8fcSPQu/6buK7W1TiGEd+Z9T1eOBOXvhUCgYEAunzUYvKhJcwn/k9D7SnS+2N65haxlZQe7RuKVINcn/+U9UT5xTnE7cUSys0RVaNwvzg3wTV0/iq6rgz0zXoG8i/38/7JqPbGD7txMJo/XmmApnjv197ZM4JIhEWhlfG1dxRSCnFMddvw4fyRBN8e0GYZhyTKShVU6usIXlv7yNECgYEAsCxY5r2xnOBNc+AIwAFsKqcivOONiP/VcpAWZSKGbRg3tlI4cZbjtxqlnBgacygJK3l4jYDugA3uUG07sypYzZVLuLYUD6adh2nHmLqNImUKacvvg3wka1VTE+PEroUjz2a4vDNlfFWpul5FyulhTZdlzEq5inI5gr4vr5KQHKU=\n" +
            "-----END PRIVATE KEY-----";

    //测试AutoUpdateCertificatesVerifier的verify方法参数

    private static String serialNumber = "";  //序列号
    private static String message = "";    //消息
    private static String signature = "";   //签名
    private static CloseableHttpClient httpClient;
    private static AutoUpdateCertificatesVerifier verifier;


    public static void main(String[] args) throws Exception {

        /**----------------------------------------更新商户API证书------------------------------------------**/
        // 加载商户私钥（privateKey：私钥字符串）
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new ByteArrayInputStream(privateKey.getBytes("utf-8")));
        //使用自动更新的签名验证器，不需要传入证书
        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),
                apiV3Key.getBytes("utf-8"));
        // 初始化httpClient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
        URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/certificates");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "application/json");
        CloseableHttpResponse response1 = httpClient.execute(httpGet);
        //assertEquals(200, response1.getStatusLine().getStatusCode());
        String bodyAsString = EntityUtils.toString(response1.getEntity());
        System.out.println("---------------------------------------");
        System.out.println(bodyAsString);
        System.out.println("---------------------------------------");
        System.err.println(response1.toString());
        try {
            HttpEntity entity1 = response1.getEntity();
            // 对响应体做些有用的事情
            // 确保它被完全消耗
            EntityUtils.consume(entity1);
        } finally {
            response1.close();
        }
        /**----------------------------------------更新商户API证书------------------------------------------**/


        /**---------------------------------------请求支付-----------------------------------------------------**/
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type","application/json; charset=utf-8");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("mchid","1608918873")
                .put("appid", "wxd678efh567hg6787")
                .put("description", "Image形象店-深圳腾大-QQ公仔")
                .put("notify_url", "https://www.weixin.qq.com/wxpay/pay.php")
                .put("out_trade_no", "1217752501201407033233368018");
        rootNode.putObject("amount")
                .put("total", 1);
        rootNode.putObject("payer")
                .put("openid", "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
        objectMapper.writeValue(bos, rootNode);
        httpPost.setEntity(new StringEntity(bos.toString("UTF-8")));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String bodyAsString1 = EntityUtils.toString(response.getEntity());
        System.out.println(bodyAsString1);
        /**---------------------------------------请求支付-----------------------------------------------------**/


        httpClient.close();

    }


}
