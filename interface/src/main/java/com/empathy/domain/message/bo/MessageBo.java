package com.empathy.domain.message.bo;

import com.empathy.common.PageBo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/22 20:36
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class MessageBo extends PageBo {

    @ApiModelProperty(hidden = true)
    private Long userId;

    @ApiModelProperty(hidden = true)
    private int type = 0;
}
