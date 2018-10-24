package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.album.bo.RecordAddUrlBo;
import com.empathy.domain.baseReport.bo.ReportAddBo;
import com.empathy.domain.baseReport.bo.ReportFindBo;
import com.empathy.domain.live.bo.*;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.bo.ProveAddBo;
import com.empathy.domain.user.bo.ProveUpdBo;

/**
 * Created by MI on 2018/4/19.
 */
public interface IBaseLiveSerivce extends BaseService<BaseMember, Long, PageBo> {
    RspResult addProve(ProveAddBo bo);

    RspResult updProve(ProveUpdBo bo);

    RspResult addLiveClassfiy(LiveClassifyAddBo bo);

    RspResult updLiveClassfiy(LiveClassifyUpdBo bo);

    RspResult delLiveClassfiy(LiveClassifyDelBo bo);

    RspResult findLiveClassfiy();

    RspResult beginPlay(BeginPlayBo bo);

    RspResult joinPlay(JoinPlayBo bo);

    RspResult exitPlay(JoinPlayBo bo);

    RspResult giveGift(GiveGiftBo bo);

    RspResult addReport(ReportAddBo bo);

    RspResult findReport(ReportFindBo bo);

    RspResult findProveById(Long id);


    RspResult findLiveByClassify(FindLiveByClassifyBo bo);

    RspResult findLiveForDetail(FindLiveForDetailBo bo);

    RspResult findLiveForFour(FindLiveForFourBo bo);

    RspResult searchAll(SearchAllBo bo);


    RspResult findLiveForNew(PageBo bo);

    RspResult findLiveForHot(PageBo bo);

    RspResult addRoomManage(RoomAddManageBo bo);

    RspResult updRoomManage(RoomUpddManageBo bo);

    RspResult addFreeze(FreezeAddBo bo);

    RspResult updFreeze(FreezeAddBo bo);

    RspResult findManageList(ManageListBo bo);

    RspResult beginAppointment(BeginAppointmentBo bo);

    RspResult findLiveForAppointment(PageBo bo);

    RspResult findGiftBest(GiftBestBo bo);

    RspResult closePlay(Long liveId);

    RspResult addBlacklist(BlacklistAddBo bo);

    RspResult updBlacklist(BlacklistAddBo bo);

    RspResult findBlacklist(BlackListBo bo);

    RspResult giftList(GifiListBo bo);

    RspResult getTokenAndAccid(TokenAccidBo bo);

    RspResult moneyForLive(Long liveId);

    RspResult getTotalThisEarnings(JoinPlayBo bo);

    RspResult rankByLiveRoom(Long liveId);

}
