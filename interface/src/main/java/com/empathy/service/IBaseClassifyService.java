package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.classify.BaseClassify;
import com.empathy.domain.classify.bo.ClassifyAddBo;
import com.empathy.domain.classify.bo.ClassifyDelBo;
import com.empathy.domain.classify.bo.ClassifyUpdBo;
import com.empathy.domain.classify.bo.FindClassifyBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/16.
 */
public interface IBaseClassifyService extends BaseService<BaseClassify, Long, PageBo> {
    RspResult addClassify(ClassifyAddBo bo);

    RspResult updClassify(ClassifyUpdBo bo);

    RspResult findAllClassify(FindClassifyBo bo);

    RspResult findClassify(ClassifyDelBo bo);

    String findClassifyCount(FindClassifyBo type);

    RspResult delClassify(ClassifyDelBo bo);
}
