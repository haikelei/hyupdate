package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.baseRecording.BaseRecording;
import com.empathy.domain.baseRecording.bo.RecordingAddBo;
import com.empathy.service.IBaseRecordingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.DiskFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ Description    音频接口
 * @ Author         dong
 * @ Date           2018-09-21 17:33
 */
@Controller
@RequestMapping("/record")
@Api(tags = {"音频接口"})
public class BaseRecordingController {

    @Autowired
    private IBaseRecordingService baseRecordingService;



    @ApiOperation(value = "后台音频上传", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addBybackstage", method = RequestMethod.POST)
    @ResponseBody
    public RspResult addBybackstage(RecordingAddBo bo){

        return baseRecordingService.addBybackstage(bo);
    }


    @ApiOperation(value = "后台音频上传", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addRecording", method = RequestMethod.POST)
    @ResponseBody
    public RspResult addRecording(MultipartFile file,HttpServletRequest request){

        return baseRecordingService.uploadToServer(file,request);
    }



}
