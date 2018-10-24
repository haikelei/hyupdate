package com.empathy.domain.classify.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/16.
 */
@Getter
@Setter
public class ClassifyUpdBo {
    @ApiModelProperty("分类id")
    @Required
    private Long id;


    @ApiModelProperty("分类名")
    @Required
    private String name;

    @ApiModelProperty("分类code")
    @Required
    private Integer code;


    @ApiModelProperty("父id")
    @Required
    private Long parentId;

    @ApiModelProperty("分类图片地址")
    @Required
    private String url;


}
