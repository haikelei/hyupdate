package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.baseDynamic.BaseDynamic;
import com.empathy.domain.baseDynamic.bo.DynamicAddBo;
import com.empathy.domain.baseDynamic.bo.DynamicAddPointBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/26.
 */
public interface IBaseDynamicService extends BaseService<BaseDynamic, Long, PageBo> {
    RspResult addDynamic(DynamicAddBo bo);

    RspResult addPoint(DynamicAddPointBo bo);
}
