package com.empathy.utils.sms;

import com.empathy.cache.CacheUtils;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 发送短信工具类
 *
 */
public class SmsNewUtils {

    private static final String SID = "8aaf07086077a6e60160960996400ec8";
    private static final String TOKEN = "0338a71e097f48f59a57ae23385ccbde";
    private static final String REGISTER_TPL_ID = "208740";
    private static final String RETRIEVE_PWD_TPL_ID = "208741";
    private static final String SECURE_PWD_TPL_ID = "209662";
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private static final String PREFIX = "sms_";
    private static final String SECURE_PREFIX = "sms_secure_";
    private static final String BINDING_PREFIX = "sms_binding_";
    private static final String RETRIEVE_PREFIX = "sms_retrieve_";
    private static final String FORGET_PREFIX = "sms_forget_";
    private static final String CHANGE_BINDING_PREFIX = "sms_change_binding_";
    private static final String LOGIN_PREFIX = "sms_login_";
    private static final String GET_GOODS_PREFIX = "sms_get_goods";
    private static final String SERVICE_GOODS_PREFIX = "sms_service_goods";
    private static final String CHANGE_DEAL_PASSWORD_PREFIX = "sms_change_password";
    private static final String UPD_DEAL_PASSWORD_PREFIX = "sms_upd_password";
    private static final String BE_PROVE_PREFIX = "sms_prove";
    public static final String CHANGE_WITHDRAW_PWS = "sms_change_withdraw_";

    private static final String SMS_VERIFY_TEMPLATE = "【华语】您的验证码是%s，在5分钟内有效。如非本人操作请忽略本短信。";
    private static final String SMS_APPOINTMENT_TEMPLATE = "【华语】您预约的直播还有半小时开始，请及时做好准备。";

    /**
     * 忘记密码发送短信验证码
     * @param phone
     * @return
     */
    public static boolean changePassword(String phone) {
        return sendTemplate(CHANGE_DEAL_PASSWORD_PREFIX, phone);
    }

    /**
     * 修改交易密码发送短信验证码
     * @param phone
     * @return
     */
    public static boolean changeDealPassword(String phone) {
        return sendTemplate(UPD_DEAL_PASSWORD_PREFIX, phone);
    }

    /**
     * 绑定手机发送短信验证码
     * @param phone
     * @return
     */
    public static boolean beforeRegister(String phone) {
        return sendTemplate(PREFIX, phone);
    }

    /**
     * 注册发送短信验证码
     * @param phone
     * @return
     */
    public static boolean beforeBinding(String phone) {
        return sendTemplate(BINDING_PREFIX, phone);
    }

    /**
     * 直播预约短信发送
     * @param phone
     * @return
     */
    public static boolean beginAppointment(String phone) {
        return sendYzm(phone, SMS_APPOINTMENT_TEMPLATE);
    }

    /**
     * 修改交易密码
     * @param phone
     * @return
     */
    public static boolean changePayPassword(String phone) {
        return sendTemplate(CHANGE_WITHDRAW_PWS,phone);
    }

    //验证验证码
    public static Boolean checkCode(String tag,String code, String phone) {
        Object o = CacheUtils.get(tag + phone);
        if (!code.equals(o + "")) {
            return true;
        }
        return false;
    }

    /**
     * 通用短信模板Send
     * @param prefix
     * @param phone
     * @return
     */
    public static boolean sendTemplate(String prefix, String phone) {
        String code = String.valueOf(random.nextLong(100000,999999));
        String content = String.format(SMS_VERIFY_TEMPLATE, code);
        boolean result = sendYzm(phone, content);
        CacheUtils.put(prefix + phone, code, 300000);
        return result;
    }

    /**
     * 短信验证码
     *
     * @param phone 电话
     * @param content  内容
     */
    public static boolean sendYzm(String phone, String content) {
        try {
            String result = SmsBase.SendSms(phone, content);
            if (Integer.valueOf(result) > 0) {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

}