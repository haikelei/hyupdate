package com.empathy.domain.live.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/6.
 */
@Getter@Setter
public class RoomAddManageBo {

    @ApiModelProperty("用户id")
    @Required
    private Long userId;
    @ApiModelProperty("房间id")
    @Required
    private Long liveId;
    @ApiModelProperty("主播id")
    @Required
    private Long id;

}
