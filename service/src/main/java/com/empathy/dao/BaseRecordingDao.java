package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.baseRecording.BaseRecording;
import com.empathy.domain.baseRecording.bo.RecordingFindBo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

/**
 * Created by MI on 2018/4/23.
 */
public interface BaseRecordingDao extends BaseDao<BaseRecording, Long, LogBo> {
    List<BaseRecording> list(RecordingFindBo bo);

    int count(Long albumId);

    void cancelDel(Long id);

    List<BaseRecording> listForAccount(RecordingFindBo bo);

    int countForAccount(Long albumId);

    List<BaseRecording> findRecordingByUser(RecordingFindBo bo);

    int findRecordingCountByUser(RecordingFindBo bo);

    List<BaseRecording> findByAlbumId(Long albumId);

    /**
     * 获取专辑的播放量
     */
    Integer getPayNumber(Long albumId);


}
