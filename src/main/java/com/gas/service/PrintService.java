package com.gas.service;

import com.gas.pojo.*;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/3/31 22:29
 */
public interface PrintService {

    Records_consumption findOperatorsPrint(Integer rc_id);

    int updatePrintInfo(Records_consumption recordsConsumption);

    StationPrintInfo findStationPrintInfo(User user);

    int addStationRecordInfo(User user);

    Page<StationPrintInfo> findAllStationPrintInfo(StationPrintInfo stationPrintInfo, Integer currentpage, Integer currentnumber);

    StationPrintInfo findStationRecordByUser(User user);

    int insertPrinterInfo(Printer printer);

    int updatePrinterInfo(Printer printer);

    int deletePrinterInfo(Integer printer_id);

    Page<Printer> findAllPrinterInfo(Integer printer_state_id,Integer currentpage, Integer currentnumber);
}
