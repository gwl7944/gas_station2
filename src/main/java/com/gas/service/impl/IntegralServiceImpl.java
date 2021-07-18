package com.gas.service.impl;

import com.gas.dao.IntegralDao;
import com.gas.dao.UserDao;
import com.gas.pojo.Pointegers_Item;
import com.gas.pojo.Product_Picture;
import com.gas.service.IntegralService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/17
 * Time: 17:43
 * Description: No Description
 */
@Service
public class IntegralServiceImpl implements IntegralService {

    @Resource
    IntegralDao integralDao;
    @Resource
    UserDao userDao;

    @Override
    public Integer insertIntegralProduct(Pointegers_Item pointegersItem) {
        int i = integralDao.insertIntegralProduct(pointegersItem);
        if (i>0){
            if (pointegersItem.getPictureList()!=null){
                for (Product_Picture product_picture : pointegersItem.getPictureList()) {
                    i = userDao.insertProductPicture(product_picture);
                }
            }
        }
        return i;
    }


}
