package com.empathy.domain.live.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/28.
 */
@Getter@Setter
public class FindLiveForDetailBo {


    @ApiModelProperty(value = "直播间id")
    @Required
    private Long id;
    @ApiModelProperty(value = "用户id")
    @Required
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
