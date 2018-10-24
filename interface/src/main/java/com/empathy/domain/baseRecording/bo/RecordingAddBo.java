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
public class RecordingAddBo {


    @ApiModelProperty(value = "专辑id")
    @Required
    private Long albumId;

    @ApiModelProperty(value = "标题")
    @Required
    private String title;
    @ApiModelProperty(value = "用户id")
    @Required
    private Long userId;
    @ApiModelProperty(value = "唯一标识")
    @Required
    private String sign;
    @ApiModelProperty(value = "专辑封面地址")
    @Required
    private String url;




}
