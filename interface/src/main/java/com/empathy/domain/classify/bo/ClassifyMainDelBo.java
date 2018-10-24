package com.empathy.domain.classify.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/13.
 */
@Getter@Setter
public class ClassifyMainDelBo {

    @ApiModelProperty("分类id")
    @Required
    private Long id;

}
