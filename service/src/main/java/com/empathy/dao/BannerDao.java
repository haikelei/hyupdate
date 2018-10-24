package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.account.Account;
import com.empathy.domain.article.Article;
import com.empathy.domain.banner.BaseBanner;
import com.empathy.domain.banner.bo.BannerFindBo;
import com.empathy.domain.log.bo.LogBo;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.boot.Banner;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface BannerDao  extends BaseDao<BaseBanner, Long, LogBo> {

    List<BaseBanner> list(BannerFindBo bo);

    int count(BannerFindBo bo);
}
