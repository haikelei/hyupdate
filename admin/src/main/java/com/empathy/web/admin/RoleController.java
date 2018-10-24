package com.empathy.web.admin;

import com.empathy.common.AbstractBaseController;
import com.empathy.common.RspResult;
import com.empathy.domain.permission.Permission;
import com.empathy.domain.role.Role;
import com.empathy.domain.role.bo.RoleBo;
import com.empathy.domain.role.vo.RolePermissionVo;
import com.empathy.service.IPermissionService;
import com.empathy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/8/5 10:55
 * @email zhoujian699@126.com
 * @desc
 **/
@Controller
@RequestMapping("/role")
public class RoleController extends AbstractBaseController {


    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping
    public String toList() {
        return "admin/role/list";
    }

    /**
     * 编辑-跳转
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap map) {
        map.put("entity", roleService.findById(id));
        List<RolePermissionVo> permissions = new ArrayList<>();
        map.put("permissions", permissions);
        initHadAssign(permissionService.findAll(), roleService.findHadAssignPermissionById(id), permissions);
        return "admin/role/edit";
    }

    /**
     * @param all
     * @param ids
     * @param roles
     */
    private void initHadAssign(List<Permission> all, List<Long> ids, List<RolePermissionVo> roles) {
        if (all != null && all.size() > 0) {
            boolean had = (ids != null && ids.size() > 0);
            for (Permission p : all) {
                RolePermissionVo vo = new RolePermissionVo();
                vo.setPermissionId(p.getId());

                if (had) {
                    vo.setChecked(ids.contains(vo.getPermissionId()) ? 1 : 0);
                }
                roles.add(vo);
            }
        }
    }

    /**
     * 新增-调整
     *
     * @return
     */
    @RequestMapping("/add")
    public String toAdd(ModelMap map) {
        map.put("permissions", permissionService.findAll());
        return "admin/role/add";
    }

    /**
     * 新增-保存
     *
     * @param entity
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public RspResult save(Role entity) {
        return roleService.save(entity);
    }


    /**
     * 编辑-保存
     *
     * @param entity
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public RspResult update(Role entity) {
        return roleService.update(entity);
    }

    /**
     * 分页
     *
     * @param bo
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public RspResult page(RoleBo bo) {
        return roleService.page(bo);
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public RspResult remove(@PathVariable("id") Long id, ModelMap model) {
        return roleService.delById(id);
    }
}
