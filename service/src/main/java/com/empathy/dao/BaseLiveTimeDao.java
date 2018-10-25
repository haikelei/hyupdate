package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.live.BaseLiveTime;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/24.
 */
public interface BaseLiveTimeDao extends BaseDao<BaseLiveTime, Long, LogBo> {

    BaseLiveTime findByLiveIdForClose(Long liveId);


    /** 主播编号查询主播最后一次直播的直播时间 */
    BaseLiveTime findByLiveUserId(Long userId);

    /** 直播房间号查询主播最后一次直播的直播时间 */
    BaseLiveTime findByLiveId(Long liveId);

    void delByLiveId(Long liveId);

}
