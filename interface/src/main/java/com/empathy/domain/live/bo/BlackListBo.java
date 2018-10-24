package com.empathy.domain.live.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/6.
 */
@Getter@Setter
public class BlackListBo extends PageBo{

    @ApiModelProperty("主播id")
    @Required
    private Long id;
}
