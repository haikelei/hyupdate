package com.empathy.domain.baseGift.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/25.
 */
@Getter
@Setter
public class GiftAddBo {


    @ApiModelProperty(value = "礼物名")
    @Required
    private String giftName;
    @ApiModelProperty(value = "礼物价格")
    @Required
    private BigDecimal price;
    @ApiModelProperty(value = "礼物华语币价格")
    @Required
    private BigDecimal money;
    @ApiModelProperty(value = "礼物初始价格")
    @Required
    private BigDecimal defaultPrice;
    @ApiModelProperty(value = "礼物初始华语币价格")
    @Required
    private BigDecimal defaultMoney;
    @ApiModelProperty(value = "排序值")
    @Required
    private Integer code;
    @ApiModelProperty(value = "经验值")
    private Integer exp;
    @ApiModelProperty(value = "图片地址")
    private String url;


}
