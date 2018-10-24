package com.empathy.web;

import com.alibaba.fastjson.JSONObject;
import com.empathy.CheckSumBuilder;
import com.empathy.Constants;
import com.empathy.file.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ Description
 * @ Author         dong
 * @ Date           2018-09-21 17:40
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    /**
     * 图片上传到服务器，并返回图片在服务器的地址
     */
    @RequestMapping("/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(HttpServletRequest request,
                                           MultipartFile image, HttpServletResponse response)
            throws IOException {
        String url = FileUtils.saveFile(request, image);
        response.setContentType("text/html;charset=utf-8");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("imageUrl", url);

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(map));
        return null;
    }


    /**
     * 网易抄送消息验证接口
     */
    @ResponseBody
    @RequestMapping(value = "/copyMag", method = RequestMethod.POST )
    public JSONObject copyMsg(HttpServletRequest request){

        JSONObject result = new JSONObject();
        try{
            // 获取请求体
            byte[] body = CheckSumBuilder.readBody(request);
            if (body == null) {
                result.put("code", 414);
                return result;
            }
            String contenType = request.getContentType();
            String appKey = request.getHeader("AppKey");
            String nonce = request.getHeader("Nonce");
            String curTime = request.getHeader("CurTime");
            String checkSum = request.getHeader("CheckSum");

            // 将请求体转成String格式，并打印
            String requestBody = new String(body, "utf-8");
            // 获取计算过的md5及checkSum
            String verifyMD5 = CheckSumBuilder.getMD5(requestBody);
            String verifyChecksum = CheckSumBuilder.getCheckSum(Constants.WANGYI_APP_SECRET, verifyMD5, curTime);
            //比较md5、checkSum是否一致，以及后续业务处理
            if(verifyChecksum.equals(checkSum)){
                result.put("code",414);
            }else{
                result.put("code", 200);
            }
            return result;
        }catch (Exception e){
            result.put("code", 414);
            return result;
        }
    }




}
