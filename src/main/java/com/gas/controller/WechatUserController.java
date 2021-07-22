package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gas.pojo.*;
import com.gas.service.AuthorityService;
import com.gas.service.WechatUsersService;
import com.gas.util.*;
import com.jlpay.ext.qrcode.trans.request.OfficialAccPayRequest;
import com.jlpay.ext.qrcode.trans.response.OfficialAccPayResponse;
import com.jlpay.ext.qrcode.trans.service.TransExecuteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/3/21 22:21
 */

/**
 * @ProjectName: gas_station
 * @Package: com.gas.controller
 * @ClassName: WechatUserController
 * @Author: gwl
 * @Description: 微信用户功能模块接口
 * @Date: 2021/3/22 11:48
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@Slf4j
public class WechatUserController {

    @Resource
    WechatUsersService wechatUsersService;
    @Resource
    AuthorityService authorityService;

    /**
     * 根据AppId查询站点  重复
     */
    @PostMapping("/WechatUserController/getSiteByAppId")
    public JSON getSiteByAppId(@ModelAttribute Site site) {

        Site site1 = wechatUsersService.findSiteByAppId(site.getSite_appid());
        if (site1 != null) {
            return ResultData.getResponseData(site1, ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(site1, ResultCode.QUERY_ERROR);
    }


    /**
     * 获取站点信息
     * */
    @PostMapping("/WechatUserController/getSite")
    public JSON getSite(@ModelAttribute Site site) {
        Site site1 = wechatUsersService.findSiteByAppId(site.getSite_appid());
        if (site1 != null) {
            return ResultData.getResponseData(site1, ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(site1, ResultCode.QUERY_ERROR);
    }


    /**
     * 获取小程序token   前端没调
     */
    @PostMapping("/WechatUserController/GetAccessToken")
    public JSON GetAccessToken(@ModelAttribute Site site) {
        System.out.println("site》》" + site);
        String accessToken = WeChatPushUtil.getAccessToken(site);
        //System.out.println(accessToken);
        if (accessToken != null && !"".equals(accessToken)) {
            return ResultData.getResponseData(accessToken, ResultCode.QUERY_SUCCESS);
        } else {
            return ResultData.getResponseData(null, ResultCode.QUERY_ERROR);
        }
    }

    /**
     * 获取小程序openId
     */
    @PostMapping("/WechatUserController/getOpenId")
    public JSON getOpenId(@ModelAttribute Open open) {
        System.out.println(open.toString());

        Map<String, String> map = WeChatPushUtil.weChatGetOpenid(open);
        System.out.println("map>>"+map);
        if (map != null && !"".equals(map)) {
            return ResultData.getResponseData(map, ResultCode.QUERY_SUCCESS);
        } else {
            return ResultData.getResponseData(null, ResultCode.QUERY_ERROR);
        }
    }

    /**
     * 获取H5 jsapi_ticket
     */
    @PostMapping("/WechatUserController/getJsapiTicket")
    public JSON getJsapiTicket(@ModelAttribute Open open) {
        String JsapiTicket = WeChatPushUtil.getJsapiTicket(open);
        if (JsapiTicket != null && JsapiTicket != "") {
            return ResultData.getResponseData(JsapiTicket, ResultCode.QUERY_SUCCESS);
        } else {
            return ResultData.getResponseData(null, ResultCode.QUERY_ERROR);
        }
    }

    /**
     * 小程序登录
     *
     * @param wechat_users -->wu_name,wu_openid,wu_sitecode(appID)
     */
    @PostMapping("/WechatUserController/WxLogin")
    public JSON WxLogin(@ModelAttribute Wechat_users wechat_users) {
        Wechat_users wechat_usersByOpenId = wechatUsersService.getWechat_usersByOpenId(wechat_users.getWu_openid());
        if (wechat_usersByOpenId != null) {
            return ResultData.getResponseData(wechat_usersByOpenId, ResultCode.LOGIN_SUCCESS);
        } else {
            if (wechatUsersService.insertWechat_users(wechat_users) > 0) {
                return ResultData.getResponseData(wechatUsersService.getWechat_usersByOpenId(wechat_users.getWu_openid()), ResultCode.LOGIN_SUCCESS);
            } else {
                return ResultData.getResponseData(null, ResultCode.SYS_ERROR);
            }
        }
    }

    /**
     * openid查看用户信息
     */
    @PostMapping("/WechatUserController/findWechat_usersByOpenId/{openid}")
    public JSON findWechat_usersByOpenId(@PathVariable("openid") String openid) {
        return ResultData.getResponseData(wechatUsersService.getWechat_usersByOpenId(openid), ResultCode.SYS_SUCCESS);
    }

    /**
     * 小程序修改用户信息
     */
    @PostMapping("/WechatUserController/updateWechat_users")
    public JSON updateWechat_users(@ModelAttribute Wechat_users wechat_users) {
        return ResultData.getResponseData(wechatUsersService.updateWechat_users(wechat_users), ResultCode.SYS_SUCCESS);
    }

    /**
     * 查询用户优惠券列表
     */
    @GetMapping("/WechatUserController/findCouponByWci_wu_id/{wci_wu_id}")
    public JSON findCouponByWci_wu_id(@PathVariable("wci_wu_id") Integer wci_wu_id) {
        return ResultData.getResponseData(wechatUsersService.getCouponByWci_wu_id(wci_wu_id), ResultCode.SYS_SUCCESS);
    }


    /**
     * 查询用户消费记录
     */
    @PostMapping("/WechatUserController/findRecords_consumptionByRc_wu_id")
    public JSON findRecords_consumptionByRc_wu_id(@ModelAttribute Records_consumption records_consumption) {
        return ResultData.getResponseData(wechatUsersService.getRecords_consumptionByRc_wu_id(records_consumption), ResultCode.SYS_SUCCESS);
    }


    /**
     * 查询全部活动信息
     *
     * @param activity_sitecode appID
     */
    @GetMapping("/WechatUserController/findAllActivity/{activity_sitecode}")
    public JSON findAllActivity(@PathVariable("activity_sitecode") String activity_sitecode) {
        return ResultData.getResponseData(wechatUsersService.getAllActivity(activity_sitecode), ResultCode.SYS_SUCCESS);
    }


    /**
     * 查询全部优惠券
     */
    @GetMapping("/WechatUserController/findAllCoupon/{coupon_sitecode}")
    public JSON findAllCoupon(@PathVariable("coupon_sitecode") String coupon_sitecode) {
        return ResultData.getResponseData(wechatUsersService.getAllCoupon(coupon_sitecode), ResultCode.SYS_SUCCESS);
    }


    /**
     * 用户积分兑换优惠券 --------改成不用积分领
     */
    @PostMapping("/WechatUserController/CouponExchange")
    public JSON CouponExchange(@ModelAttribute Wu_coupon_information wu_coupon_information, @Param("coupon_integralnum") Integer coupon_integralnum) {
        return ResultData.getResponseData(wechatUsersService.CouponExchange(wu_coupon_information, coupon_integralnum), ResultCode.SYS_SUCCESS);
    }


    /**
     * 查询全部油价
     */
    @GetMapping("/WechatUserController/findAllOil_price/{op_sitecode}")
    public JSON findAllOil_price(@PathVariable("op_sitecode") String op_sitecode) {
        return ResultData.getResponseData(wechatUsersService.getAllOil_price(op_sitecode), ResultCode.SYS_SUCCESS);
    }


    /**
     * 余额充值
     */
    @PostMapping("/WechatUserController/remainderTopUp")
    public JSON remainderTopUp(@ModelAttribute Records_consumption recordsConsumption) {
        int i = wechatUsersService.insertRecords(recordsConsumption);
        if (i > 0) {
            return ResultData.getResponseData(i, ResultCode.TOP_UP_SUCCESS);
        }
        return ResultData.getResponseData(i, ResultCode.TOP_UP_ERROR);
    }

    /**
     * 余额充值待支付生成
     */
    @PostMapping("/WechatUserController/remainderTopUpWait")
    public JSON remainderTopUpWait(@ModelAttribute Records_consumption recordsConsumption) {
        String rc_number = wechatUsersService.insertRecordsWait(recordsConsumption);
        if (rc_number != null) {
            return ResultData.getResponseData(rc_number, ResultCode.TOP_UP_SUCCESS);
        }
        return ResultData.getResponseData(rc_number, ResultCode.TOP_UP_ERROR);
    }


    /**
     * 加油站支付 嘉联支付
     */
    @PostMapping("/WechatUserController/jlPay")
    public JSON jlPay(@ModelAttribute Records_consumption recordsConsumption) {
        System.out.println("recordsConsumption>>"+recordsConsumption);
        OfficialAccPayService officialAccPayService = new OfficialAccPayService();
        OfficialAccPayResponse response = new OfficialAccPayResponse();
        try {
            //Wechat_users wechat_users = wechatUsersService.getWechat_usersByOpenId(recordsConsumption.getOpenId());
            Site site = authorityService.getSiteById(recordsConsumption.getRc_sitecode());
            if (site != null) {
                TransContants.setJlpayProperty2(site.getSite_private_key(),site.getSite_public_key());
                recordsConsumption.setMch_id(site.getSite_mch_id());
                recordsConsumption.setOrg_code(site.getSite_org_code());
                recordsConsumption.setAppid(site.getSite_appid());
            }

            //组装请求参数
            OfficialAccPayRequest request = officialAccPayService.gasRequestData(recordsConsumption);
            //交易请求
            response = TransExecuteService.executor(request, OfficialAccPayResponse.class);
            //System.out.println("嘉联公钥："+TransContants.jlPubKey);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.getResponseData("支付异常", ResultCode.CONSUME_ERROR);
        }
        //将交易返回数据录入数据库
        JSONObject jsonObject = (JSONObject) JSON.toJSON(response);
        JlOrder jlOrder = JSON.toJavaObject(jsonObject, JlOrder.class);
        int i = wechatUsersService.insertJlOrder(jlOrder);
        log.info("【支付信息录入】-->"+i);
        //System.out.println("返回参数=========>"+ JSON.toJSON(response));
        return ResultData.getResponseData(JSON.toJSON(response), ResultCode.CONSUME_SUCCESS);
    }

    /**
     * @throws IOException
     * @嘉联异步通知
     */
    @RequestMapping(value = "/applets/async", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject start(HttpServletRequest request) throws IOException {
        System.out.println("进入方法");
        // 流获取
        BufferedReader br = new BufferedReader(
                new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer();
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        String str = sb.toString();
        log.info(str);
        JSONObject json = JSONObject.parseObject(str);
        // 验签
        //Thread device1 = new Thread(new DeviceThread(json));
        //device1.start();
        Attestation attestation = new Attestation();
        JSONObject jsonObject = new JSONObject();
        log.info("【嘉联异步返回数据】-->" + json);
        JSONObject attestation1 = attestation.getAttestation(json);
        //int i =0;
        try {
            JlOrder jlOrder = JSON.toJavaObject(attestation1, JlOrder.class);
            //i = wechatUsersService.insert
        } catch (Exception e) {
            e.printStackTrace();
            log.info("【商家录入异步信息】-->");
        }

        String status = attestation1.getString("status");
        if (status.equals("2")) {  //机构业务层
            //返回嘉联支付
            jsonObject.put("retCode", "success");
            try {
                //根据单号查询待支付信息录入正式消费记录表
                int i = wechatUsersService.insertConsumeReco(attestation1.getString("out_trade_no"));
                log.info("【商家订单录入情况】-->" + i);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("【商家订单录入情况】-->" + "失败");
            }
        } else {
            //返回嘉联支付
            jsonObject.put("retCode", "fail");
        }
        log.info("【响应嘉联】-->" + jsonObject);
        return jsonObject;
    }



    /**
     * 加油消费
     */
    @PostMapping("/WechatUserController/consumeInfoAdd")
    public JSON consumeInfoAdd(@ModelAttribute Records_consumption recordsConsumption) {
        int i = wechatUsersService.insertConsumeInfo(recordsConsumption);
        if (i > 0) {
            return ResultData.getResponseData(i, ResultCode.CONSUME_SUCCESS);
        }
        return ResultData.getResponseData(i, ResultCode.CONSUME_ERROR);
    }


    @GetMapping("/WechatUserController/getIP")
    public JSON getIP(HttpServletRequest request) {
        String ip = getRealIpAddr(request);
        if (ip != null && ip != "") {
            return ResultData.getResponseData(ip, ResultCode.QUERY_SUCCESS);
        } else {
            return ResultData.getResponseData(ip, ResultCode.QUERY_ERROR);
        }
    }

    public static String getRealIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 查询全部会员日
     */
    @PostMapping("/WechatUserController/getAllMember_day")
    //,@Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber
    public JSON getAllMember_day(@Param("med_sitecode") Integer med_sitecode) {
        List<Member_day> allMember_day = wechatUsersService.getAllMember_day(med_sitecode);
        return ResultData.getResponseData(allMember_day, ResultCode.QUERY_SUCCESS);
    }

    /**
     * 根据油品ID 星期值 查询会员日
     */
    @GetMapping("/WechatUserController/findMember_dayByMed_op_id/{med_op_id}/{med_weekday}/{med_sitecode}")
    public JSON findMember_dayByMed_op_id(@PathVariable("med_op_id") Integer med_op_id, @PathVariable("med_weekday") Integer med_weekday, @PathVariable("med_sitecode") Integer med_sitecode) {
        Member_day member_dayByMed_op_id = wechatUsersService.getMember_dayByMed_op_id(med_op_id, med_weekday, med_sitecode);
        return ResultData.getResponseData(member_dayByMed_op_id, ResultCode.QUERY_SUCCESS);
    }


    /**
     * 查询会员日所有油价
     */
    @GetMapping("/WechatUserController/findMemberDayOilPrice/{op_sitecode}")
    public JSON findMemberDayOilPrice(@PathVariable("op_sitecode") String op_sitecode) {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;

        List<Oil_price> oil_prices = wechatUsersService.findMemberDayOilPrice(op_sitecode, dayOfWeek);

        if (oil_prices.size() > 0) {
            return ResultData.getResponseData(oil_prices, ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(oil_prices, ResultCode.QUERY_ERROR);
    }

    /**
     * 新增会员日
     */
    @PostMapping("/WechatUserController/insertMember_day")
    public JSON insertMember_day(@RequestBody Member_day member_day) {
        //System.out.println("member_day>>>"+member_day);
        Integer integer = wechatUsersService.insertMember_day(member_day);
        if (integer > 0) {
            return ResultData.getResponseData(integer, ResultCode.INSERT_SUCCESS);
        } else {
            return ResultData.getResponseData(integer, ResultCode.INSERT_ERROR);
        }
    }


    /**
     * 删除会员日
     */
    @GetMapping("/WechatUserController/deleteMember_day/{med_id}")
    public JSON deleteMember_day(@PathVariable("med_id") Integer med_id) {
        Integer integer = wechatUsersService.deleteMember_day(med_id);
        if (integer > 0) {
            return ResultData.getResponseData(integer, ResultCode.DELETE_SUCCESS);
        } else {
            return ResultData.getResponseData(integer, ResultCode.DELETE_ERROR);
        }
    }


    /**
     * 修改会员日
     */
    @PostMapping("/WechatUserController/updateMember_day")
    public JSON updateMember_day(@RequestBody Member_day member_day) {
        Integer integer = wechatUsersService.updateMember_day(member_day);
        if (integer > 0) {
            return ResultData.getResponseData(integer, ResultCode.INSERT_SUCCESS);
        } else {
            return ResultData.getResponseData(integer, ResultCode.INSERT_ERROR);
        }
    }


    /**
     * 拉取全部无卡消费信息
     */
    @GetMapping("/WechatUserController/findRecords_consumptionByNocard/{currentpage}/{currentnumber}/{rc_sitecode}")
    public JSON findRecords_consumptionByNocard(@PathVariable("currentpage") Integer currentpage, @PathVariable("currentnumber") Integer currentnumber, @PathVariable("rc_sitecode") Integer rc_sitecode) {
        Page<Records_consumption> records_consumptionByNocard = wechatUsersService.findRecords_consumptionByNocard(currentpage, currentnumber, rc_sitecode);
        return ResultData.getResponseData(records_consumptionByNocard, ResultCode.QUERY_SUCCESS);
    }


    /**------------------------------------------ 2.0 新增 ----------------------------------------------*/

    /**
     * 查询轮播图
     * @param cal_id 轮播位id
     * @param ppe_siteid 门店ID
     * */
    @GetMapping("/WechatUserController/findCarouselByCal_id/{cal_id}/{ppe_siteid}")
    public JSON findCarouselByCal_id(@PathVariable("cal_id") Integer cal_id,@PathVariable("ppe_siteid") Integer ppe_siteid){
        return ResultData.getResponseData(wechatUsersService.getCarouselByCal_id(cal_id,ppe_siteid), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 查询全部会员权益  站点
     * */
    @GetMapping("/WechatUserController/getAllMembership_rules/{site_id}")
    public JSON getAllMembership_rules(@PathVariable("site_id") Integer site_id) {
        return ResultData.getResponseData(wechatUsersService.getAllMembership_rules(site_id), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 领取会员卡
     * */
    @PostMapping("/WechatUserController/getTheCard")
    public JSON getTheCard(@ModelAttribute Wechat_users wechat_users,@Param("coupon_id") Integer coupon_id){
        if (wechatUsersService.getTheCard(wechat_users,coupon_id)>0){
            return ResultData.getResponseData(null, ResultCode.INSERT_SUCCESS);
        }else {
            return ResultData.getResponseData(null, ResultCode.INSERT_ERROR);
        }
    }

    /**
     * 查询门店开卡福利
     * */
    @GetMapping("/WechatUserController/getDevelopment_welfareById/{dwe_siteid}")
    public JSON getDevelopment_welfareById(@PathVariable("dwe_siteid") Integer dwe_siteid){
        return  ResultData.getResponseData(wechatUsersService.getDevelopment_welfareById(dwe_siteid), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 查询用户会员成长值变动记录
     * */
    @GetMapping("/WechatUserController/getGrowthvalue_recordByGvr_wu_id/{gvr_wu_id}")
    public JSON getGrowthvalue_recordByGvr_wu_id(@PathVariable("gvr_wu_id") Integer gvr_wu_id){
        return  ResultData.getResponseData(wechatUsersService.getGrowthvalue_recordByGvr_wu_id(gvr_wu_id), ResultCode.QUERY_SUCCESS);
    }


    /**
     * 查询全部充值额度
     * */
    @GetMapping("/WechatUserController/getAllRecharge/{rech_site_id}")
    public JSON getAllRecharge(@PathVariable("rech_site_id") Integer rech_site_id){
        return  ResultData.getResponseData(wechatUsersService.getAllRecharge(rech_site_id), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 查询用户的积分详情
     * @param pds_wu_id 用户主键
     * */
    @GetMapping("/WechatUserController/getPoIntegers_detailsByPds_wu_id/{pds_wu_id}")
    public JSON getPoIntegers_detailsByPds_wu_id(@PathVariable("pds_wu_id") Integer pds_wu_id){
        return  ResultData.getResponseData(wechatUsersService.getPoIntegers_detailsByPds_wu_id(pds_wu_id), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 查询门店积分规则 (消费)
     * @param lr_siteid 门店主键
     * */
    @GetMapping("/WechatUserController/getIntegeregral_ruleByLr_siteid/{lr_siteid}")
    public JSON getIntegeregral_ruleByLr_siteid(@PathVariable("lr_siteid") Integer lr_siteid){
        return  ResultData.getResponseData(wechatUsersService.getIntegeregral_ruleByLr_siteid(lr_siteid), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 添加用户积分变更记录
     * @param pointegers_details
     * */
    @PostMapping("/WechatUserController/insertPointegers_details")
    public JSON insertPointegers_details(@ModelAttribute Pointegers_details pointegers_details) {
        if (wechatUsersService.insertPointegers_details(pointegers_details) > 0) {
            return ResultData.getResponseData(null, ResultCode.SYS_SUCCESS);
        } else {
            return ResultData.getResponseData(null, ResultCode.SYS_ERROR);
        }
    }

    /**
     * 查询用户消费记录
     * @param rc_wu_id  用户主键
     * @param rc_type  消费类型  0-全部  1-充值   2-消费
     * */
    @GetMapping("/WechatUserController/findRecords_consumptionByRc_wu_id2/{rc_wu_id}/{rc_type}")
    public JSON findRecords_consumptionByRc_wu_id2(@PathVariable("rc_wu_id") Integer rc_wu_id,@PathVariable("rc_type") Integer rc_type){
        return  ResultData.getResponseData(wechatUsersService.getRecords_consumptionByRc_wu_id2(rc_wu_id,rc_type), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 查询门店积分商品信息
     * @param pim_site_id  门店主键
     * */
    @GetMapping("/WechatUserController/findAllPointegers_item/{pim_site_id}")
    public JSON findAllPointegers_item(@PathVariable("pim_site_id") Integer pim_site_id){
        return  ResultData.getResponseData(wechatUsersService.getAllPointegers_item(pim_site_id), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 查询全部奖池奖品
     * @param pl_site_id 门店主键
     * */
    @GetMapping("/WechatUserController/findAllPoints_lottery/{pl_site_id}")
    public JSON findAllPoints_lottery(@PathVariable("pl_site_id") Integer pl_site_id){
        return  ResultData.getResponseData(wechatUsersService.getAllPoints_lottery(pl_site_id), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 点击抽奖
     * @param pl_site_id 门店主键
     * @param pim_poIntegers_number 积分值
     * */
    @GetMapping("/WechatUserController/getLuck_Draw/{pl_site_id}/{pim_poIntegers_number}/{wu_id}")
    public JSON getLuck_Draw(@PathVariable("pl_site_id") Integer pl_site_id,
                             @PathVariable("pim_poIntegers_number") Integer pim_poIntegers_number,
                             @PathVariable("wu_id") Integer wu_id){
        return  ResultData.getResponseData(wechatUsersService.getLuck_Draw(pl_site_id,pim_poIntegers_number,wu_id), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 拉取全部油枪信息
     * */
    @GetMapping("/WechatUserController/findAllOilGun/{oil_gun_sitecode}/{oil_op_id}")
    public JSON findAllOilGun(@PathVariable("oil_gun_sitecode") Integer oil_gun_sitecode,@PathVariable("oil_op_id") Integer oil_op_id){
        return  ResultData.getResponseData(wechatUsersService.getAllOilGun(oil_gun_sitecode,oil_op_id), ResultCode.QUERY_SUCCESS);
    }

    /**
     * 查询是否符合活动
     * */
    @GetMapping("/WechatUserController/findActivityNow/{activity_oil_price}/{activity_siteid}")
    public JSON findActivityNow(@PathVariable("activity_oil_price") Integer activity_oil_price,@PathVariable("activity_siteid") Integer activity_siteid){
        return  ResultData.getResponseData(wechatUsersService.findActivityNow(activity_oil_price,activity_siteid),ResultCode.QUERY_SUCCESS);
    }

    /**
     * 用户领取优惠券
     * */
    @PostMapping("/WechatUserController/insertWCI")
    public JSON insertWCI(@ModelAttribute Wu_coupon_information wu_coupon_information){
        int i = wechatUsersService.insertWCI(wu_coupon_information);
        if (i<0){
            return ResultData.getResponseData(null,ResultCode.REPEAT_ERROR);
        }
        return  ResultData.getResponseData(null,ResultCode.QUERY_SUCCESS);
    }





}
