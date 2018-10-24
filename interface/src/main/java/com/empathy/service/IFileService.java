package com.empathy.service;

import com.empathy.common.RspResult;
import com.empathy.domain.bidding.File;
import com.empathy.domain.bidding.bo.FileFindBo;
import com.empathy.domain.bidding.bo.TokenBo;
import com.empathy.domain.file.bo.FileCarBo;
import org.apache.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by MI on 2017/12/21.
 */
public interface IFileService {
    RspResult saveFile(MultipartFile multipartFile, String filePurpose, Long fkPurposeId, String type) throws IOException;
    RspResult saveFile1(MultipartFile multipartFile, String fileType, String filePurpose, Long fkPurposeId) throws IOException;
    void insertFile(String filePurpose, String fileLogicLocation, Long fkPurposeId);


    File findFile(FileCarBo fileFindBo);

    File findById(Long i);

    void updateFile(TokenBo tokenBo, Long userId, Long thirdId);

    RspResult saveFileOnly(MultipartFile file) throws IOException;

    RspResult saveMainClassifyFile(MultipartFile file) throws IOException;

    RspResult saveClassifyFile(MultipartFile file) throws  IOException;

    RspResult saveSomeFile(MultipartFile pic1, MultipartFile pic2, String filePurpose, Long fkPurposeId, String fileType);

    RspResult saveBannerFile(MultipartFile file) throws IOException;

    RspResult saveFileGift(MultipartFile file) throws IOException;

    RspResult saveRecordingFile(MultipartFile file) throws IOException;


}
