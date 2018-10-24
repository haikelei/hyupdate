package com.empathy.web.admin;

import com.empathy.domain.sys.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MI on 2018/4/27.
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected Long getDeptId() {
        return 0l;
    }
}
