package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.live.LiveClassify;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;

import java.util.List;

/**
 * Created by MI on 2018/4/24.
 */
public interface LiveClassifyDao extends BaseDao<LiveClassify, Long, LogBo> {
    List<LiveClassify> list();
}
