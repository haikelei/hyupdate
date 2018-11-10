package com.empathy.domain.user.bo;

import com.empathy.common.PageBo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserBo extends PageBo {

    private Long sex;

    private Integer proveStatus;

    private Integer memberStatus;

    private Integer delStatus;

    private String username;

    private String phone;
}
