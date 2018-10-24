package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.domain.user.UserMoney;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseMemberService;
import com.empathy.service.IUserMoneyService;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/17.
 */
@Service
public class UserMoneyService extends AbstractBaseService implements IUserMoneyService {

    @Override
    public RspResult save(UserMoney entity) {
        return null;
    }

    @Override
    public UserMoney findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(UserMoney entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
