package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.baseMessage.bo.MessageAddBo;
import com.empathy.domain.baseMessage.bo.MessageFindBo;
import com.empathy.domain.baseMessage.bo.MessageUpdBo;
import com.empathy.domain.classify.bo.ClassifyAddBo;
import com.empathy.service.IBaseMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/6/5.
 */
@RestController
@RequestMapping("/base/message")
@Api(tags = {"app消息接口"})
public class BaseMessageController {

    @Autowired
    private IBaseMessageService baseMessageService;


    @ApiOperation(value = "添加", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public RspResult addMessage(MessageAddBo bo) {


        return baseMessageService.addMessage(bo);
    }


    @ApiOperation(value = "删除", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delMessage", method = RequestMethod.POST)
    public RspResult delMessage(Long id) {


        return baseMessageService.delMessage(id);
    }

    @ApiOperation(value = "修改", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updMessage", method = RequestMethod.POST)
    public RspResult updMessage(MessageUpdBo bo) {


        return baseMessageService.updMessage(bo);
    }

    @ApiOperation(value = "后台查看全部", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMessage", method = RequestMethod.POST)
    public RspResult findMessage(MessageFindBo bo) {


        return baseMessageService.findMessage(bo);
    }

    @ApiOperation(value = "app查看列表数据", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMessageForApp", method = RequestMethod.POST)
    public RspResult findMessageForApp(MessageFindBo bo) {


        return baseMessageService.findMessageForApp(bo);
    }


    @ApiOperation(value = "后台查看全部个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMessageCount", method = RequestMethod.POST)
    public String findMessageCount() {


        return baseMessageService.findMessageCount();
    }



    @ApiOperation(value = "根据id查询单个", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMessageById", method = RequestMethod.POST)
    public RspResult findMessageById(Long id) {


        return baseMessageService.findMessageById(id);
    }

    @ApiOperation(value = "改变已读状态", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/changeMessageStatus", method = RequestMethod.POST)
    public RspResult changeMessageStatus(Long id) {


        return baseMessageService.changeMessageStatus(id);
    }





}
