package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.classify.BaseClassify;
import com.empathy.domain.classify.bo.ClassifyDelBo;
import com.empathy.domain.classify.bo.FindClassifyBo;
import com.empathy.domain.live.LiveClassify;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;

import java.util.List;

/**
 * Created by MI on 2018/4/16.
 */
public interface BaseClassifyDao extends BaseDao<BaseClassify, Long, LogBo> {


    List<BaseClassify> list(FindClassifyBo bo);

    int count(FindClassifyBo bo);

    List<LiveClassify> listForLive();
}
