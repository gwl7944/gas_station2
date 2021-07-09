package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: Gwl
 * @Date: 2021/3/20
 * @Description: com.gas.pojo
 * @version: 1.0
 */


/**
 * 微信用户
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wechat_users {

    private	Integer	wu_id;    //主键

    private	String	wu_name;     //名称

    private	String	wu_fullname;   //真实姓名

    private	String	wu_telephone;//手机号

    private	Integer	wu_gender;   //性别

    private Date wu_birthday;     //生日

    private String wu_birthday_str;     //生日

    private	String	wu_id_number;  //身份证号

    private	String	wu_qr_code;   //二维码

    private	String	wu_bar_code;   //条形码

    private	Integer	wu_integral;    //累计积分

    private Integer wu_current_points;  //当前积分

    private	Double	wu_remainder;     //余额

    private	String	wu_Invitation_code;   //邀请码

    private	String	wu_openid;    //微信识别码

    private	String	wu_membership_card_number;   //会员卡号

    private	Integer	wu_membership_card_growth ;   //会员成长值

    private	Integer	wu_sitecode;     //站点编号

    private Site site;   //站点对象

    private	Integer	wu_recommend_num;   //推荐成功次数

    private	Integer	wu_state;    //状态

    private	Integer	wu_del;    //删除（默认为1   删除为2）

    private	String	wu_remarks;     //备注

    private Integer wu_coupon_num;   //优惠券数量

    private String wu_appid;  //APPID


}
