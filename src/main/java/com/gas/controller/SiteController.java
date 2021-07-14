package com.gas.controller;

import com.alibaba.fastjson.JSON;
import com.gas.pojo.Page;
import com.gas.pojo.ResultCode;
import com.gas.pojo.ResultData;
import com.gas.pojo.Site;
import com.gas.service.SiteService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywj
 * Date: 2021/7/14
 * Time: 16:03
 * Description: 站点
 */
@RestController
public class SiteController {

    @Resource
    SiteService siteService;

    /**
     * 查询所有站点
     */
    @GetMapping("/AuthorityController/findSitePage")
    public JSON findSitePage(@Param("currentpage") Integer currentpage, @Param("currentnumber") Integer currentnumber) {
        Page<Site> page = siteService.findSite(currentpage, currentnumber);
        return ResultData.getResponseData(page, ResultCode.SYS_SUCCESS);
    }

    /**
     * 查询所有站点
     */
    @GetMapping("/AuthorityController/findSite")
    public JSON findSite() {
        List<Site> siteList = siteService.findSiteNoPage();
        return ResultData.getResponseData(siteList, ResultCode.SYS_SUCCESS);
    }

    /**
     * 新增站点
     */
    @PostMapping("/AuthorityController/addSite")
    public JSON addSite(@RequestBody Site site) {
        return ResultData.getResponseData(siteService.addSite(site), ResultCode.SYS_SUCCESS);
    }

    /**
     * 修改站点
     */
    @PostMapping("/AuthorityController/updateSite")
    public JSON updateSite(@RequestBody Site site) {
        return ResultData.getResponseData(siteService.updateSite(site), ResultCode.SYS_SUCCESS);
    }

    /**
     * 删除站点
     */
    @GetMapping("/AuthorityController/deleteSite/{site_id}")
    public JSON deleteSite(@PathVariable("site_id") Integer site_id) {
        return ResultData.getResponseData(siteService.deleteSite(site_id), ResultCode.SYS_SUCCESS);
    }
}
