package com.empathy.dao;


import com.empathy.common.BaseDao;
import com.empathy.domain.log.Log;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.log.vo.LogVo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/10/25 11:55
 * @email zhoujian699@126.com
 * @desc
 **/
public interface LogDao extends BaseDao<Log, Long, LogBo> {

    long count(LogBo bo);

    List<LogVo> page(LogBo bo);
}
