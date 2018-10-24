package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.album.HotStr;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.PrivateChat;
import com.empathy.domain.user.bo.PrivateChatFindBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MI on 2018/8/30.
 */
public interface PrivateChatDao  extends BaseDao<PrivateChat, Long, PageBo> {

    List<PrivateChat> list(PrivateChatFindBo chatFindBo);
    int count(@Param("userId")Long userId);
}
