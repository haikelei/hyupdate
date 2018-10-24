package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.domain.baseCode.BaseCode;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseCodeService;
import com.empathy.service.IBaseMemberService;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/17.
 */
@Service
public class BaseCodeService extends AbstractBaseService implements IBaseCodeService {

    @Override
    public RspResult save(BaseCode entity) {
        return null;
    }

    @Override
    public BaseCode findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseCode entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
