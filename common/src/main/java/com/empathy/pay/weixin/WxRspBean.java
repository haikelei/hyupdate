package com.empathy.pay.weixin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author tybest
 * @date 2017/9/8 17:59
 * @email zhoujian699@126.com
 * @desc
 **/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType
public class WxRspBean {

    private static final long serialVersionUID = -204099981749783108L;

    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String result_code;
    private String err_code;
    private String err_code_des;
    private String openid;
    private String is_subscribe;
    private String trade_type;
    private String bank_type;//bank_type
    private Integer total_fee;//分
    private String fee_type;
    private Integer cash_fee;//分
    private String cash_fee_type;
    private Integer coupon_fee;
    private Integer coupon_count;
    private String coupon_id_$n;
    private Integer coupon_fee_$n;
    private String transaction_id;
    private String out_trade_no;
    private String attach;//商家数据包
    private String time_end;//20141030133525


    public WxRspBean(String appid, String mch_id, String device_info, String nonce_str, String sign,
                     String result_code, String err_code, String err_code_des, String openid, String is_subscribe,
                     String trade_type, String bank_type, Integer total_fee, String fee_type, Integer cash_fee,
                     String cash_fee_type, Integer coupon_fee, Integer coupon_count, String coupon_id_$n, Integer coupon_fee_$n,
                     String transaction_id, String out_trade_no, String attach, String time_end) {
        super();
        this.appid = appid;
        this.mch_id = mch_id;
        this.device_info = device_info;
        this.nonce_str = nonce_str;
        this.sign = sign;
        this.result_code = result_code;
        this.err_code = err_code;
        this.err_code_des = err_code_des;
        this.openid = openid;
        this.is_subscribe = is_subscribe;
        this.trade_type = trade_type;
        this.bank_type = bank_type;
        this.total_fee = total_fee;
        this.fee_type = fee_type;
        this.cash_fee = cash_fee;
        this.cash_fee_type = cash_fee_type;
        this.coupon_fee = coupon_fee;
        this.coupon_count = coupon_count;
        this.coupon_id_$n = coupon_id_$n;
        this.coupon_fee_$n = coupon_fee_$n;
        this.transaction_id = transaction_id;
        this.out_trade_no = out_trade_no;
        this.attach = attach;
        this.time_end = time_end;
    }

    public WxRspBean() {
        super();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public Integer getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(Integer coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_id_$n() {
        return coupon_id_$n;
    }

    public void setCoupon_id_$n(String coupon_id_$n) {
        this.coupon_id_$n = coupon_id_$n;
    }

    public Integer getCoupon_fee_$n() {
        return coupon_fee_$n;
    }

    public void setCoupon_fee_$n(Integer coupon_fee_$n) {
        this.coupon_fee_$n = coupon_fee_$n;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
}
