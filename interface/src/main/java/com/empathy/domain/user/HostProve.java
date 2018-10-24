package com.empathy.domain.user;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/24.
 */
@Getter
@Setter
public class HostProve extends BaseDomain {

    private Long userId;

    private Integer cardClassify;

    private String cardNumber;


    private Integer flowStatus;


    private String name;


}
