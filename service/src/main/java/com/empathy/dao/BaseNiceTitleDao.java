package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.album.BaseNice;
import com.empathy.domain.album.BaseNiceTitle;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface BaseNiceTitleDao extends BaseDao<BaseNiceTitle, Long, LogBo> {


    List<BaseNiceTitle> list();
}
