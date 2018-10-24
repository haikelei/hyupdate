package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/13.
 */
@Getter
@Setter
public class RegistBo {

    @ApiModelProperty("手机号")
    @Required
    private String phone;


    @ApiModelProperty("验证码")
    @Required
    private String code;


}
