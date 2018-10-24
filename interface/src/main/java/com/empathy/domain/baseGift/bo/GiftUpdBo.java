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
public class GiftUpdBo {


    @ApiModelProperty(value = "礼物id")
    @Required
    private Long id;
    @ApiModelProperty(value = "礼物名")
    private String giftName;
    @ApiModelProperty(value = "礼物价格")
    private BigDecimal price;
    @ApiModelProperty(value = "礼物华语币价格")
    private BigDecimal money;
    @ApiModelProperty(value = "礼物价格")
    private BigDecimal defaultPrice;
    @ApiModelProperty(value = "礼物华语币价格")
    private BigDecimal defaultMoney;
    @ApiModelProperty(value = "排序值")
    private Integer code;
    @ApiModelProperty(value = "经验值")
    private Integer exp;
    @ApiModelProperty(value = "图片地址")
    private String url;
}
