package com.empathy.service.impl;

import com.empathy.cache.CacheUtils;
import com.empathy.common.CreateLiveChannel;
import com.empathy.common.Live;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.album.Album;
import com.empathy.domain.album.bo.FindAlbumForAccountBo;
import com.empathy.domain.album.bo.RecordAddUrlBo;
import com.empathy.domain.album.vo.AlbumAccountVo;
import com.empathy.domain.article.Article;
import com.empathy.domain.baseGift.BaseGift;
import com.empathy.domain.baseReport.BaseReport;
import com.empathy.domain.baseReport.bo.ReportAddBo;
import com.empathy.domain.baseReport.bo.ReportFindBo;
import com.empathy.domain.baseReport.vo.ReportVo;
import com.empathy.domain.bidding.File;
import com.empathy.domain.configuration.Configuration;
import com.empathy.domain.file.bo.FileCarBo;
import com.empathy.domain.grade.BaseGrade;
import com.empathy.domain.live.*;
import com.empathy.domain.live.bo.*;
import com.empathy.domain.live.vo.GiveGiftVo;
import com.empathy.domain.live.vo.RankVo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.HostProve;
import com.empathy.domain.user.UserMoney;
import com.empathy.domain.user.bo.ProveAddBo;
import com.empathy.domain.user.bo.ProveUpdBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IAlbumService;
import com.empathy.service.IBaseLiveSerivce;
import com.empathy.service.IBaseMemberService;
import com.empathy.utils.DateUtils;
import com.empathy.utils.StringUtil;
import com.empathy.utils.YXUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by MI on 2018/4/19.
 */
@Service
public class BaseLiveService extends AbstractBaseService implements IBaseLiveSerivce {

    @Autowired
    private HostProveDao hostProveDao;
    @Autowired
    private BaseMemberDao baseMemberDao;

    @Autowired
    private BaseLiveDao baseLiveDao;

    @Autowired
    private LiveClassifyDao liveClassifyDao;

    @Autowired
    private FileService fileService;

    @Autowired
    private BaseLiveTimeDao baseLiveTimeDao;

    @Autowired
    private BaseGiftDao baseGiftDao;

    @Autowired
    private ConfigurationDao configurationDao;

    @Autowired
    private BaseReportDao baseReportDao;

    @Autowired
    private BaseLiveChannelDao liveChannelDao;

    @Autowired
    private IAlbumService albumService;
    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private RoomManageDao roomManageDao;

    @Autowired
    private BaseClassifyDao baseClassifyDao;

    @Autowired
    private BaseLiveGiftDao baseLiveGiftDao;

    @Autowired
    private LiveBlacklistDao liveBlacklistDao;

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private BaseLiveChannelDao baseLiveChannelDao;

    @Autowired
    private UserFocusDao userFocusDao;

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private BaseGradeDao baseGradeDao;

    @Override
    public RspResult moneyForLive(Long liveId) {
        BaseLive byId = baseLiveDao.findById(liveId);
        if(byId==null){
            return errorMo();
        }
        BigDecimal money =null;
        //查询本场时间
        BaseLiveTime byLiveIdForClose = baseLiveTimeDao.findByLiveIdForClose(liveId);
        if(byLiveIdForClose.getEndTime()==null){

            money = baseLiveGiftDao.findMoneyByLiveId(byId.getId(), byLiveIdForClose.getStartTime());
        }else {

            money=  baseLiveGiftDao.findMoneyByLiveIdAndTime(byId.getId(),byLiveIdForClose.getStartTime(),byLiveIdForClose.getEndTime());
        }



        return success(money);
    }

    @Override
    public RspResult getTokenAndAccid(TokenAccidBo bo) {
        String tokenAndAccid = YXUtils.getTokenAndAccid(bo.getAccid());
        String[] split = tokenAndAccid.split(";");
        if(split.length!=2){
            return error(1,"请求失败！");
        }
        BaseLiveChannel baseLiveChannel = baseLiveChannelDao.findByAccid(bo.getAccid());
        baseLiveChannel.setFileAccid(split[0]);
        baseLiveChannel.setFileToken(split[1]);
        baseLiveChannelDao.update(baseLiveChannel);
        return success(baseLiveChannel);
    }

    @Override
    public RspResult giftList(GifiListBo bo) {
        List<BaseMember> list = new ArrayList<>();
        if(bo.getType()==0){
            //查询主播最后一次的直播时间
            BaseLiveTime baseLiveTime = baseLiveTimeDao.findByLiveId(bo.getId());
            list = baseLiveDao.giftByUserRank(bo,baseLiveTime.getStartTime());
        }else {
           //list= baseLiveDao.giftListForWeek(bo);
           list= baseLiveDao.giftByUserRank(bo,DateUtils.getWeekStartDate().getTime());
        }
        int count = baseLiveDao.giftCount(bo.getId());
        return success(count,list);
    }



    @Override
    public RspResult findBlacklist(BlackListBo bo) {
        try {

            List<BaseMember> baseMembers = liveBlacklistDao.list(bo);
            int count = liveBlacklistDao.count(bo.getId());
            return success(count,baseMembers);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult updBlacklist(BlacklistAddBo bo) {
        LiveBlacklist liveBlacklist = liveBlacklistDao.findByUserIdAndId(bo);
        if(liveBlacklist==null){
            return errorMo();
        }
        liveBlacklistDao.delById(liveBlacklist.getId());

        return success();
    }

    @Override
    public RspResult addBlacklist(BlacklistAddBo bo) {
        BaseLive byId = baseLiveDao.findByUserId(bo.getId());
        if(byId==null){
            return errorMo();
        }
        JoinPlayBo joinPlayBo = new JoinPlayBo();
        joinPlayBo.setLiveId(byId.getUserId());
        joinPlayBo.setUserId(bo.getUserId());
        int blackCount = liveBlacklistDao.findBlackCount(joinPlayBo);
        if(blackCount>0){
            return error(1,"已拉黑");
        }
        LiveBlacklist liveBlacklist = new LiveBlacklist();
        liveBlacklist.setLiveId(byId.getId());
        liveBlacklist.setUserId(bo.getUserId());
        liveBlacklistDao.save(liveBlacklist);
        return success();
    }

    @Override
    public RspResult closePlay(Long liveId) {
        BaseLive baseLive = baseLiveDao.findById(liveId);
        if(baseLive==null){
            return  errorMo();
        }
        if(baseLive.getLiveStatus()==0){
            return error(1,"直播尚未开启！");
        }
        BaseLiveTime baseliveTime = baseLiveTimeDao.findByLiveIdForClose(liveId);
        baseliveTime.setEndTime(System.currentTimeMillis());
        baseLiveTimeDao.update(baseliveTime);
        long l = baseliveTime.getEndTime() - baseliveTime.getStartTime();
        double time = l/1000/60/60;
        baseLive.setTimeSum(baseLive.getTimeSum()+time);
        baseLive.setLiveStatus(0);
        baseLiveDao.update(baseLive);
        return success();
    }

    @Override
    public RspResult findGiftBest(GiftBestBo bo) {
        try {
            List<GiveGiftVo> list = new ArrayList<>();
            int count = 0;
            PageBo bo1 = new PageBo();
            bo1.setLimit(bo.getLimit());
            bo1.setStart(bo.getStart());
            if(bo.getType()==0){
                list = baseLiveGiftDao.listForDay(bo1);
                count = baseLiveGiftDao.countForDay(bo1);
            }else if(bo.getType()==1){
                //list = baseLiveGiftDao.listForWeek(bo);
                list = baseLiveGiftDao.giftRankByTime(bo,DateUtils.getWeekStartDate().getTime());
                count = baseLiveGiftDao.countForDay(bo);
            }else {
                list = baseLiveGiftDao.listForMonth(bo);
                count = baseLiveGiftDao.countForDay(bo);

            }
            return success(count,list);
        }catch (Exception e){
            e.printStackTrace();
            return  errorNo();
        }
    }

    @Override
    public RspResult findLiveForAppointment(PageBo bo) {
        try {
            List<BaseLive> list = baseLiveDao.findLiveForAppointment(bo);

            return success(6,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult beginAppointment(BeginAppointmentBo bo) {
        BaseMember baseMember = baseMemberDao.findById(bo.getUserId());
        if (baseMember.getProveStatus() != 1) {
            return error(1, "您还不是主播");
        }
        BaseLive baseLive = baseLiveDao.findByUserId(baseMember.getId());

        if (StringUtil.isNotEmpty(bo.getTheme())) {
            baseLive.setTheme(bo.getTheme());
        }
        baseLive.setLiveStatus(2);
        baseLive.setTitle(bo.getTitle());
        baseLive.setBeginTime(bo.getBeginTime());
        baseLiveDao.update(baseLive);
        if (bo.getType() == 0) {
            //发送推送
        }
        //添加时间记录
        BaseLiveTime baseLiveTime = new BaseLiveTime();
        baseLiveTime.setStartTime(System.currentTimeMillis());
        baseLiveTime.setLiveId(baseLive.getId());
        baseLiveTimeDao.save(baseLiveTime);

        return success();
    }

    @Override
    public RspResult findManageList(ManageListBo bo) {
        try {
            List<RoomManage> list = roomManageDao.list(bo);
            int count = roomManageDao.count(bo.getId());

            return success(count,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult updFreeze(FreezeAddBo bo) {
        //查看是不是本房间主播
        BaseLive baseLive = baseLiveDao.findByUserId(bo.getFreezeId());
        if(baseLive==null){
            //查看是不是房管
            RoomUpddManageBo bo1 = new RoomUpddManageBo();
            bo1.setId(bo.getId());
            bo1.setUserId(bo.getFreezeId());
            RoomManage roomManage1 = roomManageDao.findByTwoId(bo1);
            if(roomManage1==null){
                return error(1,"权限不足");
            }
            if(roomManage1.getStatus()!=1){
                return error(1,"权限不足");
            }

        }
        RoomUpddManageBo bo2 = new RoomUpddManageBo();
        bo2.setId(bo.getId());
        bo2.setUserId(bo.getUserId());
        RoomManage roomManage2 = roomManageDao.findByTwoId(bo2);
        if(roomManage2==null){
            return error(1,"用户id未被冻结");
        }else {
            roomManage2.setFreezeStatus(0);
            roomManageDao.update(roomManage2);
        }

        return success();
    }

    @Override
    public RspResult addFreeze(FreezeAddBo bo) {
        //查看是不是本房间主播
        BaseLive baseLive = baseLiveDao.findByUserId(bo.getFreezeId());
        if(baseLive==null){
            //查看是不是房管
            RoomUpddManageBo bo1 = new RoomUpddManageBo();
            bo1.setId(bo.getId());
            bo1.setUserId(bo.getFreezeId());
            RoomManage roomManage1 = roomManageDao.findByTwoId(bo1);
            if(roomManage1==null){
                return error(1,"权限不足");
            }
            if(roomManage1.getStatus()!=1){
                return error(1,"权限不足");
            }

        }
        RoomUpddManageBo bo2 = new RoomUpddManageBo();
        bo2.setId(bo.getId());
        bo2.setUserId(bo.getUserId());
        RoomManage roomManage2 = roomManageDao.findByTwoId(bo2);
        if(roomManage2==null){
            roomManage2.setStatus(0);
            roomManage2.setManageUserId(bo.getUserId());
            roomManage2.setStatus(0);
            roomManage2.setFreezeStatus(1);
            roomManage2.setUserId(baseLive.getUserId());
            roomManage2.setLiveId(baseLive.getId());
            roomManageDao.save(roomManage2);

        }else {
            if(roomManage2.getStatus()==1){
                return error(1,"请先解除房管！");
            }
            roomManage2.setFreezeStatus(1);
            roomManageDao.update(roomManage2);
        }


        return success();
    }

    @Override
    public RspResult updRoomManage(RoomUpddManageBo bo) {
        RoomManage roomManage = roomManageDao.findByTwoId(bo);
        if(roomManage==null){
            return errorMo();
        }
        roomManage.setStatus(0);
        roomManageDao.update(roomManage);
        return success(roomManage);
    }

    @Override
    public RspResult addRoomManage(RoomAddManageBo bo) {
        RoomUpddManageBo bo1 = new RoomUpddManageBo();
        bo1.setId(bo.getId());
        bo1.setUserId(bo.getUserId());
        RoomManage roomManage1 = roomManageDao.findByTwoId(bo1);
        if(roomManage1==null){
        RoomManage roomManage = new RoomManage();
        roomManage.setFreezeStatus(0);
        roomManage.setLiveId(bo.getLiveId());
        roomManage.setManageUserId(bo.getUserId());
        roomManage.setUserId(bo.getId());
        roomManage.setStatus(1);
        roomManageDao.save(roomManage);
        return success(roomManage);
        } else {

            roomManage1.setStatus(1);
            roomManageDao.update(roomManage1);
            return success(roomManage1);
        }
    }

    @Override
    public RspResult findLiveForHot(PageBo bo) {
        try {
            List<BaseLive> list = baseLiveDao.findLiveForHot(bo);

            return success(6,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult findLiveForNew(PageBo bo) {
        try {
            List<BaseLive> list = baseLiveDao.findLiveForNew(bo);

            return success(6,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult searchAll(SearchAllBo bo) {

        if(bo.getType()==0){
        //搜索专辑列表
            FindAlbumForAccountBo bo1  = new FindAlbumForAccountBo();
            bo1.setStr(bo.getStr());
            bo1.setLimit(bo.getLimit());
            bo1.setStart(bo.getStart());
            List<AlbumAccountVo> albumForAccount = albumDao.findAlbumForAccount(bo1);
            int count = albumDao.findAlbumForAccountCount(bo1);
            return success(count,albumForAccount);
        }else if(bo.getType()==1){
            //搜索直播间列表
            List<BaseLive> lives = baseLiveDao.search(bo);
            int count = baseLiveDao.searchCount(bo);
            return success(count,lives);
        }


        return null;
    }

    @Override
    public RspResult findLiveForFour(FindLiveForFourBo bo) {
        try {

            List<BaseLive> lives = baseLiveDao.findLiveForFour(bo);
            return success(lives);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult findLiveForDetail(FindLiveForDetailBo bo) {
        LiveAll liveAll = baseLiveDao.findLiveForDetail(bo);

        int countResult = userFocusDao.findCountByIds(bo.getUserId(),liveAll.getUserId());
        if(countResult>0){
            liveAll.setFocusStatus(1);
        }else {
            liveAll.setFocusStatus(0);
        }
        JoinPlayBo bo1 = new JoinPlayBo();
        bo1.setUserId(bo.getUserId());
        bo1.setLiveId(liveAll.getId());
        int count = roomManageDao.findCountByPlayBo(bo1);
        int blackCount  = liveBlacklistDao.findBlackCount(bo1);
        if(count>0){
            liveAll.setRoomManageStatus(1);
        }else {
            liveAll.setRoomManageStatus(0);

        }
        if(blackCount>0){
            liveAll.setRoomBlackStatus(1);
        }else {

            liveAll.setRoomBlackStatus(1);
        }
        double price = 0;
        //排名
        List<RankVo> rank = baseLiveDao.findRank();
        for (RankVo rankVo : rank) {
            if(rankVo.getLiveId()==liveAll.getId()){
                price = rankVo.getPrice();
            }else {
                liveAll.setRank(0);
            }
        }

        double[] arr = new double[rank.size()];
        for (int i = 0;i<rank.size();i++) {
            arr[i]=rank.get(i).getPrice();
        }
        //Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    double temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i = 0;i<arr.length;i++) {
            if(arr[i]==price){
                liveAll.setRank(i + 1);
            }
        }
        return success(liveAll);
    }

    @Override
    public RspResult findLiveByClassify(FindLiveByClassifyBo bo) {

        try {

            List<BaseLive> baseLives = baseLiveDao.findLiveByClassify(bo);
            int count = baseLiveDao.findLiveByClassifyCount(bo);
            return success(count,baseLives);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult findProveById(Long id) {
        BaseLive baseLive = baseLiveDao.findById(id);
        if (baseLive == null) {
            return error(1, "id绑定对象为空");
        }
        return success(baseLive);
    }

    //举报列表（后台）
    @Override
    public RspResult findReport(ReportFindBo bo) {
        List<ReportVo> list = baseReportDao.list(bo);
        int count = baseReportDao.count(bo);
        return success(count, list);
    }

    /**
     * 举报房间
     *
     * @param bo
     * @return
     */
    @Override
    public RspResult addReport(ReportAddBo bo) {
        BaseReport baseReport = new BaseReport();
        if(bo.getReportType()==100){

            Album byId = albumDao.findById(bo.getLiveId());
            baseReport.setLiveId(byId.getId());
            baseReport.setReportUserId(byId.getUserId());
        }else if(bo.getReportType()==200){
            BaseMember byId = baseMemberDao.findById(bo.getLiveId());
            baseReport.setLiveId(byId.getId());
            baseReport.setReportUserId(byId.getId());

        }else {
            Article byId = articleDao.findById(bo.getLiveId());
            baseReport.setLiveId(byId.getId());
            baseReport.setReportUserId(byId.getUserId());
        }
        baseReport.setUserId(bo.getUserId());
        baseReport.setReportType(bo.getReportType());
        baseReport.setType(bo.getType());

        if (StringUtil.isNotEmpty(bo.getContent())) {
            baseReport.setContent(bo.getContent());
        }

        baseReportDao.save(baseReport);

        return success();
    }

    @Override
    public RspResult giveGift(GiveGiftBo bo) {
        try {
            //判断用户的余额
            UserMoney userMoney = userMoneyDao.findByUserId(bo.getUserId());
            BaseGift gift = baseGiftDao.findById(bo.getId());
            BigDecimal cost = gift.getMoney().multiply(new BigDecimal(bo.getCount()));
            if(userMoney.getMoney().compareTo(cost) < 0){
                return error(1,"余额不足");
            }

            //先减去用户的余额
            BigDecimal balance = userMoney.getMoney().subtract(cost);
            userMoney.setMoney(balance);
            userMoney.setLastRevampTime(new Date().getTime());
            userMoneyDao.update(userMoney);

            //礼物送出
            BaseLiveGift baseLiveGift = new BaseLiveGift();
            baseLiveGift.setCount(bo.getCount());
            BaseLive baseLive = baseLiveDao.findById(bo.getLiveId());
            baseLiveGift.setGiftId(bo.getId());
            baseLiveGift.setLiveId(bo.getLiveId());
            baseLiveGift.setUserId(bo.getUserId());
            baseLiveGift.setLiveUserId(baseLive.getUserId());

            baseLiveGift.setMoney(cost);
            baseLiveGift.setPrice(BigDecimal.valueOf(gift.getPrice().doubleValue() * bo.getCount()));
            baseLiveGiftDao.save(baseLiveGift);

            //给主播增加经验值
            BaseMember baseMember = baseMemberDao.findByIdUser(baseLive.getUserId());
            int exp = baseMember.getExperience() + (gift.getExp() * bo.getCount());
            baseMember.setExperience(exp);
            baseMemberDao.update(baseMember);
            return success(baseLiveGift);
        } catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult exitPlay(JoinPlayBo bo) {
        //调redis去改变数据

        return success();
    }

    @Override
    public RspResult joinPlay(JoinPlayBo bo) {
        BaseMember baseMember = baseMemberDao.findById(bo.getUserId());
        if(baseMember==null){
            return errorMo();
        }
        BaseLive baseLive = baseLiveDao.findById(bo.getLiveId());
        int countResult = userFocusDao.findCountByIds(baseMember.getId(),baseLive.getUserId());
        if(countResult>0){
            baseMember.setFocusStatus(1);
        }else {
            baseMember.setFocusStatus(0);
        }
        int count = roomManageDao.findCountByPlayBo(bo);
        int blackCount  = liveBlacklistDao.findBlackCount(bo);

        if(count>0){
            baseMember.setRoomManageStatus(1);
        }else {
            baseMember.setRoomManageStatus(0);

        }
        if(blackCount>0){
            baseMember.setRoomBlackStatus(1);
        }else {

            baseMember.setRoomBlackStatus(1);
        }
        double price = 0;
        //排名
        List<RankVo> rank = baseLiveDao.findRank();
        for (RankVo rankVo : rank) {
            if(rankVo.getLiveId()==bo.getLiveId()){
                price = rankVo.getPrice();
            }
        }

        double[] arr = new double[rank.size()];
        for (int i = 0;i<rank.size();i++) {
            arr[i]=rank.get(i).getPrice();
        }
        Arrays.sort(arr);
        for (int i =0;i<arr.length;i++) {
            if(arr[i]==price){
                baseMember.setRank(i);
            }
        }
        FileCarBo fileCarBo = new FileCarBo();
        fileCarBo.setPurposeId(baseMember.getId());
        fileCarBo.setType("user");
        File file = fileService.findFile(fileCarBo);
        if(file!=null){
            baseMember.setUrl(file.getLocation());
        }
        //调redis去存储数据
        return success(baseMember);
    }

    @Override
    public RspResult beginPlay(BeginPlayBo bo) {
        BaseMember baseMember = baseMemberDao.findById(bo.getUserId());
        if (baseMember.getProveStatus() != 1) {
            return error(1, "您还不是主播");
        }
        BaseLive baseLive = baseLiveDao.findByUserId(baseMember.getId());

        if (StringUtil.isNotEmpty(bo.getTheme())) {
            baseLive.setTheme(bo.getTheme());
        }
        baseLive.setLiveStatus(1);
        baseLive.setTitle(bo.getTitle());
        baseLiveDao.update(baseLive);
        if (bo.getType() == 0) {
            //发送推送
        }
        //添加时间记录
        BaseLiveTime baseLiveTime = new BaseLiveTime();
        baseLiveTime.setStartTime(System.currentTimeMillis());
        baseLiveTime.setLiveId(baseLive.getId());
        baseLiveTimeDao.save(baseLiveTime);

        return success();
    }

    @Override
    public RspResult findLiveClassfiy() {
        List<LiveClassify> list = baseClassifyDao.listForLive();

        return success(list);
    }

    @Override
    public RspResult delLiveClassfiy(LiveClassifyDelBo bo) {
        liveClassifyDao.delById(bo.getId());

        return success();
    }

    @Override
    public RspResult updLiveClassfiy(LiveClassifyUpdBo bo) {
        LiveClassify liveClassify = liveClassifyDao.findById(bo.getId());
        liveClassify.setName(bo.getName());
        liveClassify.setLastRevampTime(System.currentTimeMillis());
        liveClassifyDao.update(liveClassify);

        return success(liveClassify);
    }

    @Override
    public RspResult addLiveClassfiy(LiveClassifyAddBo bo) {
        LiveClassify liveClassify = new LiveClassify();
        liveClassify.setName(bo.getName());
        liveClassifyDao.save(liveClassify);

        return success(liveClassify);
    }

    //审核主播
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RspResult updProve(ProveUpdBo bo) {
        HostProve hostProve = hostProveDao.findById(bo.getId());
        if (hostProve == null) {

            return error(1, "id绑定对象为空");
        }
        if (bo.getStatus() == 0) {
            //认证通过
            BaseMember baseMember = baseMemberDao.findById(hostProve.getUserId());
            baseMember.setProveStatus(1);
            baseMember.setLastRevampTime(System.currentTimeMillis());
            baseMember.setExperience(0);
            baseMemberDao.update(baseMember);
            BaseLive baseLive = new BaseLive();
            baseLive.setUserId(baseMember.getId());
            baseLive.setLiveNumber(0);
            baseLive.setLiveStatus(0);
            baseLive.setCode(0);
            baseLive.setClassifyId(bo.getClassifyId());
            baseLive.setPersonCount(0);
            baseLive.setLevel(1);
            baseLive.setTimeSum((double) 0);
            baseLiveDao.save(baseLive);
            CreateLiveChannel createLiveChannel = YXUtils.createLive(baseMember.getId()+"",baseMember.getUsername());
            if(createLiveChannel.getBaseChannel().getCid()==null){
                return error(1,"直播间或频道申请失败！");
            }
            BaseLiveChannel baseLiveChannel = new BaseLiveChannel();
            baseLiveChannel.setBroadcasturl(createLiveChannel.getLive().getBroadcasturl());
            baseLiveChannel.setCid(createLiveChannel.getBaseChannel().getCid());
            baseLiveChannel.setHlsPullUrl(createLiveChannel.getBaseChannel().getHlsPullUrl());
            baseLiveChannel.setHttpPullUrl(createLiveChannel.getBaseChannel().getHttpPullUrl());
            baseLiveChannel.setLiveId(baseLive.getId());
            baseLiveChannel.setUserId(baseMember.getId());
            baseLiveChannel.setName(createLiveChannel.getBaseChannel().getName());
            baseLiveChannel.setRtmpPullUrl(createLiveChannel.getBaseChannel().getRtmpPullUrl());
            baseLiveChannel.setRoomId(createLiveChannel.getLive().getRoomId());
            baseLiveChannel.setPushUrl(createLiveChannel.getBaseChannel().getPushUrl());
            liveChannelDao.save(baseLiveChannel);
            hostProve.setFlowStatus(1);
            hostProveDao.update(hostProve);

            return success();
        } else if (bo.getStatus() == 1) {
            //认证失败
            hostProve.setFlowStatus(2);
            hostProveDao.update(hostProve);
            return success();
        } else {
            return error(1, "无此操作状态");
        }


    }

    //验证认证主播验证码
    private Boolean checkProve(String code, String phone) {
        Object o = CacheUtils.get("sms_prove" + phone);
        if (!code.equals(o + "")) {
            return true;
        }
        return false;
    }

    @Override
    public RspResult addProve(ProveAddBo bo) {
        BaseMember count = baseMemberDao.findById(bo.getUserId());
        if(count==null){
            return errorMo();
        }
        int countByUpd = hostProveDao.findCountByUpd(bo.getUserId());
        if(countByUpd>0){
            return error(1,"已提交,请耐心等待");
        }

        HostProve hostProve = new HostProve();
        hostProve.setCardClassify(bo.getCardClassify());
        hostProve.setCardNumber(bo.getCardNumber());
        hostProve.setFlowStatus(0);
        hostProve.setUserId(bo.getUserId());
        hostProve.setName(bo.getName());
        hostProveDao.save(hostProve);

        return success(hostProve);
    }

    @Override
    public RspResult getTotalThisEarnings(JoinPlayBo bo) {
        Map<String,Object> map = new HashMap<>();
        //获取本场总收入
        BaseLiveTime baseLiveTime = baseLiveTimeDao.findByLiveId(bo.getLiveId());
        if(baseLiveTime == null){
            return error("你还没有开始直播哦");
        }
        map.put("totalEarnings",baseLiveDao.getGiftMoney(bo,baseLiveTime.getStartTime()));
        //本场直播时间
        Long liveTime = 0L;
        if(baseLiveTime.getEndTime() == null){
            liveTime = new Date().getTime() - baseLiveTime.getStartTime();
        }else{
            liveTime = baseLiveTime.getEndTime() - baseLiveTime.getStartTime();
        }
        map.put("liveTime",liveTime);
        //本场参与人数

        //本场获得的经验值
        BaseMember baseMember = baseMemberDao.findByIdUser(bo.getUserId());
        BaseGrade grade = baseGradeDao.findByExp(baseMember.getExperience());
        int time = (int)(liveTime/6000);
        int exp = baseMember.getExperience() + time/grade.getLiveTime()*grade.getExp();
        baseMember.setExperience(exp);
        baseMemberDao.update(baseMember);
        return success(map);
    }

    @Override
    public RspResult rankByLiveRoom(Long liveId) {
        Map<String,Object> map = new HashMap<>();
        List<RankVo> rank = baseLiveDao.findRankByPrice();
        if(rank.size() <= 0){
            map.put("rank",1);
            return success(map);
        }
        for(int i = 0;i<rank.size(); i++){
            if(rank.get(i).getLiveId().equals(liveId)){
                map.put("rank",i + 1);
                break;
            }
        }
        return success(map);
    }

    @Override
    public RspResult save(BaseMember entity) {
        return null;
    }

    @Override
    public BaseMember findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseMember entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
