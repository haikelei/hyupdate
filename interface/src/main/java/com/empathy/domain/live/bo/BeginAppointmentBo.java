package com.empathy.domain.live.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/6.
 */
@Getter@Setter
public class BeginAppointmentBo {


    @ApiModelProperty("标题")
    @Required
    private String title;


    @ApiModelProperty("话题 选填")
    private String theme;

    @ApiModelProperty("用户id")
    @Required
    private Long userId;


    @ApiModelProperty("分类id")
    @Required
    private Long classifyId;


    @ApiModelProperty("开播时间")
    @Required
    private Long beginTime;


    @ApiModelProperty("是否通知粉丝 0通知 1不通知")
    @Required
    private Integer type;
}
