package com.empathy.domain.classify.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/16.
 */
@Getter
@Setter
public class ClassifyAddBo {

    @ApiModelProperty("分类名")
    @Required
    private String name;
    @ApiModelProperty("分类code")
    @Required
    private Integer code;
    @ApiModelProperty("分类类型 0专辑分类 1主播直播间分类")
    @Required
    private Integer type;

    @ApiModelProperty("主分类id")
    @Required
    private Long id;
    @ApiModelProperty("图片url")
    @Required
    private String url;

}
