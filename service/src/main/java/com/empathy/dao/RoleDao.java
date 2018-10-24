package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.role.Role;
import com.empathy.domain.role.RolePermission;
import com.empathy.domain.role.bo.RoleBo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/11/1 9:53
 * @email zhoujian699@126.com
 * @desc 车次
 **/
public interface RoleDao extends BaseDao<Role, Long, RoleBo> {


    long count(RoleBo bo);

    List<Role> list(RoleBo bo);

    List<Role> findAll();

    void delPermisionsById(Long id);

    void addPermissions(List<RolePermission> list);

    List<String> findRolesByMemberId(Long memberId);

    List<Long> findHadAssignPermissionById(Long roleId);
}
