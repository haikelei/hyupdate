package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.collection.bo.CollectionFindBo;
import com.empathy.domain.live.BaseLive;
import com.empathy.domain.live.LiveAll;
import com.empathy.domain.live.bo.*;
import com.empathy.domain.live.vo.RankVo;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MI on 2018/4/19.
 */
public interface BaseLiveDao extends BaseDao<BaseLive, Long, LogBo> {


    BaseLive findByUserId(Long id);

    int countById(Long id);

    void cancleProve(Long id);

    List<BaseLive> findLiveByClassify(FindLiveByClassifyBo bo);

    int findLiveByClassifyCount(FindLiveByClassifyBo bo);

    LiveAll findLiveForDetail(FindLiveForDetailBo bo);

    List<BaseLive> findLiveForFour(FindLiveForFourBo bo);

    int searchCount(SearchAllBo bo);

    List<BaseLive> search(SearchAllBo bo);

    List<BaseLive> findLiveForNew(PageBo bo);

    List<BaseLive> findLiveForHot(PageBo bo);

    List<BaseLive> findLiveForAppointment(PageBo bo);

    List<BaseLive> findForCollection(CollectionFindBo bo);

    int findForCollectionCount(CollectionFindBo bo);

    List<BaseMember> giftList(GifiListBo bo);

    int giftCount(Long id);

    List<BaseMember> giftListForWeek(GifiListBo bo);

    List<RankVo> findRank();

    List<BaseMember> giftByUserRank(@Param("bo")GifiListBo bo,@Param("liveTime")Long liveTime);

    /** 获取指定时间的总收入 */
    BigDecimal getGiftMoney(@Param("bo")JoinPlayBo bo,@Param("liveTime")Long liveTime);

    List<RankVo> findRankByPrice();

}
