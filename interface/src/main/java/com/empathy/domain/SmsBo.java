package com.empathy.domain;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2017/12/27.
 */
@Getter
@Setter
public class SmsBo {
    @ApiModelProperty("手机号")
    @Required
    private String phone;
}
