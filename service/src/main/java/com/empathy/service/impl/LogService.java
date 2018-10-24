package com.empathy.service.impl;

import com.empathy.common.Constants;
import com.empathy.common.RspResult;
import com.empathy.dao.LogDao;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.log.vo.LogVo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/10/25 11:55
 * @email zhoujian699@126.com
 * @desc
 **/
@Service
public class LogService extends AbstractBaseService implements ILogService {

    @Autowired
    private LogDao logDao;

    @Override
    public RspResult page(LogBo bo) {
        final long count = logDao.count(bo);
        List<LogVo> vos = new ArrayList<>();
        if (count > 0) {
            vos = logDao.page(bo);
            if (vos != null) {
                for (LogVo vo : vos) {
                    vo.setTypeStr(Constants.LogType.getMemo(vo.getType()));
                }
            }
        }
        return success(count, vos);
    }
}
