package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.album.HotStr;
import com.empathy.domain.banner.BaseBanner;
import com.empathy.domain.banner.bo.BannerFindBo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface HotStrDao extends BaseDao<HotStr, Long, LogBo> {
        List<HotStr> list();
}
