package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserMember;

/**
 * Created by MI on 2018/4/18.
 */
public interface UserMemberDao extends BaseDao<UserMember, Long, LogBo> {
    UserMember findByUserId(Long userId);

    int findCountByUserId(Long userId);
}
