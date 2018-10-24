package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.RspResult;
import com.empathy.domain.role.Role;
import com.empathy.domain.role.bo.RoleBo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:14
 * @email zhoujian699@126.com
 * @desc
 **/
public interface IRoleService extends BaseService<Role, Long, RoleBo> {


    RspResult page(RoleBo bo);

    /**
     * 用户的角色列表
     *
     * @param memberId
     * @return
     */
    List<String> findRolesByMemberId(Long memberId);

    List<Role> findAll();

    List<Long> findHadAssignPermissionById(Long roleId);
}
