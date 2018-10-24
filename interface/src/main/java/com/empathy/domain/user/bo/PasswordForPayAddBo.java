package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/30.
 */
@Getter@Setter
public class PasswordForPayAddBo {

    @ApiModelProperty("用户id")
    @Required
    private Long userId;

    @ApiModelProperty("密码")
    @Required
    private String password;


}
