package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.album.Album;
import com.empathy.domain.album.bo.*;
import com.empathy.domain.baseRecording.bo.*;
import com.empathy.domain.comments.bo.CommentsAddBo;
import com.empathy.domain.comments.bo.CommentsFindBo;
import com.empathy.domain.live.bo.FindLiveByClassifyBo;

/**
 * Created by MI on 2018/4/16.
 */
public interface IAlbumService extends BaseService<Album, Long, PageBo> {
    RspResult addAlbum(AlbumAddBo bo);

    RspResult updAlbum(AlbumUpdBo bo);

    RspResult delAlbum(AlbumDelBo bo);

    RspResult findAlbumById(AlbumFindByIdBo bo);

    RspResult findAlbumByUserId(AlbumFindByUserIdBo bo);

    RspResult findAlbumByClassifyId(AlbumFindByClassifyIdBo bo);

    RspResult addRecording(RecordingAddBo bo);

    RspResult findRecording(RecordingFindBo bo);

    RspResult addClickNumber(RecordingAddNumberBo bo);

    RspResult addPlayNumber(RecordingAddNumberBo bo);

    RspResult addComments(CommentsAddBo bo);

    RspResult findComments(CommentsFindBo bo);

    RspResult updRecording(RecordingUpdBo bo);

    RspResult findAlbumForAccount(FindAlbumForAccountBo bo);

    String findAlbumForAccountCount(FindAlbumForAccountBo bo);

    RspResult findAlbumMoney(Long id);

    String findRecordingCount(RecordingFindBo bo);

    RspResult freezeRecording(RecordingFreezeBo bo);

    RspResult findRecordingForAccount(RecordingFindBo bo);

    RspResult findAlbumByClassify(FindLiveByClassifyBo bo);

    String findAlbumByBestCount(AlbumFindBestBo bo);

    RspResult findAlbumByBest(AlbumFindBestBo bo);

    RspResult addAlbumByBest(Long ids,Long id);

    RspResult delAlbumByBest(Long albumId);

    RspResult findAlbumByBestTitle();


    RspResult findHotStr();

    RspResult findMyBuy(FindBuyBo buyBo);

    RspResult findMyBuyRecording(RecordingFindBo bo);

    RspResult addMyBuy(BuyAddBo bo);

    RspResult addVideo(RecordAddUrlBo bo);

    RspResult findBestAlbumForMain();

    RspResult findRecordingByUser(RecordingFindBo bo);

    RspResult recordPlay(RecordPlayBo bo);


    int getPayNumber(Long albumId);

}
