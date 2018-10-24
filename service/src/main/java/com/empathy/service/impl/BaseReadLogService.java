package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseReadLogDao;
import com.empathy.domain.baseReadLog.BaseReadLog;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseReadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ Description
 * @ Author         dong
 * @ Date           2018-09-25 19:53
 */
@Service
public class BaseReadLogService extends AbstractBaseService implements IBaseReadLogService {

    @Autowired
    private BaseReadLogDao readLogDao;

    @Override
    public RspResult addReadLog(Long articleId, Long userId) {
        //判断是否已经读过
        BaseReadLog ReadLog = readLogDao.findByUserAndArticle(articleId,userId);
        if(ReadLog != null){
            return success();
        }
        //插入数据
        BaseReadLog baseReadLog = new BaseReadLog();
        baseReadLog.setArticleId(articleId);
        baseReadLog.setUserId(userId);
        baseReadLog.setCreateTime(new Date().getTime());
        baseReadLog.setLastRevampTime(new Date().getTime());
        baseReadLog.setDelFlag(0);
        readLogDao.insert(baseReadLog);
        return success();
    }
}
