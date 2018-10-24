package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class MemberAddBo {

    @ApiModelProperty("用户id")
    @Required
    private Long userId;

    @ApiModelProperty("金额")
    @Required
    private BigDecimal money;

    @ApiModelProperty("支付方式和充值华语币一样")
    @Required
    private Integer payType;


}
