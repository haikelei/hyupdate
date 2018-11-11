package com.empathy.domain.deal.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealFindPageBo extends PageBo {


    @ApiModelProperty("用户id")
    @Required
    private Long userId;

    @ApiModelProperty("状态")
    @Required
    private Integer status;

    @ApiModelProperty("类型")
    @Required
    private Integer type;


}
