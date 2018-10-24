package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.message.Message;
import com.empathy.domain.message.bo.MessageBo;
import com.empathy.domain.message.vo.MessageVo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/8/22 20:39
 * @email zhoujian699@126.com
 * @desc
 **/
public interface MessageDao extends BaseDao<Message, Long, MessageBo> {

    long count(MessageBo bo);

    List<MessageVo> list(MessageBo bo);
}
