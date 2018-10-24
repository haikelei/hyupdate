package com.empathy.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.album.bo.*;
import com.empathy.domain.baseRecording.bo.*;
import com.empathy.domain.comments.bo.CommentsAddBo;
import com.empathy.domain.comments.bo.CommentsFindBo;
import com.empathy.domain.live.bo.FindLiveByClassifyBo;
import com.empathy.service.IAlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/16.
 */
@RestController
@RequestMapping("/album")
@Api(tags = {"专辑接口"})
public class AlbumController {

    @Autowired
    private IAlbumService albumService;


    @ApiOperation(value = "添加", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addAlbum", method = RequestMethod.POST)
    public RspResult addAlbum(AlbumAddBo bo) {


        return albumService.addAlbum(bo);
    }

    @ApiOperation(value = "修改", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updAlbum", method = RequestMethod.POST)
    public RspResult updAlbum(AlbumUpdBo bo) {

        return albumService.updAlbum(bo);

    }

    @ApiOperation(value = "删除", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delAlbum", method = RequestMethod.POST)
    public RspResult delAlbum(AlbumDelBo bo) {


        return albumService.delAlbum(bo);

    }

    @ApiOperation(value = "根据id查找", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumById", method = RequestMethod.POST)
    public RspResult findAlbumById(AlbumFindByIdBo bo) {


        return albumService.findAlbumById(bo);

    }

    @ApiOperation(value = "根据用户id查找", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumByUserId", method = RequestMethod.POST)
    public RspResult findAlbumByUserId(AlbumFindByUserIdBo bo) {


        return albumService.findAlbumByUserId(bo);

    }

    @ApiOperation(value = "根据分类id查找专辑", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumByClassifyId", method = RequestMethod.POST)
    public RspResult findAlbumByClassifyId(AlbumFindByClassifyIdBo bo) {


        return albumService.findAlbumByClassifyId(bo);

    }

    @ApiOperation(value = "存储单个录音", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addRecording", method = RequestMethod.POST)
    public RspResult addRecording(RecordingAddBo bo) {


        return albumService.addRecording(bo);

    }

    @ApiOperation(value = "上下架录音", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/freezeRecording", method = RequestMethod.POST)
    public RspResult freezeRecording(RecordingFreezeBo bo) {


        return albumService.freezeRecording(bo);

    }
    @ApiOperation(value = "录音上一首下一首，文档我看了，播放地址是你们根据那个唯一标识拿的，差不多这个样子", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/recordPlay", method = RequestMethod.POST)
    public RspResult recordPlay(RecordPlayBo bo) {


        return albumService.recordPlay(bo);

    }

    @ApiOperation(value = "修改录音标题", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updRecording", method = RequestMethod.POST)
    public RspResult updRecording(RecordingUpdBo bo) {

        return albumService.updRecording(bo);
    }


    @ApiOperation(value = "查看专辑下所有录音", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findRecording", method = RequestMethod.POST)
    public RspResult findRecording(RecordingFindBo bo) {


        return albumService.findRecording(bo);

    }

    @ApiOperation(value = "查看主播所有录音", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findRecordingByUser", method = RequestMethod.POST)
    public RspResult findRecordingByUser(RecordingFindBo bo) {


        return albumService.findRecordingByUser(bo);

    }

    @ApiOperation(value = "后台查看专辑下所有录音", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findRecordingForAccount", method = RequestMethod.POST)
    public RspResult findRecordingForAccount(RecordingFindBo bo) {


        return albumService.findRecordingForAccount(bo);

    }

    @ApiOperation(value = "后台查看专辑下所有录音的个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findRecordingCount", method = RequestMethod.POST)
    public String findRecordingCount(RecordingFindBo bo) {


        return albumService.findRecordingCount(bo);

    }

    @ApiOperation(value = "增加录音点击量", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addCleckNumber", method = RequestMethod.POST)
    public RspResult addCleckNumber(RecordingAddNumberBo bo) {


        return albumService.addClickNumber(bo);

    }

    @ApiOperation(value = "增加录音播放量", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addPlayNumber", method = RequestMethod.POST)
    public RspResult addPlayNumber(RecordingAddNumberBo bo) {


        return albumService.addPlayNumber(bo);

    }


    @ApiOperation(value = "增加对录音的评论（根据传的类型可选择录音评论或是听友评论）", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addComments", method = RequestMethod.POST)
    public RspResult addComments(CommentsAddBo bo) {


        return albumService.addComments(bo);

    }

    @ApiOperation(value = "查看评论（根据传的类型可查看录音评论或是听友评论）", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findComments", method = RequestMethod.POST)
    public RspResult findComments(CommentsFindBo bo) {


        return albumService.findComments(bo);

    }

    @ApiOperation(value = "后台查看所有的专辑", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumForAccount", method = RequestMethod.POST)
    public RspResult findAlbumForAccount(FindAlbumForAccountBo bo) {


        return albumService.findAlbumForAccount(bo);

    }

    @ApiOperation(value = "后台查看所有的专辑的个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumForAccountCount", method = RequestMethod.POST)
    public String findAlbumForAccountCount(FindAlbumForAccountBo bo) {


        return albumService.findAlbumForAccountCount(bo);

    }

    @ApiOperation(value = "通过专辑id查看专辑金额", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumMoney", method = RequestMethod.POST)
    public RspResult findAlbumMoney(Long id) {


        return albumService.findAlbumMoney(id);

    }

    @ApiOperation(value = "通过分类di查看专辑列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumByClassify", method = RequestMethod.POST)
    public RspResult findAlbumByClassify(FindLiveByClassifyBo bo) {


        return albumService.findAlbumByClassify(bo);

    }


    @ApiOperation(value = "精品标题下方列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumByBest", method = RequestMethod.POST)
    public RspResult findAlbumByBest(AlbumFindBestBo bo) {


        return albumService.findAlbumByBest(bo);

    }

    @ApiOperation(value = "后台增加精品页面", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addAlbumByBest", method = RequestMethod.POST)
    public RspResult addAlbumByBest(Long albumId,Long id) {


        return albumService.addAlbumByBest(albumId,id);

    }

    @ApiOperation(value = "精品页面（点击进去开始访问这个接口，默认给标题加上专辑列表，一个列表下三个专辑）", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findBestAlbumForMain", method = RequestMethod.POST)
    public RspResult findBestAlbumForMain() {


        return albumService.findBestAlbumForMain();

    }


    @ApiOperation(value = "精品标题列表（例如大咖说）", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAlbumByBestTitle", method = RequestMethod.POST)
    public RspResult findAlbumByBestTitle() {


        return albumService.findAlbumByBestTitle();

    }

    @ApiOperation(value = "热门搜索", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findHotStr", method = RequestMethod.POST)
    public RspResult findHotStr() {


        return albumService.findHotStr();

    }

    @ApiOperation(value = "我购买的音频", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMyBuy", method = RequestMethod.POST)
    public RspResult findMyBuy(FindBuyBo buyBo) {


        return albumService.findMyBuy(buyBo);

    }

    @ApiOperation(value = "我购买的音频下面的录音文件", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMyBuyRecording", method = RequestMethod.POST)
    public RspResult findMyBuyRecording(RecordingFindBo bo) {


        return albumService.findMyBuyRecording(bo);

    }
    @ApiOperation(value = "购买音频", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addMyBuy", method = RequestMethod.POST)
    public RspResult addMyBuy(BuyAddBo bo) {


        return albumService.addMyBuy(bo);

    }

    @ApiOperation(value = "音频上传接口", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addVideo", method = RequestMethod.POST)
    public RspResult addVideo(RecordAddUrlBo bo) {
        return albumService.addVideo(bo);
    }

    @ApiOperation(value = "音频上传接口", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/getPayNumber", method = RequestMethod.POST)
    public Object getPayNumber(Long albumId) {
        return albumService.getPayNumber(albumId);
    }

}
