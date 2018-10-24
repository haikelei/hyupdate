package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.baseMain.BaseMain;
import com.empathy.domain.baseMain.bo.MainButtonAddBo;
import com.empathy.domain.baseMain.bo.MainButtonDelBo;
import com.empathy.domain.baseMain.bo.MainButtonUpdBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/19.
 */
public interface IBaseMainService extends BaseService<BaseMain, Long, PageBo> {
    RspResult addMainButton(MainButtonAddBo bo);

    RspResult updMainButton(MainButtonUpdBo bo);

    RspResult delMainButton(MainButtonDelBo bo);
}
