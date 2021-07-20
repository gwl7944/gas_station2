package com.gas.service.impl;

import com.gas.dao.IntegralDao;
import com.gas.dao.PictureDao;
import com.gas.pojo.Pointegers_item;
import com.gas.pojo.Product_picture;
import com.gas.service.IntegralService;
import com.gas.util.OOS_Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    PictureDao pictureDao;

    @Override
    @Transactional
    public Integer insertIntegralProduct(Pointegers_item pointegersItem) {
        int i = integralDao.insertIntegralProduct(pointegersItem);
        if (i>0){
            if (pointegersItem.getPictureList()!=null){
                for (Product_picture product_picture : pointegersItem.getPictureList()) {
                    product_picture.setPpe_pim_id(pointegersItem.getPim_id());
                    i = pictureDao.insertProductPicture(product_picture);
                }
            }
        }
        return i;
    }

    @Override
    @Transactional
    public Integer updateIntegralProduct(Pointegers_item pointegersItem) {
        try {
            int i = integralDao.updateIntegralProduct(pointegersItem);
            if (pointegersItem.getPictureList().size()>0){
                for (Product_picture picture : pointegersItem.getPictureList()) {
                    if (picture.getPpe_id()==null){
                        //System.out.println("picture》》》"+picture);
                        picture.setPpe_pim_id(pointegersItem.getPim_id());
                        i = pictureDao.insertProductPicture(picture);
                    }else {
                        //删除
                        i = pictureDao.deleteProductPictureById(picture.getPpe_id());
                        if (i>0){
                            //删除图片
                            String flag = OOS_Util.deleteimg(picture.getPpe_url());
                            if (flag.equals("success")){
                                i++;
                            }
                        }
                    }
                }
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //失败回滚
            return 0;
        }
    }


}
