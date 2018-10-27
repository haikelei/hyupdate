package com.empathy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.empathy.common.RspResult;
import com.empathy.dao.BaseDealDao;
import com.empathy.dao.BaseMemberDao;
import com.empathy.dao.UserMemberDao;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserMember;
import com.empathy.domain.user.bo.MemberAddBo;
import com.empathy.pay.alipay.AlipayUtils;
import com.empathy.pay.alipay.RefundContent;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IUserMemberService;
import com.empathy.utils.CodeUtils;
import com.empathy.utils.XmlUtil;
import com.empathy.utils.wx.WXPayConstants;
import com.empathy.utils.wx.WXPayUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by MI on 2018/4/18.
 */
@Service
public class UserMemberService extends AbstractBaseService implements IUserMemberService {

    @Autowired
    private UserMemberDao userMemberDao;

    @Autowired
    private BaseMemberDao baseMemberDao;

    @Autowired
    private BaseDealDao baseDealDao;



    //----------------------------微信账号--------------------------------

    private static final String ORDER_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 统一下单

//    private static final String WECHAT_APP_ID ="wxc27fe76af5a55120";
//
//    private static final String MCH_ID = "1496007892";
//    //商户平台密钥
//    public static final String API_SECRET = "WFFXB8wHrg4IRSagNhzZ7iIJo3QNC91h";
//
//    private static final String CALLBACK_URL_WECHAT = "http://pay.hy960.com/alipay/wechatCallBack/";

    private static final String WECHAT_APP_ID ="wxad49888a3aa32ae8";

    private static final String MCH_ID = "1517033091";
    //商户平台密钥
    public static final String API_SECRET = "gW7Zr2ry0mei7h1qCo35ormt4ZZC0ki8";

    private static final String CALLBACK_URL_WECHAT = "http://47.106.196.89:8080/hy/alipay/wechatCallBack/";


    @Override
    public RspResult tobeMember(MemberAddBo bo) {


        BaseMember baseMember = baseMemberDao.findById(bo.getUserId());
        if(baseMember==null){
            return errorMo();
        }
        int payType = bo.getPayType();
        BaseDeal baseDeal = new BaseDeal();
        baseDeal.setType(5);
        baseDeal.setCode(CodeUtils.getCode());
        baseDeal.setStatus(0);
        baseDeal.setUserId(baseMember.getId());
        baseDeal.setMoney(bo.getMoney());
        baseDealDao.save(baseDeal);
        //处理用户钱包模块
        if(payType==0) {
            //支付宝支付
            Map<String ,Object> map = new HashMap<>();
            RefundContent refundContent = new RefundContent();

            refundContent.setTrade_no( System.currentTimeMillis()+"");
            refundContent.setOut_trade_no(baseDeal.getId()+"");
            // map.put("map",refundContent);
            String al = AlipayUtils.buildPayInfo("杭州华语之声传媒有限公司", bo.getMoney().doubleValue()/10+"", map, baseDeal.getId() + "");
            return success(al);

        }else if(payType==1){
            String body = "购买会员";
            String spbillCreateIp = "47.106.196.89";
            String tradeType = "APP";

            BigDecimal money = new BigDecimal(bo.getMoney().doubleValue()/10);
            Map<String, String> restmap = null;
            String total_fee = money.multiply(BigDecimal.valueOf(100)) .setScale(0, BigDecimal.ROUND_HALF_UP).toString();
            Map<String, String> parm = new HashMap<>();
            parm.put("appid", WECHAT_APP_ID);
            parm.put("mch_id", MCH_ID);
            //parm.put("device_info", "WEB");
            String nonceStr = WXPayUtil.generateNonceStr();
            parm.put("nonce_str", nonceStr);
            parm.put("body", body);
            //parm.put("attach", "Crocutax");   //附加数据
            parm.put("out_trade_no",baseDeal.getId()+"");
            parm.put("total_fee", total_fee);
            parm.put("spbill_create_ip", spbillCreateIp);
            parm.put("notify_url", CALLBACK_URL_WECHAT); //微信服务器异步通知支付结果地址  下面的order/notify 方法
            parm.put("trade_type", tradeType);

            String sign = null;
            try {
                sign = WXPayUtil.generateSignature(parm, API_SECRET, WXPayConstants.SignType.MD5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("sign:" + sign);

            parm.put("sign", sign);

            String xml = "<xml>" +
                    "<appid>" + WECHAT_APP_ID + "</appid>" +
                    "<body>" + body + "</body>" +
                    "<mch_id>" + MCH_ID + "</mch_id>" +
                    "<nonce_str>" + nonceStr + "</nonce_str>" +
                    "<notify_url>" + CALLBACK_URL_WECHAT + "</notify_url>" +
                    "<out_trade_no>" + baseDeal.getId() + "</out_trade_no>" +
                    "<spbill_create_ip>" + spbillCreateIp + "</spbill_create_ip>" +
                    "<total_fee>" + total_fee + "</total_fee>" +
                    "<trade_type>" + tradeType + "</trade_type>" +
                    "<sign>" + sign + "</sign>" +
                    "</xml>";

            System.out.println("xmlFormat:" + xml);

            String prepayId = getPrepayId(ORDER_PAY, xml);
            if (StringUtils.isEmpty(prepayId)) {
                return  error(1, "调用微信统一下单接口失败");
            }

            SortedMap<String, String> finalpackage = new TreeMap<String, String>();
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String packages = "Sign=WXPay";
            finalpackage.put("appid", WECHAT_APP_ID);
            finalpackage.put("noncestr", nonceStr);
            finalpackage.put("timestamp", timestamp);
            finalpackage.put("partnerid", MCH_ID);
            finalpackage.put("package", packages);
            finalpackage.put("prepayid", prepayId);
            String finalsign  = null ;
            try {
                finalsign = WXPayUtil.generateSignature(finalpackage, API_SECRET, WXPayConstants.SignType.MD5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HashMap<String, String> resultMap = new HashMap();
            resultMap.put("appId", WECHAT_APP_ID);
            resultMap.put("nonceStr", nonceStr);
            resultMap.put("timeStamp", timestamp);
            resultMap.put("package", packages);
            resultMap.put("partnerId", MCH_ID);
            resultMap.put("prepayId", prepayId);
            resultMap.put("sign", finalsign);
            System.out.println("结果码:"+resultMap);

//            String al = WXPayUtil.buildOrderParam(resultMap);
            String al = JSON.toJSONString(resultMap);

            return success(al);
        }
        return errorNo();
    }

        /**
         * 获取
         *
         * @param url
         * @param xmlParam
         * @return
         */
    public static String getPrepayId(String url, String xmlParam) {
        Map v_map = new HashMap();
        int result;
        String respost = null;
        String prepay_id = "";
        HttpClient client = new HttpClient();
//        //设置超时时间
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(5000);
        client.getParams().setParameter("http.socket.timeout", new Integer(60000));

        PostMethod post = new PostMethod(url);
        try {
            //设置参数编码
            post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            post.setRequestBody(xmlParam);//这里添加xml字符串
            client.executeMethod(post);
            //返回的结果集
            respost = IOUtils.toString(new InputStreamReader(post.getResponseBodyAsStream()));
            v_map = XmlUtil.doXMLParse(respost);
            prepay_id = (String) v_map.get("prepay_id");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放链接
            post.releaseConnection();
            ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
        }
        return prepay_id;
    }


    public void tobeMemberForPay(Long id){

        BaseMember baseMember = baseMemberDao.findById(id);

        int count = userMemberDao.findCountByUserId(id);
        baseMember.setLastRevampTime(System.currentTimeMillis());
        baseMember.setMemberStatus(1);
        baseMemberDao.update(baseMember);
        if (count > 0) {

            System.out.println("会员续费: " + baseMember.getId() );
            UserMember userMember = userMemberDao.findByUserId(baseMember.getId());
            userMember.setEndTime(30 * 24 * 60 * 60 * 1000 + userMember.getEndTime());
            userMember.setLastRevampTime(System.currentTimeMillis());
            userMemberDao.update(userMember);

        } else {

            System.out.println("会员新增: " + baseMember.getId() );
            UserMember userMember = new UserMember();
            userMember.setUserId(baseMember.getId());
            userMember.setEndTime(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000);
            userMemberDao.save(userMember);
        }
    }



    @Override
    public RspResult save(UserMember entity) {
        return null;
    }

    @Override
    public UserMember findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(UserMember entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
