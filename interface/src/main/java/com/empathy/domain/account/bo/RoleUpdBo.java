package com.empathy.domain.account.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/25.
 */
@Getter@Setter
public class RoleUpdBo {

    private String permissionIds;
    private Long id;
    private String code;
    private String memo;
    private String name;
 }
