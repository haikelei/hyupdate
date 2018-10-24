package com.empathy.web;

import com.empathy.dao.BaseDealDao;

import com.empathy.domain.deal.BaseDeal;
import com.empathy.service.IBaseMemberService;
import com.empathy.service.IUserMemberService;
import com.empathy.utils.wx.WXPayConstants;
import com.empathy.utils.wx.WXPayUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import static com.empathy.service.impl.UserinfoService.API_SECRET;
//import static jdk.nashorn.internal.codegen.ObjectClassGenerator.LOG;

/**
 * Created by MI on 2018/2/28.
 */
@RestController
@RequestMapping("/alipay")
@Api(tags = {"支付宝回调接口"})
public class AlipayController {

    @Autowired
    private BaseDealDao baseDealDao;

    @Autowired
    private IBaseMemberService baseMemberService;

    @Autowired
    private IUserMemberService userMemberService;


    /**
     * 服务器异步通知页面路径
     */
    @RequestMapping(value = "/notifyUrl")
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {


        System.out.println("支付宝进入回调！");

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        System.out.println("1！");

        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        System.out.println("2！");
        //商户订单号
        double money = Double.valueOf(params.get("total_amount"));
        String wcCode = params.get("trade_no");
        String outTradeNo = params.get("out_trade_no");
        BaseDeal deal = baseDealDao.findById(Long.parseLong(outTradeNo));
        if(deal.getStatus()==1){
           response.getWriter().print("failure");
           return;
       }
        deal.setStatus(1);
        deal.setLastRevampTime(System.currentTimeMillis());
        baseDealDao.update(deal);
        if(deal.getType()==5){
        userMemberService.tobeMemberForPay(deal.getUserId());

        }else {
            //用户加钱
            baseMemberService.addMoney(money,deal.getUserId());
        }

      /*  Recharge recharge = rechargeService.queryRecharge(wcCode);
        System.out.println("3！");
        if(recharge!=null){
            if (recharge.getStatus()==1) {//已经支付过，重复通知
                response.getWriter().print("failure");
                return;
            }}

        //支付宝交易号
        String trade_no = params.get("trade_no");
        //交易状态
        String trade_status = params.get("trade_status");

        double money = Double.valueOf(params.get("total_amount"));
        System.out.println(money);
        System.out.print(wcCode);
        payUtilService.payService1(wcCode,money);

      */  response.getWriter().print("success");

    }



    /**
     * 订单支付微信服务器异步通知
     *
     * @param request
     * @param response
     */
    @RequestMapping("/wechatCallBack")
    public void wechatCallBack(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        System.out.print("wechat进来了");
        try {
            ServletInputStream in = request.getInputStream();
            String resxml = convertStreamToString(in);
            Map<String, String> restmap = WXPayUtil.xmlToMap(resxml);
            //LOG.info("支付结果通知：" + restmap);
            System.out.print(restmap.get("return_code"));
            if ("SUCCESS".equals(restmap.get("return_code"))) {
                // 订单支付成功 业务处理
                String out_trade_no = restmap.get("out_trade_no"); // 商户订单号
                // 通过商户订单判断是否该订单已经处理 如果处理跳过 如果未处理先校验sign签名 再进行订单业务相关的处理
                System.out.print("进入支付");
                String sing = restmap.get("sign"); // 返回的签名
                restmap.remove("sign");
                // String signnow = WXPayUtil.generateSignature(restmap, "wx.api.secret");
                String signnow =  WXPayUtil.generateSignature(restmap, API_SECRET, WXPayConstants.SignType.MD5);
                System.out.print("拿到签名"+signnow);
                System.out.print("返回签名"+sing);
                if (signnow.equals(sing)) {
                    System.out.print("进行业务处理");
                    // 进行业务处理
                    //LOG.info("订单支付通知： 支付成功，订单号" + out_trade_no);
                    BaseDeal baseDeal = baseDealDao.findById(Long.parseLong(out_trade_no));
                    if(baseDeal!=null){
                        if (baseDeal.getStatus()==1) {//已经支付过，重复通知
                            response.getWriter().print("failure");
                            return;
                        }}
                        if(baseDeal.getType()==5){
                            userMemberService.tobeMemberForPay(baseDeal.getUserId());
                        }else {

                            double money = Double.valueOf(restmap.get("total_fee"));
                            baseMemberService.addMoney(money,baseDeal.getUserId());
                        }
                } else {
                    //LOG.info("订单支付通知：签名错误");
                   /* return error(CommonErrorResult.SECRET_FAIL, "订单支付通知：签名错误");*/
                }
            } else {
                //LOG.info(  "订单支付通知：支付失败，" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
               /* throw new BusinessException(CommonErrorResult.SECRET_FAIL,
                        "订单支付通知：支付失败，" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));*/
            }
        } catch (Exception e) {
            //LOG.info("获取回调失败");
            /*throw new BusinessException(CommonErrorResult.SECRET_FAIL,"获取回调失败");*/
        }
    }



    public static String convertStreamToString(InputStream is) {
        /*
          * To convert the InputStream to String we use the BufferedReader.readLine()
          * method. We iterate until the BufferedReader return null which means
          * there's no more data to read. Each line will appended to a StringBuilder
          * and returned as String.
          */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }






}
