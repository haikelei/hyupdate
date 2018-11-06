package com.empathy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.empathy.common.PropertiesConifig;
import com.empathy.common.RspResult;
import com.empathy.dao.FileDao;
import com.empathy.domain.bidding.bo.TokenBo;
import com.empathy.domain.file.FilePurpose;
import com.empathy.domain.file.FileTypeAndPath;
import com.empathy.domain.file.bo.FileCarBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IFileService;
import com.empathy.utils.FileUploadUtils;
import com.sun.tools.javac.util.Log;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by MI on 2017/12/21.
 */
@Service
public class FileService extends AbstractBaseService implements IFileService {

    @Autowired
    private FileDao fileDao;

    public static Logger lo = Logger.getLogger(FileService.class);

    @Override
    public RspResult saveBannerFile(MultipartFile file) throws IOException{
        String filePurpose="banner";
        String type="image";
        String fileTypePath = FileTypeAndPath.getPathByType(type);
        //如果找不到文档类型对应路径配置
        if (StringUtils.isEmpty(fileTypePath)) {
            return new RspResult("文档类型错误", 1);
        }

        //如果用途不存在
        FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
        if (filePurposeObj == null) {
            return new RspResult("图片用途不存在", 1);

        }

        //获取上传文件类型的扩展名
        String extensionName = FileUploadUtils.getExtensionName(file);
        //生成唯一文件标识，作为文件名
        String uuIdFileName = UUID.randomUUID().toString();
        String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //拼接逻辑访问路径给前端请求
        String fileLogicLocation = "/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //创建文件
        File repositoryfile = new File(fileLocation);
        //如果目录不存在，则创建
        if (!repositoryfile.getParentFile().exists()) {
            repositoryfile.getParentFile().mkdirs();
        }
        //保存文件
        file.transferTo(repositoryfile);

        return success(fileLogicLocation);
    }

    @Override
    public RspResult saveSomeFile(MultipartFile pic1, MultipartFile pic2, String filePurpose, Long fkPurposeId, String fileType) {
        try {

            String fileTypePath = FileTypeAndPath.getPathByType(fileType);
            //如果找不到文档类型对应路径配置
            if (StringUtils.isEmpty(fileTypePath)) {
                return new RspResult("文档类型错误", 1);
            }

            //如果用途不存在
            FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
            if (filePurposeObj == null) {
                return new RspResult("图片用途不存在", 1);

            }


            //获取上传文件类型的扩展名
            String extensionName = FileUploadUtils.getExtensionName(pic1);
            String extensionName2 = FileUploadUtils.getExtensionName(pic2);
            //生成唯一文件标识，作为文件名
            String uuIdFileName = UUID.randomUUID().toString();
            String uuIdFileName2 = UUID.randomUUID().toString();
            //拼接文件完整存储URL
            String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
            String fileLocation2 =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName2 + "." + extensionName2;
            //拼接逻辑访问路径给前端请求
            String fileLogicLocation ="/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
            String fileLogicLocation2 ="/" + "image" + "/" + filePurpose + "/" + uuIdFileName2 + "." + extensionName2;
            //创建文件
            File repositoryfile = new File(fileLocation);
            File repositoryfile2 = new File(fileLocation2);
            //如果目录不存在，则创建
            if (!repositoryfile.getParentFile().exists()) {
                repositoryfile.getParentFile().mkdirs();
            }
            //如果目录不存在，则创建
            if (!repositoryfile2.getParentFile().exists()) {
                repositoryfile2.getParentFile().mkdirs();
            }
            //保存文件
            pic1.transferTo(repositoryfile);
            pic2.transferTo(repositoryfile);
            //保存文件信息到数据库
            try {
                insertFile(filePurpose,fileLogicLocation,fkPurposeId);
                insertFile1(filePurpose, fileLogicLocation2, fkPurposeId);
            } catch (Exception e) {

                e.printStackTrace();
                return new RspResult("操作失败", 1);


            }
            //返回数据
            // return new ObjectResponse<>(fileLogicLocation);
            return success(fileLogicLocation);
        } catch (Exception e) {
            e.printStackTrace();
            lo.info(e);
            return new RspResult("操作失败", 1);
        }


    }

    @Override
    public RspResult saveFile1(MultipartFile multipartFile, String fileType, String filePurpose, Long fkPurposeId) throws IOException {


        try {

            String fileTypePath = FileTypeAndPath.getPathByType(fileType);
            //如果找不到文档类型对应路径配置
            if (StringUtils.isEmpty(fileTypePath)) {
                return new RspResult("文档类型错误", 1);
            }

            //如果用途不存在
            FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
            if (filePurposeObj == null) {
                return new RspResult("图片用途不存在", 1);

            }


            //获取上传文件类型的扩展名
            String extensionName = FileUploadUtils.getExtensionName(multipartFile);
            //生成唯一文件标识，作为文件名
            String uuIdFileName = UUID.randomUUID().toString();
            //拼接文件完整存储URL
            String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
            //拼接逻辑访问路径给前端请求
            String fileLogicLocation ="/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
            //创建文件
            File repositoryfile = new File(fileLocation);
            //如果目录不存在，则创建
            if (!repositoryfile.getParentFile().exists()) {
                repositoryfile.getParentFile().mkdirs();
            }
            //保存文件
            multipartFile.transferTo(repositoryfile);
            //保存文件信息到数据库
            try {

                insertFile1(filePurpose, fileLogicLocation, fkPurposeId);
            } catch (Exception e) {

                e.printStackTrace();
                return new RspResult("操作失败", 1);


            }
            //返回数据
            // return new ObjectResponse<>(fileLogicLocation);
            return success(fileLogicLocation);
        } catch (Exception e) {
            e.printStackTrace();
            lo.info(e);
            return new RspResult("操作失败", 1);
        }
    }

    private void insertFile1(String filePurpose, String fileLogicLocation, Long fkPurposeId) {
        com.empathy.domain.bidding.File file = new com.empathy.domain.bidding.File();
        file.setLocation(fileLogicLocation);
        file.setPurpose(filePurpose);
        file.setDelFlag(0);
        file.setPurposeId(fkPurposeId);
        file.setType("image");
        file.setCreateTime(System.currentTimeMillis());
        file.setLastRevampTime(System.currentTimeMillis());
        fileDao.insert(file);

    }

    @Override
    public void updateFile(TokenBo tokenBo, Long userId, Long thirdId) {
        FileCarBo fileCarBo = new FileCarBo();
        fileCarBo.setType("userPhotoHead");
        fileCarBo.setPurposeId(thirdId);
        com.empathy.domain.bidding.File file = fileDao.findFileByPurposeIdAndType(fileCarBo);
        file.setPurposeId(userId);
        file.setLastRevampTime(System.currentTimeMillis());
        fileDao.updateByPrimaryKey(file);

    }

    @Override
    public com.empathy.domain.bidding.File findById(Long i) {

        return fileDao.selectByPrimaryKey(i);
    }

    @Override
    public com.empathy.domain.bidding.File findFile(FileCarBo fileCarBo) {

        com.empathy.domain.bidding.File file = fileDao.findFileByPurposeIdAndType(fileCarBo);
        return file;
    }

    @Override
    public RspResult saveFile(MultipartFile multipartFile, String filePurpose, Long fkPurposeId, String type) throws IOException {

        try {


            //如果用途不存在
            FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
            if (filePurposeObj == null) {
                return new RspResult("图片用途不存在", 1);

            }


            //获取上传文件类型的扩展名
            String extensionName = FileUploadUtils.getExtensionName(multipartFile);
            //生成唯一文件标识，作为文件名
            String uuIdFileName = UUID.randomUUID().toString();
            //拼接文件完整存储URL
            String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
            //拼接逻辑访问路径给前端请求
            String fileLogicLocation ="/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
            //创建文件
            File repositoryfile = new File(fileLocation);
            //如果目录不存在，则创建
            if (!repositoryfile.getParentFile().exists()) {
                repositoryfile.getParentFile().mkdirs();
            }
            //保存文件
            multipartFile.transferTo(repositoryfile);
            //保存文件信息到数据库
            try {

                insertFile(filePurpose, fileLogicLocation, fkPurposeId);
            } catch (Exception e) {

                e.printStackTrace();
                return new RspResult("操作失败", 1);


            }
            //返回数据
            // return new ObjectResponse<>(fileLogicLocation);
            return success(fileLogicLocation);
        } catch (Exception e) {
            e.printStackTrace();
            lo.info(e);
            return new RspResult("操作失败", 1);
        }

    }

    @Override
    public void insertFile(String filePurpose, String fileLogicLocation, Long fkPurposeId) {
        FileCarBo fileCarBo = new FileCarBo();
        fileCarBo.setType(filePurpose);
        fileCarBo.setPurposeId(fkPurposeId);
        List<com.empathy.domain.bidding.File> fileByPurposeIdAndType = fileDao.findFileByPurposeIdAndTypeList(fileCarBo);
        if (fileByPurposeIdAndType.size()>0) {
            for (int i = 0;i<fileByPurposeIdAndType.size();i++){
                fileDao.deleteByPrimaryKey(fileByPurposeIdAndType.get(i).getId());
            }

        }
        com.empathy.domain.bidding.File file = new com.empathy.domain.bidding.File();
        file.setLocation(fileLogicLocation);
        file.setPurpose(filePurpose);
        file.setDelFlag(0);
        file.setPurposeId(fkPurposeId);
        file.setType("image");
        file.setCreateTime(System.currentTimeMillis());
        file.setLastRevampTime(System.currentTimeMillis());
        fileDao.insert(file);
    }

    @Override
    public RspResult saveFileOnly(MultipartFile file) throws IOException{

        String filePurpose="article";
        String type="image";
        String fileTypePath = FileTypeAndPath.getPathByType(type);
        //如果找不到文档类型对应路径配置
        if (StringUtils.isEmpty(fileTypePath)) {
            return new RspResult("文档类型错误", 1);
        }

        //如果用途不存在
        FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
        if (filePurposeObj == null) {
            return new RspResult("图片用途不存在", 1);

        }

        //获取上传文件类型的扩展名
        String extensionName = FileUploadUtils.getExtensionName(file);
        //生成唯一文件标识，作为文件名
        String uuIdFileName = UUID.randomUUID().toString();
        String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //拼接逻辑访问路径给前端请求
        String fileLogicLocation = "/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //创建文件
        File repositoryfile = new File(fileLocation);
        //如果目录不存在，则创建
        if (!repositoryfile.getParentFile().exists()) {
            repositoryfile.getParentFile().mkdirs();
        }
        //保存文件
        file.transferTo(repositoryfile);

        return success(fileLogicLocation);
    }

    @Override
    public RspResult saveMainClassifyFile(MultipartFile file) throws IOException{

        String filePurpose="mainClassify";
        String type="image";
        String fileTypePath = FileTypeAndPath.getPathByType(type);
        //如果找不到文档类型对应路径配置
        if (StringUtils.isEmpty(fileTypePath)) {
            return new RspResult("文档类型错误", 1);
        }

        //如果用途不存在
        FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
        if (filePurposeObj == null) {
            return new RspResult("图片用途不存在", 1);

        }

        //获取上传文件类型的扩展名
        String extensionName = FileUploadUtils.getExtensionName(file);
        //生成唯一文件标识，作为文件名
        String uuIdFileName = UUID.randomUUID().toString();
        String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //拼接逻辑访问路径给前端请求
        String fileLogicLocation = "/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //创建文件
        File repositoryfile = new File(fileLocation);
        //如果目录不存在，则创建
        if (!repositoryfile.getParentFile().exists()) {
            repositoryfile.getParentFile().mkdirs();
        }
        //保存文件
        file.transferTo(repositoryfile);

        return success(fileLogicLocation);
    }


    @Override
    public RspResult saveClassifyFile(MultipartFile file) throws IOException{

        String filePurpose="classify";
        String type="image";
        String fileTypePath = FileTypeAndPath.getPathByType(type);
        //如果找不到文档类型对应路径配置
        if (StringUtils.isEmpty(fileTypePath)) {
            return new RspResult("文档类型错误", 1);
        }

        //如果用途不存在
        FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
        if (filePurposeObj == null) {
            return new RspResult("图片用途不存在", 1);

        }

        //获取上传文件类型的扩展名
        String extensionName = FileUploadUtils.getExtensionName(file);
        //生成唯一文件标识，作为文件名
        String uuIdFileName = UUID.randomUUID().toString();
        String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //拼接逻辑访问路径给前端请求
        String fileLogicLocation = "/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //创建文件
        File repositoryfile = new File(fileLocation);
        //如果目录不存在，则创建
        if (!repositoryfile.getParentFile().exists()) {
            repositoryfile.getParentFile().mkdirs();
        }
        //保存文件
        file.transferTo(repositoryfile);

        return success(fileLogicLocation);
    }

    @Override
    public RspResult saveFileGift(MultipartFile file) throws IOException{

        String filePurpose="gift";
        String type="image";
        String fileTypePath = FileTypeAndPath.getPathByType(type);
        //如果找不到文档类型对应路径配置
        if (StringUtils.isEmpty(fileTypePath)) {
            return new RspResult("文档类型错误", 1);
        }

        //如果用途不存在
        FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
        if (filePurposeObj == null) {
            return new RspResult("图片用途不存在", 1);

        }

        //获取上传文件类型的扩展名
        String extensionName = FileUploadUtils.getExtensionName(file);
        //生成唯一文件标识，作为文件名
        String uuIdFileName = UUID.randomUUID().toString();
        String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //拼接逻辑访问路径给前端请求
        String fileLogicLocation = "/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //创建文件
        File repositoryfile = new File(fileLocation);
        //如果目录不存在，则创建
        if (!repositoryfile.getParentFile().exists()) {
            repositoryfile.getParentFile().mkdirs();
        }
        //保存文件
        file.transferTo(repositoryfile);

        return success(fileLogicLocation);
    }

    @Override
    public RspResult saveRecordingFile(MultipartFile file) throws IOException{

        String filePurpose="recording";
        String type="image";
        String fileTypePath = FileTypeAndPath.getPathByType(type);
        //如果找不到文档类型对应路径配置
        if (StringUtils.isEmpty(fileTypePath)) {
            return new RspResult("文档类型错误", 1);
        }

        //如果用途不存在
        FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
        if (filePurposeObj == null) {
            return new RspResult("图片用途不存在", 1);

        }

        //获取上传文件类型的扩展名
        String extensionName = FileUploadUtils.getExtensionName(file);
        //生成唯一文件标识，作为文件名
        String uuIdFileName = UUID.randomUUID().toString();
        String fileLocation =  "/pic/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //拼接逻辑访问路径给前端请求
        String fileLogicLocation = "/" + "image" + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
        //创建文件
        File repositoryfile = new File(fileLocation);
        //如果目录不存在，则创建
        if (!repositoryfile.getParentFile().exists()) {
            repositoryfile.getParentFile().mkdirs();
        }
        //保存文件
        file.transferTo(repositoryfile);

        return success(fileLogicLocation);
    }



    @Override
    public RspResult saveAppFileOnly(MultipartFile file) throws IOException{

        String filePurpose="app";
        //如果用途不存在
        FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
        if (filePurposeObj == null) {
            return new RspResult("文件用途不存在", 1);
        }

        String fileLocation =  "/pic/" + "app" + "/" + "HYZS.apk";
        //拼接逻辑访问路径给前端请求
        String fileLogicLocation = "/" + "app" + "/" + "HYZS.apk";
        //创建文件
        File repositoryfile = new File(fileLocation);
        //如果目录不存在，则创建
        if (!repositoryfile.getParentFile().exists()) {
            repositoryfile.getParentFile().mkdirs();
        }
        //保存文件
        file.transferTo(repositoryfile);

        return success(fileLogicLocation);
    }


}
