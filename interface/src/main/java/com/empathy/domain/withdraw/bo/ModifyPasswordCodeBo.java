package com.empathy.domain.withdraw.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyPasswordCodeBo {
    @ApiModelProperty("用户手机号")
    @Required
    private String phone;
}
