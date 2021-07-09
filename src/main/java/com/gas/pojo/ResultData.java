package com.gas.pojo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.pojo
 * @ClassName: ResultData
 * @Author: gwl
 * @Description:
 * @Date: 2020/7/29 16:17
 * @Version: 1.0
 */


public class ResultData implements Serializable {

    private static final long serialVersionUID = -8653073814619156295L;

    public static JSON getResponseData(Object data,ResultCode rc) {

        JSONObject json = new JSONObject();
        json.put("data", data);
        json.put("code", rc.getResCode());
        json.put("resMsg", rc.getResMsg());

        return json;
    }
}
