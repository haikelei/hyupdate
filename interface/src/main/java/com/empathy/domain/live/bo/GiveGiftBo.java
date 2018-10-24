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
public class GiveGiftBo {


    @ApiModelProperty(value = "礼物id")
    @Required
    private Long id;

    @ApiModelProperty(value = "用户id")
    @Required
    private Long userId;
    @ApiModelProperty(value = "直播间id")
    @Required
    private Long liveId;

    @ApiModelProperty(value = "个数")
    @Required
    private Integer count;


}
