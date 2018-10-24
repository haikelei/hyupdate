package com.empathy.domain.configuration.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class ConfigChangeCommitBo {

    @ApiModelProperty("1开启 0关闭")
    @Required
    private Integer status;

}
