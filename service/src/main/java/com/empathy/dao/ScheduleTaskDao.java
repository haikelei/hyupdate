package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.schedule.ScheduleJob;

import java.util.List;

public interface ScheduleTaskDao extends BaseDao<ScheduleJob,Long,LogBo> {

     List<ScheduleJob> getAll();

}
