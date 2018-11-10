package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.album.*;
import com.empathy.domain.album.bo.*;
import com.empathy.domain.album.vo.AlbumAccountVo;
import com.empathy.domain.album.vo.AlbumBestVo;
import com.empathy.domain.album.vo.AlbumVo;
import com.empathy.domain.baseRecording.BaseRecording;
import com.empathy.domain.baseRecording.bo.*;
import com.empathy.domain.baseRecording.vo.RecordPlayVo;
import com.empathy.domain.bidding.File;
import com.empathy.domain.comments.Comments;
import com.empathy.domain.comments.TbComment;
import com.empathy.domain.comments.bo.CommentsAddBo;
import com.empathy.domain.comments.bo.CommentsFindBo;
import com.empathy.domain.enumer.CommentType;
import com.empathy.domain.file.bo.FileCarBo;
import com.empathy.domain.live.bo.FindLiveByClassifyBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserMember;
import com.empathy.domain.user.UserMoney;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IAlbumService;
import com.empathy.utils.StringUtil;
import com.empathy.utils.YXUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MI on 2018/4/16.
 */
@Service
public class AlbumService extends AbstractBaseService implements IAlbumService {

    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private AlbumMoneyDao albumMoneyDao;

    @Autowired
    private BaseRecordingDao baseRecordingDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BaseNiceDao baseNiceDao;
    @Autowired
    private BaseNiceTitleDao baseNiceTitleDao;
    @Autowired
    private HotStrDao hotStrDao;

    @Autowired
    private UserMemberDao userMemberDao;
    @Autowired
    private BaseMemberDao baseMemberDao;
    @Autowired
    private UserMoneyDao userMoneyDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private TbCommentDao tbCommentDao;


    @Override
    public RspResult recordPlay(RecordPlayBo bo) {
        BaseRecording baseRecording = baseRecordingDao.findById(bo.getId());
        if(baseRecording==null){
            return errorMo();
        }
        int result = 0;
        Long albumId = baseRecording.getAlbumId();
        AlbumVo album = albumDao.findAlbumById(albumId);
        UserMember byUserId = userMemberDao.findByUserId(bo.getUserId());
        BaseMember baseMember = baseMemberDao.findByIdUser(baseRecording.getUserId());
        List<BaseRecording> list = baseRecordingDao.findByAlbumId(albumId);

        int countInfo =0;
        for (int h=0;h<list.size();h++){
            if(list.get(h).getId()==baseRecording.getId()){
                countInfo = h;
            }
        }

        boolean falg = false;
        if(album.getBumMoney() != null){
            falg = album.getBumMoney().compareTo(new BigDecimal("0")) > 0;
        }
        if(bo.getType()==0) {
            if (album.getType() == 300 && falg) {
                if (album.getChargeStatus() == 0 && byUserId != null) {

                } else {
                    int count = albumDao.findMyBuy(bo.getUserId(), baseRecording.getId());
                    if (count < 1) {
                        return error(1, "尚未购买此音频");
                    }
                }

            }else if(album.getType()==200 && falg){
                if (album.getChargeStatus() == 0 && byUserId != null) {

                } else if (album.getChargeStatus()==1&&countInfo<=album.getBumSet()) {

                } else if (album.getChargeStatus() == 0 &&countInfo<=album.getBumSet()) {

                }else {

                    int count = albumDao.findMyBuy(bo.getUserId(), baseRecording.getId());
                    if (count < 1) {
                        return error(1, "尚未购买此音频");
                    }

                }

            }

            String url =  YXUtils.getVoice(Long.valueOf(baseRecording.getSign()));
            baseRecording.setPlayNumber(baseRecording.getPlayNumber()+1);
            baseRecordingDao.update(baseRecording);



            RecordPlayVo recordPlayVo = new RecordPlayVo();
            recordPlayVo.setId(baseRecording.getId());
            recordPlayVo.setUserId(baseMember.getId());
            recordPlayVo.setUsername(baseMember.getUsername());
            recordPlayVo.setPlayUrl(url);
            recordPlayVo.setAlbumId(album.getId());
            recordPlayVo.setAlbumName(album.getAlbumName());
            recordPlayVo.setAlbumDetail(album.getDetail());
            recordPlayVo.setPlayNumber(baseRecording.getPlayNumber());
            recordPlayVo.setRecordingName(baseRecording.getTitle());

            // album
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setPurposeId(album.getId());
            fileCarBo.setType("album");
            File file = fileService.findFile(fileCarBo);
            if(file!=null){
                recordPlayVo.setAlbumUrl(file.getLocation());
            }

            return success(recordPlayVo);
        }


        if(bo.getType()==1){
            //上一首
           for (int j =0;j<list.size();j++){
               if(list.get(j).getId()==bo.getId()){
                   if(j==0){
                        return error(2,"没有上一首！");
                   }else {
                       result=j-1;
                   }
               }

           }
           //查看是否有购买
            if (album.getType() == 300 && falg) {
                if (album.getChargeStatus() == 0 && byUserId != null) {

                } else {

                    int count1 = albumDao.findMyBuy(bo.getUserId(),list.get(result).getId());
                    if(count1<1){
                        return error(1,"尚未购买此音频");
                    }
                }


            }else if(album.getType()==200 && falg){
                if (album.getChargeStatus() == 0 && byUserId != null) {

                } else if (album.getChargeStatus()==1&&result<=album.getBumSet()) {

                } else if (album.getChargeStatus() == 0&&result<=album.getBumSet()) {

                }else {

                    int count1 = albumDao.findMyBuy(bo.getUserId(),list.get(result).getId());
                    if(count1<1){
                        return error(1,"尚未购买此音频");
                    }
                }

            }


            String url1 =  YXUtils.getVoice(Long.valueOf(list.get(result).getSign()));

            baseRecording.setPlayNumber(baseRecording.getPlayNumber()+1);
            baseRecordingDao.update(baseRecording);

            RecordPlayVo recordPlayVo = new RecordPlayVo();
            recordPlayVo.setId(list.get(result).getId());
            recordPlayVo.setUserId(baseMember.getId());
            recordPlayVo.setUsername(baseMember.getUsername());
            recordPlayVo.setPlayUrl(url1);
            recordPlayVo.setAlbumId(album.getId());
            recordPlayVo.setAlbumName(album.getAlbumName());
            recordPlayVo.setAlbumDetail(album.getDetail());
            recordPlayVo.setPlayNumber(list.get(result).getPlayNumber());
            recordPlayVo.setRecordingName(list.get(result).getTitle());

            // album
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setPurposeId(album.getId());
            fileCarBo.setType("album");
            File file = fileService.findFile(fileCarBo);
            if(file!=null){
                recordPlayVo.setAlbumUrl(file.getLocation());
            }

           return success(recordPlayVo);


        }else{
            //下一首

            for (int i =0;i<list.size();i++){
                if(list.get(i).getId()==bo.getId()){
                    if(i+1>=list.size()){
                        return error(2,"没有下一首！");
                    }else {
                        result=i+1;
                    }

                }

            }
            //查看是否有购买
            if (album.getType() == 300) {
                if (album.getChargeStatus() == 0 && byUserId != null) {

                } else {

                    int count1 = albumDao.findMyBuy(bo.getUserId(),list.get(result).getId());
                    if(count1<1){
                        return error(1,"尚未购买此音频");
                    }

                }


            }else if(album.getType()==200){
                if (album.getChargeStatus() == 0 && byUserId != null) {

                } else if (album.getChargeStatus()==1&&result<=album.getBumSet()) {

                }else {

                    int count1 = albumDao.findMyBuy(bo.getUserId(),list.get(result).getId());
                    if(count1<1){
                        return error(1,"尚未购买此音频");
                    }
                }

            }
            String url1 =  YXUtils.getVoice(Long.valueOf(list.get(result).getSign()));

            baseRecording.setPlayNumber(baseRecording.getPlayNumber()+1);
            baseRecordingDao.update(baseRecording);

            RecordPlayVo recordPlayVo = new RecordPlayVo();
            recordPlayVo.setId(list.get(result).getId());
            recordPlayVo.setUserId(baseMember.getId());
            recordPlayVo.setUsername(baseMember.getUsername());
            recordPlayVo.setPlayUrl(url1);
            recordPlayVo.setAlbumId(album.getId());
            recordPlayVo.setAlbumName(album.getAlbumName());
            recordPlayVo.setAlbumDetail(album.getDetail());
            recordPlayVo.setPlayNumber(list.get(result).getPlayNumber());
            recordPlayVo.setRecordingName(list.get(result).getTitle());

            // album
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setPurposeId(album.getId());
            fileCarBo.setType("album");
            File file = fileService.findFile(fileCarBo);
            if(file!=null){
                recordPlayVo.setAlbumUrl(file.getLocation());
            }

            return success(recordPlayVo);


        }

    }

    @Override
    public RspResult findRecordingByUser(RecordingFindBo bo) {
        try {

            List<BaseRecording> list = baseRecordingDao.findRecordingByUser(bo);
            int count = baseRecordingDao.findRecordingCountByUser(bo);

            return success(count,list);
        }catch (Exception e){
            return errorNo();
        }
    }

    @Override
    public RspResult findBestAlbumForMain() {


        try {
                List<AlbumVo> vo1= baseNiceDao.findAlbumBest();
                List<AlbumVo> vo2= baseNiceDao.findAlbumBest1();
                List<AlbumVo> vo3= baseNiceDao.findAlbumBest2();
                List<AlbumBestVo> list = new ArrayList<>();
                List<BaseNiceTitle> list1 = baseNiceTitleDao.list();
                for (int i =0;i<list1.size();i++) {
                AlbumBestVo albumBestVo = new AlbumBestVo();
                if(list1.get(i).getId()==1){

                    for(AlbumVo albumVo1:vo1){
                        Integer payNumber1 = baseRecordingDao.getPayNumber(albumVo1.getId());
                        if(payNumber1 != null){
                            albumVo1.setPayNumber(payNumber1);
                        }else{
                            albumVo1.setPayNumber(0);
                        }
                    }
                    albumBestVo.setAlbumVo(vo1);
                    albumBestVo.setTitle(list1.get(i).getName());
                    albumBestVo.setTitleId(list1.get(i).getId());
                }
                if(list1.get(i).getId()==2){
                    for(AlbumVo albumVo2:vo2){
                        Integer payNumber2 = baseRecordingDao.getPayNumber(albumVo2.getId());
                        if(payNumber2 != null){
                            albumVo2.setPayNumber(payNumber2);
                        }else{
                            albumVo2.setPayNumber(0);
                        }
                    }
                    albumBestVo.setAlbumVo(vo2);
                    albumBestVo.setTitle(list1.get(i).getName());
                    albumBestVo.setTitleId(list1.get(i).getId());
                }

                if(list1.get(i).getId()==3){
                    for(AlbumVo albumVo3:vo3){
                        Integer payNumber3 = baseRecordingDao.getPayNumber(albumVo3.getId());
                        if(payNumber3 != null){
                            albumVo3.setPayNumber(payNumber3);
                        }else{
                            albumVo3.setPayNumber(0);
                        }
                    }
                    albumBestVo.setAlbumVo(vo3);
                    albumBestVo.setTitle(list1.get(i).getName());
                    albumBestVo.setTitleId(list1.get(i).getId());
                }
                    list.add(albumBestVo);
              }

            int count = 3;
            return success(count,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();

        }
    }

    @Override
    public RspResult addVideo(RecordAddUrlBo bo) {
        BaseRecording byId = baseRecordingDao.findById(bo.getId());
        if(byId==null){
            return errorMo();
        }
        if(!StringUtil.isNotEmpty(bo.getSign())){
            return error(1,"缺少录音标识");
        }
        byId.setSign(bo.getSign());
        baseRecordingDao.update(byId);
        return success(byId);
    }

    @Override
    public RspResult addMyBuy(BuyAddBo bo) {
        BaseMember byId = baseMemberDao.findById(bo.getId());
        if(byId==null){
            return errorMo();
        }
        UserMoney byUserId = userMoneyDao.findByUserId(bo.getId());
        AlbumVo albumById = albumDao.findAlbumById(bo.getAlbumId());
        if(byUserId == null || byUserId.getMoney()==null){
            return error(1,"金额不足");
        }
        if(albumById != null && byUserId.getMoney().doubleValue()<albumById.getBumMoney().doubleValue()){
            return error(1,"金额不足");
        }

        byUserId.setMoney(BigDecimal.valueOf(byUserId.getMoney().doubleValue()-albumById.getBumMoney().doubleValue()));
        userMoneyDao.update(byUserId);


        TbUserRecord tbUserRecord = new TbUserRecord();
        tbUserRecord.setAlbumId(bo.getAlbumId());
        tbUserRecord.setRecordId(bo.getRecordId());
        tbUserRecord.setUserId(bo.getId());
        albumDao.addMyBuy(tbUserRecord);
//        BaseRecording byId1 = baseRecordingDao.findById(bo.getRecordId());
//        String voice = YXUtils.getVoice(Long.valueOf(byId1.getSign()));
        return success();
    }

    @Override
    public RspResult findMyBuyRecording(RecordingFindBo bo) {
        AlbumVo albumVo = albumDao.findAlbumById(bo.getAlbumId());
        List<BaseRecording> list = baseRecordingDao.listForAccount(bo);
        List<String> recordings = albumDao.findRecording(bo);

        if(albumVo.getType()==100){
            for (int i=0;i<list.size();i++){

                list.get(i).setMoney(new BigDecimal(0));
            }
        }
        if(albumVo.getType()==200){
            int bugSet = albumVo.getBumSet();
            for (int i=0;i<list.size();i++){
                if(i<=bugSet){
                    list.get(i).setMoney(new BigDecimal(0));
                }else {
                    for (String temp : recordings) {
                        if (temp.equals(list.get(i).getId())) {
                            list.get(i).setBuyStatus(1);
                        }}
                    list.get(i).setMoney(albumVo.getBumMoney());
                }
            }
        }
        if(albumVo.getType()==300){
            int bugSet = albumVo.getBumSet();
            for (int i=0;i<list.size();i++){
                for (String temp : recordings) {
                    if (temp.equals(list.get(i).getId())) {
                        list.get(i).setBuyStatus(1);
                    }}

                    list.get(i).setMoney(albumVo.getBumMoney());
            }
        }



        int count = baseRecordingDao.countForAccount(bo.getAlbumId());

        return success(count, list);
    }

    @Override
    public RspResult findMyBuy(FindBuyBo buyBo) {
        try {
            List<AlbumVo> albums = albumDao.findAlbumByBuy(buyBo);
            for(AlbumVo albumVo:albums){
                Integer payNumber = baseRecordingDao.getPayNumber(albumVo.getId());
                if(payNumber != null){
                    albumVo.setPayNumber(payNumber);
                }else{
                    albumVo.setPayNumber(0);
                }
            }
            int count = albumDao.findAlbumByBuyCount(buyBo);
            return success(count,albums);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }

    }

    @Override
    public RspResult findHotStr() {
        try {

            List<HotStr> hotStrs = hotStrDao.list();
            return success(hotStrs);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult findAlbumByBestTitle() {
        try {

            List<BaseNiceTitle> baseNiceTitles = baseNiceTitleDao.list();

            return success(baseNiceTitles);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult addAlbumByBest(Long ids,Long id) {
        BaseNiceTitle baseNiceTitle = baseNiceTitleDao.findById(id);
        if(baseNiceTitle==null){
            return errorMo();
        }
        Album album = albumDao.findById(ids);
        if(album==null){
            return errorMo();
        }
        int count  = baseNiceDao.findCount(ids,id);
        if(count>0){
            return error(1,"已添加");
        }
        BaseNice baseNice = new BaseNice();
        baseNice.setAlbumId(ids);
        baseNice.setParentId(id);
        baseNice.setCode(0);
        baseNiceDao.save(baseNice);
        return success();
    }

    @Override
    public RspResult findAlbumByBest(AlbumFindBestBo bo) {

        try {

            List<AlbumVo> list = baseNiceDao.list(bo);
            int count = baseNiceDao.listCount(bo);
            return success(count,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();

        }
    }

    @Override
    public RspResult findAlbumByClassify(FindLiveByClassifyBo bo) {

        try {
            List<AlbumVo> albums = albumDao.findAlbumByClassify(bo);
            for (AlbumVo albumVo:albums) {
                Integer payNumber = baseRecordingDao.getPayNumber(albumVo.getId());
                if(payNumber != null){
                    albumVo.setPayNumber(payNumber);
                }else{
                    albumVo.setPayNumber(0);
                }
            }
            int count = albumDao.findAlbumByClassifyCount(bo);
            return success(count,albums);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult freezeRecording(RecordingFreezeBo bo) {
        if (bo.getType() == 0) {
            baseRecordingDao.delById(bo.getId());
        } else {
            baseRecordingDao.cancelDel(bo.getId());
        }
        return success();
    }

    @Override
    public String findRecordingCount(RecordingFindBo bo) {
        int count = baseRecordingDao.countForAccount(bo.getAlbumId());
        return count + "";
    }

    @Override
    public RspResult findAlbumMoney(Long id) {
        AlbumMoney byAlbumId = albumMoneyDao.findByAlbumId(id);
        return success(byAlbumId);
    }

    @Override
    public RspResult findAlbumForAccount(FindAlbumForAccountBo bo) {
        List<AlbumAccountVo> list = albumDao.findAlbumForAccount(bo);
        return success(list);
    }

    @Override
    public String findAlbumForAccountCount(FindAlbumForAccountBo bo) {

        int count = albumDao.findAlbumForAccountCount(bo);
        return count + "";
    }

    @Override
    public RspResult updRecording(RecordingUpdBo bo) {
        BaseRecording byId = baseRecordingDao.findById(bo.getId());
        byId.setTitle(bo.getTitle());
        byId.setLastRevampTime(System.currentTimeMillis());
        baseRecordingDao.update(byId);
        return success(byId);
    }

    //查看评论
    @Override
    public RspResult findComments(CommentsFindBo bo) {
        List<Comments> list = commentDao.list(bo);
        int count = commentDao.count(bo);
        return success(count, list);
    }

    //添加对录音的评论
    @Override
    public RspResult addComments(CommentsAddBo bo) {
        String way = CommentType.getWay(bo.getCommentType());
        if (!StringUtil.isNotEmpty(way)) {
            return error(1, "不存在此种评论方式");
        }

        // 判断评论功能是否被关闭
        Integer type = -1;
        if ("dynamic".equals(way)) {
            type = 0;
        } else if ("recording".equals(way)) {
            type = 1;
        }
        TbComment tbComment = tbCommentDao.findByType(type);

        if (tbComment != null && tbComment.getStatus() != null && tbComment.getStatus() == 1) {
            return error(1, "评论功能已关闭");
        }

        Comments comments = new Comments();
        comments.setCommentType(way);
        comments.setCommentId(bo.getCommentId());
        comments.setContent(bo.getContent());
        comments.setUserId(bo.getUserId());
        commentDao.save(comments);

        return success(comments);
    }

    @Override
    public RspResult addClickNumber(RecordingAddNumberBo bo) {
        BaseRecording baseRecording = baseRecordingDao.findById(bo.getId());
        baseRecording.setClickNumber(baseRecording.getClickNumber() + 1);
        baseRecording.setLastRevampTime(System.currentTimeMillis());
        baseRecordingDao.update(baseRecording);
        return success();
    }

    @Override
    public RspResult addPlayNumber(RecordingAddNumberBo bo) {
        BaseRecording baseRecording = baseRecordingDao.findById(bo.getId());
        baseRecording.setPlayNumber(baseRecording.getPlayNumber() + 1);
        baseRecording.setLastRevampTime(System.currentTimeMillis());
        baseRecordingDao.update(baseRecording);
        return success();
    }

    @Override
    public RspResult findRecordingForAccount(RecordingFindBo bo) {
        AlbumVo albumVo = albumDao.findAlbumById(bo.getAlbumId());

        List<BaseRecording> list = baseRecordingDao.listForAccount(bo);
        if(albumVo.getType()==100){
            for (int i=0;i<list.size();i++){

                list.get(i).setMoney(new BigDecimal(0));
            }
        }
        if(albumVo.getType()==200){
            int bugSet = albumVo.getBumSet();
            for (int i=0;i<list.size();i++){
                if(i<=bugSet){
                    list.get(i).setMoney(new BigDecimal(0));
                }else {
                    list.get(i).setMoney(albumVo.getBumMoney());
                }
            }
        }
        if(albumVo.getType()==300){
            int bugSet = albumVo.getBumSet();
            for (int i=0;i<list.size();i++){

                list.get(i).setMoney(albumVo.getBumMoney());
            }
        }



        int count = baseRecordingDao.countForAccount(bo.getAlbumId());

        return success(count, list);
    }

    //查看专辑下的录音
    @Override
    public RspResult findRecording(RecordingFindBo bo) {
        AlbumVo albumVo = albumDao.findAlbumById(bo.getAlbumId());
        List<String> recordings = albumDao.findRecording(bo);

        List<BaseRecording> list = baseRecordingDao.listForAccount(bo);
        if(albumVo.getType()==100){
            for (int i=0;i<list.size();i++){

                list.get(i).setMoney(new BigDecimal(0));
            }
        }
        if(albumVo.getType()==200){
            int bugSet = albumVo.getBumSet();
            for (int i=0;i<list.size();i++){
                if(i<=bugSet){
                    list.get(i).setMoney(new BigDecimal(0));
                }else {
                    for (String temp : recordings) {
                        if (temp.equals(list.get(i).getId()+"")) {
                            list.get(i).setBuyStatus(1);
                        }}

                    list.get(i).setMoney(albumVo.getBumMoney());
                }
            }
        }
        if(albumVo.getType()==300){
            for (int i=0;i<list.size();i++){
                for (String temp : recordings) {

                    if (temp.equals(list.get(i).getId()+"")) {
                        list.get(i).setBuyStatus(1);
                    }}

                list.get(i).setMoney(albumVo.getBumMoney());
            }
        }




        int count = baseRecordingDao.count(bo.getAlbumId());

        return success(count, list);
    }

    //添加录音
    @Override
    public RspResult addRecording(RecordingAddBo bo) {
        BaseRecording baseRecording = new BaseRecording();
        baseRecording.setAlbumId(bo.getAlbumId());
        baseRecording.setUserId(bo.getUserId());
        baseRecording.setClickNumber(0);
        baseRecording.setPlayNumber(0);
        baseRecording.setSign(bo.getSign());
        baseRecording.setTitle(bo.getTitle());
        baseRecordingDao.save(baseRecording);

        return success(baseRecording);
    }

    @Override
    public RspResult findAlbumByClassifyId(AlbumFindByClassifyIdBo bo) {
        List<AlbumVo> albumVos = albumDao.findByClassifyId(bo);
        int count = albumDao.findCountByClassifyId(bo.getClassifyId());

        return success(count, albumVos);
    }

    @Override
    public RspResult findAlbumByUserId(AlbumFindByUserIdBo bo) {
        List<AlbumVo> albumVos = albumDao.findAlbumByUserId(bo);
        for(AlbumVo albumVo:albumVos){
            Integer payNumber = baseRecordingDao.getPayNumber(albumVo.getId());
            if(payNumber != null){
                albumVo.setPayNumber(payNumber);
            }else{
                albumVo.setPayNumber(0);
            }
        }
        int count = albumDao.findAlbumCountByUserId(bo.getUserId());

        return success(count, albumVos);
    }

    //根据id查找单个
    @Override
    public RspResult findAlbumById(AlbumFindByIdBo bo) {

        AlbumVo albumVo = albumDao.findAlbumById(bo.getId());
        return success(albumVo);
    }

    @Override
    public RspResult delAlbum(AlbumDelBo bo) {
        albumDao.delById(bo.getId());
        return new RspResult();
    }

    //修改专辑
    @Override
    public RspResult updAlbum(AlbumUpdBo bo) {
        Album album = albumDao.findById(bo.getId());
        if (album == null) {
            return new RspResult("id绑定对象为空", 1);
        }

        if (StringUtil.isNotLongEmpty(bo.getClassifyId())) {
            album.setClassifyId(bo.getClassifyId());
        }
        if (StringUtil.isNotEmpty(bo.getAlbumName())) {
            album.setAlbumName(bo.getAlbumName());
        }
        if (StringUtil.isNotEmpty(bo.getDetail())) {
            album.setDetail(bo.getDetail());
        }

        album.setLastRevampTime(System.currentTimeMillis());

        albumDao.update(album);

        if (StringUtil.isNotIntegerEmpty(bo.getType())) {
            AlbumMoney albumMoney = albumMoneyDao.findByAlbumId(album.getId());
            if (bo.getType() == 100) {
                albumMoney.setChargeStatus(0);
                albumMoney.setType(100);
                albumMoney.setLastRevampTime(System.currentTimeMillis());
            } else if (bo.getType() == 200) {
                albumMoney.setType(200);
                albumMoney.setChargeStatus(bo.getChargeStatus());
                albumMoney.setBumSet(bo.getBumSet());
                albumMoney.setBumMoney(bo.getBumMoney());
                albumMoney.setLastRevampTime(System.currentTimeMillis());
            } else if (bo.getType() == 300) {
                albumMoney.setType(300);
                albumMoney.setChargeStatus(bo.getChargeStatus());
                albumMoney.setBumMoney(bo.getBumMoney());
                albumMoney.setLastRevampTime(System.currentTimeMillis());
            }
            albumMoneyDao.update(albumMoney);

        }


        return success(album);
    }

    @Override
    public RspResult addAlbum(AlbumAddBo bo) {
        Album album = new Album();
        album.setAlbumName(bo.getAlbumName());
        album.setClassifyId(bo.getClassifyId());
        album.setUserId(bo.getUserId());
        album.setDetail(bo.getDetail());
        album.setCreateTime(System.currentTimeMillis());
        album.setLastRevampTime(System.currentTimeMillis());
        albumDao.save(album);

        AlbumMoney albumMoney = new AlbumMoney();
        albumMoney.setAlbumId(album.getId());
        albumMoney.setCreateTime(System.currentTimeMillis());
        albumMoney.setLastRevampTime(System.currentTimeMillis());
        if (bo.getType() == 100) {
            albumMoney.setChargeStatus(0);
            albumMoney.setType(bo.getType());
            albumMoneyDao.save(albumMoney);
        } else if (bo.getType() == 200) {
            albumMoney.setChargeStatus(bo.getChargeStatus());
            albumMoney.setType(bo.getType());
            albumMoney.setBumSet(bo.getBumSet());
            albumMoney.setBumMoney(bo.getBumMoney());
            albumMoneyDao.save(albumMoney);
        } else if (bo.getType() == 300) {
            albumMoney.setChargeStatus(bo.getChargeStatus());
            albumMoney.setType(bo.getType());
            albumMoney.setBumMoney(bo.getBumMoney());
            albumMoneyDao.save(albumMoney);
        }
        return success(album);
    }


    /**
     * 获取专辑的播放量
     */
    public int getPayNumber(Long albumId){

        return baseRecordingDao.getPayNumber(albumId);
    }


    @Override
    public RspResult save(Album entity) {
        return null;
    }

    @Override
    public Album findById(Long aLong) {
        return albumDao.findById(aLong);
    }

    @Override
    public RspResult update(Album entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        albumDao.delById(aLong);
        return success();
    }
}
