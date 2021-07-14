package com.gas.service;

import com.gas.pojo.Oil_price;
import com.gas.pojo.Page;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:23
 * Description: No Description
 */
public interface OliInService {

    Page<Oil_price> findAllOliInfoBySite(Integer site_id, Integer currentpage, Integer currentnumber);

    Oil_price findOliInfoById(Integer oil_id);

    int insertOliInfo(Oil_price oilPrice);

    int updateOliInfo(Oil_price oilPrice);

    int deleteOliInfoById(Integer oil_id);
}
