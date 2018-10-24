package com.empathy.service;

import com.empathy.common.RspResult;

/**
 * @ Description
 * @ Author         dong
 * @ Date           2018-09-25 19:50
 */
public interface IBaseReadLogService {

    /** 添加用户阅读日志信息 */
    RspResult addReadLog(Long articleId,Long userId);


}
