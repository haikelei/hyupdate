package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.comments.Comments;
import com.empathy.domain.comments.TbComment;
import com.empathy.domain.comments.bo.CommentsFindBo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/4/23.
 */
public interface TbCommentDao extends BaseDao<TbComment, Long, LogBo> {

    TbComment findByType(Integer type);

    List<TbComment> list();
}
