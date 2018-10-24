package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.sys.SysUser;
import com.empathy.domain.user.BaseMember;

import java.util.List;

/**
 * Created by MI on 2018/4/27.
 */
public interface SysUserDao {


    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);
}
