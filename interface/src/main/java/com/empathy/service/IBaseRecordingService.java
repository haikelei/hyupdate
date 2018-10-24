package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.baseRecording.BaseRecording;
import com.empathy.domain.baseRecording.bo.RecordingAddBo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Description
 * @ Author         dong
 * @ Date           2018-09-21 19:09
 */
public interface IBaseRecordingService extends BaseService<BaseRecording, Long, PageBo> {

    /** 后台上传音频 */
    RspResult addBybackstage(RecordingAddBo bo);

    RspResult uploadToServer(MultipartFile file ,HttpServletRequest request);

}
