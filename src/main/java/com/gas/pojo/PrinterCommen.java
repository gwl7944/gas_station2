package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.pojo
 * @ClassName: PrinterCommen
 * @Author: gwl
 * @Description:  打印机请求公共参数封装
 * @Date: 2021/4/24 18:09
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrinterCommen {

    private String user;  //飞鹅云后台注册用户名。

    private String stime;  //当前UNIX时间戳，10位，精确到秒。。

    private String sig;  //对参数 user+UKEY+stime拼接后（+号表示连接符）进行SHA1加密得到签名，加密后签名值为40位小写字符串。。

    private String apiname;  //请求的接口名称：Open_printMsg。

    private String debug;  //debug=1返回非json格式的数据。仅测试时候使用。。


}
