package com.empathy.domain.withdraw;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class Withdraw extends BaseDomain {

    private Integer withdrawType;

    private String bank;

    private String card;

    private BigDecimal money;

    private String code;

    private String alipayAccount;

    private Integer withdrawStatus;

    private BigDecimal lastMoney;

    private String name;

    private Long userId;


    private String username;

    private String phone;


}
