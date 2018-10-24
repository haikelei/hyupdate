package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserMoney;

/**
 * Created by MI on 2018/4/17.
 */
public interface UserMoneyDao extends BaseDao<UserMoney, Long, LogBo> {

    UserMoney findByUserId(Long id);
}
