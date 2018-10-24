package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserFocus;
import com.empathy.domain.user.bo.FocusAddBo;
import com.empathy.domain.user.bo.FocusCancelBo;
import com.empathy.domain.user.bo.FocusFindBo;

/**
 * Created by MI on 2018/4/18.
 */
public interface IUserFocusService extends BaseService<UserFocus, Long, PageBo> {


    RspResult addFocus(FocusAddBo bo);

    RspResult cancelFocus(FocusCancelBo bo);

    RspResult findFocus(FocusFindBo bo);
}
