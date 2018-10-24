package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.account.bo.ProveListAddBo;
import com.empathy.domain.account.vo.ProveAddVo;
import com.empathy.domain.member.Member;
import com.empathy.domain.member.bo.MemberBo;
import com.empathy.domain.user.HostProve;

import java.util.List;

/**
 * Created by MI on 2018/4/24.
 */
public interface HostProveDao extends BaseDao<HostProve, Long, PageBo> {

    int count(ProveListAddBo bo);

    List<ProveAddVo> list(ProveListAddBo bo);


    int findCountByUpd(Long id);
}
