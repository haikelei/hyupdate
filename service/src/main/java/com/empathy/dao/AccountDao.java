package com.empathy.dao;

import com.empathy.common.PageBo;
import com.empathy.domain.account.Account;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface AccountDao {

    Account findById(Long id);

    void save(Account account);

    void update(Account account);

    void delById(Long id);

    List<Account> list(PageBo bo);

    int count();

    int findCountByCode(String code);

    Account findByCode(String code);

    int findByRoleId(Long id);
}
