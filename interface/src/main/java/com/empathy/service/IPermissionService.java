package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.RspResult;
import com.empathy.domain.permission.Permission;
import com.empathy.domain.permission.bo.PermissionBo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:14
 * @email zhoujian699@126.com
 * @desc
 **/
public interface IPermissionService extends BaseService<Permission, Long, PermissionBo> {


    RspResult page(PermissionBo bo);

    List<Permission> findAll();

    /**
     * 用户的权限列表
     *
     * @param memberId
     * @return
     */
    List<String> findPermissionsByMemberId(Long memberId);
}
