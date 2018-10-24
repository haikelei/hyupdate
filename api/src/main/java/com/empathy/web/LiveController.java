package com.empathy.web;

import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.SmsBo;
import com.empathy.domain.album.bo.RecordAddUrlBo;
import com.empathy.domain.baseReport.bo.ReportAddBo;
import com.empathy.domain.baseReport.bo.ReportFindBo;
import com.empathy.domain.live.bo.*;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.domain.user.bo.ProveAddBo;
import com.empathy.domain.user.bo.ProveUpdBo;
import com.empathy.service.IBaseLiveSerivce;
import com.empathy.utils.SMSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/18.
 */
@RestController
@RequestMapping("/live")
@Api(tags = {"直播间接口"})
public class LiveController {

    @Autowired
    private IBaseLiveSerivce baseLiveSerivce;



    @ApiOperation(value = "查看最新得直播间 6人为单位分页", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findLiveForNew", method = RequestMethod.POST)
    public RspResult findLiveForNew(PageBo bo) {
        return baseLiveSerivce.findLiveForNew(bo);
    }


    @ApiOperation(value = "查看最热得直播间 6人为单位分页", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findLiveForHot", method = RequestMethod.POST)
    public RspResult findLiveForHot(PageBo bo) {
        return baseLiveSerivce.findLiveForHot(bo);
    }


    @ApiOperation(value = "查看预约得直播间 6人为单位分页", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findLiveForNotLive", method = RequestMethod.POST)
    public RspResult findLiveForAppointment(PageBo bo) {
        return baseLiveSerivce.findLiveForAppointment(bo);
    }



    @ApiOperation(value = "认证主播", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addProve", method = RequestMethod.POST)
    public RspResult addProve(ProveAddBo bo) {


        return baseLiveSerivce.addProve(bo);
    }


    @ApiOperation(value = "主播开启预约", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/beginAppointment", method = RequestMethod.POST)
    public RspResult beginAppointment(BeginAppointmentBo bo) {


        return baseLiveSerivce.beginAppointment(bo);
    }



    @ApiOperation(value = "根据id查看直播间属性", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findProveById", method = RequestMethod.POST)
    public RspResult findProveById(Long id) {


        return baseLiveSerivce.findProveById(id);
    }


    @ApiOperation(value = "认证主播发送短信验证码", httpMethod = "GET", response = String.class)
    @RequestMapping(value = "/get/changeDealPassword", method = RequestMethod.GET)
    public RspResult changeDealPassword(SmsBo bo) {
        SMSUtils.beProve(bo.getPhone());
        return new RspResult("发送成功", 200);
    }


    @ApiOperation(value = "审核主播", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updProve", method = RequestMethod.POST)
    public RspResult updProve(ProveUpdBo bo) {
        return baseLiveSerivce.updProve(bo);
    }




    @ApiOperation(value = "增加主播房间分类", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addLiveClassfiy", method = RequestMethod.POST)
    public RspResult addLiveClassfiy(LiveClassifyAddBo bo) {
        return baseLiveSerivce.addLiveClassfiy(bo);
    }

    @ApiOperation(value = "修改主播房间分类名", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updLiveClassfiy", method = RequestMethod.POST)
    public RspResult updLiveClassfiy(LiveClassifyUpdBo bo) {
        return baseLiveSerivce.updLiveClassfiy(bo);
    }


    @ApiOperation(value = "删除主播房间分类名", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delLiveClassfiy", method = RequestMethod.POST)
    public RspResult delLiveClassfiy(LiveClassifyDelBo bo) {
        return baseLiveSerivce.delLiveClassfiy(bo);
    }


    @ApiOperation(value = "查看主播房间分类列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findLiveClassfiy", method = RequestMethod.POST)
    public RspResult findLiveClassfiy() {
        return baseLiveSerivce.findLiveClassfiy();
    }


    @ApiOperation(value = "主播开播", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/beginPlay", method = RequestMethod.POST)
    public RspResult beginPlay(BeginPlayBo bo) {
        return baseLiveSerivce.beginPlay(bo);
    }

    @ApiOperation(value = "主播下播", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/closePlay", method = RequestMethod.POST)
    public RspResult closePlay(Long liveId) {
        return baseLiveSerivce.closePlay(liveId);
    }


    @ApiOperation(value = "本场收入", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/moneyForLive", method = RequestMethod.POST)
    public RspResult moneyForLive(Long liveId) {
        return baseLiveSerivce.moneyForLive(liveId);
    }



    @ApiOperation(value = "用户进入直播间", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/joinPlay", method = RequestMethod.POST)
    public RspResult joinPlay(JoinPlayBo bo) {
        return baseLiveSerivce.joinPlay(bo);
    }


    @ApiOperation(value = "用户退出直播间", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/exitPlay", method = RequestMethod.POST)
    public RspResult exitPlay(JoinPlayBo bo) {
        return baseLiveSerivce.exitPlay(bo);
    }


    @ApiOperation(value = "送礼物", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/giveGift", method = RequestMethod.POST)
    public RspResult giveGift(GiveGiftBo bo) {
        return baseLiveSerivce.giveGift(bo);
    }

    @ApiOperation(value = "查看大咖列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findGiftBest", method = RequestMethod.POST)
    public RspResult findGiftBest(GiftBestBo bo) {
        return baseLiveSerivce.findGiftBest(bo);
    }


    @ApiOperation(value = "添加举报,已解决，线上数据库缺少字段（直接复用到专辑上，把liveId这个键传专辑id）", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addReport", method = RequestMethod.POST)
    public RspResult addReport(ReportAddBo bo) {
        return baseLiveSerivce.addReport(bo);
    }


    @ApiOperation(value = "查看举报", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findReport", method = RequestMethod.POST)
    public RspResult findReport(ReportFindBo bo) {
        return baseLiveSerivce.findReport(bo);
    }



    @ApiOperation(value = "根据分类查看直播间", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findLiveByClassify", method = RequestMethod.POST)
    public RspResult findLiveByClassify(FindLiveByClassifyBo bo) {
        return baseLiveSerivce.findLiveByClassify(bo);
    }



    @ApiOperation(value = "根据id拿到直播间的详情加上推拉流地址和频道的内容", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findLiveForDetail", method = RequestMethod.POST)
    public RspResult findLiveForDetail(FindLiveForDetailBo bo) {
        return baseLiveSerivce.findLiveForDetail(bo);
    }



    @ApiOperation(value = "首页推荐的4个直播间", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findLiveForFour", method = RequestMethod.POST)
    public RspResult findLiveForFour(FindLiveForFourBo bo) {
        return baseLiveSerivce.findLiveForFour(bo);
    }


    @ApiOperation(value = "搜索动态专辑和直播间", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/searchAll", method = RequestMethod.POST)
    public RspResult searchAll(SearchAllBo bo) {
        return baseLiveSerivce.searchAll(bo);
    }


    @ApiOperation(value = "添加房管", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addRoomManage", method = RequestMethod.POST)
    public RspResult addRoomManage(RoomAddManageBo bo) {
        return baseLiveSerivce.addRoomManage(bo);
    }

    @ApiOperation(value = "解除房管", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updRoomManage", method = RequestMethod.POST)
    public RspResult updRoomManage(RoomUpddManageBo bo) {
        return baseLiveSerivce.updRoomManage(bo);
    }


    @ApiOperation(value = "添加禁言", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addFreeze", method = RequestMethod.POST)
    public RspResult addFreeze(FreezeAddBo bo) {
        return baseLiveSerivce.addFreeze(bo);
    }

    @ApiOperation(value = "解除禁言", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updFreeze", method = RequestMethod.POST)
    public RspResult updFreeze(FreezeAddBo bo) {
        return baseLiveSerivce.updFreeze(bo);
    }

    @ApiOperation(value = "我的房管列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findManageList", method = RequestMethod.POST)
    public RspResult findManageList(ManageListBo bo) {
        return baseLiveSerivce.findManageList(bo);
    }

    @ApiOperation(value = "直播间拉黑用户", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addBlacklist", method = RequestMethod.POST)
    public RspResult addBlacklist(BlacklistAddBo bo) {
        return baseLiveSerivce.addBlacklist(bo);
    }

    @ApiOperation(value = "直播间取消拉黑用户", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updBlacklist", method = RequestMethod.POST)
    public RspResult updBlacklist(BlacklistAddBo bo) {
        return baseLiveSerivce.updBlacklist(bo);
    }


    @ApiOperation(value = "直播间查看拉黑用户列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findBlacklist", method = RequestMethod.POST)
    public RspResult findBlacklist(BlackListBo bo) {
        return baseLiveSerivce.findBlacklist(bo);
    }


    @ApiOperation(value = "直播间礼物排行榜", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/giftList", method = RequestMethod.POST)
    public RspResult giftList(GifiListBo bo) {
        return baseLiveSerivce.giftList(bo);
    }


    @ApiOperation(value = "更新终端用户,也就是传文件需要请求的网易云接口得到的token和accid", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/getTokenAndAccid", method = RequestMethod.POST)
    public RspResult getTokenAndAccid(TokenAccidBo bo) {
        return baseLiveSerivce.getTokenAndAccid(bo);
    }

    @ApiOperation(value = "主播本场收入", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/getTotalThisEarnings", method = RequestMethod.POST)
    public RspResult getTotalThisEarnings(JoinPlayBo bo){
        return baseLiveSerivce.getTotalThisEarnings(bo);
    }

    @ApiOperation(value = "主播直播间排名", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/rankByLiveRoom", method = RequestMethod.POST)
    public RspResult rankByLiveRoom(Long liveId){
        return baseLiveSerivce.rankByLiveRoom(liveId);
    }







}
