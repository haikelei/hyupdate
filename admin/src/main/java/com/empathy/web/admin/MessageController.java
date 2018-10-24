package com.empathy.web.admin;

import com.empathy.common.AbstractBaseController;
import com.empathy.common.RspResult;
import com.empathy.domain.message.bo.MessageBo;
import com.empathy.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tybest
 * @date 2017/8/22 20:54
 * @email zhoujian699@126.com
 * @desc
 **/
@RequestMapping("/message")
@Controller
public class MessageController extends AbstractBaseController {

    @Autowired
    private IMessageService messageService;

    @RequestMapping
    public String toList(ModelMap model) {
        return "admin/message/list";
    }

    /**
     * 分页
     *
     * @param bo
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public RspResult page(MessageBo bo) {
        return messageService.page(bo);
    }

}
