package com.empathy.web.admin;

import com.empathy.common.AbstractBaseController;
import com.empathy.common.RspResult;
import com.empathy.ex.SystemErrorException;
import com.empathy.utils.DateUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Controller
@RequestMapping("/image")
public class ImageController extends AbstractBaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);


    private static final String OS_TYPE = System.getProperty("os.name").toUpperCase();
    private static final String winDir = "D:/buscaptain/";
    private static final String linuxDir = "/www/buscaptain/";
    private static final Set<String> SUFFIXES = new HashSet<>();
    private static String CUR_DIR = null;

    @PostConstruct
    public void init() {
        if (OS_TYPE.contains("WINDOWS")) {
            CUR_DIR = winDir;
        } else {
            CUR_DIR = linuxDir;
        }
        SUFFIXES.add(".png");
        SUFFIXES.add(".jpg");
        SUFFIXES.add(".jpeg");
        SUFFIXES.add(".gif");
        SUFFIXES.add(".tif");
        SUFFIXES.add(".bmp");
    }

    /**
     * 存放个 groupName+fileName  前端加 API
     *
     * @param file
     * @param req
     * @return
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest req) {
        if (null == file) {
            DefaultMultipartHttpServletRequest mreq = (DefaultMultipartHttpServletRequest) req;
            file = mreq.getMultiFileMap().getFirst("wangEditorH5File");
        }
        String key = "";
        if (file != null) {
            File dir = new File(CUR_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            key = genFileName() + getSuffix(file.getOriginalFilename());
            String savePath = CUR_DIR + key;
            File f = new File(savePath);
            try {
                file.transferTo(f);
            } catch (IOException e) {
                LOG.info("Upload Image Error:  {}", e);
                if (f.exists()) {
                    f.delete();
                }
                throw new SystemErrorException();
            } finally {

            }
        }
        return "/image/" + key;
    }

    private String genFileName() {
        return DateUtils.getDateStr(new Date(), DateUtils.DEF_TIME);
    }

    private String getSuffix(String originalName) {
        final int index = originalName.lastIndexOf(".");
        if (StringUtils.isNotBlank(originalName) && index > -1) {
            return originalName.substring(index).toLowerCase();
        }
        return "";
    }

    /**
     * 显示图片 富文本本地上传怎么解决
     *
     * @param req
     * @param resp
     */
    @GetMapping(value = "/{key}")
    public void show(@PathVariable("key") String key, HttpServletRequest req, HttpServletResponse resp) {
        final String source = CUR_DIR + req.getRequestURI().replace("/image", "");
        File f = new File(source);
        final String suffix = getSuffix(source);
        if (f.exists()) {//show
            if (SUFFIXES.contains(suffix)) {
                resp.setContentType(suffix.replace(".", ""));
                FileInputStream bis = null;
                try {
                    OutputStream os = resp.getOutputStream();
                    bis = new FileInputStream(f);
                    IOUtils.copy(bis, os);
                    bis.close();
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    LOG.error("Show Picture Error, Reason: {}", e);
                } finally {
                    try {
                        if (bis != null) {
                            bis.close();
                        }
                    } catch (IOException e) {

                    }
                }
            } else {//下载
                FileInputStream bis = null;
                try {
                    resp.addHeader("Content-Disposition", "attachment;filename=" + new String(f.getName().getBytes("utf-8"), "ISO-8859-1"));
                    resp.addHeader("Content-Length", "" + f.length());
                    resp.setContentType("application/octet-stream");
                    bis = new FileInputStream(f);
                    OutputStream os = resp.getOutputStream();
                    IOUtils.copy(bis, os);
                    bis.close();
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    LOG.error("Download File Error, Reason: {}", e);
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {

                        }
                    }
                }
            }
        }
    }

    /**
     * @param req
     * @return
     */
    @DeleteMapping(value = "/{key}")
    @ResponseBody
    public RspResult delete(@PathVariable("key") String key, HttpServletRequest req) {
        final String source = CUR_DIR + req.getRequestURI().replace("/image", "");
        File f = new File(source);
        if (f.exists()) {
            f.delete();
        }
        return new RspResult();
    }
}
