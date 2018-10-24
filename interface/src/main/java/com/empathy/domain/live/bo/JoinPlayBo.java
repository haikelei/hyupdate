package com.empathy.domain.live.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/25.
 */
@Getter
@Setter
public class JoinPlayBo {

    @ApiModelProperty("用户id")
    @Required
    private Long userId;

    @ApiModelProperty("直播间id")
    @Required
    private Long liveId;

}
