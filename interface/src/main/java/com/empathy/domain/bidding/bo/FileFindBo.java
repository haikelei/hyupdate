package com.empathy.domain.bidding.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/1/29.
 */
@Getter
@Setter
public class FileFindBo {

    @ApiModelProperty("用途id")
    @Required
    private Long purposeId;

    @ApiModelProperty("用途")
    @Required
    private String purpose;


}
