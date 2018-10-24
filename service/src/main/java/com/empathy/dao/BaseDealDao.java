package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.deal.bo.DealFindByUserIdBo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/4/17.
 */
public interface BaseDealDao extends BaseDao<BaseDeal, Long, LogBo> {
    List<BaseDeal> list(DealFindByUserIdBo bo);

    int count(Long id);



}
