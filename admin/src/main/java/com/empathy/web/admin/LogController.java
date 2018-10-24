package com.empathy.web.admin;

import com.empathy.common.AbstractBaseController;
import com.empathy.common.RspResult;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tybest
 * @date 2017/10/25 11:30
 * @email zhoujian699@126.com
 * @desc
 **/
@Controller
@RequestMapping("/log")
public class LogController extends AbstractBaseController {


    @Autowired
    private ILogService logService;

    @RequestMapping
    public String toList(ModelMap model) {
        return "admin/log/list";
    }

    /**
     * 分页
     *
     * @param bo
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public RspResult page(LogBo bo) {
        return logService.page(bo);
    }
}
