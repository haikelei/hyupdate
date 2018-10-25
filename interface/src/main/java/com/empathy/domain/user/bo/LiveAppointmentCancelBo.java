package com.empathy.domain.user.bo;


import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiveAppointmentCancelBo {

    @ApiModelProperty("需要取消的直播ID")
    @Required
    private Long liveId;

    @ApiModelProperty("主播用户id")
    @Required
    private Long userId;
}
