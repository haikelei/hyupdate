package com.empathy.service.impl;

import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.TbPermission;
import com.empathy.domain.account.TbRole;
import com.empathy.domain.account.bo.*;
import com.empathy.domain.account.vo.ProveAddVo;
import com.empathy.domain.account.vo.ProveVo;
import com.empathy.domain.live.BaseLive;
import com.empathy.domain.live.bo.ProveAccountUpdBo;
import com.empathy.domain.live.bo.ProveFreezeBo;
import com.empathy.domain.permission.Permission;
import com.empathy.domain.permission.bo.PermissionAddBo;
import com.empathy.domain.role.Role;
import com.empathy.domain.role.bo.RoleAddBo;
import com.empathy.domain.user.HostProve;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IAccountService;
import com.empathy.utils.MD5Utils;
import com.empathy.utils.StringUtil;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * Created by MI on 2018/5/9.
 */
@Service
public class AccountService extends AbstractBaseService implements IAccountService {


    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TbRoleDao roleDao;

    @Autowired
    private BaseMemberDao baseMemberDao;

    @Autowired
    private HostProveDao hostProveDao;

    @Autowired
    private BaseLiveDao baseLiveDao;

    @Autowired
    private TbPermissionDao permissionDao;

    @Override
    public RspResult findPermissionByIds(String ids) {
        try {
            List<TbPermission> permissions = permissionDao.lists();

            for (int i=0;i<permissions.size();i++){
                String[] strings = ids.split(";");
                List<String> list= Arrays.asList(strings);
                permissions.get(i).setFlag(0);
                for (int j =0;j<list.size();j++){
                    if((permissions.get(i).getId()+"").equals(list.get(j))){
                        permissions.get(i).setFlag(1);
                    }
                }
            }

            return success(permissions);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();

        }
    }

    @Override
    public String findRoleCount() {
        int count = roleDao.count();
        return count+"";
    }

    @Override
    public RspResult findPermissions(PageBo bo) {

        try {

            List<TbPermission> permissions = permissionDao.list(bo);
            return success(permissions);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();

        }
    }

    @Override
    public String findPermissionsCount() {

        int count = permissionDao.count();
        return count+"";
    }

    @Override
    public RspResult delRole(Long id) {
        TbRole role = roleDao.findById(id);
        if (role==null){
            return errorMo();
        }
        int count = accountDao.findByRoleId(id);
        if(count>0){
            return error(1,"请先删除拥有此角色得管理员！");
        }
        roleDao.delById(id);
        return success();
    }

    @Override
    public RspResult updRole(RoleUpdBo bo) {

        TbRole role = roleDao.findById(bo.getId());
        if(role==null){
            return errorMo();
        }
        if(StringUtil.isNotEmpty(bo.getCode())){
            role.setCode(bo.getCode());
        }
        if(StringUtil.isNotEmpty(bo.getMemo())){
            role.setMemo(bo.getMemo());
        }
        if(StringUtil.isNotEmpty(bo.getName())){
            role.setName(bo.getName());
        }
        if(StringUtil.isNotEmpty(bo.getPermissionIds())){
            role.setPermissionIds(bo.getPermissionIds());
        }
        role.setLastRevampTime(System.currentTimeMillis());

        roleDao.update(role);

         return success();

    }

    @Override
    public RspResult findRole(PageBo bo) {
        try {
            List<TbRole> tbRoles = roleDao.list(bo);

            return success(tbRoles);
        }catch (Exception e){
            e.printStackTrace();
            return  errorNo();

        }

    }

    @Override
    public RspResult findRoleById(Long id) {
        TbRole role = roleDao.findById(id);
        if(role==null){
            return errorMo();
        }
        return success(role);
    }

    @Override
    public RspResult addRole(RoleAddBo bo) {
        if(!StringUtil.isNotEmpty(bo.getName())){
            return error(1,"角色名不能为空!");
        }

        if(!StringUtil.isNotEmpty(bo.getPermissionIdlist())){
            return error(1,"权限不能为空!");
        }

        TbRole role = new TbRole();
        if(StringUtil.isNotEmpty(bo.getCode())){

            role.setCode(bo.getCode());
        }
        if(StringUtil.isNotEmpty(bo.getMemo())){

            role.setMemo(bo.getMemo());
        }
        role.setPermissionIds(bo.getPermissionIdlist());
        role.setName(bo.getName());
        roleDao.save(role);

        return success();
    }

    @Override
    public RspResult addPermissions(PermissionAddBo bo) {

        if(StringUtil.isNotEmpty(bo.getCode())){
            return error(1,"权限编码不能为空!");
        }

        Permission permission = new Permission();
        permission.setCode(bo.getCode());
        permission.setMemo(bo.getMemo());


        return success();
    }

    @Override
    public RspResult logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();
        return success();
    }

    @Override
    public RspResult findAccount(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
        return success(account);
    }

    //修改排序值
    @Override
    public RspResult updateProve(ProveAccountUpdBo bo) {
        BaseLive baseLive = baseLiveDao.findById(bo.getId());
        if (baseLive == null) {
            return error(1, "id绑定对象为空");
        }
        if (StringUtil.isNotIntegerEmpty(bo.getCode())) {
            baseLive.setCode(bo.getCode());
        }
        if (StringUtil.isNotIntegerEmpty(bo.getPersonCount())) {
            baseLive.setPersonCount(bo.getPersonCount());
        }
        if (StringUtil.isNotIntegerEmpty(bo.getNewCode())) {
            baseLive.setNewCode(bo.getNewCode());
        }
        baseLiveDao.update(baseLive);
        return success();
    }

    //冻结直播间
    @Override
    public RspResult freezeProve(ProveFreezeBo bo) {

        int count = baseLiveDao.countById(bo.getId());
        if (count < 1) {
            return error(1, "id绑定对象为空");
        }
        if (bo.getType() == 0) {

            baseLiveDao.delById(bo.getId());

        } else {

            baseLiveDao.cancleProve(bo.getId());
        }

        return success();
    }

    @Override
    public BufferedImage getImage() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(new Properties()));
        String code = defaultKaptcha.createText();
        return defaultKaptcha.createImage(code);
    }

    @Override
    public RspResult findListAddProve(ProveListAddBo bo) {
        List<ProveAddVo> list = hostProveDao.list(bo);

        return success(list);
    }

    @Override
    public String findListAddProveCount(ProveListAddBo bo) {
        int count = hostProveDao.count(bo);
        return count + "";
    }

    @Override
    public RspResult findProve(ProveListBo bo) {
        List<ProveVo> list = baseMemberDao.findProve(bo);

        return success(list);
    }

    @Override
    public String findProveCount(ProveListBo bo) {
        int count = baseMemberDao.findProveCount(bo);

        return count + "";
    }


    @Override
    public RspResult save(Account entity) {
        return null;
    }

    @Override
    public Account findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(Account entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }


    @Override
    public int getAllCount() {
        return accountDao.count();
    }


    @Override
    public RspResult accountList(PageBo bo) {
        List<Account> list = accountDao.list(bo);

        return success(list);
    }


    @Override
    public RspResult findListRole(PageBo bo) {
        List<TbRole> list = roleDao.list(bo);

        return success(list);
    }

    @Override
    public RspResult addAccount(Account bo) {
        int count = roleDao.countById(bo.getRoleId());
        if (count < 1) {
            return error(1, "角色不存在");
        }

        int countByCode = accountDao.findCountByCode(bo.getCode());
        if (countByCode >= 1) {
            return error(2, "工号不能重复");
        }
        bo.setPassword(MD5Utils.encrypt(bo.getPassword()));
        accountDao.save(bo);

        return success();
    }


    @Override
    public RspResult updAccount(Account bo) {
        Account account = accountDao.findById(bo.getId());

        int count = roleDao.countById(bo.getRoleId());
        if (count < 1) {
            return error(1, "角色不存在");
        }
        if (!bo.getCode().equals(account.getCode())) {

            int countByCode = accountDao.findCountByCode(bo.getCode());
            if (countByCode >= 1) {
                return error(2, "工号不能重复");
            }
        }
        account.setRoleId(bo.getRoleId());
        account.setUsername(bo.getUsername());
        account.setCode(bo.getCode());
        account.setLastRevampTime(System.currentTimeMillis());
        accountDao.update(account);
        return success();
    }


    @Override
    public RspResult updPassword(UpdPasswordBo bo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");


        if (MD5Utils.encrypt(bo.getPassword()).equals(account.getPassword())) {
            return error(1, "密码错误");
        }
        if (MD5Utils.encrypt(bo.getNewPassword()).equals(account.getPassword())) {
            return error(1, "两次密码不能相同");
        }
        account.setPassword(MD5Utils.encrypt(bo.getNewPassword()));
        accountDao.update(account);

        return success();
    }

    @Override
    public RspResult delAccount(Long id) {

        accountDao.delById(id);
        return success();
    }


    @Override
    public RspResult find(Long id) {

        Account account = accountDao.findById(id);
        return success(account);
    }


    @Override
    public RspResult login(AccoutLoginBo bo, HttpServletRequest request) {
        int count = accountDao.findCountByCode(bo.getCode());
        if (count < 1) {
            return error(1, "工号不存在");
        }
        Account account = accountDao.findByCode(bo.getCode());
        if (!(MD5Utils.encrypt(bo.getPassword())).equals(account.getPassword())) {
            return error(1, "工号或者密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", account);
        return success(account);
    }


}
