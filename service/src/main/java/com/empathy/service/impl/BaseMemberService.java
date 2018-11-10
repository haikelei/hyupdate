package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseGradeDao;
import com.empathy.dao.BaseMemberDao;
import com.empathy.dao.UserMoneyDao;
import com.empathy.domain.grade.BaseGrade;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserMoney;
import com.empathy.domain.user.bo.FindUserBo;
import com.empathy.domain.user.bo.FreezeUserBo;
import com.empathy.domain.user.vo.MemberVo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MI on 2018/4/13.
 */
@Service
public class BaseMemberService extends AbstractBaseService implements IBaseMemberService {
    @Autowired
    private BaseMemberDao baseMemberDao;

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private BaseGradeDao baseGradeDao;


    //充值加钱
    @Override
    public void addMoney(double money,long id) {

        UserMoney byUserId = userMoneyDao.findByUserId(id);
        byUserId.setMoney(new BigDecimal(byUserId.getMoney().doubleValue()+money*10));
        byUserId.setLastRevampTime(System.currentTimeMillis());
        userMoneyDao.update(byUserId);
    }

    /**
     * 冻结
     *
     * @param bo
     * @return
     */
    @Override
    public RspResult freezeUser(FreezeUserBo bo) {
        BaseMember baseMember = baseMemberDao.findById(bo.getId());
        if (bo.getType() == 0) {
            //冻结
            baseMember.setDelFlag(1);

        } else if (bo.getType() == 1) {
            //解冻
            baseMember.setDelFlag(0);
        } else {
            return error(1, "无此操作参数");
        }
        baseMember.setLastRevampTime(System.currentTimeMillis());
        baseMemberDao.update(baseMember);
        return success();
    }


    @Override
    public RspResult findByUserExp(Long userId) {
        BaseMember baseMember = baseMemberDao.findByIdUser(userId);
        if(baseMember == null){
            return error(1,"主播走丢了");
        }
        BaseGrade grade = baseGradeDao.findByExp(baseMember.getExperience());
        Map<String,Object> map = new HashMap<>();
        map.put("userName",baseMember.getUsername());
        map.put("url",baseMember.getUrl());
        map.put("experience",baseMember.getExperience());
        map.put("proveLevel",grade.getGrade());
        map.put("grade", Integer.parseInt(grade.getGrade()) + 1);
        map.put("minExp",grade.getMinExp());
        map.put("maxExp",grade.getMaxExp());
        map.put("rule",grade.getRule());
        return success(map);
    }


    @Override
    public RspResult findAllUser(FindUserBo bo) {
        List<MemberVo> list = baseMemberDao.findAllUser(bo);
        return success(list);
    }

    @Override
    public String findAllUserCount(FindUserBo bo) {
        int count = baseMemberDao.findAllUserCount(bo);

        return count + "";
    }

    @Override
    public RspResult save(BaseMember entity) {
        return null;
    }

    @Override
    public BaseMember findById(Long aLong) {
        return baseMemberDao.findById(aLong);
    }

    @Override
    public RspResult update(BaseMember entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        baseMemberDao.delById(aLong);
        return success();
    }
}
