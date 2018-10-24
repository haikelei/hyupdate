package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseDynamicDao;
import com.empathy.domain.baseDynamic.BaseDynamic;
import com.empathy.domain.baseDynamic.bo.DynamicAddBo;
import com.empathy.domain.baseDynamic.bo.DynamicAddPointBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseDynamicService;
import com.empathy.service.IBaseMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/26.
 */
@Service
public class BaseDynamicService extends AbstractBaseService implements IBaseDynamicService {

    @Autowired
    private BaseDynamicDao baseDynamicDao;


    @Override
    public RspResult addPoint(DynamicAddPointBo bo) {
        BaseDynamic baseDynamic = baseDynamicDao.findById(bo.getId());
        if (baseDynamic == null) {

            return errorMo();
        }
        baseDynamic.setRedPoint(baseDynamic.getRedPoint() + 1);
        baseDynamic.setLastRevampTime(System.currentTimeMillis());
        baseDynamicDao.update(baseDynamic);
        return success();
    }

    @Override
    public RspResult addDynamic(DynamicAddBo bo) {
        BaseDynamic baseDynamic = new BaseDynamic();
        baseDynamic.setUserId(bo.getUserId());
        baseDynamic.setContent(bo.getContent());
        baseDynamic.setTitle(bo.getTitle());
        baseDynamic.setRedPoint(0);
        baseDynamic.setCheckContent(bo.getCheckontent());
        baseDynamicDao.save(baseDynamic);
        return success();
    }

    @Override
    public RspResult save(BaseDynamic entity) {
        return null;
    }

    @Override
    public BaseDynamic findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseDynamic entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
