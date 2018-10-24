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
public class RecordingAddNumberBo {


    @ApiModelProperty(value = "录音id")
    @Required
    private Long id;

}
