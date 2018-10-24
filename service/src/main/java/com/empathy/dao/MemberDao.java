package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.member.Member;
import com.empathy.domain.member.MemberRole;
import com.empathy.domain.member.bo.MemberBo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:15
 * @email zhoujian699@126.com
 * @desc
 **/
public interface MemberDao extends BaseDao<Member, Long, MemberBo> {

    Member findByUsername(String username);

    List<Member> list(MemberBo bo);

    long count(MemberBo bo);

    List<Long> findHadAssignRolesByUserId(Long memberId);

    void delRolesById(Long memberId);

    void saveRoles(List<MemberRole> list);

    List<String> findRoleById(Long id);
}
