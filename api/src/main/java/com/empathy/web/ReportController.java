package com.empathy.web;

import com.empathy.common.AbstractBaseController;
import com.empathy.common.RspResult;
import com.empathy.domain.baseReport.bo.ReportFindBo;
import com.empathy.service.IBaseReportSerivce;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tybest
 * @date 2017/8/7 14:34
 * @email zhoujian699@126.com
 * @desc
 **/
@Controller
@RequestMapping("/report")
public class ReportController extends AbstractBaseController {

    @Autowired
    private IBaseReportSerivce baseReportSerivce;

//    @RequestMapping
//    public String toList(ModelMap model) {
//        return "admin/member/list";
//    }

    @ApiOperation(value = "后台查看所有个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findReportCount", method = RequestMethod.POST)
    @ResponseBody
    public String findReportCount(ReportFindBo bo) {

        return baseReportSerivce.findReportCount(bo);
    }

    /**
     * 分页
     *
     * @param bo
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public RspResult page(ReportFindBo bo) {
        return baseReportSerivce.page(bo);
    }

    /**
     * 审核不通过、直接删除
     * @param id
     * @return
     */
    @RequestMapping("/delReport/{id}")
    @ResponseBody
    public RspResult detail(@PathVariable("id") Long id) {
        return baseReportSerivce.delById(id);
    }
}
