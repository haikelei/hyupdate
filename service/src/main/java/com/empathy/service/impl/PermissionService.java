package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.PermissionDao;
import com.empathy.domain.permission.Permission;
import com.empathy.domain.permission.bo.PermissionBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IPermissionService;
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
public class PermissionService extends AbstractBaseService implements IPermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public RspResult page(PermissionBo bo) {
        List<Permission> vos = new ArrayList<>();
        final long count = permissionDao.count(bo);
        if (count > 0) {
            vos = permissionDao.list(bo);
        }
        return success(count, vos);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<String> findPermissionsByMemberId(Long memberId) {
        return permissionDao.findPermissionsByMemberId(memberId);
    }

    @Override
    public RspResult save(Permission entity) {
        permissionDao.save(entity);
        return success();
    }

    @Override
    public Permission findById(Long aLong) {
        return permissionDao.findById(aLong);
    }

    @Override
    public RspResult update(Permission entity) {
        permissionDao.update(entity);
        return success();
    }

    @Override
    public RspResult delById(Long aLong) {
        permissionDao.delById(aLong);
        return success();
    }
}
