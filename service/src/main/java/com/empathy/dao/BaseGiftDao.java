package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.baseGift.BaseGift;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;

import java.util.List;

/**
 * Created by MI on 2018/4/25.
 */
public interface BaseGiftDao extends BaseDao<BaseGift, Long, LogBo> {

    List<BaseGift> list();

    int count();
}
