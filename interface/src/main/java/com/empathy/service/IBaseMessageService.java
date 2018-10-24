package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.baseMessage.BaseMessage;
import com.empathy.domain.baseMessage.bo.MessageAddBo;
import com.empathy.domain.baseMessage.bo.MessageFindBo;
import com.empathy.domain.baseMessage.bo.MessageUpdBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/6/5.
 */
public interface IBaseMessageService  extends BaseService<BaseMessage, Long, PageBo> {
    RspResult addMessage(MessageAddBo bo);

    RspResult delMessage(Long id);

    RspResult updMessage(MessageUpdBo bo);

    RspResult findMessage(MessageFindBo bo);

    String findMessageCount();

    RspResult findMessageById(Long id);

    RspResult findMessageForApp(MessageFindBo bo);

    RspResult changeMessageStatus(Long id);
}
