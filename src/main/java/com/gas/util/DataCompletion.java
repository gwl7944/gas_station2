package com.gas.util;

import com.gas.pojo.Wechat_users;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.util
 * @ClassName: DataCompletion
 * @Author: gwl
 * @Description:
 * @Date: 2021/3/22 16:20
 * @Version: 1.0
 */

public class DataCompletion {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    public  static Wechat_users getWechat_usersData(Wechat_users wechat_users){
        wechat_users.setWu_membership_card_number(getNumberData());   //会员卡号
        wechat_users.setWu_membership_card_growth(0);  //会员成长值
        //wechat_users.setWu_integral(0);  //累计积分
        wechat_users.setWu_remainder(0.00);   //余额
        wechat_users.setWu_recommend_num(0); //推荐成功次数
        return wechat_users;
    }
    public static String getNumberData(){

        Calendar now =  Calendar.getInstance();
        String dateNum = getDateNum(now);
        //System.out.println(year.toString()+month+date+hour+minute+second+Math.round(Math.random() * 100000));
        //sdf.format(new Date()).replace("-", "").replace(":", "").replace(" ", "")+Math.random()*10000
        return dateNum+Math.round(Math.random() * 100000);
    }

    public static String getRcNum(){
        Calendar now =  Calendar.getInstance();
        String dateNum = getDateNum(now);
        return dateNum+generateUID();
    }

    /***
     * 生成uuid 8位数字
     */
    public static String generateUID() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 8; i++) {
            //首字母不能为0
            result += (random.nextInt(9) + 1);
        }
        return sdf.format(new Date())+result;
    }

    public static String getDateNum(Calendar now){
        Integer year, month, date, hour, minute, second;
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH)+1;
        date = now.get(Calendar.DATE);
        hour = now.get(Calendar.HOUR_OF_DAY);
        minute = now.get(Calendar.MINUTE);
        second = now.get(Calendar.SECOND);
        return year.toString()+month+date+hour+minute+second;
    }

    public static void main(String[] args) {
        System.out.println("getRcNum>>"+getRcNum());
        System.out.println("generateUID>>"+generateUID());
        System.out.println("getDateNum>>"+getDateNum(Calendar.getInstance()));
    }
}
