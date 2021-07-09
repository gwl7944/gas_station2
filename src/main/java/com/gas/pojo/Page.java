package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.util
 * @ClassName: Page
 * @Author: gwl
 * @Description:
 * @Date: 2020/7/29 15:52
 * @Version: 1.0
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {

    private Integer currentpage;     //当前页
    private Integer currentnumber;   //当前条数
    private Integer totalnumber;     //总条数
    private Integer pagecount;       //总页数

    private List<T> datalist;
    private T obj;

}
