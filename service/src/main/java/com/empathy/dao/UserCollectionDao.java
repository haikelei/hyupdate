package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.collection.UserCollection;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.UserFocus;
import com.empathy.domain.user.bo.FocusFindBo;
import com.empathy.domain.user.vo.UserFocusVo;

import java.util.List;

/**
 * Created by MI on 2018/4/18.
 */
public interface UserCollectionDao extends BaseDao<UserCollection, Long, LogBo> {

}
