package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.agreement.Agreement;
import com.empathy.domain.baseMessage.BaseMessage;
import com.empathy.domain.baseMessage.bo.MessageFindBo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/6/5.
 */
public interface BaseMessageDao  extends BaseDao<BaseMessage, Long, LogBo> {
    int count();

    List<BaseMessage> list(MessageFindBo bo);

    List<BaseMessage> listForApp(MessageFindBo bo);
}
