package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.configuration.bo.ConfigChangeCommitBo;
import com.empathy.domain.configuration.bo.ConfigFindByTypeBo;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.service.IConfigurationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/18.
 */
@RestController
@RequestMapping("/config")
@Api(tags = {"全局配置接口"})
public class ConfigurationController {

    @Autowired
    private IConfigurationService configurationService;


    @ApiOperation(value = "查看", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findByType", method = RequestMethod.POST)
    public RspResult findByType(ConfigFindByTypeBo bo) {


        return configurationService.findByType(bo);
    }

    @ApiOperation(value = "是否开启评论", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/changeCommit", method = RequestMethod.POST)
    public RspResult changeCommit(ConfigChangeCommitBo bo) {


        return configurationService.changeCommit(bo);
    }


}
