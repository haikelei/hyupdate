package com.empathy.service.impl;

import com.empathy.common.Constants;
import com.empathy.common.RspResult;
import com.empathy.dao.MessageDao;
import com.empathy.domain.message.Message;
import com.empathy.domain.message.bo.MessageBo;
import com.empathy.domain.message.vo.MessageVo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/8/22 20:50
 * @email zhoujian699@126.com
 * @desc
 **/
@Service
public class MessageService extends AbstractBaseService implements IMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageDao messageDao;


    @Override
    public void push(Long fuserId, Long tuserId, String content, Integer type, Long reference) {
    }

    /**
     * 封装要推送的内容
     *
     * @param type
     * @return
     */
    private String buildContent(int type) {
        switch (type) {
            case 1:

                break;
            default:
                break;
        }

        return "";
    }

    private MessageVo build(Long id, Long fuserId, Long tuserId, Long reference, int type, String conetent) {
        MessageVo entity = new MessageVo();
        entity.setId(id);
        entity.setType(type);
        entity.setContent(conetent);
        //  entity.setCreated(getNow());
        entity.setFuserId(fuserId);
        entity.setTuserId(tuserId);
        entity.setReaded(0);
        //  entity.setModified(entity.getCreated());
        entity.setReference(reference);
        return entity;
    }

    @Override
    public void push(Long tuserId, String content) {
        push(Constants.SYS_ID, tuserId, content, Constants.MessageType.SYS_MSG.getType(), null);
    }

    @Override
    public RspResult page(MessageBo bo) {
        final long count = messageDao.count(bo);
        List<MessageVo> list = new ArrayList<>();
        if (count > 0) {
            list = messageDao.list(bo);
        }
        return success(count, list);
    }

    @Override
    public RspResult save(Message entity) {
        return success(entity);
    }

    @Override
    public Message findById(Long aLong) {
        return messageDao.findById(aLong);
    }

    @Override
    public RspResult update(Message entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        messageDao.delById(aLong);
        return success();
    }

}
