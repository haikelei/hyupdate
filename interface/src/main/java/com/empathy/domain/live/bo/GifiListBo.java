package com.empathy.domain.live.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/8/13.
 */
@Getter@Setter
public class GifiListBo extends PageBo {
    @ApiModelProperty("主播id")
    @Required
    private Long id;
    @ApiModelProperty("0本场礼物，1本周礼物")
    @Required
    private Integer type;

}
