package com.empathy.web.admin;

import com.empathy.common.AbstractBaseController;
import com.empathy.common.RspResult;
import com.empathy.domain.permission.Permission;
import com.empathy.domain.permission.bo.PermissionBo;
import com.empathy.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tybest
 * @date 2017/8/5 10:55
 * @email zhoujian699@126.com
 * @desc
 **/
@Controller
@RequestMapping("/permission")
public class PermissionController extends AbstractBaseController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping
    public String toList() {
        return "admin/permission/list";
    }

    /**
     * 新增-跳转
     *
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return "admin/permission/add";
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
        map.put("entity", permissionService.findById(id));
        return "admin/permission/edit";
    }

    /**
     * 新增-保存
     *
     * @param entity
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public RspResult save(Permission entity) {
        return permissionService.save(entity);
    }

    /**
     * 编辑-保存
     *
     * @param entity
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public RspResult update(Permission entity) {
        return permissionService.update(entity);
    }

    /**
     * 分页
     *
     * @param bo
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public RspResult page(PermissionBo bo) {
        return permissionService.page(bo);
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public RspResult remove(@PathVariable("id") Long id, ModelMap model) {
        return permissionService.delById(id);
    }
}
