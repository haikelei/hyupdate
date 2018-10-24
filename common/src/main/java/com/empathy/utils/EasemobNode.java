package com.empathy.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/9/27 11:20
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@ApiModel("环信")
public class EasemobNode {
    @ApiModelProperty("访问token")
    private String access_token;
    @ApiModelProperty("appkey")
    private String appkey;
}
