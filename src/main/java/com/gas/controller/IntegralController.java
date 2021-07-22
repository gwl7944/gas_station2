package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.*;
import com.gas.service.IntegralService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/17
 * Time: 17:42
 * Description: 积分业务  2.0新增
 */
@RestController
@CrossOrigin
public class IntegralController {

    @Resource
    IntegralService integralService;

    /**
     * 积分商品 新增
     */
    @PostMapping("/integral/addIntegralProduct")
    public JSON addIntegralProduct(@RequestBody Pointegers_item pointegersItem){

        Integer i = integralService.insertIntegralProduct(pointegersItem);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 积分商品修改
     */
    @RequestMapping(value = "/integral/editIntegralProduct",method = RequestMethod.POST)
    public String editIntegralProduct(@RequestBody Pointegers_item pointegersItem){
        if (integralService.updateIntegralProduct(pointegersItem)>0){
            return ResultData.getResponseData(null,ResultCode.UPDATE_SUCCESS).toString();
        }else {
            return ResultData.getResponseData(null,ResultCode.UPDATE_ERROR).toString();
        }
    }

    /**
     * 查询积分商品
     */
    @GetMapping("/integral/integralProduct")
    public JSON integralProduct(@ModelAttribute Pointegers_item pointegersItem, @Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber){

        Page<Pointegers_item> pointegersItemPage = integralService.findIntegralProduct(pointegersItem,currentpage,currentnumber);

        return ResultData.getResponseData(pointegersItemPage, ResultCode.SYS_SUCCESS);
    }

    /*---------------------------  积分抽奖  2.0新增  ------------------------------*/
    /**
     * 新增积分抽奖
     */
    @PostMapping("/integral/addPointsLottery")
    public JSON addPointsLottery(@RequestBody Points_lottery pointsLottery){

        int i = integralService.insertPointsLottery(pointsLottery);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR);
    }

    /**
     * 修改积分抽奖
     */
    @PostMapping("/integral/editPointsLottery")
    public JSON editPointsLottery(@RequestBody Points_lottery pointsLottery){

        int i = integralService.updatePointsLottery(pointsLottery);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(i,ResultCode.UPDATE_ERROR);
    }

    /**
     * 查询积分抽奖
     */
    @PostMapping("/integral/getPointsLottery")
    public JSON getPointsLottery(@ModelAttribute Points_lottery pointsLottery){
        List<Points_lottery> lotteryList = integralService.findPointsLottery(pointsLottery);
        if (lotteryList.size()>0){
            return ResultData.getResponseData(lotteryList,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(lotteryList,ResultCode.QUERY_ERROR);
    }

}
