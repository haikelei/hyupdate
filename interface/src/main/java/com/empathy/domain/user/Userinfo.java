package com.empathy.domain.user;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/13.
 */
@Getter
@Setter
public class Userinfo extends BaseDomain {

    private String loginType;

    private Long memberId;

    private String qq;

    private String wechat;

    private String token;

    private String password;

    private String address;


}
