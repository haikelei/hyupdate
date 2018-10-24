package com.empathy.domain.baseDynamic.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/26.
 */
@Getter
@Setter
public class DynamicAddPointBo {

    @ApiModelProperty(value = "动态id")
    @Required
    private Long id;


}
