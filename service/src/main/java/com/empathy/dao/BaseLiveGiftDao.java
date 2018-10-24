package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.live.BaseLiveGift;
import com.empathy.domain.live.bo.GiftBestBo;
import com.empathy.domain.live.vo.GiveGiftVo;
import com.empathy.domain.log.bo.LogBo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MI on 2018/4/25.
 */
public interface BaseLiveGiftDao extends BaseDao<BaseLiveGift, Long, LogBo> {


    List<GiveGiftVo> listForDay(PageBo bo);

    int countForDay(PageBo bo);

    List<GiveGiftVo> listForWeek(GiftBestBo bo);


    List<GiveGiftVo> listForMonth(GiftBestBo bo);


    BigDecimal findMoneyByLiveId(@Param("id") Long id, @Param("startTime") Long startTime);

    BigDecimal findMoneyByLiveIdAndTime(@Param("id")Long id, @Param("startTime")Long startTime,@Param("endTime") Long endTime);

    /** 根据时间判断主播礼物排行榜 */
    List<GiveGiftVo> giftRankByTime(@Param("bo")GiftBestBo bo,@Param("time")Long time);

}
