package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.configuration.Configuration;
import com.empathy.domain.log.bo.LogBo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by MI on 2018/4/17.
 */
public interface ConfigurationDao extends BaseDao<Configuration, Long, LogBo> {
    Configuration findByType(String withdraw);
}
