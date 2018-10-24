package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.classify.BaseClassify;
import com.empathy.domain.classify.BaseMainClassify;
import com.empathy.domain.classify.bo.*;

/**
 * Created by MI on 2018/4/16.
 */
public interface IBaseMainClassifyService extends BaseService<BaseMainClassify, Long, PageBo> {

    RspResult addMainClassify(ClassifyMainAddBo bo);

    RspResult delMainClassify(ClassifyMainDelBo bo);

    RspResult updMainClassify(ClassifyMainUpdBo bo);

    RspResult findMainAllClassify(FindClassifyBo bo);

    RspResult findMainClassify(ClassifyDelBo bo);

    String findMainClassifyCount(FindClassifyBo type);
}
