package com.empathy.domain.live.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/8/30.
 */
@Getter@Setter
public class TokenAccidBo {

    @ApiModelProperty("登录给你的accid")
    @Required
    private String accid;
}
