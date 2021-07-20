package com.gas.service.impl;

import com.gas.dao.IntegralDao;
import com.gas.dao.PictureDao;
import com.gas.pojo.Pointegers_item;
import com.gas.pojo.Page;
import com.gas.pojo.Product_picture;
import com.gas.service.IntegralService;
import com.gas.util.OOS_Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

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
                        if (picture.getPpe_default()==1){
                            i = pictureDao.updatePictureDefault(pointegersItem.getPim_id());
                        }
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

    @Override
    public Page<Pointegers_item> findIntegralProduct(Pointegers_item pointegersItem, Integer currentpage, Integer currentnumber) {

        Page<Pointegers_item> page = new Page<>();
        PageHelper.startPage(currentpage, currentnumber);
        List<Pointegers_item> pointegers_items = integralDao.findIntegralProduct(pointegersItem);
        System.out.println("pointegers_items>>"+pointegers_items);
        for (Pointegers_item pointegers_item : pointegers_items) {
                pointegers_item.setPictureList(pictureDao.findProductPictureByPpePimId(pointegers_item.getPim_id()));
        }
        PageInfo<Pointegers_item> info = new PageInfo<>(pointegers_items);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(currentpage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }


}
