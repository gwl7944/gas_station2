package com.gas.dao;

import com.gas.pojo.Oil_price;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:25
 * Description: No Description
 */
@Mapper
public interface OliInDao {


    int insertOliInfo(Oil_price oilPrice);

    int updateOliInfo(Oil_price oilPrice);

    int deleteOliInfoById(Integer oil_id);

    List<Oil_price> findAllOliInfoBySite(Integer site_id);

    Oil_price findOliInfoById(Integer oil_id);
}
