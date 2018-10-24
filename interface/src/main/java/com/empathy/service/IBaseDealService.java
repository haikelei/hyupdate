package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.deal.bo.DealFindByUserIdBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/17.
 */
public interface IBaseDealService extends BaseService<BaseDeal, Long, PageBo> {
    RspResult findByUserId(DealFindByUserIdBo bo);
}
