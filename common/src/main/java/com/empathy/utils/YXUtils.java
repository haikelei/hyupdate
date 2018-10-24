package com.empathy.utils;

import com.empathy.common.BaseChannel;
import com.empathy.common.CreateLiveChannel;
import com.empathy.common.Live;
import com.empathy.common.RspResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MI on 2018/6/26.
 */
public class YXUtils {
    private final static String appKey = "b224307fdfbcc6ca13e19b03942069de";
    private final static String appSecret = "c707e717306c";



    /**
     * 创建能传文件的token和accid
     *创建终端用户
     */
    public  static String getTokenAndAccid(String accid){
        String error = "";
        try {
            SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
            DefaultHttpClient httpClient = new DefaultHttpClient();
            String url = "https://vcloud.163.com/app/vod/thirdpart/user/create";
            HttpPost httpPost = new HttpPost(url);

            String nonce =  "1";
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

            // 设置请求的参数
            StringEntity params = new StringEntity("{\"accid\":\""+accid+"\", \"type\":1}",Consts.UTF_8);
            //           StringEntity params = new StringEntity("{\"name\":\"5\", \"type\":0}",Consts.UTF_8);

            httpPost.setEntity(params);

            // 设置请求的参数
            String strResult1 = "";
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {

                        String s =EntityUtils.toString(entity);
                        ObjectMapper mapper = new ObjectMapper();
                        //JSON ----> JsonNode
                        JsonNode jsonNode = mapper.readTree(s);
                        String  cid =jsonNode.findValue("accid").textValue();
                        String token = jsonNode.findValue("token").textValue();

                        return  cid+";"+token;

                    }
                    return error;
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    return error;
                }
            }else {

                return error;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return error;
        }
    }





        /**
         * 创建im的账号，传入用户id 和姓名 得到token
         *
         * @param id
         * @param name
         * @return
         */
    public static String getYxToken(Long id,String name) {
        String error = "请求失败！";
        try {

            DefaultHttpClient
                    httpClient = new DefaultHttpClient();
            String url = "https://api.netease.im/nimserver/user/create.action";
            HttpPost httpPost = new HttpPost(url);
            String nonce = "12345";
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码

            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 设置请求的参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("accid", id + ""));
            nvps.add(new BasicNameValuePair("name", name));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

            String strResult1 = "";
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {

                        String s =EntityUtils.toString(entity);
                        ObjectMapper mapper = new ObjectMapper();
                        //JSON ----> JsonNode
                        JsonNode jsonNode = mapper.readTree(s);
                        JsonNode listSource = jsonNode.findValue("token");
                        return  listSource.textValue();

                    }
                    return error;
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    return error;
                }
            }else {

                return error;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return error;
        }

    }


    /**
     * 获取频道
     *
     */
    public  static BaseChannel getChannel(String id){
        BaseChannel error = new BaseChannel();
        try {
            SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
            DefaultHttpClient httpClient = new DefaultHttpClient();
            String url = "https://vcloud.163.com/app/channel/create";
            HttpPost httpPost = new HttpPost(url);

            String nonce =  "1";
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

            // 设置请求的参数
            StringEntity params = new StringEntity("{\"name\":\""+id+"\", \"type\":0}",Consts.UTF_8);
            //           StringEntity params = new StringEntity("{\"name\":\"5\", \"type\":0}",Consts.UTF_8);

            httpPost.setEntity(params);

            // 设置请求的参数
            String strResult1 = "";
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {

                        String s =EntityUtils.toString(entity);
                        ObjectMapper mapper = new ObjectMapper();
                        //JSON ----> JsonNode
                        JsonNode jsonNode = mapper.readTree(s);
                        String  cid =jsonNode.findValue("cid").textValue();
                        String name = jsonNode.findValue("name").textValue();
                        String pushUrl = jsonNode.findValue("pushUrl").textValue();
                        String httpPullUrl = jsonNode.findValue("httpPullUrl").textValue();
                        String hlsPullUrl = jsonNode.findValue("hlsPullUrl").textValue();
                        String rtmpPullUrl = jsonNode.findValue("rtmpPullUrl").textValue();
                        BaseChannel live = new BaseChannel();
                        live.setCid(cid);
                        live.setName(name);
                        live.setPushUrl(pushUrl);
                        live.setHlsPullUrl(hlsPullUrl);
                        live.setHttpPullUrl(httpPullUrl);
                        live.setRtmpPullUrl(rtmpPullUrl);
                        return  live;

                    }
                    return error;
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    return error;
                }
            }else {

                return error;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return error;
        }



    }




    /**
     * 获取直播间的
     *
     */
    public static Live getYxLive(String id, String name,String url1) {

        Live error = new Live();

        if(url1==""&&url1==null){
            return error;
        }

        try {
            DefaultHttpClient
                    httpClient = new DefaultHttpClient();
            String url = "https://api.netease.im/nimserver/chatroom/create.action";
            HttpPost httpPost = new HttpPost(url);
            String nonce = "12345";
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码

            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 设置请求的参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("creator", id));
            nvps.add(new BasicNameValuePair("name", name));
            nvps.add(new BasicNameValuePair("queuelevel", "1"));
            nvps.add(new BasicNameValuePair("broadcasturl", url1));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

            String strResult1 = "";
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {

                        String s =EntityUtils.toString(entity);
                        ObjectMapper mapper = new ObjectMapper();
                        //JSON ----> JsonNode
                        JsonNode jsonNode = mapper.readTree(s);
                        String  roomid = jsonNode.findValue("roomid").asText();
                        boolean valid = Boolean.getBoolean(jsonNode.findValue("valid").textValue());
                        String announcement = jsonNode.findValue("announcement").textValue();
                        String name1 = jsonNode.findValue("name").textValue();
                        String broadcasturl = jsonNode.findValue("broadcasturl").textValue();
                        String creator = jsonNode.findValue("creator").textValue();
                        Live live = new Live();
                        live.setAnnouncement(announcement);
                        live.setBroadcasturl(broadcasturl);
                        live.setCreator(creator);
                        live.setName(name1);
                        live.setRoomId(roomid);
                        live.setValid(valid);
                        return  live;

                    }
                    return error;
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    return error;
                }
            }else {

                return error;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return error;
        }
    }


    public static CreateLiveChannel createLive(String id, String name){
        CreateLiveChannel error = new CreateLiveChannel();
        BaseChannel channel = getChannel(id+"");
        if(channel.getCid()==null){
            return  error;
        }
        Live live = getYxLive(id,name,channel.getPushUrl());
        if(live.getRoomId()==null){
            return error;
        }
        CreateLiveChannel createLiveChannel = new CreateLiveChannel();
        createLiveChannel.setBaseChannel(channel);
        createLiveChannel.setLive(live);
        return createLiveChannel;
    }


    public static void main(String[] args) {

        String voice = getVoice((long) 1940248133);
        System.out.println(voice);
    }


    public static String getVoice(Long sign) {
        String error = "";
        try {
            SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
            DefaultHttpClient httpClient = new DefaultHttpClient();
            String url = "https://vcloud.163.com/app/vod/video/get";
            HttpPost httpPost = new HttpPost(url);

            String nonce =  "1";
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

            // 设置请求的参数
            StringEntity params = new StringEntity("{\"vid\":"+sign+"}",Consts.UTF_8);

            httpPost.setEntity(params);

            // 设置请求的参数
            String strResult1 = "";
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String s =EntityUtils.toString(entity);
                        System.out.println(s);
                        ObjectMapper mapper = new ObjectMapper();
                        //JSON ----> JsonNode
                        JsonNode jsonNode = mapper.readTree(s);
                        String  hdHlsUrl =jsonNode.findValue("origUrl").textValue();


                        return  hdHlsUrl;

                    }
                    return error;
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    return error;
                }
            }else {

                return error;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return error;
        }

    }
}
