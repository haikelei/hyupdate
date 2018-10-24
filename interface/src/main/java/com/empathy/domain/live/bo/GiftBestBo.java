package com.empathy.domain.live.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/16.
 */
@Getter
@Setter
public class GiftBestBo extends PageBo {

    @ApiModelProperty(value = "0日榜 1周榜2 月榜")
    @Required
    private Integer type;

}
