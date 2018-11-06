package com.empathy.web;

import com.alibaba.fastjson.JSONObject;
import com.empathy.common.RspResult;
import com.empathy.domain.file.bo.FileBo;
import com.empathy.domain.file.bo.FileSaveOnlyBo;
import com.empathy.service.IFileService;
import com.empathy.service.impl.FileService;
import com.empathy.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.ehcache.search.impl.BaseResult;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * Created by MI on 2017/12/21.
 */
@RestController
@RequestMapping("/file")
@Api(tags = {"图片接口"})
public class FileController {

    @Autowired
    private IFileService fileService;


    /**
     * MultipartFile方式
     */
    @ApiOperation(value = "添加图片", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public RspResult uploadFile(
            @RequestParam(value = "uploadFile", required = true) MultipartFile uploadFile,
            FileBo bo) throws IOException {


        return fileService.saveFile(uploadFile, bo.getFilePurpose(), bo.getFkPurposeId(), bo.getType());
    }


    /**
     * MultipartFile方式
     */
    @ApiOperation(value = "单纯的保存文章图片，不参加数据量的操作", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public RspResult uploadFile(  @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {


        return fileService.saveFileOnly(file);
    }

    /**
     * MultipartFile方式
     */
    @ApiOperation(value = "单纯的保存主分类图片，不参加数据量的操作", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveMainClassifyFile", method = RequestMethod.POST)
    public RspResult saveMainClassifyFile(  @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        return fileService.saveMainClassifyFile(file);
    }
    /**
     * MultipartFile方式
     */
    @ApiOperation(value = "单纯的保存banner图片，不参加数据量的操作", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveBannerFile", method = RequestMethod.POST)
    public RspResult saveBannerFile(  @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        return fileService.saveBannerFile(file);
    }

    /**
     * MultipartFile方式
     */
    @ApiOperation(value = "单纯的保存分类图片，不参加数据量的操作", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveClassifyFile", method = RequestMethod.POST)
    public RspResult saveClassifyFile(  @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        return fileService.saveClassifyFile(file);
    }

    /**
     * MultipartFile方式
     */
    @ApiOperation(value = "单纯的保存礼物得图片，不参加数据量的操作", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveGiftFile", method = RequestMethod.POST)
    public RspResult saveGiftFile(  @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        return fileService.saveFileGift(file);
    }

    /**
     * MultipartFile方式
     */
    @ApiOperation(value = "单纯的保存阴音频封面的图片，不参加数据量的操作", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveRecordingFile", method = RequestMethod.POST)
    public RspResult saveRecordingFile(  @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        return fileService.saveRecordingFile(file);
    }

    @ApiOperation(value = "多图上传", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveFileForMultiObj", method = RequestMethod.POST)
    public  RspResult save(@RequestParam(value = "pic1", required = false) MultipartFile pic1,
          @RequestParam(value = "pic2", required = false) MultipartFile pic2,
          @RequestParam(value = "pic3", required = false) MultipartFile pic3,
          @RequestParam(value = "pic4", required = false) MultipartFile pic4,
          @RequestParam(value = "pic5", required = false) MultipartFile pic5,
          @RequestParam(value = "pic6", required = false) MultipartFile pic6,
          @RequestParam(value = "pic7", required = false) MultipartFile pic7,
          @RequestParam(value = "pic8", required = false) MultipartFile pic8,
          @RequestParam(value = "pic9", required = false) MultipartFile pic9,
          @RequestParam(value = "fileType", required = true) String fileType,
          @RequestParam(value = "filePurpose", required = true) String filePurpose,
          @RequestParam(value = "fkPurposeId", required = true) Long fkPurposeId) throws Exception {
        try {

            if (null != pic1 && !StringUtils.isEmpty(pic1.getOriginalFilename())) {
                //fileService.saveFile(pic1, fileType, fkPurposeId, filePurpose);
                fileService.saveFile1(pic1, fileType, filePurpose, fkPurposeId);
            }
            if (null != pic2 && !StringUtils.isEmpty(pic2.getOriginalFilename())) {
                fileService.saveFile1(pic2, fileType, filePurpose, fkPurposeId);
            }
            if (null != pic3 && !StringUtils.isEmpty(pic3.getOriginalFilename())) {
                fileService.saveFile1(pic3, fileType, filePurpose, fkPurposeId);
            }

            if (null != pic4 && !StringUtils.isEmpty(pic4.getOriginalFilename())) {
                fileService.saveFile1(pic4, fileType, filePurpose, fkPurposeId);

            }
            if (null != pic5 && !StringUtils.isEmpty(pic5.getOriginalFilename())) {
                fileService.saveFile1(pic5, fileType, filePurpose, fkPurposeId);

            }
            if (null != pic6 && !StringUtils.isEmpty(pic6.getOriginalFilename())) {
                fileService.saveFile1(pic6, fileType, filePurpose, fkPurposeId);
            }
            if (null != pic7 && !StringUtils.isEmpty(pic7.getOriginalFilename())) {
                fileService.saveFile1(pic7, fileType, filePurpose, fkPurposeId);

            }
            if (null != pic8 && !StringUtils.isEmpty(pic8.getOriginalFilename())) {
                fileService.saveFile1(pic8, fileType, filePurpose, fkPurposeId);

            }
            if (null != pic9 && !StringUtils.isEmpty(pic9.getOriginalFilename())) {
                fileService.saveFile1(pic9, fileType, filePurpose, fkPurposeId);
            }


            return new RspResult();
        }catch (Exception e){
            e.printStackTrace();
            return new RspResult("操作失败",1);
        }

    }





    /**
     *  CKeditor富文本编辑器图片上传
     */
    @ApiOperation(value = "单纯的保存文章图片，不参加数据量的操作", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/uploadfileCKeditor", method = RequestMethod.POST)
    public Object uploadfileCKeditor( HttpServletRequest request,
                                         @RequestParam(value = "upload", required = true) MultipartFile file) throws IOException {
        String fileName = "";
        String msg = "";
        String imageUrl = "";
        JSONObject result = new JSONObject();
        try {
            RspResult rspResult = fileService.saveFileOnly(file);
            fileName = rspResult.getData().toString();

            if (StringUtil.isNotEmpty(fileName)) {
                String imagePath = "http://" + request.getServerName() //服务器地址
                        + ":"
                        + request.getServerPort();
                imageUrl = imagePath + fileName;
            } else {
                msg = "文件上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "服务器异常";
        } finally {
            if (!StringUtils.isEmpty(msg)) {
                //上传失败
                result.put("uploaded", 0);
                JSONObject errorObj = new JSONObject();
                errorObj.put("message", msg);
                result.put("error", errorObj);
            } else {
                //上传成功
                result.put("uploaded", 1);
                result.put("fileName", fileName);
                result.put("url", imageUrl);
            }
            System.out.println("===================" + result.toJSONString());
            return result;
        }
    }


    /**
     * MultipartFile方式
     */
    @ApiOperation(value = "上传APP文件", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveAppFile", method = RequestMethod.POST)
    public RspResult saveFile(  @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {


        return fileService.saveAppFileOnly(file);
    }


}
