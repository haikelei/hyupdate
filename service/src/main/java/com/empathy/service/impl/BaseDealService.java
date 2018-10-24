package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseDealDao;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.deal.bo.DealFindByUserIdBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseDealService;
import com.empathy.service.IBaseMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/4/17.
 */
@Service
public class BaseDealService extends AbstractBaseService implements IBaseDealService {

    @Autowired
    private BaseDealDao baseDealDao;


    @Override
    public RspResult findByUserId(DealFindByUserIdBo bo) {
        try {

            List<BaseDeal> list = baseDealDao.list(bo);
            int count = baseDealDao.count(bo.getId());
            return success(count, list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult save(BaseDeal entity) {
        return null;
    }

    @Override
    public BaseDeal findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseDeal entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
