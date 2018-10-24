package com.empathy.domain.user;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class UserFocus extends BaseDomain {

    private Long userId;

    private Long focusUserId;

}
