package com.empathy.domain.deal;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class BaseDeal extends BaseDomain {

    private Long userId;

    private String code;

    private Integer type;

    private BigDecimal money;


    private Integer status=1;

}
