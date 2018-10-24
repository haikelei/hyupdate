package com.empathy.web.admin;

import com.empathy.common.Constants;
import com.empathy.common.LoginSession;
import com.empathy.service.IMemberService;
import com.empathy.service.IPermissionService;
import com.empathy.service.IRoleService;
import com.empathy.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tybest
 * @date 2017/8/5 11:05
 * @email zhoujian699@126.com
 * @desc
 **/
@Controller
public class LoginController {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("")
    public String index(ModelMap model) {
        return "admin/index";
    }

    /**
     * 登录页
     * admin-登录
     *
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(ModelMap model) {
        return "admin/login";
    }
/*
    @RequestMapping(value = "/login/check")
    public String loginCheck(LoginBo bo, ModelMap map, HttpServletRequest request){
        if(StringUtils.isEmpty(bo.getUsername()) ||
                StringUtils.isEmpty(bo.getPassword())){
            map.put("message","登录名或密码必填!");
            return "admin/login";
        }
        bo.setUsername(bo.getUsername());
        final Member member = memberService.findByUsername(bo.getUsername());
        if(null == member){
            map.put("message","用户不存在!");
            return "admin/login";
        }
        final String pwd = EncryptUtils.encrypt(bo.getPassword());
        if(!pwd.equals(member.getPassword())){
            map.put("message","密码错误!");
            return "admin/login";
        }
        LoginSession session = new LoginSession();
        session.setId(member.getId());
        session.setUsername(member.getUsername());
        request.getSession().setAttribute(Constants.LOGIN_KEY,session);
        initPermission(session);
        return "redirect:/admin/";
    }*/

    /**
     * 初始化权限
     *
     * @param session
     */
    private void initPermission(LoginSession session) {
        session.setPermissiones(permissionService.findPermissionsByMemberId(session.getId()));
        session.setRoles(roleService.findRolesByMemberId(session.getId()));
    }

    /**
     * 登出
     *
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(ModelMap model, HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.LOGIN_KEY);
        return "redirect:/admin/";
    }

    /**
     * 未授权
     *
     * @param model
     * @return
     */
    @RequestMapping("/403")
    public String unauthorize(ModelMap model) {
        return "admin/403";
    }

    @RequestMapping("/500")
    public String syserror(ModelMap model) {
        return "admin/500";
    }

    /**
     * 未授权
     *
     * @param model
     * @return
     */
    //@RequestMapping("/error")
    public String error(ModelMap model) {
        return "admin/500";
    }

    @RequestMapping("/qiniu/token")
    @ResponseBody
    public Map<String, String> qiniuToken() {
        Map<String, String> result = new HashMap<>();
        result.put("uptoken", QiniuUtils.getToken(null));
        return result;
    }
}
