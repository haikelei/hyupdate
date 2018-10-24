package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.baseGift.BaseGift;
import com.empathy.domain.live.BaseLiveChannel;
import com.empathy.domain.log.bo.LogBo;

/**
 * Created by MI on 2018/6/28.
 */
public interface BaseLiveChannelDao extends BaseDao<BaseLiveChannel, Long, LogBo> {

    BaseLiveChannel findByUserId(Long id);

    BaseLiveChannel findByAccid(String accid);
}
