package com.empathy.domain.classify.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/13.
 */
@Getter@Setter
public class ClassifyMainAddBo {
    @ApiModelProperty("分类名")
    @Required
    private String name;
    @ApiModelProperty("分类code")
    @Required
    private Integer code;
    @ApiModelProperty("分类类型 0是专辑1是直播间")
    @Required
    private Integer type;
    @ApiModelProperty("分类图片")
    @Required
    private String url;
}
