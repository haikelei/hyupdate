package com.empathy.domain.withdraw.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyPasswordBo {
    @ApiModelProperty("用户手机号")
    @Required
    private String phone;
    @ApiModelProperty("提现人密码")
    @Required
    private String password;
    @ApiModelProperty("验证码")
    @Required
    private String code;
}
