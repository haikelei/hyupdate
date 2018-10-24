package com.empathy.domain.withdraw.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class WithdrawAddBo {

    @ApiModelProperty("提现人姓名")
    @Required
    private String name;
    @ApiModelProperty("用户id")
    @Required
    private Long userId;
    @ApiModelProperty("提现方式 0支付宝 1银行")
    @Required
    private Integer withdrawType;
    @ApiModelProperty("金额")
    @Required
    private BigDecimal money;
    @ApiModelProperty("提现人密码")
    @Required
    private String password;
    @ApiModelProperty("支付宝账号")
    private String alipayAccount;
    @ApiModelProperty("银行")
    private String bank;
    @ApiModelProperty("银行卡号")
    private String card;


}
