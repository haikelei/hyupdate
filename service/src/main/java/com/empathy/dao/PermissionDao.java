package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.permission.Permission;
import com.empathy.domain.permission.bo.PermissionBo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/11/1 9:53
 * @email zhoujian699@126.com
 * @desc 车次
 **/
public interface PermissionDao extends BaseDao<Permission, Long, PermissionBo> {


    long count(PermissionBo bo);

    List<Permission> list(PermissionBo bo);

    List<Permission> findAll();

    List<String> findPermissionsByMemberId(Long memberId);
}
