package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/3/29 20:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordsConsumptionInfo {

    private Integer totalNum;  //当前总计次数

    private Double addUpSave;  //当前累计节省

    private Double amountPayable;  //当前应付金额

    private Double amountPaid;  //当前实付金额

    private List<Records_consumption> recordsConsumptions;   //充值记录详情

}
