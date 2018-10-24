package com.empathy.domain.live.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/24.
 */
@Getter
@Setter
public class LiveClassifyUpdBo {
    @ApiModelProperty("分类名")
    @Required
    private String name;

    @ApiModelProperty("分类id")
    @Required
    private Long id;
}
