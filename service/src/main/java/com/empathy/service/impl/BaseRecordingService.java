package com.empathy.service.impl;

import cn.jiguang.common.utils.StringUtils;
import com.empathy.Constants;
import com.empathy.common.RspResult;
import com.empathy.dao.BaseRecordingDao;
import com.empathy.dao.FileDao;
import com.empathy.domain.baseRecording.BaseRecording;
import com.empathy.domain.baseRecording.bo.RecordingAddBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseRecordingService;
import com.netease.vcloud.auth.BasicCredentials;
import com.netease.vcloud.auth.Credentials;
import com.netease.vcloud.client.VcloudClient;
import com.netease.vcloud.upload.param.QueryVideoIDorWatermarkIDParam;
import com.netease.vcloud.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Description
 * @ Author         dong
 * @ Date           2018-09-21 19:10
 */
@Service
public class BaseRecordingService extends AbstractBaseService implements IBaseRecordingService {

    @Autowired
    private BaseRecordingDao baseRecordingDao;

    @Autowired
    private FileDao fileDao;



    @Override
    public RspResult addBybackstage(RecordingAddBo bo) {

        String sign = bo.getSign();
        if(StringUtils.isEmpty(sign)){
            return error("服务器忙，请重新上传");
        }

        BaseRecording recording = new BaseRecording();
        recording.setAlbumId(bo.getAlbumId());
        recording.setSign(sign);
        recording.setTitle(bo.getTitle());
        recording.setUserId(bo.getUserId());
        recording.setPlayNumber(0);
        recording.setClickNumber(0);
        recording.setCreateTime(new Date().getTime());
        recording.setLastRevampTime(new Date().getTime());
        baseRecordingDao.save(recording);
        //保存图片文件地址
        com.empathy.domain.bidding.File file = new com.empathy.domain.bidding.File();
        file.setLocation(bo.getUrl());
        file.setPurpose("recording");
        file.setDelFlag(0);
        file.setPurposeId(recording.getId());
        file.setType("image");
        file.setCreateTime(System.currentTimeMillis());
        file.setLastRevampTime(System.currentTimeMillis());
        fileDao.insert(file);

        return success();
    }

    @Override
    public RspResult uploadToServer(MultipartFile file ,HttpServletRequest request) {
        if (file == null) {
            return error("文件为空");
        }
        try{
            Credentials credentials = new BasicCredentials(Constants.WANGYI_APP_KEY, Constants.WANGYI_APP_SECRET);
            VcloudClient vclient = new VcloudClient(credentials);
            /*请输入上传文件路径*/
            String filePath = uploadMp3(file,request);;

            Map<String, Object> initParamMap = new HashMap<String, Object>();
            /*输入上传文件的相关信息 */
            /* 上传文件的原始名称（包含后缀名） 此参数必填*/
            initParamMap.put("originFileName", FileUtil.getFileName(filePath));
            /* 视频所属的类别ID（不填写为默认分类）此参数非必填 */
            initParamMap.put("typeId", Constants.WANGYI_TYPE_ID);

            QueryVideoIDorWatermarkIDParam queryVideoIDParam = vclient.uploadVideo(filePath, initParamMap);

            if (null != queryVideoIDParam) {

                String path =  queryVideoIDParam.getRet().getList().get(0).getVid().toString();
                return success(path);
            }
        }catch(Exception e){
            e.printStackTrace();
            return error("服务器忙，请重新上传");
        }
        return error("网络故障，请重新上传");
    }

    private String uploadMp3(MultipartFile file , HttpServletRequest request){
        //临时目录
        String dir = request.getSession().getServletContext()
                .getRealPath("/");
        String path = dir.substring(dir.length()-1,dir.length());
        String name = file.getOriginalFilename();
        String temp = dir + "temp";
        File tempFile = new File(temp);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        try{
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(tempFile,name)));
            return temp + path + name;
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public RspResult save(BaseRecording entity) {
        return null;
    }

    @Override
    public BaseRecording findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseRecording entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
