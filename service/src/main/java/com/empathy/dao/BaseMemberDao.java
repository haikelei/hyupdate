package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.account.bo.ProveListBo;
import com.empathy.domain.account.vo.ProveVo;
import com.empathy.domain.log.Log;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.vo.MemberVo;

import java.util.List;


/**
 * Created by MI on 2018/4/13.
 */
public interface BaseMemberDao extends BaseDao<BaseMember, Long, LogBo> {
    int findAllUserCount();

    List<MemberVo> findAllUser(PageBo bo);

    int findProveCount(ProveListBo bo);

    List<ProveVo> findProve(ProveListBo bo);

    /** 根据id查询用户 */
    BaseMember findByIdUser(Long id);

}
