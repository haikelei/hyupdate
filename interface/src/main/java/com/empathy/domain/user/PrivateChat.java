package com.empathy.domain.user;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/8/30.
 */
@Getter@Setter
public class PrivateChat extends BaseDomain{

    private Long userId;
    private Long chatUserId;
    private String content;
}
