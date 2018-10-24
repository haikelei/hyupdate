package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.account.TbRole;
import com.empathy.domain.user.Userinfo;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface TbRoleDao extends BaseDao<TbRole, Long, PageBo> {

    List<TbRole> list(PageBo bo);


    int count();

    int countById(Long roleId);
}
