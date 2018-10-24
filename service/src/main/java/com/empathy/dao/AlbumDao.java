package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.album.Album;
import com.empathy.domain.album.TbUserRecord;
import com.empathy.domain.album.bo.AlbumFindByClassifyIdBo;
import com.empathy.domain.album.bo.AlbumFindByUserIdBo;
import com.empathy.domain.album.bo.FindAlbumForAccountBo;
import com.empathy.domain.album.bo.FindBuyBo;
import com.empathy.domain.album.vo.AlbumAccountVo;
import com.empathy.domain.album.vo.AlbumVo;
import com.empathy.domain.baseRecording.bo.RecordingFindBo;
import com.empathy.domain.collection.bo.CollectionFindBo;
import com.empathy.domain.live.bo.FindLiveByClassifyBo;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MI on 2018/4/16.
 */
public interface AlbumDao extends BaseDao<Album, Long, LogBo> {
    AlbumVo findAlbumById(Long id);

    List<AlbumVo> findAlbumByUserId(AlbumFindByUserIdBo bo);

    int findAlbumCountByUserId(Long userId);

    List<AlbumVo> findByClassifyId(AlbumFindByClassifyIdBo bo);

    int findCountByClassifyId(Long classifyId);

    List<AlbumAccountVo> findAlbumForAccount(FindAlbumForAccountBo bo);

    int findAlbumForAccountCount(FindAlbumForAccountBo bo);

    List<AlbumVo> findAlbumByClassify(FindLiveByClassifyBo bo);

    int findAlbumByClassifyCount(FindLiveByClassifyBo bo);

    List<AlbumVo> findAlbumForCollection(CollectionFindBo bo);

    int findAlbumForCollectionCount(CollectionFindBo bo);

    int findAlbumByBuyCount(FindBuyBo buyBo);

    List<AlbumVo> findAlbumByBuy(FindBuyBo buyBo);

    List<String> findRecording(RecordingFindBo bo);

    void addMyBuy(TbUserRecord tbUserRecord);

    int findMyBuy(@Param("userId") Long userId,@Param("id") Long id);
}
