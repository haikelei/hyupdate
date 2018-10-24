package com.empathy.utils;

import com.alibaba.fastjson.JSON;
import com.empathy.common.Live;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.junit.jupiter.api.Test;
import sun.misc.BASE64Decoder;


import javax.net.ssl.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.SystemColor.text;

/**
 * Created by MI on 2018/7/1.
 */
public class VoiceUtils {

    private final static int AppID = 1107010384;
    private final static String AppKey = "ThucAuTdCprKKsBi";
    //private final static String AppKey = "a95eceb1ac8c24ee28b70f7dbba912bf";



    /**
     * 在线语音All lab合成接口
     *
     * @param speaker
     * @param text
     * @return
     */
    public  String getVoice(Integer speaker,String text) throws Exception{
        HashMap<String,String> param = new HashMap<>();

        String  url =  "https://api.ai.qq.com/fcgi-bin/aai/aai_tts";
        String time = System.currentTimeMillis()+"";
        time = time.substring(0, time.length() - 3);

        int timeH = Integer.parseInt(time);
        String nonceStr = MD5Utils.encrypt(time).substring(0,30);

       param.put("app_id",AppID+"");
       param.put("speaker",1+"");
       param.put("format",2+"");
       param.put("volume",0+"");
       param.put("speed",100+"");
       param.put("text",text);
       param.put("aht",0+"");
       param.put("apc",58+"");
       param.put("time_stamp",timeH+"");
       param.put("nonce_str",nonceStr);
       param.put("sign","");

        String params = "?app_id="+AppID+"&&speaker="+1+"&&format="+2+"&&volume="+0+"&&speed="+100+"&&text="+text+"&&aht="+0+"&&" +
                "apc="+58+"&&time_stamp="+timeH+"&&nonce_str="+nonceStr;
        String reqSign =  SignUtils.getSignature(param);
//        String reqSign = "";
        System.out.println(reqSign.length()+"======"+reqSign);
        param.put("sign",reqSign);
        params+= "&&sign="+reqSign;

        HttpClient httpClient = WebClientDevWrapper.wrapClient(new DefaultHttpClient());
        HttpGet httpGet = new HttpGet(url+params);
       // String jsonString = JSON.toJSONString(param);
        //System.out.println(jsonString);
//        httpGet.setURI(params);
        HttpResponse response = httpClient.execute(httpGet);
        String ss = EntityUtils.toString(response.getEntity(), "utf-8");
        return ss;
    }



    /**
     * 在线语音优图合成接口
     *
     * @param speaker
     * @param text
     * @return
     */
    public  String getYtVoice(Integer speaker,String text) throws Exception{
        HashMap<String,String> param = new HashMap<>();

        String  url =  "https://api.ai.qq.com/fcgi-bin/aai/aai_tta";
        String time = System.currentTimeMillis()+"";
        time = time.substring(0, time.length() - 3);

        int timeH = Integer.parseInt(time);
        String nonceStr = MD5Utils.encrypt(time).substring(0,30);

       param.put("app_id",AppID+"");
       param.put("model_type",0+"");
       param.put("speed","1");
       param.put("text",text);
       param.put("time_stamp",timeH+"");
       param.put("nonce_str",nonceStr);
       param.put("sign","");

        String params = "?app_id="+AppID+"&&speaker="+speaker+"&&speed="+1+"&&text="+text+"&&time_stamp="+timeH+"&&nonce_str="+nonceStr;
        String reqSign =  SignUtils.getSignature(param);
//        String reqSign = "";
        System.out.println(reqSign.length()+"======"+reqSign);
        param.put("sign",reqSign);
        params+= "&&sign="+reqSign;

        HttpClient httpClient = WebClientDevWrapper.wrapClient(new DefaultHttpClient());
        HttpGet httpGet = new HttpGet(url+params);
       // String jsonString = JSON.toJSONString(param);
        //System.out.println(jsonString);
//        httpGet.setURI(params);
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            try {
                /**读取服务器返回过来的json字符串数据**/
                HttpEntity entity = response.getEntity();
                if (entity != null) {

                    String s =EntityUtils.toString(entity);
                    ObjectMapper mapper = new ObjectMapper();
                    //JSON ----> JsonNode
                    JsonNode jsonNode = mapper.readTree(s);
                    String  voice = jsonNode.findValue("voice").asText();

                    return  voice;

                }
                return "";
            }catch (Exception e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                return "";
            }
        }else {

            return "";
        }
    }



    public static void toFile(String base64Code, String targetPath)
            throws Exception {

        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }


    public static void getMp3(String music,String targetPath) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] data = decoder.decodeBuffer(music);
            FileOutputStream out = new FileOutputStream(targetPath);
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception ex) {

        }
    }


    public static void main(String[] args) throws Exception{

        String hahaa =new VoiceUtils().getYtVoice(6, "黄从其，你得鸡鸡只有六厘米");
        getMp3(hahaa,"D:\\\\th\\3.mp3");
//        String hahaa =new VoiceUtils().getVoice(100, "妈妈，匹克学会了撸管，像江总一样就爱上了撸管");
//        getMp3(hahaa,"D:\\\\th\\3.mp3");
        //System.out.println(hahaa);
    }




    public static class WebClientDevWrapper {

        public static org.apache.http.client.HttpClient wrapClient(org.apache.http.client.HttpClient base) {
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                X509TrustManager tm = new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
                };
                ctx.init(null, new TrustManager[] { tm }, null);
                SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                SchemeRegistry registry = new SchemeRegistry();
                registry.register(new Scheme("https", 443, ssf));
                ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
                return new DefaultHttpClient(mgr, base.getParams());
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
//
//    public static Map<String, Object> getSortedData(Map<String, Object> map){
//        if(map == null){
//            return null;
//        }
//
//        Map<String, Object> m = new TreeMap<String, Object>();
//        for(Map.Entry<String, Object> o : map.entrySet()){
//            m.put(o.getKey(), o.getValue());
//        }
//        return m;
//    }
//
//
//    public  String getReqSign(Map params, String appKey)
//    {
//
//        //params.put("app_key",appKey);
//        // 1. 字典升序排序
//        Collection<String> keyset= params.keySet();
//        List list=new ArrayList<String>(keyset);
//        Collections.sort(list);
//
//        // 2. 拼按URL键值对
//        String str = "";
//
//        for(int i=0;i<list.size();i++){
//            if(list.get(i)!=""){
//                str+=list.get(i)+"="+ URLEncoder.encode(params.get(list.get(i)).toString())+"&";
//            }
//        }
//
//        // 3. 拼接app_key
//        str += "app_key="+appKey;
//        //echo $str;
//        // 4. MD5运算+转换大写，得到请求签名
//        str = md52(str);
//        String sign = str.toUpperCase();
//        return sign;
//    }
//
//
//
//    public String md52(String txt) {
//        try{
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(txt.getBytes("GBK"));    //问题主要出在这里，Java的字符串是unicode编码，不受源码文件的编码影响；而PHP的编码是和源码文件的编码一致，受源码编码影响。
//            StringBuffer buf=new StringBuffer();
//            for(byte b:md.digest()){
//                buf.append(String.format("%02x", b&0xff));
//            }
//            return  buf.toString();
//        }catch( Exception e ){
//            e.printStackTrace();
//
//            return null;
//        }
//
//
//    }
//
//
//
//    private String convertString(String str){
//        char[] ch = str.toCharArray();
//        StringBuffer sbf = new StringBuffer();
//        for(int i=0; i< ch.length; i++){
//                sbf.append(charToUpperCase(ch[i]));
//        }
//        return sbf.toString();
//    }
//
//    /**转大写**/
//    private static char charToUpperCase(char ch) {
//        if (ch <= 122 && ch >= 97) {
//            ch -= 32;
//        }
//        return ch;
//
//    }
//
//




}
