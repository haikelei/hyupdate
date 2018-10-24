package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.live.LiveBlacklist;
import com.empathy.domain.live.RoomManage;
import com.empathy.domain.live.bo.*;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface LiveBlacklistDao extends BaseDao<LiveBlacklist, Long, LogBo> {



    int count(Long id);

    List<BaseMember> list(BlackListBo bo);
    int findCountByUserId(BlacklistAddBo bo);

    LiveBlacklist findByUserIdAndId(BlacklistAddBo bo);

    int findBlackCount(JoinPlayBo bo);
}
