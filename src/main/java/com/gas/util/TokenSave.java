package com.gas.util;

import com.gas.pojo.Open;
import java.util.Date;

public class TokenSave {

    private static String access_token;   //定义要存储的变量
    private static Date date;    //定义上一次存储信息的时间

    private TokenSave() {

    }

    public static String getToken(Open open) {
        int minus = 0;
        if (null != date) {
            minus = (int) (new Date().getTime() - date.getTime());
        }
        if (access_token == null || minus > 60 * 60 * 1000 * 2) {
            synchronized (TokenSave.class) {
                if (access_token == null || minus > 60 * 60 * 1000 * 2) {
                    access_token = WeChatPushUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token","appid="+open.getAppId()+"&secret="+open.getSecret()+"&grant_type=client_credential");
                    date = new Date();
                }
            }
        }
        return access_token;
    }
}