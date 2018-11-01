package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseReportDao;
import com.empathy.domain.baseReport.BaseReport;
import com.empathy.domain.baseReport.bo.ReportFindBo;
import com.empathy.domain.baseReport.vo.ReportVo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseReportSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MI on 2018/4/19.
 */
@Service
public class BaseReportService extends AbstractBaseService implements IBaseReportSerivce {

    @Autowired
    private BaseReportDao baseReportDao;

    @Override
    public String findReportCount(ReportFindBo bo) {

        int count = baseReportDao.count(bo);

        return count+"";
    }

    @Override
    public RspResult page(ReportFindBo bo) {

        long count = baseReportDao.count(bo);
        List<ReportVo> list = new ArrayList<>();
        if (count > 0) {
            list = baseReportDao.list(bo);
//            if (list != null) {
//                for (Member m : list) {
//                    m.setRole(getRole(m.getId()));
//                }
//            }
        }
        return success(count, list);
    }

    @Override
    public RspResult save(BaseReport entity) {
        return null;
    }

    @Override
    public BaseReport findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseReport entity) {
        return null;
    }

    @Override
    public RspResult delById(Long id) {
        baseReportDao.delById(id);
        return success();
    }
}
