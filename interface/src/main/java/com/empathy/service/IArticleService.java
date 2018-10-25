package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.article.Article;
import com.empathy.domain.article.bo.ArticleAddBo;
import com.empathy.domain.article.bo.ArticleFindBo;
import com.empathy.domain.article.bo.ArticleUpdBo;
import com.empathy.domain.article.bo.PointFindBo;

/**
 * Created by MI on 2018/5/9.
 */
public interface IArticleService extends BaseService<Article, Long, PageBo> {

    RspResult addArticle(ArticleAddBo bo);

    RspResult updArticle(ArticleUpdBo bo);

    RspResult findArticle(ArticleFindBo bo);

    String findArticleCount(ArticleFindBo bo);

    RspResult findAllArticle(ArticleFindBo bo);

    RspResult findArticleById(Long id);

    RspResult addPoint(Long id,Long userId);

    RspResult cancelPoint(Long id,Long userId);

    RspResult addArticleByAlbum(ArticleAddBo bo);


    RspResult findArticleBySchoole(ArticleFindBo bo);

    RspResult findArticleByIdAtSchoole(Long articleId,Long userId);

    RspResult findPoint(PointFindBo bo);
}
