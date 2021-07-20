package com.gas.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gas.dao.*;
import com.gas.pojo.*;
import com.gas.service.WechatUsersService;
import com.gas.util.Api_java_demo;
import com.gas.util.DataCompletion;
import com.gas.util.DateTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.service
 * @ClassName: WechatUsersServiceImpl
 * @Author: gwl
 * @Description:
 * @Date: 2021/3/22 16:18
 * @Version: 1.0
 */

@Service
public class WechatUsersServiceImpl implements WechatUsersService {

    @Resource
    WechatUsersDao wechatUsersDao;
    @Resource
    AuthorityDao authorityDao;
    @Resource
    PrintDao printDao;
    @Resource
    SiteDao siteDao;
    @Resource
    OliInDao oliInDao;
    @Resource
    UserDao userDao;

    @Override
    public Wechat_users getWechat_usersByOpenId(String wu_openid) {
        Wechat_users wechat_usersByOpenId = wechatUsersDao.findWechat_usersByOpenId(wu_openid);
        if (wechat_usersByOpenId != null) {
            //获取用户优惠券数量
            wechat_usersByOpenId.setWu_coupon_num(wechatUsersDao.findWci_numberByWci_wu_id(wechat_usersByOpenId.getWu_id()));
            if(wechat_usersByOpenId.getWu_ml_id()!=null && wechat_usersByOpenId.getWu_ml_id()!=0){
                //查询用户会员等级信息
                Membership_level membership_levelById = wechatUsersDao.findMembership_levelById(wechat_usersByOpenId.getWu_ml_id());
                //查询会员特权信息
                Membership_rules membership_rulesByMl_id = wechatUsersDao.findMembership_rulesByMl_id(membership_levelById.getMl_id());
                membership_levelById.setMembership_rules(membership_rulesByMl_id);
                wechat_usersByOpenId.setMembership_level(membership_levelById);
            }
        }
        return wechat_usersByOpenId;
    }

    @Override
    public int insertWechat_users(Wechat_users wechat_users) {
        return wechatUsersDao.insertWechat_users(DataCompletion.getWechat_usersData(wechat_users));
    }

    @Override
    public int updateWechat_users(Wechat_users wechat_users) {
        return wechatUsersDao.updateWechat_users(wechat_users);
    }

    @Override
    public List<Coupon> getCouponByWci_wu_id(Integer wci_wu_id) {
        return wechatUsersDao.findCouponByWci_wu_id(wci_wu_id);
    }

    @Override
    public RecordsConsumptionInfo getRecords_consumptionByRc_wu_id(Records_consumption records_consumption) {
        List<Records_consumption> recordsList = wechatUsersDao.findRecords_consumptionByRc_wu_id(records_consumption);

        RecordsConsumptionInfo records = new RecordsConsumptionInfo();

        Double amountPayable1 = 0.0;  //实付金额
        Double rcActualAmount = 0.0;  //应付金额
        DecimalFormat df = new DecimalFormat("0.00");
        for (Records_consumption recordsConsumption : recordsList) {
            recordsConsumption.setRc_Date_str(DateTO.getStringDateTime(recordsConsumption.getRc_datetime()));
            rcActualAmount = rcActualAmount + recordsConsumption.getRc_actual_amount();
            amountPayable1 = amountPayable1 + recordsConsumption.getRc_amount_payable();
        }

        records.setTotalNum(recordsList.size());  //累计消费次数
        records.setAmountPaid(Double.parseDouble(df.format(rcActualAmount))); //实付金额
        records.setAmountPayable(Double.parseDouble(df.format(amountPayable1))); //应付金额
        records.setAddUpSave(Double.parseDouble(df.format(rcActualAmount-amountPayable1)));
        records.setRecordsConsumptions(recordsList); //消费记录
        return records;
    }

    @Override
    public List<Activity> getAllActivity(String activity_sitecode) {
        String date = DateTO.getStringDate(new Date());
        List<Activity> allActivity = wechatUsersDao.findAllActivity(activity_sitecode, date);

        List<Activity> activityList2 = wechatUsersDao.findNoEndTimeActivity(activity_sitecode);

        for (Activity activity : allActivity) {
            activity.setActivity_end_date_str(DateTO.getStringDate(activity.getActivity_end_date()));
            activity.setActivity_start_date_str(DateTO.getStringDate(activity.getActivity_start_date()));
        }
        allActivity.addAll(activityList2);
        return allActivity;
    }

    @Override
    public List<Coupon> getAllCoupon(String coupon_sitecode) {
        String now_date = DateTO.getStringDate(new Date());
        List<Coupon> allCoupon = wechatUsersDao.findAllCoupon(now_date,coupon_sitecode);
        for (Coupon coupon : allCoupon) {
            coupon.setCoupon_term_validity_str(DateTO.getStringDate(coupon.getCoupon_term_validity()));
            coupon.setCoupon_enddate_str(DateTO.getStringDate(coupon.getCoupon_enddate()));
        }
        return allCoupon;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int CouponExchange(Wu_coupon_information wu_coupon_information, Integer coupon_integralnum) {
        //Integer wcIcount = wechatUsersDao.findWCIcount(wu_coupon_information.getWci_wu_id(),wu_coupon_information.getWci_coupon_type());
        Coupon coupon = new Coupon();
        Wechat_users wechat_users = new Wechat_users();
        /*if (wcIcount > 0) {
            //客户已有此优惠卷 查询此优惠卷是否用完
            Integer wcIcountUseNum = wechatUsersDao.findWCIcountUseNum(wu_coupon_information.getWci_wu_id(), wu_coupon_information.getWci_coupon_type());
            if (wcIcountUseNum>0){
                if (wechatUsersDao.updateWCI(wu_coupon_information) > 0) {  //增加用户优惠卷数量
                    //减去积分
                    //wechat_users.setWu_current_points(-coupon_integralnum);
                    //wechat_users.setWu_id(wu_coupon_information.getWci_wu_id());
                    //int i2 = wechatUsersDao.updateWechat_usersWu_current_points(wechat_users);
                    //减去当前优惠卷数量（发布的优惠卷数量）
                    coupon.setCoupon_number(-wu_coupon_information.getWci_number());
                    coupon.setCoupon_id(wu_coupon_information.getWci_coupon_type());
                    int i1 = wechatUsersDao.updateCouponCoupon_number(coupon);
                    if (i1 > 0) {
                        return 1;
                    }
            }
            }else {
                return -1;
            }
        } else {
            if (wechatUsersDao.insertWCI(wu_coupon_information) > 0) {    //新增用户没有的优惠卷
                //减去发布的优惠卷数量
                coupon.setCoupon_number(-wu_coupon_information.getWci_number());
                coupon.setCoupon_id(wu_coupon_information.getWci_coupon_type());
                int i1 = wechatUsersDao.updateCouponCoupon_number(coupon);
                //减去用户当前积分
                //wechat_users.setWu_current_points(-coupon_integralnum);
                //wechat_users.setWu_id(wu_coupon_information.getWci_wu_id());
                //int i2 = wechatUsersDao.updateWechat_usersWu_current_points(wechat_users);

                if (i1 > 0) {
                    return 1;
                }
            }
        }*/
        return 0;
    }

    @Override
    public List<Oil_price> getAllOil_price(String op_sitecode) {
        return wechatUsersDao.findAllOil_price(op_sitecode);
    }

    @Override
    public Site findSiteByAppId(String site_appid) {
        return wechatUsersDao.findSiteByAppId(site_appid);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int insertRecords(Records_consumption recordsConsumption) {

        /*用户账号余额  累计积分  当前积分  增加*/
        int i = 0;
        if (recordsConsumption.getRc_wu_id() != null) {    //客户id不为空  客户充值
            //用户账号余额充值 加积分
            Wechat_users wechatUsers = new Wechat_users();
            wechatUsers.setWu_id(recordsConsumption.getRc_wu_id());
            wechatUsers.setWu_remainder(recordsConsumption.getRc_actual_amount()); //增加实际金额
            Double rc_actual_amount = recordsConsumption.getRc_actual_amount();
            Double integral = 0.0;
            if (rc_actual_amount != null) {
                integral = rc_actual_amount / 1.0;
            }
            //wechatUsers.setWu_integral(integral.intValue());  //累计积分
            wechatUsers.setWu_current_points(integral.intValue());  //增加当前积分
            wechatUsers.setWu_membership_card_growth(integral.intValue());  //增加会员成长值

            //更新用户充值信息
            i = wechatUsersDao.updateWechatTopUpInfo(wechatUsers);
            //增加充值记录
            //recordsConsumption.setRc_number(DataCompletion.getRcNum());
            //recordsConsumption.setRc_datetime(new Date());
        }
        int i1 = wechatUsersDao.insertRecordsConsumption(recordsConsumption);  //客户id为空  无卡支付
        if (i + i1 < 0) {
            return 0;
        }
        return i + i1;
    }

    @Override
    public String insertRecordsWait(Records_consumption recordsConsumption) {
        DecimalFormat df = new DecimalFormat("0.00");
        recordsConsumption.setRc_number("GZH" + RandomStringUtils.randomNumeric(10));
        recordsConsumption.setRc_datetime(new Date());
        if (recordsConsumption.getRc_consumer_projects_code() != null) {
            Oil_price oilPrice = oliInDao.findOliInfoById(recordsConsumption.getRc_consumer_projects_code());
            recordsConsumption.setRc_oil_num(Double.parseDouble(df.format(recordsConsumption.getRc_actual_amount() / oilPrice.getOp_price())));
        }
        int i = wechatUsersDao.insertRecordsConsumptionWait(recordsConsumption);
        if (i > 0) {
            return recordsConsumption.getRc_number();
        }
        return null;
    }


    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int insertConsumeInfo(Records_consumption recordsConsumption) {
        //System.out.println("recordsConsumption》》》" + recordsConsumption);
        DecimalFormat df = new DecimalFormat("0.00");
        //减少账户余额
        Wechat_users wechatUsers = new Wechat_users();
        wechatUsers.setWu_remainder(recordsConsumption.getRc_amount_payable()); //减少账户余额(应付金额)
        wechatUsers.setWu_id(recordsConsumption.getRc_wu_id());
        //更新用户余额信息
        int i = wechatUsersDao.updateWechatBalanceInfo(wechatUsers);
        //增加消费记录
        recordsConsumption.setRc_number(DataCompletion.getRcNum());
        recordsConsumption.setRc_datetime(new Date());
        if (recordsConsumption.getRc_coupon() == 0) {
            recordsConsumption.setRc_coupon(null);
        }
        if (recordsConsumption.getRc_consumer_projects_code() != null) {
            //Oil_price oilPrice = authorityDao.findOliInfoById(recordsConsumption.getRc_consumer_projects_code());
            recordsConsumption.setRc_oil_num(Double.parseDouble(df.format(recordsConsumption.getRc_actual_amount() / recordsConsumption.getRc_oil_price())));
        }
        int i1 = wechatUsersDao.insertRecordsConsumption(recordsConsumption);
        if (i + i1 < 2) {
            return 0;
        }
        //如果使用优惠卷的减少对应的优惠卷
        if (recordsConsumption.getRc_coupon() != null) {
            Wu_coupon_information wuCouponInformation = new Wu_coupon_information();
            wuCouponInformation.setWci_wu_id(recordsConsumption.getRc_wu_id());  //客户id
            //wuCouponInformation.setWci_coupon_type(recordsConsumption.getRc_coupon());  //优惠券id
            int i2 = wechatUsersDao.updateWuCouponNum(wuCouponInformation);
            if (i2 < 1) {
                return 0;
            }
        }
        if (i + i1 > 0) {
            Site site = siteDao.findSiteById(recordsConsumption.getRc_sitecode());
            recordsConsumption.setRc_sitecode_name(site.getSite_name());
            recordsConsumption.setRc_Date_str(DateTO.getStringDateTime(recordsConsumption.getRc_datetime()));
            Oil_price oilPrice = oliInDao.findOliInfoById(recordsConsumption.getRc_consumer_projects_code());
            recordsConsumption.setOilPrice(oilPrice);
            //查询用户
            if (recordsConsumption.getRc_wu_id() != null) {
                Wechat_users wcUserById = userDao.findWcUserById(recordsConsumption.getRc_wu_id());
                recordsConsumption.setWechat_users(wcUserById);
            }
            try {
                //查询消费所属站点的打印机信息
                List<Printer> printerList = printDao.findPrinterBySiteId(recordsConsumption.getRc_sitecode());
                for (Printer printer : printerList) {
                    //调用飞鹅打印
                    Api_java_demo.queryPrinterStatus2(printer.getPrinter_code());
                    String s = Api_java_demo.print2(printer, recordsConsumption);
                    JSONObject jsonObject = JSON.parseObject(s);
                    String ret = jsonObject.getString("ret");
                    if (ret.equals("0")){
                        //更新当前消费记录的打印单号
                        String data = jsonObject.getString("data");
                        int i3 =  userDao.updateReByRcNum(recordsConsumption.getRc_number(),data);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return i + i1;
    }

    @Override
    public int insertJlOrder(JlOrder jlOrder) {

        return wechatUsersDao.insertJlOrder(jlOrder);
    }

    @Override
    public int insertConsumeReco(String out_trade_no) {
        //查询当前单号的待支付信息
        Records_consumption records_consumption = wechatUsersDao.findRecordsByRcNum(out_trade_no);
        //新增到消费记录正式表里
        int i = insertRecords(records_consumption);
        if (records_consumption.getRc_consumer_projects_code()!=null){
            Site site = siteDao.findSiteById(records_consumption.getRc_sitecode());
            records_consumption.setRc_sitecode_name(site.getSite_name());
            records_consumption.setRc_Date_str(DateTO.getStringDateTime(records_consumption.getRc_datetime()));

            //油价
            Oil_price oilPrice = oliInDao.findOliInfoById(records_consumption.getRc_consumer_projects_code());
            records_consumption.setOilPrice(oilPrice);

            //查询用户
            if (records_consumption.getRc_wu_id() != null) {
                Wechat_users wcUserById = userDao.findWcUserById(records_consumption.getRc_wu_id());
                records_consumption.setWechat_users(wcUserById);
            }
            try {
                //查询消费所属站点的打印机信息
                List<Printer> printerList = printDao.findPrinterBySiteId(records_consumption.getRc_sitecode());
                for (Printer printer : printerList) {
                    //调用飞鹅打印
                    Api_java_demo.queryPrinterStatus2(printer.getPrinter_code());
                    String s = Api_java_demo.print2(printer, records_consumption);
                    JSONObject jsonObject = JSON.parseObject(s);
                    String ret = jsonObject.getString("ret");
                    if (ret.equals("0")){
                        //更新当前消费记录的打印单号
                        String data = jsonObject.getString("data");
                        int i3 =  userDao.updateReByRcNum(records_consumption.getRc_number(),data);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public List<Member_day> getAllMember_day(Integer med_sitecode) {  //,Integer currentpage, Integer currentnumber
        List<Member_day> allMember_day = wechatUsersDao.findAllMember_day(med_sitecode);
        for (Member_day member_day : allMember_day) {
            //查询站点
            Site site = siteDao.findSiteById(member_day.getMed_sitecode());
            member_day.setSite(site);

            //查询油价
            Oil_price oilPrice = oliInDao.findOliInfoById(member_day.getMed_op_id());
            member_day.setOilPrice(oilPrice);
        }
        return allMember_day;
    }

    @Override
    public Member_day getMember_dayByMed_op_id(Integer med_op_id, Integer med_weekday,Integer med_sitecode) {

        Member_day member_day = wechatUsersDao.findMember_dayByMed_op_id(med_op_id, med_weekday, med_sitecode);
        if (member_day!=null){
            //查询站点
            Site site = siteDao.findSiteById(med_sitecode);
            member_day.setSite(site);

            //查询油价
            Oil_price oilPrice = oliInDao.findOliInfoById(med_op_id);
            member_day.setOilPrice(oilPrice);
        }
        return member_day;
    }

    @Override
    public Integer insertMember_day(Member_day member_day) {

        //查找当前站点 油品 会员日 有无信息
        Member_day member_day2 = wechatUsersDao.findMember_dayByMed_op_id(member_day.getMed_op_id(), member_day.getMed_weekday(), member_day.getMed_sitecode());
        Integer i=0;
        if (member_day2!=null){
            return -1;
        }else {
            i = wechatUsersDao.insertMember_day(member_day);
        }
        return i;
    }

    @Override
    public Integer deleteMember_day(Integer med_id) {
        return wechatUsersDao.deleteMember_day(med_id);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Integer updateMember_day(Member_day member_day) {

        Integer i = wechatUsersDao.updateMember_day(member_day);
        try {
            //查找当前站点 油品 会员日 有无信息
            Member_day member_day2 = wechatUsersDao.findMember_dayByMed_op_id(member_day.getMed_op_id(), member_day.getMed_weekday(), member_day.getMed_sitecode());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return i;
    }

    @Override
    public List<Oil_price> findMemberDayOilPrice(String op_sitecode,Integer med_weekday) {
        DecimalFormat df = new DecimalFormat("0.00");
        List<Oil_price> allOil_price = wechatUsersDao.findAllOil_price(op_sitecode);
        for (Oil_price oil_price : allOil_price) {
            Member_day member_day = wechatUsersDao.findMember_dayByMed_op_id(oil_price.getOp_id(), med_weekday, oil_price.getOp_sitecode());
            if (member_day!=null){
               Double memberPrice = oil_price.getOp_price()+member_day.getMed_float_value();
                oil_price.setOp_price_member(Double.parseDouble(df.format(memberPrice)));
                oil_price.setMember_day(1);   // 1 会员日
            }else {
                oil_price.setOp_price_member(oil_price.getOp_price());
                oil_price.setMember_day(2); // 2 非会员日
            }
        }
        return allOil_price;
    }

    @Override
    public Page<Records_consumption> findRecords_consumptionByNocard(Integer currentpage, Integer currentnumber,Integer rc_sitecode) {
        Page<Records_consumption> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<Records_consumption> records_consumptionByNocard = wechatUsersDao.findRecords_consumptionByNocard(rc_sitecode);
        PageInfo<Records_consumption> info = new PageInfo<>(records_consumptionByNocard);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }



    /**------------------------------------------------ 2.0 新增 -----------------------------------------------------------*/


    @Override
    public Carousel getCarouselByCal_id(Integer cal_id, Integer ppe_siteid) {
        return wechatUsersDao.findCarouselByCal_id(cal_id,ppe_siteid);
    }

    @Override
    public List<Membership_rules> getAllMembership_rules(Integer site_id) {
        return wechatUsersDao.findAllMembership_rules(site_id);
    }

    @Override
    @Transactional
    public Integer getTheCard(Wechat_users wechat_users) {
        Growthvalue_record growthvalue_record = new Growthvalue_record();
        growthvalue_record.setGvr_valuenum(wechat_users.getWu_membership_card_growth());
        growthvalue_record.setGvr_type(3);growthvalue_record.setGvr_date(new Date());growthvalue_record.setGvr_wu_id(wechat_users.getWu_id());
        Integer integer = wechatUsersDao.insertGrowthvalue_record(growthvalue_record);
        Integer theCard = wechatUsersDao.getTheCard(wechat_users);
        if (integer+theCard>1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Development_welfare getDevelopment_welfareById(Integer dwe_siteid) {
        return wechatUsersDao.findDevelopment_welfareById(dwe_siteid);
    }

    @Override
    public List<Growthvalue_record> getGrowthvalue_recordByGvr_wu_id(Integer gvr_wu_id) {
        return wechatUsersDao.findGrowthvalue_recordByGvr_wu_id(gvr_wu_id);
    }

    @Override
    public List<Recharge> getAllRecharge(Integer rech_site_id) {
        return wechatUsersDao.findAllRecharge(rech_site_id);
    }

    @Override
    public List<Pointegers_details> getPoIntegers_detailsByPds_wu_id(Integer pds_wu_id) {
        return wechatUsersDao.findPoIntegers_detailsByPds_wu_id(pds_wu_id);
    }

    @Override
    public Integer insertPointegers_details(Pointegers_details pointegers_details) {
        return wechatUsersDao.insertPointegers_details(pointegers_details);
    }

    @Override
    public Integeregral_rule getIntegeregral_ruleByLr_siteid(Integer lr_siteid) {
        return wechatUsersDao.findIntegeregral_ruleByLr_siteid(lr_siteid);
    }

    @Override
    public List<Records_consumption> getRecords_consumptionByRc_wu_id2(Integer rc_wu_id,Integer rc_type) {
        return wechatUsersDao.findRecords_consumptionByRc_wu_id2(rc_wu_id,rc_type);
    }

}
