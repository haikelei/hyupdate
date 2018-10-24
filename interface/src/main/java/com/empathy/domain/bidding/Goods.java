package com.empathy.domain.bidding;

import com.empathy.common.BaseDomain;
import com.empathy.common.Required;
import com.empathy.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2017/12/25.
 */
@Getter
@Setter
public class Goods extends BaseDomain {

    @ApiModelProperty("文件类型")
    @Required
    private String type;

    @ApiModelProperty("文件大小")
    @Required
    private Double weight;

    @ApiModelProperty("文件大小单位")
    @Required
    private String weightNature;

    private Integer count;

}
