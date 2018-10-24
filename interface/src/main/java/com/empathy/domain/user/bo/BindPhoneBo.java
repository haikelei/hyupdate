package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/16.
 */
@Getter
@Setter
public class BindPhoneBo {

    @ApiModelProperty("登录方式 根据登录方式来决定参数是否选填")
    @Required
    private String loginType;

    @ApiModelProperty("手机号 ")
    @Required
    private String phone;
    @ApiModelProperty("验证码 ")
    @Required
    private String code;

    @ApiModelProperty("token 三方token ")
    @Required
    private String token;

}
