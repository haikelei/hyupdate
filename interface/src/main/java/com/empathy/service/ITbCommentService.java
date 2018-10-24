package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.comments.TbComment;
import com.empathy.domain.comments.bo.CommentOpenBo;
import com.empathy.domain.comments.bo.CommentOpenFindBo;
import com.empathy.domain.withdraw.Withdraw;

/**
 * Created by MI on 2018/6/6.
 */
public interface ITbCommentService extends BaseService<TbComment, Long, PageBo> {
    RspResult openComment(CommentOpenBo bo);

    RspResult findComment(CommentOpenFindBo bo);

    RspResult findAllComment();
}
