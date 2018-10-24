package com.empathy.domain.baseMain.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/19.
 */
@Getter
@Setter
public class MainButtonDelBo {


    @ApiModelProperty(value = "id")
    @Required
    private Long id;
}
