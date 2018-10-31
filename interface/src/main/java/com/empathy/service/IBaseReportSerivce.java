package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.baseReport.BaseReport;
import com.empathy.domain.baseReport.bo.ReportFindBo;

/**
 * Created by MI on 2018/4/19.
 */
public interface IBaseReportSerivce extends BaseService<BaseReport, Long, PageBo> {


    String findReportCount(ReportFindBo bo);

    RspResult page(ReportFindBo bo);
}
