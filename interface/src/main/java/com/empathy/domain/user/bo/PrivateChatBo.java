package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/8/30.
 */
@Getter@Setter
public class PrivateChatBo {

    @ApiModelProperty("用户id")
    @Required
    private Long userId;
    @ApiModelProperty("被私聊人id")
    @Required
    private Long id;
    @ApiModelProperty("内容")
    @Required
    private String content;

}
