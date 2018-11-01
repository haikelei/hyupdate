package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseReportDao;
import com.empathy.domain.album.Album;
import com.empathy.domain.article.Article;
import com.empathy.domain.baseReport.BaseReport;
import com.empathy.domain.baseReport.bo.ReportFindBo;
import com.empathy.domain.baseReport.vo.ReportVo;
import com.empathy.domain.user.BaseMember;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseReportSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MI on 2018/4/19.
 */
@Service
public class BaseReportService extends AbstractBaseService implements IBaseReportSerivce {

    @Autowired
    private BaseReportDao baseReportDao;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private BaseMemberService memberService;

    @Override
    public String findReportCount(ReportFindBo bo) {

        int count = baseReportDao.count(bo);

        return count+"";
    }

    @Override
    public RspResult page(ReportFindBo bo) {

        long count = baseReportDao.count(bo);
        List<ReportVo> list = new ArrayList<>();
        if (count > 0) {
            list = baseReportDao.list(bo);

            if (list != null) {
                for (ReportVo reportVo : list) {
                    // 专辑
                    if (reportVo.getType() != null && 100 == reportVo.getType()) {
                        Album album = albumService.findById(reportVo.getLiveId());

                        if (album != null) {
                            reportVo.setDetail(album.getAlbumName());
                        }
                    }
                    // 个人
                    else if (reportVo.getType() != null && 200 == reportVo.getType()) {
                        BaseMember baseMember = memberService.findById(reportVo.getLiveId());

                        if (baseMember != null) {
                            reportVo.setDetail(baseMember.getPhone());
                        }
                    }
                    // 文章
                    else if (reportVo.getType() != null && 300 == reportVo.getType()) {
                        Article article = articleService.findById(reportVo.getLiveId());

                        if (article != null) {
                            reportVo.setDetail(article.getContent());
                        }
                    }
                }
            }
        }
        return success(count, list);
    }

    @Override
    public RspResult auditPass(Long id) {

        BaseReport baseReport = baseReportDao.findById(id);

        if (baseReport == null) {
            return error(1,"数据不存在！");
        }

        // 举报专辑
        if (baseReport.getType() != null && 100 == baseReport.getType()) {

            Album album = albumService.findById(baseReport.getLiveId());

            if (album == null) {
                return error(1,"该专辑不存在！");
            }
            if (1 == album.getDelFlag()) {
                return error(1,"该专辑已被删除！");
            }

            // 删除该专辑
            albumService.delById(album.getId());
        }
        // 举报个人
        else if (baseReport.getType() != null && 200 == baseReport.getType()) {

            BaseMember baseMember = memberService.findById(baseReport.getLiveId());

            if (baseMember == null) {
                return error(1,"该用户不存在！");
            }
            if (1 == baseMember.getDelFlag()) {
                return error(1,"该用户已被禁用！");
            }

            // 删除该文章
            memberService.delById(baseMember.getId());
        }
        // 举报文章
        else if (baseReport.getType() != null && 300 == baseReport.getType()) {
            Article article = articleService.findById(baseReport.getLiveId());

            if (article == null) {
                return error(1,"该文章不存在！");
            }
            if (1 == article.getDelFlag()) {
                return error(1,"该文章已被删除！");
            }

            // 删除该文章
            articleService.delById(article.getId());
        }

        // 再删除该举报信息
        baseReportDao.delById(id);

        return success();
    }

    @Override
    public RspResult save(BaseReport entity) {
        return null;
    }

    @Override
    public BaseReport findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseReport entity) {
        return null;
    }

    @Override
    public RspResult delById(Long id) {
        baseReportDao.delById(id);
        return success();
    }
}
