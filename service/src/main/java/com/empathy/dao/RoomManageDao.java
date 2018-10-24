package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.article.Article;
import com.empathy.domain.article.bo.ArticleFindBo;
import com.empathy.domain.article.vo.ArticleVo;
import com.empathy.domain.live.RoomManage;
import com.empathy.domain.live.bo.JoinPlayBo;
import com.empathy.domain.live.bo.ManageListBo;
import com.empathy.domain.live.bo.RoomUpddManageBo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface RoomManageDao extends BaseDao<RoomManage, Long, LogBo> {


    RoomManage findByTwoId(RoomUpddManageBo bo);

    int count(Long id);

    List<RoomManage> list(ManageListBo bo);

    int findCountByPlayBo(JoinPlayBo bo);
}
