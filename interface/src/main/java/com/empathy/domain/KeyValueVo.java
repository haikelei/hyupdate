package com.empathy.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/7 14:28
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class KeyValueVo {

    @ApiModelProperty("键")
    private String id;
    @ApiModelProperty("值")
    private String text;
    @ApiModelProperty("备注")
    private String memo;
    @ApiModelProperty("扩展字段，可能是图标地址")
    private String ext1;
}
