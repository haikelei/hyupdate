package com.empathy.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author tybest
 * @date 2017/8/5 8:46
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class BaseDomain {

    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Long createTime = System.currentTimeMillis();

    @ApiModelProperty(value = "修改时间", hidden = true)
    private Long lastRevampTime = System.currentTimeMillis();

    @ApiModelProperty(value = "删除标志：0：正常；1：删除", hidden = true)
    private Integer delFlag = 0;
}
