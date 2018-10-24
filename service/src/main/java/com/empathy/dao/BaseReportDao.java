package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.baseReport.BaseReport;
import com.empathy.domain.baseReport.bo.ReportFindBo;
import com.empathy.domain.baseReport.vo.ReportVo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/4/26.
 */
public interface BaseReportDao extends BaseDao<BaseReport, Long, LogBo> {
    List<ReportVo> list(ReportFindBo bo);

    int count(ReportFindBo bo);
}
