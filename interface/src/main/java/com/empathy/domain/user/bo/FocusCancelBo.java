package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class FocusCancelBo {

    @ApiModelProperty("被关注人id")
    @Required
    private Long id;
    @ApiModelProperty("用户id")
    @Required
    private Long userId;

}
