package com.empathy.service.impl;

import com.empathy.common.IdWorker;
import com.empathy.common.RspResult;
import com.empathy.dao.RoleDao;
import com.empathy.domain.role.Role;
import com.empathy.domain.role.RolePermission;
import com.empathy.domain.role.bo.RoleBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/11/6 10:58
 * @email zhoujian699@126.com
 * @desc
 **/
@Service
public class RoleService extends AbstractBaseService implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public RspResult page(RoleBo bo) {
        List<Role> vos = new ArrayList<>();
        final long count = roleDao.count(bo);
        if (count > 0) {
            vos = roleDao.list(bo);
        }
        return success(count, vos);
    }

    @Override
    public List<String> findRolesByMemberId(Long memberId) {
        return roleDao.findRolesByMemberId(memberId);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Long> findHadAssignPermissionById(Long roleId) {
        return roleDao.findHadAssignPermissionById(roleId);
    }

    @Override
    public RspResult save(Role entity) {
        entity.setId(IdWorker.genId());
        roleDao.save(entity);
        addPermissions(entity.getId(), entity.getPermissionIds());
        return success();
    }

    /**
     * 添加用户角色
     *
     * @param roleId
     * @param ids
     */
    private void addPermissions(Long roleId, List<Long> ids) {
        roleDao.delPermisionsById(roleId);
        if (ids != null && ids.size() > 0) {
            List<RolePermission> list = new ArrayList<>();
            for (Long id : ids) {
                RolePermission mr = new RolePermission();
                mr.setPermissionId(id);
                mr.setRoleId(roleId);
                list.add(mr);
            }
            roleDao.addPermissions(list);
        }
    }

    @Override
    public Role findById(Long aLong) {
        return roleDao.findById(aLong);
    }

    @Override
    public RspResult update(Role entity) {
        roleDao.update(entity);
        addPermissions(entity.getId(), entity.getPermissionIds());
        return success();
    }

    @Override
    public RspResult delById(Long aLong) {
        roleDao.delById(aLong);
        return success();
    }
}
