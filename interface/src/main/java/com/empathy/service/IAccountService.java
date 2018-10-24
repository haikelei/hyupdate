package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.bo.*;
import com.empathy.domain.album.AlbumMoney;
import com.empathy.domain.live.bo.ProveAccountUpdBo;
import com.empathy.domain.live.bo.ProveFreezeBo;
import com.empathy.domain.permission.bo.PermissionAddBo;
import com.empathy.domain.role.bo.RoleAddBo;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * Created by MI on 2018/5/9.
 */
public interface IAccountService extends BaseService<Account, Long, PageBo> {
    int getAllCount();

    RspResult accountList(PageBo bo);

    RspResult findListRole(PageBo bo);

    RspResult addAccount(Account bo);

    RspResult updAccount(Account bo);


    RspResult updPassword(UpdPasswordBo bo, HttpServletRequest request);

    RspResult delAccount(Long id);

    RspResult find(Long id);

    RspResult login(AccoutLoginBo bo, HttpServletRequest request);

    RspResult findProve(ProveListBo bo);

    String findProveCount(ProveListBo bo);

    RspResult findListAddProve(ProveListAddBo bo);

    String findListAddProveCount(ProveListAddBo bo);

    BufferedImage getImage();

    RspResult updateProve(ProveAccountUpdBo bo);

    RspResult freezeProve(ProveFreezeBo bo);

    RspResult findAccount(HttpServletRequest request);

    RspResult logout(HttpServletRequest request);

    RspResult addPermissions(PermissionAddBo bo);

    RspResult addRole(RoleAddBo bo);

    RspResult delRole(Long id);

    RspResult updRole(RoleUpdBo bo);

    RspResult findRole(PageBo bo);

    RspResult findRoleById(Long id);

    RspResult findPermissions(PageBo bo);

    String findPermissionsCount();

    String findRoleCount();

    RspResult findPermissionByIds(String ids);
}
