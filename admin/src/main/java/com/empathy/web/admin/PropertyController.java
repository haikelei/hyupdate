package com.empathy.web.admin;

import com.empathy.common.AbstractBaseController;
import com.empathy.common.RspResult;
import com.empathy.domain.property.Property;
import com.empathy.domain.property.bo.PropertyBo;
import com.empathy.service.IPropertyService;
import com.empathy.utils.HttpHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tybest
 * @date 2017/8/7 14:36
 * @email zhoujian699@126.com
 * @desc
 **/
@Controller
@RequestMapping("/property")
public class PropertyController extends AbstractBaseController {

    @Autowired
    private IPropertyService propertyService;


    @RequestMapping
    public String toList(ModelMap model) {
        return "admin/property/list";
    }

    @RequestMapping("/page")
    @ResponseBody
    public RspResult page(PropertyBo bo) {
        return propertyService.page(bo);
    }

    @RequestMapping("/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap model) {
        final Property entity = propertyService.findById(id);
        model.put("entity", entity);
        return "admin/property/show";
    }

    @RequestMapping("/add")
    public String toAdd(Property entity, ModelMap model) {
        return "admin/property/add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public RspResult save(Property entity, ModelMap model, HttpServletRequest request) {
        RspResult result = propertyService.save(entity);
        notifyApiRefresh();
        return result;
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap model) {
        final Property entity = propertyService.findById(id);
        model.put("entity", entity);
        return "admin/property/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public RspResult update(Property entity, ModelMap model) {
        RspResult result = propertyService.update(entity);
        notifyApiRefresh();
        return result;
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public RspResult remove(@PathVariable("id") Long id, ModelMap model) {
        RspResult rt = propertyService.delById(id);
        notifyApiRefresh();
        return rt;
    }

    private void notifyApiRefresh() {
        final String remote = propertyService.findOneByClazz("remote_api_address_url");
        if (StringUtils.isNotBlank(remote)) {
            HttpHelper.doGet(remote + "/refresh/properties");
        }
    }
}
