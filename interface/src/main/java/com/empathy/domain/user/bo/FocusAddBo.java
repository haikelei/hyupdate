package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class FocusAddBo {

    @ApiModelProperty("用户id")
    @Required
    private Long userId;

    @ApiModelProperty("被关注的人的id")
    @Required
    private Long focusUserId;


}
