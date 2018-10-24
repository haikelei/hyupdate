package com.empathy.domain.baseRecording.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/23.
 */
@Getter
@Setter
public class RecordPlayBo {


    @ApiModelProperty(value = "录音id")
    @Required
    private Long id;

    @ApiModelProperty(value = "0为此首上下播放，上一首填1 下一首2 ，没有我会报错给你们")
    @Required
    private Integer type;
    @ApiModelProperty(value = "用户id")
    @Required
    private Long userId;



}
