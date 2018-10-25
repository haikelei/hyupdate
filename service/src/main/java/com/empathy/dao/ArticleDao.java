package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.article.Article;
import com.empathy.domain.article.bo.ArticleFindBo;
import com.empathy.domain.article.bo.PointFindBo;
import com.empathy.domain.article.vo.ArticleVo;
import com.empathy.domain.article.vo.PointFindVo;
import com.empathy.domain.log.bo.LogBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface ArticleDao extends BaseDao<Article, Long, LogBo> {


    int count(ArticleFindBo bo);

    List<ArticleVo> list(ArticleFindBo bo);

    ArticleVo findDetail(Long id);

    List<ArticleVo> listFor(ArticleFindBo bo);

    void addPoint(Long id);

    void addPointInfo(@Param(value = "id") Long id,@Param(value = "userId") Long userId);

    int findCount(@Param(value = "id") Long id,@Param(value = "userId") Long userId);

    void cancelPoint(Long articleId);

    void delPointInfo(@Param(value = "articleId") Long articleId,@Param(value = "userId")Long userId);

    Integer countPoint(PointFindBo bo);

    List<PointFindVo> findPoint(PointFindBo bo);
}
