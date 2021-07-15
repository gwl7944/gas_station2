package com.gas.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gas.dao.*;
import com.gas.pojo.*;
import com.gas.service.PrintService;
import com.gas.util.Api_java_demo;
import com.gas.util.DateTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/3/31 22:30
 */
@Service
public class PrintServiceImpl implements PrintService {

    @Resource
    PrintDao printDao;
    @Resource
    AuthorityDao authorityDao;
    @Resource
    SiteDao siteDao;
    @Resource
    OliInDao oliInDao;
    @Resource
    UserDao userDao;
    @Resource
    ActivityDao activityDao;
    @Resource
    CouponDao couponDao;

    public static final String USER = "794403044@qq.com";//*必填*：账号名
    public static final String UKEY = "zEKe4v6fwpeQVAKK";//*必填*: 飞鹅云后台注册账号后生成的UKEY 【备注：这不是填打印机的KEY】


    @Override
    public Records_consumption findOperatorsPrint(Integer rc_id) {
        Records_consumption recordsConsumption = printDao.findOperatorsPrint(rc_id);

        recordsConsumption.setRc_Date_str(DateTO.getStringDateTime(recordsConsumption.getRc_datetime()));

        //查询油价信息
        Oil_price oilPrice = oliInDao.findOliInfoById(recordsConsumption.getRc_consumer_projects_code());
        recordsConsumption.setOilPrice(oilPrice);

        //查询活动信息
        if (recordsConsumption.getRc_activity() != null) {
            Activity activity = activityDao.findActivityById(recordsConsumption.getRc_activity());
            recordsConsumption.setActivity(activity);
        }
        //查询优惠卷信息
        if (recordsConsumption.getRc_coupon() != null) {
            Coupon coupon = couponDao.findCouponById(recordsConsumption.getRc_coupon());
            recordsConsumption.setCoupon(coupon);
        }

        return recordsConsumption;
    }

    @Override
    public int updatePrintInfo(Records_consumption recordsConsumption) {

        return printDao.updatePrintInfo(recordsConsumption);
    }

    @Override
    public StationPrintInfo findStationPrintInfo(User user) {

        System.out.println("user>>" + user);
        StationPrintInfo stationPrintInfo = printDao.findStationPrintInfo(user);
        System.out.println("stationPrintInfo>>" + stationPrintInfo);

        //查询充值金额
        StationPrintInfo stationPrintInfo1 = printDao.findTopUpAmounp(user);

        System.out.println("stationPrintInfo1>>" + stationPrintInfo1);
        if (stationPrintInfo1 != null) {
            stationPrintInfo.setTop_up_amounp(stationPrintInfo1.getTop_up_amounp());
            //优惠金额
            //stationPrintInfo.setCoupon_amount(stationPrintInfo.getReceivable_amount() - stationPrintInfo.getPaid_amount());

            //查询当前时间断的  不同石油销售统计
            List<Records_consumption> recList = printDao.findRec(user);

            //查询当前站点下的所有
            List<Oil_price> oilPriceList = oliInDao.findAllOliInfoBySite(user.getUser_sitecode());
            int fixture_number = 0;
            List<OilPrint> list = new ArrayList<>();
            for (Oil_price oilPrice : oilPriceList) {
                OilPrint oilPrint = new OilPrint();
                Double receivable_amount = 0.0;
                Double paid_amount = 0.0;
                Double sales_volume = 0.0;

                for (Records_consumption recordsCon : recList) {
                    if (recordsCon.getRc_consumer_projects_code() != null) {
                        if (recordsCon.getRc_consumer_projects_code().equals(oilPrice.getOp_id())) {
                            //应收金额
                            receivable_amount = receivable_amount + recordsCon.getRc_actual_amount();
                            //实收金额
                            paid_amount = paid_amount + recordsCon.getRc_amount_payable();
                            //销售石油数
                            sales_volume = sales_volume + recordsCon.getRc_oil_num();
                            //成交数
                            fixture_number++;
                        }
                    }
                }
                oilPrint.setOil_name(oilPrice.getOp_name());
                oilPrint.setOil_unit_price(oilPrice.getOp_price());
                oilPrint.setReceivable_amount(receivable_amount);
                oilPrint.setPaid_amount(paid_amount);
                oilPrint.setSales_volume(sales_volume);
                oilPrint.setCoupon_amount(oilPrint.getReceivable_amount() - oilPrint.getPaid_amount());
                list.add(oilPrint);
            }
            stationPrintInfo.setOilPrintList(list);
            Date dateTime = DateTO.getDateTime(user.getUser_onduty_time_str());
            stationPrintInfo.setBusiness_day(DateTO.getStringDate(dateTime)); //开班日
            stationPrintInfo.setUser_onduty_time_str(user.getUser_onduty_time_str()); //上班时间
            stationPrintInfo.setUser_offduty_time_str(user.getUser_offduty_time_str());//下班时间
            stationPrintInfo.setFixture_number(fixture_number);
        } else {
            StationPrintInfo stationPrintInfo2 = new StationPrintInfo();
            Date dateTime = DateTO.getDateTime(user.getUser_onduty_time_str());
            stationPrintInfo2.setBusiness_day(DateTO.getStringDate(dateTime)); //开班日
            stationPrintInfo2.setUser_onduty_time_str(user.getUser_onduty_time_str()); //上班时间
            stationPrintInfo2.setUser_offduty_time_str(user.getUser_offduty_time_str());//下班时间
            stationPrintInfo2.setFixture_number(0);
            stationPrintInfo2.setCoupon_amount(0.0);
            stationPrintInfo2.setTop_up_amounp(0.0);
            stationPrintInfo2.setPaid_amount(0.0);
            //stationPrintInfo2.setReceivable_amount(0.0);
            stationPrintInfo2.setRefund_amount(0.0);
            stationPrintInfo2.setSales_volume(0.0);
            return stationPrintInfo2;
        }
        return stationPrintInfo;
    }


    @Override
    public int addStationRecordInfo(User user) {
        int i = 0;
        System.out.println("user>>" + user);

        //查询充值金额
        StationPrintInfo stationPrintInfo1 = printDao.findTopUpAmounp(user);
        StationPrintInfo stationPrintInfo = printDao.findStationPrintInfo(user);
        Date dateTime = DateTO.getDateTime(user.getUser_onduty_time_str());

        if (stationPrintInfo1 != null || stationPrintInfo != null) {

            StationPrintInfo printInfo = getPrintInfo(user);
            printInfo.setBusiness_day(DateTO.getStringDate(dateTime)); //开班日
            printInfo.setUser_onduty_time_str(user.getUser_onduty_time_str()); //上班时间
            printInfo.setUser_offduty_time_str(user.getUser_offduty_time_str());//下班时间
            printInfo.setClasses(user.getUser_classes());
            printInfo.setSite_id(user.getUser_sitecode());
            printInfo.setSite_name(user.getUser_site_name());
            printInfo.setOperator(user.getUser_name());
            System.out.println("printInfo》》》" + printInfo);
            i = printDao.insertPrint(printInfo);
            System.out.println("i#####" + i);
            if (i > 0) {
                //将用户结班时间开班时间清空
                int i2 = printDao.updateUserOnOFFClear(printInfo);
            }
            System.out.println("i》》》" + i);
            return i;
        }
        stationPrintInfo = new StationPrintInfo();
        stationPrintInfo.setBusiness_day(DateTO.getStringDate(dateTime)); //开班日
        stationPrintInfo.setUser_onduty_time_str(user.getUser_onduty_time_str()); //上班时间
        stationPrintInfo.setUser_offduty_time_str(user.getUser_offduty_time_str());//下班时间
        stationPrintInfo.setClasses(user.getUser_classes());
        stationPrintInfo.setSite_id(user.getUser_sitecode());
        stationPrintInfo.setSite_name(user.getUser_site_name());
        stationPrintInfo.setOperator(user.getUser_name());

        stationPrintInfo.setTop_up_amounp(0.00);
        stationPrintInfo.setPaid_amount(0.00);
        stationPrintInfo.setCoupon_amount(0.00);
        stationPrintInfo.setRefund_amount(0.00);   //退款金额暂无  写死

        stationPrintInfo.setSales_amount(0.00);
        stationPrintInfo.setSales_official_amount(0.00);
        stationPrintInfo.setSales_coupon_amount(0.00);
        stationPrintInfo.setSales_nocard_amount(0.00);
        stationPrintInfo.setSales_volume(0.00);

        stationPrintInfo.setFixture_number(0);
        i = printDao.insertPrint(stationPrintInfo);
        if (i > 0) {
            //将用户结班时间开班时间清空
            StationPrintInfo stationPrintInfo3 = new StationPrintInfo();
            stationPrintInfo3.setClasses(user.getUser_classes());
            stationPrintInfo3.setSite_id(user.getUser_sitecode());
            //System.out.println("stationPrintInfo3》》》" + stationPrintInfo3);
            int i2 = printDao.updateUserOnOFFClear(stationPrintInfo3);
        }
        return i;
    }

    @Override
    public Page<StationPrintInfo> findAllStationPrintInfo(StationPrintInfo stationPrintInfo, Integer currentpage, Integer currentnumber) {
        Page<StationPrintInfo> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<StationPrintInfo> stationPrintInfoList = printDao.findAllStationPrintInfo(stationPrintInfo);
        PageInfo<StationPrintInfo> info = new PageInfo<>(stationPrintInfoList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }

    @Override
    public StationPrintInfo findStationRecordByUser(User user) {
        System.out.println("user--------->" + user);
        StationPrintInfo stationPrintInfo = getPrintInfo(user);
        Date dateTime = DateTO.getDateTime(user.getUser_onduty_time_str());
        stationPrintInfo.setBusiness_day(DateTO.getStringDate(dateTime)); //开班日
        stationPrintInfo.setUser_onduty_time_str(user.getUser_onduty_time_str()); //上班时间
        if (user.getUser_offduty_time_str() != "" && user.getUser_offduty_time_str() != null) {
            stationPrintInfo.setUser_offduty_time_str(user.getUser_offduty_time_str());//下班时间
        }
        //查询站点
        Site site = siteDao.findSiteById(user.getUser_sitecode());
        if (site != null) {
            stationPrintInfo.setSite_name(site.getSite_name());
        }
        //查询操作人
        User user1 = userDao.findUserById(user.getUser_id());
        if (user1 != null) {
            stationPrintInfo.setOperator(user1.getUser_name());
            //查询打印次数
            int num = printDao.findPrintNum(user);
            stationPrintInfo.setPrint_num(num);

            //查询消费所属站点的打印机信息
            List<Printer> printerList = printDao.findPrinterBySiteId(user.getUser_sitecode());
            try {
                for (Printer printer : printerList) {
                    //调用飞鹅打印
                    String s = Api_java_demo.print3(printer, stationPrintInfo);
                    JSONObject jsonObject = JSON.parseObject(s);
                    String ret = jsonObject.getString("ret");
                    if (ret.equals("0")) {
                        //更新打印次数
                        int i3 = printDao.updatePrintNum(user);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stationPrintInfo;
    }

    @Override
    public int insertPrinterInfo(Printer printer) {
        //查询打印机状态
        printer.setPrinter_user(USER);
        printer.setPrinter_Identification_code(UKEY);
        int i = 0;
        try {
            String msg = Api_java_demo.queryPrinterStatus2(printer.getPrinter_code());
            JSONObject jsonObject = JSON.parseObject(msg);
            String data = jsonObject.getString("data");
            if (data != null) {
                if (data.equals("\u5728\u7ebf\uff0c\u5de5\u4f5c\u72b6\u6001\u6b63\u5e38\u3002")) {
                    printer.setPrinter_state(2);
                    //新增打印机
                    String snlist = printer.getPrinter_code() + "#" + printer.getPrinter_key() + "#" + printer.getPrinter_alias();  //打印机编号  打印机识别码  备注
                    String s = Api_java_demo.addprinter2(printer, snlist);
                    i = printDao.insertPrinterInfo(printer);
                } else if (data.equals("\u79bb\u7ebf\u3002")) {
                    printer.setPrinter_state(1);
                    //新增打印机
                    String snlist = printer.getPrinter_code() + "#" + printer.getPrinter_key() + "#" + printer.getPrinter_alias();  //打印机编号  打印机识别码  备注
                    String s = Api_java_demo.addprinter2(printer, snlist);
                    i = printDao.insertPrinterInfo(printer);
                } else if (data.equals("\u5728\u7ebf\uff0c\u5de5\u4f5c\u72b6\u6001\u4e0d\u6b63\u5e38\u3002")) {
                    printer.setPrinter_state(3);
                    //新增打印机
                    String snlist = printer.getPrinter_code() + "#" + printer.getPrinter_key() + "#" + printer.getPrinter_alias();  //打印机编号  打印机识别码  备注
                    String s = Api_java_demo.addprinter2(printer, snlist);
                    i = printDao.insertPrinterInfo(printer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            printer.setPrinter_state(4);  //打印机状态查询异常
        }
        return i;
    }

    @Override
    public int updatePrinterInfo(Printer printer) {
        //查询打印机状态
        try {
            String msg = Api_java_demo.queryPrinterStatus2(printer.getPrinter_code());
            JSONObject jsonObject = JSON.parseObject(msg);
            String data = jsonObject.getString("data");
            if (data.equals("\u5728\u7ebf\uff0c\u5de5\u4f5c\u72b6\u6001\u6b63\u5e38\u3002")) {
                printer.setPrinter_state(2);
            } else if (data.equals("\u79bb\u7ebf\u3002")) {
                printer.setPrinter_state(1);
            } else if (data.equals("\u5728\u7ebf\uff0c\u5de5\u4f5c\u72b6\u6001\u4e0d\u6b63\u5e38\u3002")) {
                printer.setPrinter_state(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            printer.setPrinter_state(4);  //打印机状态查询异常
        }
        return printDao.updatePrinterInfo(printer);
    }

    @Override
    public int deletePrinterInfo(Integer printer_id) {

        return printDao.deletePrinterInfo(printer_id);
    }

    @Override
    public Page<Printer> findAllPrinterInfo(Integer printer_state_id, @RequestParam("currentpage") Integer currentpage, @RequestParam("currentnumber") Integer currentnumber) {

        Page<Printer> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<Printer> printerList = printDao.findAllPrinterInfo(printer_state_id);
        for (Printer printer : printerList) {
            //查找站点
            Site site = siteDao.findSiteById(printer.getPrinter_state_id());
            printer.setSite(site);
            //查询打印机状态
            try {
                String msg = Api_java_demo.queryPrinterStatus2(printer.getPrinter_code());
                JSONObject jsonObject = JSON.parseObject(msg);
                String data = jsonObject.getString("data");
                if (data.equals("\u5728\u7ebf\uff0c\u5de5\u4f5c\u72b6\u6001\u6b63\u5e38\u3002")) {
                    printer.setPrinter_state(2);
                } else if (data.equals("\u79bb\u7ebf\u3002")) {
                    printer.setPrinter_state(1);
                } else if (data.equals("\u5728\u7ebf\uff0c\u5de5\u4f5c\u72b6\u6001\u4e0d\u6b63\u5e38\u3002")) {
                    printer.setPrinter_state(3);
                }
            } catch (Exception e) {
                e.printStackTrace();
                printer.setPrinter_state(4);  //打印机状态查询异常
            }
        }
        PageInfo<Printer> info = new PageInfo<>(printerList);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }


    public StationPrintInfo getPrintInfo(User user) {
        DecimalFormat df = new DecimalFormat("0.00");
        /*-------------------------- 充值信息 -------------------------------*/
        //查询 充值应收总金额  充值实收总金额  充值活动优惠总金额
        StationPrintInfo top = printDao.findTopInfo(user);
        if (top != null) {
            Double coupon_top = top.getTop_up_amounp() - top.getPaid_amount();
            top.setCoupon_amount(Double.parseDouble(df.format(coupon_top)));
        } else {
            top = new StationPrintInfo();
            top.setTop_up_amounp(0.0);
            top.setPaid_amount(0.0);
            top.setCoupon_amount(0.0);
        }
        /*-------------------------- 消费信息 -------------------------------*/
        //查询 消费应收总金额 消费实收总金额 消费优惠卷优惠总金额  石油总销售量
        StationPrintInfo sales = printDao.findSalesInfo(user);
        if (sales != null) {
            Double sales_coupon_amount = sales.getSales_amount() - sales.getSales_official_amount();
            sales.setSales_coupon_amount(Double.parseDouble(df.format(sales_coupon_amount)));
        } else {
            sales = new StationPrintInfo();
            sales.setSales_amount(0.0);
            sales.setSales_official_amount(0.0);
            sales.setSales_coupon_amount(0.0);
            sales.setSales_volume(0.0);
        }
        //查询 消费无卡支付总金额
        StationPrintInfo nocard = printDao.findNoCardInfo(user);
        if (nocard == null) {
            nocard = new StationPrintInfo();
            nocard.setSales_nocard_amount(0.0);
        }

        /*-------------------------- 石油售卖明细 -------------------------------*/
        //查询当前时间断的  不同石油销售统计
        List<Records_consumption> recList = printDao.findRec(user);

        //查询当前站点下的所有油价信息
        List<Oil_price> oilPriceList = oliInDao.findAllOliInfoBySite(user.getUser_sitecode());

        int fixture_number = 0;
        List<OilPrint> list = new ArrayList<>();
        for (Oil_price oilPrice : oilPriceList) {
            OilPrint oilPrint = new OilPrint();
            Double receivable_amount = 0.0;
            Double paid_amount = 0.0;
            Double sales_volume = 0.0;

            for (Records_consumption recordsCon : recList) {
                if (recordsCon.getRc_consumer_projects_code() != null) {
                    if (recordsCon.getRc_consumer_projects_code().equals(oilPrice.getOp_id())) {
                        //应收金额
                        receivable_amount = receivable_amount + recordsCon.getRc_actual_amount();
                        //System.out.println("receivable_amount>>>" + receivable_amount);
                        //实收金额
                        paid_amount = paid_amount + recordsCon.getRc_amount_payable();
                        //销售石油数
                        sales_volume = sales_volume + recordsCon.getRc_oil_num();
                        //成交数
                        fixture_number = fixture_number + 1;
                    }
                }
            }

            oilPrint.setOil_name(oilPrice.getOp_name());
            oilPrint.setOil_unit_price(oilPrice.getOp_price());
            oilPrint.setReceivable_amount(Double.parseDouble(df.format(receivable_amount)));
            oilPrint.setPaid_amount(Double.parseDouble(df.format(paid_amount)));
            oilPrint.setSales_volume(Double.parseDouble(df.format(sales_volume)));
            oilPrint.setCoupon_amount(Double.parseDouble(df.format(oilPrint.getReceivable_amount() - oilPrint.getPaid_amount())));
            list.add(oilPrint);
        }

        //查询充值数
        Integer top_up_num = printDao.findTopUpNum(user);
        //System.out.println("top_up_num》》"+top_up_num);
        fixture_number = fixture_number + top_up_num;
        /*------------------------- 总信息 -------------------------------*/
        StationPrintInfo stationPrintInfo = new StationPrintInfo();

        stationPrintInfo.setTop_up_amounp(top.getTop_up_amounp());
        stationPrintInfo.setPaid_amount(top.getPaid_amount());
        stationPrintInfo.setCoupon_amount(top.getCoupon_amount());
        stationPrintInfo.setRefund_amount(0.0);   //退款金额暂无  写死

        stationPrintInfo.setSales_amount(sales.getSales_amount());
        stationPrintInfo.setSales_official_amount(sales.getSales_official_amount());
        stationPrintInfo.setSales_coupon_amount(sales.getSales_coupon_amount());
        stationPrintInfo.setSales_nocard_amount(nocard.getSales_nocard_amount());
        stationPrintInfo.setSales_volume(sales.getSales_volume());


        stationPrintInfo.setFixture_number(fixture_number);

        stationPrintInfo.setOilPrintList(list);

        return stationPrintInfo;
    }

}
