package com.empathy.service.impl;

import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.TbRole;
import com.empathy.domain.account.bo.AccoutLoginBo;
import com.empathy.domain.account.bo.ProveListAddBo;
import com.empathy.domain.account.bo.ProveListBo;
import com.empathy.domain.account.bo.UpdPasswordBo;
import com.empathy.domain.account.vo.ProveAddVo;
import com.empathy.domain.account.vo.ProveVo;
import com.empathy.domain.agreement.Agreement;
import com.empathy.domain.agreement.bo.*;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IAccountService;
import com.empathy.service.IAgreementService;
import com.empathy.utils.MD5Utils;
import com.empathy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
@Service
public class AgreementService extends AbstractBaseService implements IAgreementService {

    @Autowired
    private AgreementDao agreementDao;


    @Override
    public RspResult findAgreementForTitle() {

        try {
            List<Agreement> list = agreementDao.findAgreementForTitle();
            return success(list);
        }catch (Exception e){
            e.printStackTrace();
            return error(1,"找不到数据");

        }
    }

    @Override
    public RspResult findAgreementById(AgreementFindByIdBo bo) {
        Agreement agreement = agreementDao.findById(bo.getId());
        return success(agreement);
    }

    @Override
    public RspResult findAgreementByType(AgreementFindByTypeBo bo) {
        Agreement agreement = agreementDao.findByType(bo.getType());
        return success(agreement);
    }

    @Override
    public RspResult delAgreement(AgreementDelBo bo) {
        agreementDao.delById(bo.getId());
        return success();
    }


    @Override
    public RspResult updAgreement(AgreementUpdBo bo) {

        Agreement agreement = agreementDao.findById(bo.getId());

        if (StringUtil.isNotEmpty(bo.getTitle())) {
            agreement.setTitle(bo.getTitle());
        }
        if (StringUtil.isNotEmpty(bo.getContent())) {
            agreement.setContent(bo.getContent());
        }
        agreement.setLastRevampTime(System.currentTimeMillis());
        agreementDao.update(agreement);
        return success();
    }

    @Override
    public RspResult addAgreement(AgreementAddBo bo) {

        if (!StringUtil.isNotIntegerEmpty(bo.getType())) {
            return error(1, "协议类型不能为空!");
        }
        if (!StringUtil.isNotEmpty(bo.getTitle())) {
            return error(1, "协议标题不能为空!");
        }
        if (!StringUtil.isNotEmpty(bo.getContent())) {
            return error(1, "协议内容不能为空!");
        }

        Agreement agreement = new Agreement();
        agreement.setContent(bo.getContent());
        agreement.setTitle(bo.getTitle());
        agreement.setType(bo.getType());
        agreementDao.save(agreement);
        return success();
    }

    @Override
    public RspResult save(Agreement entity) {
        return null;
    }

    @Override
    public Agreement findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(Agreement entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
