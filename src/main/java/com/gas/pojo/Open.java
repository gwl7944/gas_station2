package com.gas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/3/27 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Open {

    private String appId;

    private String secret;

    private String code;

}
