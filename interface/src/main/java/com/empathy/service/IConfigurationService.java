package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.configuration.Configuration;
import com.empathy.domain.configuration.bo.ConfigChangeCommitBo;
import com.empathy.domain.configuration.bo.ConfigFindByTypeBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/17.
 */
public interface IConfigurationService extends BaseService<Configuration, Long, PageBo> {
    RspResult findByType(ConfigFindByTypeBo bo);

    RspResult changeCommit(ConfigChangeCommitBo bo);
}
