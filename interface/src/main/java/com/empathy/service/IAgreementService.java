package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.bo.AccoutLoginBo;
import com.empathy.domain.account.bo.ProveListAddBo;
import com.empathy.domain.account.bo.ProveListBo;
import com.empathy.domain.account.bo.UpdPasswordBo;
import com.empathy.domain.agreement.Agreement;
import com.empathy.domain.agreement.bo.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MI on 2018/5/9.
 */
public interface IAgreementService extends BaseService<Agreement, Long, PageBo> {


    RspResult addAgreement(AgreementAddBo bo);

    RspResult updAgreement(AgreementUpdBo bo);

    RspResult delAgreement(AgreementDelBo bo);

    RspResult findAgreementById(AgreementFindByIdBo bo);

    RspResult findAgreementByType(AgreementFindByTypeBo bo);

    RspResult findAgreementForTitle();
}
