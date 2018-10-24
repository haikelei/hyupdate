package com.empathy.web.admin;

import com.empathy.common.AbstractBaseController;
import com.empathy.common.RspResult;
import com.empathy.domain.member.Member;
import com.empathy.domain.member.bo.MemberBo;
import com.empathy.domain.member.vo.MemberRoleVo;
import com.empathy.domain.role.Role;
import com.empathy.service.IMemberService;
import com.empathy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:34
 * @email zhoujian699@126.com
 * @desc
 **/
@Controller
@RequestMapping("/admin/member")
public class MemberController extends AbstractBaseController {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping
    public String toList(ModelMap model) {
        return "admin/member/list";
    }

    /**
     * 分页
     *
     * @param bo
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public RspResult page(MemberBo bo) {
        return memberService.page(bo);
    }

    @RequestMapping("/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap model) {
        final Member entity = memberService.findById(id);
        model.put("entity", entity);
        return "admin/member/show";
    }

    @RequestMapping("/add")
    public String toAdd(Member entity, ModelMap model) {
        model.put("roles", roleService.findAll());
        return "admin/member/add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public RspResult save(Member entity, ModelMap model, HttpServletRequest request) {
        return memberService.save(entity);
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap model) {
        final Member entity = memberService.findById(id);
        List<MemberRoleVo> roles = new ArrayList<>();
        model.put("entity", entity);
        model.put("roles", roles);
        initHadAssign(roleService.findAll(), memberService.findHadAssignRolesByUserId(id), roles);
        return "admin/member/edit";
    }

    private void initHadAssign(List<Role> all, List<Long> ids, List<MemberRoleVo> roles) {
        if (all != null && all.size() > 0) {
            boolean had = (ids != null && ids.size() > 0);
            for (Role role : all) {
                MemberRoleVo vo = new MemberRoleVo();
                vo.setRoleId(role.getId());
                vo.setName(role.getName());
                if (had) {
                    vo.setChecked(ids.contains(vo.getRoleId()) ? 1 : 0);
                }
                roles.add(vo);
            }
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public RspResult update(Member entity, ModelMap model) {
        return memberService.update(entity);
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public RspResult remove(@PathVariable("id") Long id, ModelMap model) {
        return memberService.delById(id);
    }
}
