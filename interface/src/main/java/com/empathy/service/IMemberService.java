package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.RspResult;
import com.empathy.domain.member.Member;
import com.empathy.domain.member.bo.MemberBo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:14
 * @email zhoujian699@126.com
 * @desc
 **/
public interface IMemberService extends BaseService<Member, Long, MemberBo> {

    Member findByUsername(String username);

    RspResult page(MemberBo bo);

    List<Long> findHadAssignRolesByUserId(Long memberId);



}
