package com.empathy.domain.account.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/5/11.
 */
@Getter
@Setter
public class ProveAddVo {

    private Long id;

    private Long memberId;

    private String username;

    private String name;

    private String userPhone;

    private Integer cardClassify;

    private String cardNumber;

    private Integer flowStatus;

    private String phone;

    private Long createTime;

    private Integer delFlag;


}
