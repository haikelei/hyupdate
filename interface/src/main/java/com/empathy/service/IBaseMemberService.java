package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.account.bo.AccoutLoginBo;
import com.empathy.domain.member.bo.MemberBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.Userinfo;
import com.empathy.domain.user.bo.FreezeUserBo;

/**
 * Created by MI on 2018/4/13.
 */
public interface IBaseMemberService extends BaseService<BaseMember, Long, PageBo> {
    String findAllUserCount();

    RspResult findAllUser(PageBo bo);

    RspResult freezeUser(FreezeUserBo bo);

    void addMoney(double money,long id);

    RspResult findByUserExp(Long userId);
}
