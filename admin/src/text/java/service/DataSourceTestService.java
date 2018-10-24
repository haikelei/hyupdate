package com.chitry.yn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chitry.yn.datasources.DataSourceNames;
import com.chitry.yn.datasources.annotation.DataSource;
import com.chitry.yn.modules.sys.entity.SysUserEntity;
import com.chitry.yn.modules.sys.service.SysUserService;

/**
 * <p>Title: DataSourceTestService</p>
 * <p>Description: 多数据源业务层测试</p>
 * <p>Company: 浙江企业在线有限公司</p> 
 * @author chitry@126.com
 * @date 2018年4月24日 下午7:50:32
 */
@Service
public class DataSourceTestService {
    @Autowired
    private SysUserService sysUserService;

    public SysUserEntity queryUser(Long userId){
        return sysUserService.selectById(userId);
    }

    @DataSource(name = DataSourceNames.SECOND)
    public SysUserEntity queryUser2(Long userId){
        return sysUserService.selectById(userId);
    }
}
