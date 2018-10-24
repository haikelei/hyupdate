package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.withdraw.Withdraw;
import com.empathy.domain.withdraw.bo.WithdrawExcleBo;
import com.empathy.domain.withdraw.bo.WithdrawFindBo;

import java.util.List;

/**
 * Created by MI on 2018/4/17.
 */
public interface WithdrawDao extends BaseDao<Withdraw, Long, LogBo> {
    List<Withdraw> list(WithdrawFindBo bo);

    int count();

    int countByBo(WithdrawFindBo bo);

    /**
     * Excle导入修改提现状态
     */
    int editStatus(Withdraw withdraw);
}
