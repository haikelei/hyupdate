package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.classify.BaseClassify;
import com.empathy.domain.classify.BaseMainClassify;
import com.empathy.domain.classify.bo.ClassifyDelBo;
import com.empathy.domain.classify.bo.FindClassifyBo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/4/16.
 */
public interface BaseMainClassifyDao extends BaseDao<BaseMainClassify, Long, LogBo> {


    int findCountForMain(Long id);

    int findByType(FindClassifyBo bo);

    List<BaseMainClassify> listByType(FindClassifyBo bo);

    BaseMainClassify findDetail(ClassifyDelBo bo);
}
