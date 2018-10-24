package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/24.
 */
@Getter
@Setter
public class ProveUpdBo {

    @ApiModelProperty("认证表id")
    @Required
    private Long id;

    @ApiModelProperty("是否通过 0通过 1失败")
    @Required
    private Integer status;
    @ApiModelProperty("分类id")
    @Required
    private Long classifyId;


}
