package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.ConfigurationDao;
import com.empathy.domain.configuration.Configuration;
import com.empathy.domain.configuration.bo.ConfigChangeCommitBo;
import com.empathy.domain.configuration.bo.ConfigFindByTypeBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseMemberService;
import com.empathy.service.IConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/17.
 */
@Service
public class ConfigurationService extends AbstractBaseService implements IConfigurationService {

    @Autowired
    private ConfigurationDao configurationDao;


    @Override
    public RspResult changeCommit(ConfigChangeCommitBo bo) {
        Configuration configuration = configurationDao.findByType("commit");
        if (bo.getStatus() == 1) {
            configuration.setConversion((double) 1);
        } else if (bo.getStatus() == 0) {
            configuration.setConversion((double) 0);
        }
        configuration.setLastRevampTime(System.currentTimeMillis());
        configurationDao.update(configuration);
        return new RspResult();
    }

    @Override
    public RspResult findByType(ConfigFindByTypeBo bo) {
        Configuration byType = configurationDao.findByType(bo.getType());
        if (byType == null) {
            return new RspResult("类型错误", 1);
        }

        return success(byType);
    }

    @Override
    public RspResult save(Configuration entity) {
        return null;
    }

    @Override
    public Configuration findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(Configuration entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
