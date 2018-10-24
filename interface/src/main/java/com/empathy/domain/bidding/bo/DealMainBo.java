package com.empathy.domain.bidding.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/1/30.
 */
@Getter
@Setter
public class DealMainBo {
    @ApiModelProperty("用户id")
    @Required
    private Long userId;

    @ApiModelProperty("端口类型0司机1用户")
    @Required
    private Integer type;

}
