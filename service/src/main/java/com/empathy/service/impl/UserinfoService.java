package com.empathy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.empathy.cache.CacheUtils;
import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.bidding.File;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.enumer.LoginType;
import com.empathy.domain.file.bo.FileCarBo;
import com.empathy.domain.live.BaseLive;
import com.empathy.domain.live.BaseLiveChannel;
import com.empathy.domain.user.*;
import com.empathy.domain.user.bo.*;
import com.empathy.pay.alipay.AlipayUtils;
import com.empathy.pay.alipay.RefundContent;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IUserinfoService;
import com.empathy.utils.*;
import com.empathy.utils.wx.WXPayConstants;
import com.empathy.utils.wx.WXPayUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by MI on 2018/4/13.
 */
@Service
public class UserinfoService extends AbstractBaseService implements IUserinfoService {

    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private BaseMemberDao baseMemberDao;

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private HostProveDao hostProveDao;
    @Autowired
    private BaseDealDao baseDealDao;
    @Autowired
    private BaseLiveDao baseLiveDao;

    @Autowired
    private UserMemberDao userMemberDao;

    @Autowired
    private BaseLiveChannelDao baseLiveChannelDao;
    @Autowired
    private UserFocusDao userFocusDao;

    @Autowired
    private PrivateChatDao privateChatDao;

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
    public RspResult delPrivateChat(long id) {
        privateChatDao.delById(id);
        return success();
    }

    @Override
    public RspResult findPrivateChat(PrivateChatFindBo chat) {

        List<PrivateChat> list = privateChatDao.list(chat);
        int count = privateChatDao.count(chat.getUserId());

        return success(count,list);
    }

    @Override
    public RspResult addPrivateChat(PrivateChatBo chat) {
        PrivateChat privateChat = new PrivateChat();
        privateChat.setChatUserId(chat.getUserId());
        privateChat.setUserId(chat.getId());
        privateChat.setContent(chat.getContent());
        privateChatDao.save(privateChat);
        return success(privateChat);
    }

    @Override
    public RspResult findMainForOtherPeroson(FindMainForOtherBo bo) {
        BaseMember baseMember = baseMemberDao.findById(bo.getUserId());
        FileCarBo fileCarBo = new FileCarBo();
        fileCarBo.setType("user");
        fileCarBo.setPurposeId(baseMember.getId());
        File file = fileDao.findFileByPurposeIdAndType(fileCarBo);
        if(file!=null){
            baseMember.setUrl(file.getLocation());
        }

        int count = userFocusDao.findCountByIds(bo.getId(),bo.getUserId());
        if(count>0){
            baseMember.setFocusStatus(1);
        }else {
            baseMember.setFocusStatus(0);
        }

        return success(baseMember);
    }

    @Override
    public RspResult addPasswordForPay(PasswordForPayAddBo bo) {
        UserMoney userMoney = userMoneyDao.findByUserId(bo.getUserId());
        if(userMoney==null){
            return errorMo();
        }
        if(StringUtil.isNotEmpty(userMoney.getPassword())){
            return error(1,"支付密码已存在！");
        }
        userMoney.setPassword(MD5Utils.encrypt(bo.getPassword()));
        userMoneyDao.update(userMoney);
        return success(userMoney);
    }

    @Override
    public RspResult addUserMoney(UserMoneyAddBo bo) {
        BaseMember baseMember = baseMemberDao.findById(bo.getId());
        if(baseMember==null){
            return errorMo();
        }
        int payType = bo.getPayType();
        BaseDeal baseDeal = new BaseDeal();
        baseDeal.setType(0);
        baseDeal.setCode(CodeUtils.getCode());
        baseDeal.setStatus(0);
        baseDeal.setUserId(bo.getId());
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
            String body = "充值";
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

    @Override
    public RspResult findMoney(Long id) {
        BaseMember baseMember = baseMemberDao.findById(id);
        if(baseMember==null){
            return errorMo();
        }
        UserMoney byUserId = userMoneyDao.findByUserId(id);

        return success(byUserId);
    }

    //测试
    @Override
    public RspResult add(RegistBo bo) {
        Userinfo userinfo = new Userinfo();
        userinfo.setPassword("1111");
        userinfo.setWechat("1111");
        userinfo.setToken("1111");
        userinfo.setLoginType("phone");
        userinfo.setAddress("111");
        userinfoDao.save(userinfo);
        return success(userinfo);
    }

    @Override
    public RspResult updMember(MemberUpdBo bo) {
        BaseMember baseMember = baseMemberDao.findById(bo.getId());
        if (StringUtil.isNotEmpty(bo.getAddress())) {
            baseMember.setAddress(bo.getAddress());
        }

        if (StringUtil.isNotIntegerEmpty(bo.getSex())) {
            baseMember.setSex(bo.getSex());
        }
        if (StringUtil.isNotEmpty(bo.getIntro())) {
            baseMember.setIntro(bo.getIntro());
        }
        if (StringUtil.isNotEmpty(bo.getUsername())) {
            baseMember.setUsername(bo.getUsername());
        }
        baseMember.setLastRevampTime(System.currentTimeMillis());
        baseMemberDao.update(baseMember);
        return success(baseMember);
    }

    //验证修改交易密码验证码
    private Boolean checkUpdPasswordCode(String code, String phone) {
        Object o = CacheUtils.get("sms_upd_password" + phone);
        if (!code.equals(o + "")) {
            return true;
        }
        return false;
    }


    //修改交易密码
    @Override
    public RspResult updDealPassword(PasswordDealUpdBo bo) {
        BaseMember member = baseMemberDao.findById(bo.getId());
        String phone = member.getPhone();
        if (checkUpdPasswordCode(bo.getCode(), phone)) {
            return new RspResult("验证码错误", 1);
        }
        String newPassword = MD5Utils.encrypt(bo.getPassword());
        UserMoney userMoney = userMoneyDao.findByUserId(member.getId());
        if (userMoney.getPassword().equals(newPassword)) {
            return new RspResult("新密码不能与旧密码相同", 1);
        }
        userMoney.setLastRevampTime(System.currentTimeMillis());
        userMoney.setPassword(newPassword);
        userMoneyDao.update(userMoney);
        return new RspResult();
    }

    @Override
    public RspResult addDealPassword(PasswordDealAddBo bo) {
        UserMoney userMoney = userMoneyDao.findByUserId(bo.getId());
        userMoney.setPassword(MD5Utils.encrypt(bo.getPassword()));
        userMoney.setLastRevampTime(System.currentTimeMillis());
        userMoneyDao.update(userMoney);
        return success(userMoney);
    }

    @Override
    public RspResult forgetPassword(ForgetPasswordBo bo) {
        if (checkChangePassword(bo.getCode(), bo.getPhone())) {
            return new RspResult("验证码错误", 1);
        }
        Userinfo userinfo = userinfoDao.findByPhone(bo.getPhone());
        if (userinfo == null) {
            return new RspResult("手机号不存在", 1);
        }
        if (userinfo.getPassword().equals(MD5Utils.encrypt(bo.getPassword()))) {
            return new RspResult("密码不能与上次密码相同", 1);
        }
        userinfo.setPassword(MD5Utils.encrypt(bo.getPassword()));
        userinfo.setLastRevampTime(System.currentTimeMillis());
        userinfoDao.update(userinfo);
        return new RspResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RspResult bindPhone(BindPhoneBo bo) {
        Boolean b = checkBindbinCode(bo.getCode(), bo.getPhone());
        if (b) {
            return new RspResult("验证码错误", 1);
        }
        FindPhone findPhone = new FindPhone(bo.getPhone(), "phone");
        int countByPhone = userinfoDao.findCountByPhone(findPhone);
        //有此手机号
        if (countByPhone > 0) {


            Userinfo userinfo = userinfoDao.findByPhone(bo.getPhone());
            BaseMember baseMember = baseMemberDao.findById(userinfo.getMemberId());
            if (baseMember.getDelFlag() == 1) {
                return new RspResult("您的账号已被冻结，请联系客服！", 1);
            }
            TokenFindBo tokenFindBo = new TokenFindBo();
            tokenFindBo.setLoginType(bo.getLoginType());
            tokenFindBo.setToken(bo.getToken());
            Userinfo user = userinfoDao.findByToken(tokenFindBo);
            user.setMemberId(userinfo.getMemberId());
            userinfoDao.update(user);
            BaseMember byId = baseMemberDao.findById(userinfo.getMemberId());
            return success(byId);
        } else {
            BaseMember baseMember = new BaseMember();
            baseMember.setPhone(bo.getPhone());
            baseMember.setSex(1);
            baseMember.setLevel(1);
            baseMember.setUsername(new NameUtils().getName());
            baseMember.setProveStatus(0);
            baseMember.setMemberStatus(0);
            baseMemberDao.save(baseMember);
            String yxToken = YXUtils.getYxToken(baseMember.getId(), baseMember.getUsername());
            baseMember.setToken(yxToken);
            baseMemberDao.update(baseMember);
            Userinfo userinfo = new Userinfo();
            userinfo.setLastRevampTime(System.currentTimeMillis());
            userinfo.setToken(bo.getPhone());
            userinfo.setLoginType("phone");
            userinfo.setMemberId(baseMember.getId());
            userinfo.setCreateTime(System.currentTimeMillis());
            userinfoDao.save(userinfo);
            TokenFindBo tokenFindBo = new TokenFindBo();
            tokenFindBo.setLoginType(bo.getLoginType());
            tokenFindBo.setToken(bo.getToken());
            Userinfo user = userinfoDao.findByToken(tokenFindBo);
            user.setMemberId(userinfo.getMemberId());
            userinfoDao.update(user);

            UserMoney userMoney = new UserMoney();
            userMoney.setMoney(BigDecimal.valueOf(0));
            userMoney.setCreateTime(System.currentTimeMillis());
            userMoney.setLastRevampTime(System.currentTimeMillis());
            userMoney.setUserId(baseMember.getId());

            userMoneyDao.update(userMoney);
            File file = new File();
            file.setLocation("/image/1.png");
            file.setPurposeId(baseMember.getId());
            file.setType("image");
            file.setPurpose("user");
            fileDao.insert(file);
            baseMember.setUrl(file.getLocation());
            return success(baseMember);
        }


    }

    @Override
    public RspResult addPassword(PasswordAddBo bo) {
        Userinfo userinfo = userinfoDao.findByMemberId(bo.getMemberId());
        userinfo.setPassword(MD5Utils.encrypt(bo.getPassword()));
        userinfo.setLastRevampTime(System.currentTimeMillis());
        userinfoDao.update(userinfo);
        return new RspResult();
    }



    @Override
    public RspResult findDetailById(Long id) {
        BaseMember baseMember = baseMemberDao.findById(id);
        if(baseMember.getProveStatus()==0){
            baseMember.setLiveStatus(0);
            int countForUpd =  hostProveDao.findCountByUpd(baseMember.getId());
            //审核中，变更状态，无法上传主播申请
            if(countForUpd>0){
                baseMember.setProveStatus(2);
            }
            baseMember.setProveLevel(0);
        }else {
            BaseLive baseLive = baseLiveDao.findByUserId(id);
            baseMember.setProveLevel(baseLive.getLiveStatus());
            baseMember.setLiveId(baseLive.getId());
            baseMember.setLevel(baseLive.getLevel());
            BaseLiveChannel baseLiveChannel = baseLiveChannelDao.findByUserId(id);
            if(baseLiveChannel==null){
                baseMember.setPushUrl("");
                baseMember.setRoomId("");
                baseMember.setRtmpPullUrl("");
            }else {
                baseMember.setPushUrl(baseLiveChannel.getPushUrl());
                baseMember.setRtmpPullUrl(baseLiveChannel.getRtmpPullUrl());
                baseMember.setRoomId(baseLiveChannel.getRoomId());
                baseMember.setAccid(baseLiveChannel.getCid());

                if(StringUtil.isNotEmpty(baseLiveChannel.getFileAccid())){

                    baseMember.setFileAccid(baseLiveChannel.getFileAccid());
                    baseMember.setFileToken(baseLiveChannel.getFileToken());
                }
            }
        }

        FileCarBo fileCarBo = new FileCarBo();
        fileCarBo.setType("user");
        fileCarBo.setPurposeId(baseMember.getId());
        File file = fileDao.findFileByPurposeIdAndType(fileCarBo);
        if(file!=null){
            baseMember.setUrl(file.getLocation());
        }

        UserMoney byUserId = userMoneyDao.findByUserId(baseMember.getId());
        if(StringUtil.isNotEmpty(byUserId.getPassword())){

            baseMember.setPasswordStatus(1);
        }else {
            baseMember.setPasswordStatus(0);
        }
        UserMember byUserId1 = userMemberDao.findByUserId(id);
        if(byUserId1==null){
            baseMember.setEndTime((long) 0);
        }else {
            baseMember.setEndTime(byUserId1.getEndTime());
        }




        return success(baseMember);
    }

    @Override
    public RspResult login(LoginBo bo) {

        String way = LoginType.getWay(bo.getLoginType());
        //如果找不到文档类型对应路径配置
        if (StringUtils.isEmpty(way)) {
            return new RspResult("此种登录方式尚未开通", 1);
        }
        if (way.equals("phone")) {

            //手机号登录
            String phone = bo.getPhone();

            FindPhone findPhone = new FindPhone(bo.getPhone(), "phone");
            int count = userinfoDao.findCountByPhone(findPhone);
//            int countByFreeze =  userinfoDao.findCountByPhoneByFreeze(findPhone);
            if (count < 1) {
//                if(countByFreeze==1){
//                    return new RspResult("您的账号已被冻结，请联系客服！",1);
//                }

                return new RspResult("手机号不存在", 1);
            }
            Userinfo userinfo = userinfoDao.findByPhone(phone);
            if(!MD5Utils.encrypt(bo.getPassword()).equals(userinfo.getPassword())){
                return new RspResult("密码错误",1);
            }
            BaseMember baseMember = baseMemberDao.findById(userinfo.getMemberId());
            if (baseMember.getDelFlag() == 1) {
                return new RspResult("您的账号已被冻结，请联系客服！", 1);
            }
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setType("user");
            fileCarBo.setPurposeId(baseMember.getId());
            File file = fileDao.findFileByPurposeIdAndType(fileCarBo);
            if(file!=null){
                baseMember.setUrl(file.getLocation());
            }

            if(baseMember.getProveStatus()==0){
                baseMember.setLiveStatus(0);
                int countForUpd =  hostProveDao.findCountByUpd(baseMember.getId());
                //审核中，变更状态，无法上传主播申请
                if(countForUpd>0){
                    baseMember.setProveStatus(2);
                }
                baseMember.setProveLevel(0);
            }else {
                BaseLive baseLive = baseLiveDao.findByUserId(baseMember.getId());
                baseMember.setProveLevel(baseLive.getLiveStatus());
                baseMember.setLevel(baseLive.getLevel());
                baseMember.setLiveId(baseLive.getId());
                BaseLiveChannel baseLiveChannel = baseLiveChannelDao.findByUserId(baseMember.getId());
                if(baseLiveChannel==null){
                    baseMember.setPushUrl("");
                    baseMember.setRoomId("");
                    baseMember.setRtmpPullUrl("");
                }else {
                    baseMember.setPushUrl(baseLiveChannel.getPushUrl());
                    baseMember.setRtmpPullUrl(baseLiveChannel.getRtmpPullUrl());
                    baseMember.setRoomId(baseLiveChannel.getRoomId());
                    baseMember.setAccid(baseLiveChannel.getCid());
                    if(StringUtil.isNotEmpty(baseLiveChannel.getFileAccid())){

                        baseMember.setFileAccid(baseLiveChannel.getFileAccid());
                        baseMember.setFileToken(baseLiveChannel.getFileToken());
                    }

                }
            }

            UserMoney byUserId = userMoneyDao.findByUserId(baseMember.getId());
            if(StringUtil.isNotEmpty(byUserId.getPassword())){

                baseMember.setPasswordStatus(1);
            }else {
                baseMember.setPasswordStatus(0);
            }
            UserMember byUserId1 = userMemberDao.findByUserId(baseMember.getId());
            if(byUserId1==null){
                baseMember.setEndTime((long) 0);
            }else {
                baseMember.setEndTime(byUserId1.getEndTime());
            }


            return success(baseMember);

        } else if (way.equals("qq")) {
            //qq登录
            String token = bo.getToken();
            TokenFindBo tokenFindBo = new TokenFindBo();
            tokenFindBo.setLoginType("qq");
            tokenFindBo.setToken(token);
            int count1 = userinfoDao.findCountByToken(tokenFindBo);
            //三方登录账户不存在 跳转到认证手机号的界面
            if (count1 == 0) {
                Userinfo userinfo = new Userinfo();
                userinfo.setLastRevampTime(System.currentTimeMillis());
                userinfo.setLoginType("qq");
                userinfo.setToken(bo.getToken());
                userinfo.setQq(bo.getQq());
                userinfo.setCreateTime(System.currentTimeMillis());
                userinfoDao.save(userinfo);
                BaseMember baseMember = new BaseMember();
                baseMember.setLogin_status(1);
                return success(baseMember);
            }
            //查看此登录方式有没有手机号
            int count = userinfoDao.findPhoneCountByToken(tokenFindBo);
            if (count == 0) {
                BaseMember baseMember = new BaseMember();
                baseMember.setLogin_status(1);
                return success(baseMember);
            }
            BaseMember baseMember = userinfoDao.findMemberByToken(tokenFindBo);
            if (baseMember.getDelFlag() == 1) {
                return new RspResult("您的账号已被冻结，请联系客服！", 1);
            }
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setType("user");
            fileCarBo.setPurposeId(baseMember.getId());
            File file = fileDao.findFileByPurposeIdAndType(fileCarBo);
            if(file!=null){
                baseMember.setUrl(file.getLocation());
            }
            if(baseMember.getProveStatus()==0){
                baseMember.setLiveStatus(0);
                int countForUpd =  hostProveDao.findCountByUpd(baseMember.getId());
                //审核中，变更状态，无法上传主播申请
                if(countForUpd>0){
                    baseMember.setProveStatus(2);
                }
                baseMember.setProveLevel(0);
            }else {
                BaseLive baseLive = baseLiveDao.findByUserId(baseMember.getId());
                baseMember.setProveLevel(baseLive.getLiveStatus());
                baseMember.setLevel(baseLive.getLevel());
                baseMember.setLiveId(baseLive.getId());
                BaseLiveChannel baseLiveChannel = baseLiveChannelDao.findByUserId(baseMember.getId());
                if(baseLiveChannel==null){
                    baseMember.setPushUrl("");
                    baseMember.setRoomId("");
                    baseMember.setRtmpPullUrl("");
                }else {
                    baseMember.setPushUrl(baseLiveChannel.getPushUrl());
                    baseMember.setRtmpPullUrl(baseLiveChannel.getRtmpPullUrl());
                    baseMember.setRoomId(baseLiveChannel.getRoomId());
                    baseMember.setAccid(baseLiveChannel.getCid());

                    if(StringUtil.isNotEmpty(baseLiveChannel.getFileAccid())){

                        baseMember.setFileAccid(baseLiveChannel.getFileAccid());
                        baseMember.setFileToken(baseLiveChannel.getFileToken());
                    }

                }
            }

            UserMoney byUserId = userMoneyDao.findByUserId(baseMember.getId());
            if(StringUtil.isNotEmpty(byUserId.getPassword())){

                baseMember.setPasswordStatus(1);
            }else {
                baseMember.setPasswordStatus(0);
            }
            UserMember byUserId1 = userMemberDao.findByUserId(baseMember.getId());
            if(byUserId1==null){
                baseMember.setEndTime((long) 0);
            }else {
                baseMember.setEndTime(byUserId1.getEndTime());
            }


            return success(baseMember);

        } else if (way.equals("wechat")) {
            //wechat登录
            String token = bo.getToken();
            TokenFindBo tokenFindBo = new TokenFindBo();
            tokenFindBo.setLoginType("wechat");
            tokenFindBo.setToken(token);
            int count1 = userinfoDao.findCountByToken(tokenFindBo);
            //三方登录账户不存在 跳转到认证手机号的界面
            if (count1 == 0) {
                Userinfo userinfo = new Userinfo();
                userinfo.setLastRevampTime(System.currentTimeMillis());
                userinfo.setLoginType("wechat");
                userinfo.setToken(bo.getToken());
                userinfo.setWechat(bo.getWechat());
                userinfo.setCreateTime(System.currentTimeMillis());
                userinfoDao.save(userinfo);
                BaseMember baseMember = new BaseMember();
                baseMember.setLogin_status(1);
                return success(baseMember);
            }
            //查看此登录方式有没有手机号
            int count = userinfoDao.findPhoneCountByToken(tokenFindBo);
            if (count == 0) {
                BaseMember baseMember = new BaseMember();
                baseMember.setLogin_status(1);
                return success(baseMember);
            }
            BaseMember baseMember = userinfoDao.findMemberByToken(tokenFindBo);
            if (baseMember.getDelFlag() == 1) {
                return new RspResult("您的账号已被冻结，请联系客服！", 1);
            }
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setType("user");
            fileCarBo.setPurposeId(baseMember.getId());
            File file = fileDao.findFileByPurposeIdAndType(fileCarBo);
            if(file!=null){
                baseMember.setUrl(file.getLocation());
            }
            if(baseMember.getProveStatus()==0){
                baseMember.setLiveStatus(0);
                int countForUpd =  hostProveDao.findCountByUpd(baseMember.getId());
                //审核中，变更状态，无法上传主播申请
                if(countForUpd>0){
                    baseMember.setProveStatus(2);
                }
                baseMember.setProveLevel(0);
            }else {
                BaseLive baseLive = baseLiveDao.findByUserId(baseMember.getId());
                baseMember.setProveLevel(baseLive.getLiveStatus());
                baseMember.setLevel(baseLive.getLevel());
                baseMember.setLiveId(baseLive.getId());
                BaseLiveChannel baseLiveChannel = baseLiveChannelDao.findByUserId(baseMember.getId());
                if(baseLiveChannel==null){
                    baseMember.setPushUrl("");
                    baseMember.setRoomId("");
                    baseMember.setRtmpPullUrl("");
                }else {
                    baseMember.setPushUrl(baseLiveChannel.getPushUrl());
                    baseMember.setRtmpPullUrl(baseLiveChannel.getRtmpPullUrl());
                    baseMember.setRoomId(baseLiveChannel.getRoomId());
                    baseMember.setAccid(baseLiveChannel.getCid());

                    if(StringUtil.isNotEmpty(baseLiveChannel.getFileAccid())){

                        baseMember.setFileAccid(baseLiveChannel.getFileAccid());
                        baseMember.setFileToken(baseLiveChannel.getFileToken());
                    }

                }
            }

            UserMoney byUserId = userMoneyDao.findByUserId(baseMember.getId());
            if(StringUtil.isNotEmpty(byUserId.getPassword())){

                baseMember.setPasswordStatus(1);
            }else {
                baseMember.setPasswordStatus(0);
            }
            UserMember byUserId1 = userMemberDao.findByUserId(baseMember.getId());
            if(byUserId1==null){
                baseMember.setEndTime((long) 0);
            }else {
                baseMember.setEndTime(byUserId1.getEndTime());
            }


            return success(baseMember);
        }
        return new RspResult("无此登录方式", 1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RspResult regist(RegistBo bo) {
        Boolean b = checkCode(bo.getCode(), bo.getPhone());
        if (b) {
            return new RspResult("验证码错误", 1);
        }
        FindPhone findPhone = new FindPhone(bo.getPhone(), "phone");
        int count = userinfoDao.findCountByPhone(findPhone);
        if (count > 0) {
            return new RspResult("手机号已注册", 1);
        }
        BaseMember baseMember = new BaseMember();
        baseMember.setPhone(bo.getPhone());
        baseMember.setSex(1);
        baseMember.setUsername(bo.getPhone());
        baseMember.setProveStatus(0);
        baseMember.setMemberStatus(0);
        baseMember.setLevel(1);
        baseMember.setExperience(0);
        baseMemberDao.save(baseMember);
        String yxToken = YXUtils.getYxToken(baseMember.getId(), baseMember.getUsername());
        baseMember.setToken(yxToken);

        baseMember.setUsername(baseMember.getId()+"");
        baseMemberDao.update(baseMember);
        Userinfo userinfo = new Userinfo();
        userinfo.setLastRevampTime(System.currentTimeMillis());
        userinfo.setToken(bo.getPhone());
        userinfo.setLoginType("phone");
        userinfo.setMemberId(baseMember.getId());
        userinfo.setCreateTime(System.currentTimeMillis());
        userinfoDao.save(userinfo);

        UserMoney userMoney = new UserMoney();
        userMoney.setMoney(BigDecimal.valueOf(0));
        userMoney.setCreateTime(System.currentTimeMillis());
        userMoney.setLastRevampTime(System.currentTimeMillis());
        userMoney.setUserId(baseMember.getId());
        userMoneyDao.save(userMoney);
        File file = new File();
        file.setLocation("/image/1.png");
        file.setPurposeId(baseMember.getId());
        file.setType("image");
        file.setPurpose("user");
        fileDao.insert(file);
        baseMember.setUrl(file.getLocation());
        return success(baseMember);
    }


    //验证验证码
    private Boolean checkCode(String code, String phone) {
        Object o = CacheUtils.get("sms_binding_" + phone);
        if (!code.equals(o + "")) {
            return true;
        }
        return false;
    }


    //验证绑定手机号验证码
    private Boolean checkBindbinCode(String code, String phone) {
        Object o = CacheUtils.get("sms_binding_" + phone);
        if (!code.equals(o + "")) {
            return true;
        }
        return false;
    }

    //验证忘记密码验证码
    private Boolean checkChangePassword(String code, String phone) {
        Object o = CacheUtils.get("sms_change_password" + phone);
        if (!code.equals(o + "")) {
            return true;
        }
        return false;
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

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public String createSign(Map<String, String> packageParams, String apiKey) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);
        String sign = MD5Utils.encode(sb.toString()).toUpperCase();
        return sign;
    }


    @Override
    public RspResult save(Userinfo entity) {
        return null;
    }

    @Override
    public Userinfo findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(Userinfo entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
