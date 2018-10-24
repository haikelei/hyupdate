package com.empathy.domain.account;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/5/8.
 */
@Getter
@Setter
public class Account extends BaseDomain {


    private String username;

    private String password;

    private String code;

    private Long roleId;

    private String roleName;


}
