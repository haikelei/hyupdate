package com.empathy.domain.schedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/9/28 17:54
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@ApiModel("任务设置")
public class ScheduleJobVo {

    @ApiModelProperty("状态：0-关；1-开")
    private int statuz = 0;

    @ApiModelProperty("小时")
    private String hour = "18";

    @ApiModelProperty("分钟")
    private String minute = "30";
}
