package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.BaseDomain;
import com.empathy.common.PageBo;
import com.empathy.domain.account.Account;
import com.empathy.domain.album.BaseNice;
import com.empathy.domain.album.bo.AlbumFindBestBo;
import com.empathy.domain.album.bo.FindBestForContentBo;
import com.empathy.domain.album.vo.AlbumBestVo;
import com.empathy.domain.album.vo.AlbumVo;
import com.empathy.domain.baseMain.BaseMain;
import com.empathy.domain.log.bo.LogBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
public interface BaseNiceDao extends BaseDao<BaseNice, Long, LogBo> {

    List<AlbumVo> list(AlbumFindBestBo bo);

    int findCount(@Param(value = "ids") Long ids,@Param(value = "id") Long id);

    int listCount(AlbumFindBestBo bo);


    List<AlbumVo> findAlbumBest();
    List<AlbumVo> findAlbumBest1();
    List<AlbumVo>  findAlbumBest2();
}
