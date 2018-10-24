package com.empathy.domain.user;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/13.
 */
@Getter
@Setter
public class UserMember extends BaseDomain {

    private Long userId;


    private Long endTime;
}
