package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.collection.UserCollection;
import com.empathy.domain.collection.bo.CollectionAddBo;
import com.empathy.domain.collection.bo.CollectionFindBo;
import com.empathy.domain.user.UserFocus;
import com.empathy.domain.user.bo.FocusAddBo;
import com.empathy.domain.user.bo.FocusCancelBo;
import com.empathy.domain.user.bo.FocusFindBo;

/**
 * Created by MI on 2018/4/18.
 */
public interface IUserCollectionService extends BaseService<UserCollection, Long, PageBo> {


    RspResult addCollection(CollectionAddBo bo);

    RspResult cancelCollection(Long id);

    RspResult findCollection(CollectionFindBo bo);
}
