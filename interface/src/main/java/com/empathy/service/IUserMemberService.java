package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserMember;
import com.empathy.domain.user.bo.MemberAddBo;

/**
 * Created by MI on 2018/4/18.
 */
public interface IUserMemberService extends BaseService<UserMember, Long, PageBo> {
    RspResult tobeMember(MemberAddBo bo);

    void tobeMemberForPay(Long userId);
}
