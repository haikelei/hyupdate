package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.article.bo.ArticleAddBo;
import com.empathy.domain.article.bo.ArticleFindBo;
import com.empathy.domain.article.bo.ArticleUpdBo;
import com.empathy.domain.baseMessage.bo.MessageAddBo;
import com.empathy.service.IArticleService;
import com.empathy.service.IBaseReadLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by MI on 2018/6/7.
 */
@RestController
@RequestMapping("/article")
@Api(tags = {"文章接口"})
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "添加", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public RspResult addArticle(ArticleAddBo bo) {

        return articleService.addArticle(bo);
    }
    @ApiOperation(value = "主播发布动态", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addArticleByAlbum", method = RequestMethod.POST)
    public RspResult addArticleByAlbum(ArticleAddBo bo) {

        return articleService.addArticleByAlbum(bo);
    }

    @ApiOperation(value = "修改", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updArticle", method = RequestMethod.POST)
    public RspResult updArticle(ArticleUpdBo bo) {

        return articleService.updArticle(bo);
    }


    @ApiOperation(value = "点赞", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addPoint", method = RequestMethod.POST)
    public RspResult addPoint(Long id,Long userId) {

        return articleService.addPoint(id,userId);
    }

    @ApiOperation(value = "取消点赞", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/cancelPoint", method = RequestMethod.POST)
    public RspResult cancelPoint(Long id,Long userId) {

        return articleService.cancelPoint(id,userId);
    }

    @ApiOperation(value = "app查找", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findArticle", method = RequestMethod.POST)
    public RspResult findArticle(ArticleFindBo bo) {

        bo.setType(0);
        return articleService.findArticle(bo);
    }
    @ApiOperation(value = "app查看详情", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findArticleById", method = RequestMethod.POST)
    public RspResult findArticleById(Long id) {

        return articleService.findArticleById(id);
    }

    @ApiOperation(value = "后台查看所有个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findArticleCount", method = RequestMethod.POST)
    public String findArticleCount(ArticleFindBo bo) {

        return articleService.findArticleCount(bo);
    }
    @ApiOperation(value = "后台查看所有", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAllArticle", method = RequestMethod.POST)
    public RspResult findAllArticle(ArticleFindBo bo) {

        return articleService.findAllArticle(bo);
    }

    @ApiOperation(value = "app查看学堂列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAllSchool", method = RequestMethod.POST)
    public RspResult findAllSchool(ArticleFindBo bo) {
        bo.setType(1);
        return articleService.findArticleBySchoole(bo);
    }

    @ApiOperation(value = "用户读学堂内容", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/readSchoolArticle", method = RequestMethod.POST)
    public RspResult readSchoolArticle(Long articleId,Long userId) {

        return articleService.findArticleByIdAtSchoole(articleId,userId);
    }

}
