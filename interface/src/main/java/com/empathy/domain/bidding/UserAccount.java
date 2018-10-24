package com.empathy.domain.bidding;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2017/12/26.
 */
@Getter
@Setter
public class UserAccount extends BaseDomain {
    private Long userId;
    private BigDecimal money;
    private String password;
}
