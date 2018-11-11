package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseDealDao;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.deal.bo.DealFindByUserIdBo;
import com.empathy.domain.deal.bo.DealFindPageBo;
import com.empathy.domain.deal.vo.DealFindVo;
import com.empathy.domain.user.BaseMember;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MI on 2018/4/17.
 */
@Service
public class BaseDealService extends AbstractBaseService implements IBaseDealService {

    @Autowired
    private BaseDealDao baseDealDao;

    @Autowired
    private BaseMemberService memberService;


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
    public String findAllDealCount(DealFindPageBo bo) {
        return baseDealDao.findAllDealCount(bo) + "";
    }

    @Override
    public RspResult findAllDeal(DealFindPageBo bo) {
        try {

            List<DealFindVo> list = baseDealDao.findAllDeal(bo);

            for (DealFindVo vo : list) {
                BaseMember baseMember = memberService.findById(vo.getUserId());
                if (baseMember != null) {
                    vo.setUsername(baseMember.getUsername());
                    vo.setPhone(baseMember.getPhone());
                }

                if (vo.getMoney() != null) {
                    vo.setMoney(vo.getMoney().divide(new BigDecimal(10)));
                }
            }

            int count = baseDealDao.findAllDealCount(bo);
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
