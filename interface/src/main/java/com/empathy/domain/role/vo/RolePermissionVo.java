package com.empathy.domain.role.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/11/17 14:40
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class RolePermissionVo {
    private Long permissionId;
    private String name;
    private int checked = 0;
}
