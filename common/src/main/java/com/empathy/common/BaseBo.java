package com.empathy.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/7 17:39
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class BaseBo {

    @ApiModelProperty("ID，可选项")
    private Long id;

    @ApiModelProperty(value = "当前登录用户的ID", hidden = true)
    private Long curUserId;
}
