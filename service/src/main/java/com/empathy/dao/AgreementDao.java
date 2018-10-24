package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.account.Account;
import com.empathy.domain.agreement.Agreement;
import com.empathy.domain.album.AlbumMoney;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface AgreementDao extends BaseDao<Agreement, Long, LogBo> {


    Agreement findByType(Integer type);

    List<Agreement> findAgreementForTitle();


}
