package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/8/29.
 */
@Getter@Setter
public class FindMainForOtherBo {

    @ApiModelProperty("被查看人id")
    @Required
    private Long userId;

    @ApiModelProperty("当前用户id")
    @Required
    private Long id;

}
