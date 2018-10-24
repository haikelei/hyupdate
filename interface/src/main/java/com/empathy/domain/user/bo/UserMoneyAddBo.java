package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/7/12.
 */
@Getter@Setter
public class UserMoneyAddBo {
    @ApiModelProperty("用户id")
    @Required
    private Long id;
    @ApiModelProperty("充值华语币金额")
    @Required
    private BigDecimal money;
    @ApiModelProperty("充值方式 0支付宝 1wechat")
    @Required
    private Integer payType;


}
