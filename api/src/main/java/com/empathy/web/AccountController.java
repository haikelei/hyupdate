package com.empathy.web;

import com.baomidou.mybatisplus.toolkit.IOUtils;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.bo.*;
import com.empathy.domain.live.bo.ProveAccountUpdBo;
import com.empathy.domain.live.bo.ProveFreezeBo;
import com.empathy.domain.permission.bo.PermissionAddBo;
import com.empathy.domain.role.bo.RoleAddBo;
import com.empathy.domain.user.bo.FindUserBo;
import com.empathy.domain.user.bo.FreezeUserBo;
import com.empathy.service.IAccountService;
import com.empathy.service.IBaseMemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by MI on 2018/5/8.
 */

@RestController
@RequestMapping("/account")
@Api(tags = {"后台管理接口"})
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IBaseMemberService baseMemberService;


    /**
     * 验证码接口
     */
    @GetMapping("captcha.jpg")
    public void getCaptcha(HttpServletResponse response) throws SerialException, IOException {
        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpeg");

        BufferedImage image = accountService.getImage();
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }


    /**
     * 登录态检验
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    @ResponseBody
    public String checkUser(HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return "1";
        } else {
            return "0";
        }
    }


    /**
     * 查看列表数据
     *
     * @return
     */
    @RequestMapping(value = "/accountList", method = RequestMethod.GET)
    @ResponseBody
    public RspResult accountList(PageBo bo) {


        return accountService.accountList(bo);
    }


    /**
     * 查看列表数据的个数
     *
     * @return
     */
    @RequestMapping(value = "/getAllCount", method = RequestMethod.GET)
    @ResponseBody
    public String getAllCount() {
        int count = accountService.getAllCount();
        return count + "";
    }

    /**
     * 查看列表数据的个数
     *
     * @return
     */
    @RequestMapping(value = "/findListRole", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findListRole(PageBo bo) {

        return accountService.findListRole(bo);
    }


    /**
     * 添加管理员
     *
     * @return
     */
    @RequestMapping(value = "/addAccount", method = RequestMethod.GET)
    @ResponseBody
    public RspResult addAccount(Account bo) {


        return accountService.addAccount(bo);
    }


    /**
     * 修改管理员
     *
     * @return
     */
    @RequestMapping(value = "/updAccount", method = RequestMethod.GET)
    @ResponseBody
    public RspResult updAccount(Account bo) {


        return accountService.updAccount(bo);
    }


    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/updPassword", method = RequestMethod.GET)
    @ResponseBody
    public RspResult updPassword(UpdPasswordBo bo, HttpServletRequest request) {


        return accountService.updPassword(bo, request);
    }


    /**
     * 退出系统
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public RspResult logout(HttpServletRequest request) {


        return accountService.logout(request);
    }


    /**
     * 删除管理员
     *
     * @return
     */
    @RequestMapping(value = "/delAccount", method = RequestMethod.GET)
    @ResponseBody
    public RspResult delAccount(Long id) {


        return accountService.delAccount(id);
    }

    /**
     * 删除管理员
     *
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findById(Long id) {


        return accountService.find(id);
    }


    /**
     * 查看用户得个数
     *
     * @return
     */
    @RequestMapping(value = "/findAllUserCount", method = RequestMethod.GET)
    @ResponseBody
    public String findAllUserCount(FindUserBo bo) {


        return baseMemberService.findAllUserCount(bo);
    }


    /**
     * 查看所有得用户
     *
     * @return
     */
    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findAllUser(FindUserBo bo) {

        return baseMemberService.findAllUser(bo);
    }


    /**
     * 冻结或者解冻
     *
     * @return
     */
    @RequestMapping(value = "/freezeUser", method = RequestMethod.GET)
    @ResponseBody
    public RspResult freezeUser(FreezeUserBo bo) {


        return baseMemberService.freezeUser(bo);
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public RspResult login(AccoutLoginBo bo, HttpServletRequest request) {


        return accountService.login(bo, request);
    }


    /**
     * 拿用户的值
     *
     * @return
     */
    @RequestMapping(value = "/findAccount", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findAccount(HttpServletRequest request) {


        return accountService.findAccount(request);
    }


    /**
     * 查看主播的列表
     *
     * @return
     */
    @RequestMapping(value = "/findProve", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findProve(ProveListBo bo) {


        return accountService.findProve(bo);
    }

    /**
     * 查看主播的个数
     *
     * @return
     */
    @RequestMapping(value = "/findProveCount", method = RequestMethod.GET)
    @ResponseBody
    public String findProveCount(ProveListBo bo) {


        return accountService.findProveCount(bo);
    }

    /**
     * 查看认证主播的列表
     *
     * @return
     */
    @RequestMapping(value = "/findListAddProve", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findListAddProve(ProveListAddBo bo) {


        return accountService.findListAddProve(bo);
    }


    /**
     * 查看认证主播的个数
     *
     * @return
     */
    @RequestMapping(value = "/findListAddProveCount", method = RequestMethod.GET)
    @ResponseBody
    public String findListAddProveCount(ProveListAddBo bo) {


        return accountService.findListAddProveCount(bo);
    }


    /**
     * 后台修改直播间
     *
     * @return
     */
    @RequestMapping(value = "/updateProve", method = RequestMethod.GET)
    @ResponseBody
    public RspResult updateProve(ProveAccountUpdBo bo) {


        return accountService.updateProve(bo);
    }

    /**
     * 冻结房间
     *
     * @return
     */
    @RequestMapping(value = "/freezeProve", method = RequestMethod.GET)
    @ResponseBody
    public RspResult freezeProve(ProveFreezeBo bo) {


        return accountService.freezeProve(bo);
    }


    /**
     * 添加角色权限
     */
    @RequestMapping(value = "/addPermissions", method = RequestMethod.GET)
    @ResponseBody
    public RspResult addPermissions(PermissionAddBo bo) {


        return accountService.addPermissions(bo);
    }




    /**
     * 查看角色权限
     */
    @RequestMapping(value = "/findPermissions", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findPermissions(PageBo bo) {


        return accountService.findPermissions(bo);
    }


    /**
     * 查看角色de 权限
     */
    @RequestMapping(value = "/findPermissionByIds", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findPermissionByIds(String ids) {


        return accountService.findPermissionByIds(ids);
    }

    /**
     * 查看角色权限个数
     */
    @RequestMapping(value = "/findPermissionsCount", method = RequestMethod.GET)
    @ResponseBody
    public String findPermissionsCount() {


        return accountService.findPermissionsCount();


    }



    /**
     * 添加角色
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    @ResponseBody
    public RspResult addRole(RoleAddBo bo) {


        return accountService.addRole(bo);
    }


    /**
     * 删除角色
     */
    @RequestMapping(value = "/delRole", method = RequestMethod.GET)
    @ResponseBody
    public RspResult delRole(Long id) {


        return accountService.delRole(id);
    }

    /**
     * 修改角色
     */
    @RequestMapping(value = "/updRole", method = RequestMethod.GET)
    @ResponseBody
    public RspResult updRole(RoleUpdBo bo) {


        return accountService.updRole(bo);
    }
    /**
     * 查看角色
     */
    @RequestMapping(value = "/findRole", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findRole(PageBo bo) {


        return accountService.findRole(bo);
    }
    /**
     * 查看角色个数
     */
    @RequestMapping(value = "/findRoleCount", method = RequestMethod.GET)
    @ResponseBody
    public String findRoleCount() {


        return accountService.findRoleCount();
    }

    /**
     * 查看单个角色根据id
     */
    @RequestMapping(value = "/findRoleById", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findRoleById(Long id) {


        return accountService.findRoleById(id);
    }




}
