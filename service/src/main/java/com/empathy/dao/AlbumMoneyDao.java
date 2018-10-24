package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.album.AlbumMoney;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/16.
 */
public interface AlbumMoneyDao extends BaseDao<AlbumMoney, Long, LogBo> {
    AlbumMoney findByAlbumId(Long id);
}
