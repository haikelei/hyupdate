package com.empathy.domain.bidding.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/1/25.
 */
@Getter
@Setter
public class PlaceBo {

    @ApiModelProperty("司机id")
    @Required
    private Long id;

    @ApiModelProperty("经度")
    @Required
    private double longitude;

    @ApiModelProperty("纬度")
    @Required
    private double latitude;


}
