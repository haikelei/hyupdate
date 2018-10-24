package com.empathy.service;

import com.empathy.common.RspResult;
import com.empathy.domain.log.bo.LogBo;

/**
 * @author tybest
 * @date 2017/10/25 11:54
 * @email zhoujian699@126.com
 * @desc
 **/
public interface ILogService {

    RspResult page(LogBo bo);
}
