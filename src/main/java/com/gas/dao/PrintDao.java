package com.gas.dao;

import com.gas.pojo.Printer;
import com.gas.pojo.Records_consumption;
import com.gas.pojo.StationPrintInfo;
import com.gas.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/3/31 22:30
 */
@Mapper
public interface PrintDao {

    Records_consumption findOperatorsPrint(Integer rc_id);


    int updatePrintInfo(Records_consumption recordsConsumption);

    StationPrintInfo findStationPrintInfo(User user);

    StationPrintInfo findTopUpAmounp(User user);

    List<Records_consumption> findRec(User user);

    int insertPrint(StationPrintInfo stationPrintInfo);

    List<StationPrintInfo> findAllStationPrintInfo(StationPrintInfo stationPrintInfo);

    int findStationPrintNowDate(StationPrintInfo stationPrintInfo);

    int updateNowDatePrintInfo(StationPrintInfo stationPrintInfo);

    int updateUserOnOFFClear(StationPrintInfo stationPrintInfo);

    StationPrintInfo findTopInfo(User user);

    StationPrintInfo findSalesInfo(User user);

    StationPrintInfo findNoCardInfo(User user);

    Integer findTopUpNum(User user);

    int insertPrinterInfo(Printer printer);

    int updatePrinterInfo(Printer printer);

    int deletePrinterInfo(Integer printer_id);

    List<Printer> findAllPrinterInfo(Integer printer_state_id);

    List<Printer> findPrinterBySiteId(Integer rc_sitecode);

    Integer findPrintNum(User user);

    int updatePrintNum(User user);

    /**
     * 门店全部订单信息
     * */
    List<Records_consumption> findAllRecords_consumptionByRc_sitecode(Records_consumption records_consumption);

}
