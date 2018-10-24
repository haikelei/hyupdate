package com.empathy.domain.user.vo;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class UserFocusVo extends BaseDomain {
    private Long userId;

    private Long focusUserId;

    private String focusUsername;

    private String url;

    private Integer status;//0用户 1主播


}
