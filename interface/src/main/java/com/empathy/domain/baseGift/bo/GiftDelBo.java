package com.empathy.domain.baseGift.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/25.
 */
@Getter
@Setter
public class GiftDelBo {


    @ApiModelProperty(value = "礼物id")
    @Required
    private Long id;

}
