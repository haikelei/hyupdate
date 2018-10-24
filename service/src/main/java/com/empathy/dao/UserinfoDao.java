package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.member.Member;
import com.empathy.domain.member.bo.MemberBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.FindPhone;
import com.empathy.domain.user.Userinfo;
import com.empathy.domain.user.bo.TokenFindBo;

/**
 * Created by MI on 2018/4/13.
 */
public interface UserinfoDao extends BaseDao<Userinfo, Long, PageBo> {
    int findCountByPhone(FindPhone findPhone);

    Userinfo findByMemberId(Long memberId);

    Userinfo findByPhone(String phone);

    int findPhoneCountByToken(TokenFindBo tokenFindBo);

    int findCountByToken(TokenFindBo tokenFindBo);


    BaseMember findMemberByToken(TokenFindBo tokenFindBo);

    Userinfo findByToken(TokenFindBo tokenFindBo);

    int findCountByPhoneByFreeze(FindPhone findPhone);
}
