package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.comments.Comments;
import com.empathy.domain.comments.bo.CommentsFindBo;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;

import java.util.List;

/**
 * Created by MI on 2018/4/23.
 */
public interface CommentDao extends BaseDao<Comments, Long, LogBo> {
    List<Comments> list(CommentsFindBo bo);

    int count(CommentsFindBo bo);

    int findCountByDynamic(Long id);
}
