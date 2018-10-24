package com.empathy.domain.bidding.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/2/28.
 */
@Getter
@Setter
public class ChangeDealPasswordBo {
    @ApiModelProperty("用户id")
    @Required
    private Long id;

    @ApiModelProperty("验证码")
    @Required
    private String code;

    @ApiModelProperty("密码")
    @Required
    private String password;
}
