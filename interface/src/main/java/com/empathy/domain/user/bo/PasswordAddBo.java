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
public class PasswordAddBo {

    @ApiModelProperty("密码")
    @Required
    private String password;
    @ApiModelProperty("用户id")
    @Required
    private Long memberId;
}
