package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseMessageDao;
import com.empathy.domain.baseMessage.BaseMessage;
import com.empathy.domain.baseMessage.bo.MessageAddBo;
import com.empathy.domain.baseMessage.bo.MessageFindBo;
import com.empathy.domain.baseMessage.bo.MessageUpdBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IAccountService;
import com.empathy.service.IBaseMessageService;
import com.empathy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/6/5.
 */
@Service
public class BaseMessageService  extends AbstractBaseService implements IBaseMessageService {

    @Autowired
    private BaseMessageDao baseMessageDao;


    @Override
    public RspResult changeMessageStatus(Long id) {
        BaseMessage baseMessage = baseMessageDao.findById(id);
        baseMessage.setReadStatus(1);
        baseMessageDao.update(baseMessage);
        return success(baseMessage);
    }

    @Override
    public RspResult findMessageForApp(MessageFindBo bo) {
        try {

            int count = baseMessageDao.count();
            List<BaseMessage> list = baseMessageDao.listForApp(bo);

            return success(count,list);
        }catch(Exception e){
            e.printStackTrace();
            return errorNo();

        }
    }

    @Override
    public RspResult findMessageById(Long id) {
        BaseMessage message = baseMessageDao.findById(id);
        return success(message);
    }

    @Override
    public String findMessageCount() {
        int count = baseMessageDao.count();
        return count+"";
    }

    @Override
    public RspResult findMessage(MessageFindBo bo) {
        try {

            int count = baseMessageDao.count();
            List<BaseMessage> list = baseMessageDao.list(bo);

            return success(count,list);
        }catch(Exception e){
            e.printStackTrace();
            return errorNo();

        }
    }

    @Override
    public RspResult updMessage(MessageUpdBo bo) {
        BaseMessage message = baseMessageDao.findById(bo.getId());
        if(message==null){
            return errorMo();
        }
        if(StringUtil.isNotIntegerEmpty(bo.getCode())){
            message.setCode(bo.getCode());
        }

        if(StringUtil.isNotEmpty(bo.getContent())){
            message.setContent(bo.getContent());
        }

        if(StringUtil.isNotEmpty(bo.getHeadMessage())){
            message.setHeadMessage(bo.getHeadMessage());
        }

        if(StringUtil.isNotEmpty(bo.getTitle())){
            message.setTitle(bo.getTitle());
        }

        message.setLastRevampTime(System.currentTimeMillis());
        try {

            baseMessageDao.update(message);
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult delMessage(Long id) {
        try {

            baseMessageDao.delById(id);
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult addMessage(MessageAddBo bo) {
        if(!StringUtil.isNotEmpty(bo.getContent())){
            error(1,"内容不能为空！");
        }
        if(!StringUtil.isNotEmpty(bo.getHeadMessage())){
            error(1,"头部通知不能为空！");
        }
        if(!StringUtil.isNotEmpty(bo.getTitle())){
            error(1,"标题不能为空！");
        }

        if(!StringUtil.isNotIntegerEmpty(bo.getCode())){
            error(1,"排序值不能为空！");
        }
        BaseMessage message = new BaseMessage();
        message.setCode(bo.getCode());
        message.setContent(bo.getContent());
        message.setHeadMessage(bo.getHeadMessage());
        message.setTitle(bo.getTitle());
        try {

            baseMessageDao.save(message);
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }

    }

    @Override
    public RspResult save(BaseMessage entity) {
        return null;
    }

    @Override
    public BaseMessage findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseMessage entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
