package com.empathy.domain.baseRecording.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/23.
 */
@Getter
@Setter
public class RecordingFindBo extends PageBo {

    @ApiModelProperty(value = "专辑id 在查看所有录音时不传")
    private Long albumId;
    @ApiModelProperty(value = "用户id")
    private Long userId;


}
