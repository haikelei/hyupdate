package com.empathy.domain.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/5/10.
 */
@Getter
@Setter
public class MemberVo {

    private Long id;

    private String username;

    private Integer sex;

    private String phone;

    private String address;

    private String intro;

    private Integer level;

    private BigDecimal money;

    private Integer proveStatus;

    private Integer memberStatus;

    private Long createTime;

    private Integer delFlag;


}
