package com.empathy.domain.user;

import com.empathy.common.BaseDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class UserMoney extends BaseDomain {

    private Long userId;

    private String password;

    private BigDecimal money;


}
