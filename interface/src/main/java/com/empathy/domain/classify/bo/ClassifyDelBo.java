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
public class ClassifyDelBo {


    @ApiModelProperty("分类id")
    @Required
    private Long id;
}
