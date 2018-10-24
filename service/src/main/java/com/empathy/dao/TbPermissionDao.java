package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.account.TbPermission;
import com.empathy.domain.comments.TbComment;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/6/25.
 */
public interface TbPermissionDao  extends BaseDao<TbPermission, Long, LogBo> {

    List<TbPermission> list(PageBo bo);

    int count();

    List<TbPermission> lists();
}
