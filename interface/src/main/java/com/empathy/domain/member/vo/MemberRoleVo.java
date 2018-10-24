package com.empathy.domain.member.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/11/17 14:35
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class MemberRoleVo {
    private Long roleId;
    private String name;
    private int checked = 0;
}
