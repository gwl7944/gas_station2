package com.gas.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gas.pojo.*;
import com.gas.service.PrintService;
import com.gas.util.Api_java_demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Ywj
 * @version 1.0
 * @date 2021/3/31 22:27
 */
@RestController
@CrossOrigin
public class PrintController {

    @Autowired
    private PrintService printService;


    /** 
    * @FunctionName: 加油员打印  废弃
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @GetMapping("/print/operatorsPrint/{rc_id}")
    public JSON operatorsPrint(@PathVariable("rc_id") Integer rc_id){

        Records_consumption recordsConsumption = printService.findOperatorsPrint(rc_id);
        if (recordsConsumption!=null){
            return ResultData.getResponseData(recordsConsumption, ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(null, ResultCode.QUERY_ERROR);
    }

    /**
    * @FunctionName: 加油员打印信息更新  废弃
    * @author: Ywj
    * @Param:
    * @Return:
    */
    @PostMapping("/print/updatePrintInfo")
    public JSON updatePrintInfo(@ModelAttribute Records_consumption recordsConsumption){

        int i = printService.updatePrintInfo(recordsConsumption);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /** 
    * @FunctionName: 打印当前站点 开班时间 -  下班时间的石油售卖情况
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @PostMapping("/print/stationPrint")
    public JSON stationPrint(@RequestBody User user){

        StationPrintInfo stationPrintInfo = printService.findStationPrintInfo(user);
        if (stationPrintInfo!=null){
            return ResultData.getResponseData(stationPrintInfo,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(stationPrintInfo,ResultCode.QUERY_ERROR);
    }


    /**
     * @FunctionName: 结班记录石油售卖情况
     * @author: Ywj
     * @Param:
     * @Return:
     */
    @PostMapping("/print/stationRecord")
    public JSON stationRecord(@RequestBody User user){

        int i = printService.addStationRecordInfo(user);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /** 
    * @FunctionName: 查询销售记录
    * @author: Ywj
    * @Param: 
    * @Return:
    */
    @GetMapping("/print/getStationRecord")
    public JSON getStationRecord(@ModelAttribute StationPrintInfo stationPrintInfo,@RequestParam("currentpage") Integer currentpage,@RequestParam("currentnumber") Integer currentnumber){

        Page<StationPrintInfo> stationPrintInfoPage = printService.findAllStationPrintInfo(stationPrintInfo,currentpage,currentnumber);

        return ResultData.getResponseData(stationPrintInfoPage,ResultCode.QUERY_SUCCESS);
    }


    /**
     * 结班页  当前班次  销售情况  统计
     */
    @PostMapping("/print/getStationRecordByUser")
    public JSON getStationRecordByUser(@RequestBody User user){
        //System.out.println("user:::::::::::"+user);
        StationPrintInfo stationPrintInfo = printService.findStationRecordByUser(user);
        if(stationPrintInfo!=null){
            return ResultData.getResponseData(stationPrintInfo,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(stationPrintInfo,ResultCode.QUERY_ERROR);
    }


    /**
     * 新增打印机信息
     */
    @PostMapping("/print/addPrinterInfo")
    public JSON addPrinterInfo(@RequestBody Printer printer){
        int i = printService.insertPrinterInfo(printer);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 修改打印机信息
     */
    @PostMapping("/print/editPrinterInfo")
    public JSON editPrinterInfo(@RequestBody Printer printer){
        int i = printService.updatePrinterInfo(printer);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /**
     * 删除打印机
     */
    @GetMapping("/print/deletePrinterInfo/{printer_id}")
    public JSON deletePrinterInfo(@PathVariable("printer_id") Integer printer_id){

        int i = printService.deletePrinterInfo(printer_id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR);
    }

    /**
     * 查询当前站点的全部打印机
     */
    @PostMapping("/print/getAllPrinterInfo")
    public JSON getAllPrinterInfo(@RequestParam("printer_state_id") Integer printer_state_id,@RequestParam("currentpage") Integer currentpage, @RequestParam("currentnumber") Integer currentnumber){

        Page<Printer> printerPage = printService.findAllPrinterInfo(printer_state_id,currentpage,currentnumber);
        if (printerPage!=null){
            return ResultData.getResponseData(printerPage,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(printerPage,ResultCode.QUERY_ERROR);
    }

}
