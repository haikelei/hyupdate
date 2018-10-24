package com.empathy.domain.live.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/6.
 */
@Getter@Setter
public class FreezeAddBo {

    @ApiModelProperty("被拉黑用户id")
    @Required
    private Long userId;
    @ApiModelProperty("主播id")
    @Required
    private Long id;
    @ApiModelProperty("房间id")
    @Required
    private Long liveId;
    @ApiModelProperty("拉黑人id（主播或者房管）")
    @Required
    private Long freezeId;

}
