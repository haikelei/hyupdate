package com.empathy.service;


import com.empathy.common.BaseService;
import com.empathy.common.RspResult;
import com.empathy.domain.message.Message;
import com.empathy.domain.message.bo.MessageBo;

/**
 * @author tybest
 * @date 2017/8/22 20:50
 * @email zhoujian699@126.com
 * @desc
 **/
public interface IMessageService extends BaseService<Message, Long, MessageBo> {

    /**
     * 推送信息
     *
     * @param fuserId
     * @param tuserId
     * @param content
     */
    void push(Long fuserId, Long tuserId, String content, Integer type, Long reference);

    /**
     * 系统推送
     *
     * @param tuserId
     * @param content
     */
    void push(Long tuserId, String content);

    RspResult page(MessageBo bo);
}
