package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.SmsBo;
import com.empathy.domain.user.bo.*;
import com.empathy.service.IUserinfoService;
import com.empathy.utils.sms.SmsNewUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/13.
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"用户接口"})
public class UserController {


    @Autowired
    private IUserinfoService userinfoService;

    @ApiOperation(value = "注册发送短信验证码", httpMethod = "GET", response = String.class)
    @RequestMapping(value = "/get/captcha", method = RequestMethod.GET)
    public RspResult captcha(SmsBo bo) {
        boolean result = SmsNewUtils.beforeBinding(bo.getPhone());
        if (result) {
            return new RspResult("发送成功", 200);
        } else {
            return new RspResult("发送失败，请稍后再试", 502);
        }
    }

    @ApiOperation(value = "绑定手机发送短信验证码", httpMethod = "GET", response = String.class)
    @RequestMapping(value = "/get/binding", method = RequestMethod.GET)
    public RspResult binding(SmsBo bo) {
        boolean result = SmsNewUtils.beforeRegister(bo.getPhone());
        if (result) {
            return new RspResult("发送成功", 200);
        } else {
            return new RspResult("发送失败，请稍后再试", 502);
        }
    }

    @ApiOperation(value = "忘记密码发送短信验证码", httpMethod = "GET", response = String.class)
    @RequestMapping(value = "/get/forgetPassword", method = RequestMethod.GET)
    public RspResult forgetPassword(SmsBo bo) {
        boolean result = SmsNewUtils.changePassword(bo.getPhone());
        if (result) {
            return new RspResult("发送成功", 200);
        } else {
            return new RspResult("发送失败，请稍后再试", 502);
        }
    }

    @ApiOperation(value = "修改交易密码发送短信验证码", httpMethod = "GET", response = String.class)
    @RequestMapping(value = "/get/changeDealPassword", method = RequestMethod.GET)
    public RspResult changeDealPassword(SmsBo bo) {
        boolean result = SmsNewUtils.changeDealPassword(bo.getPhone());
        if (result) {
            return new RspResult("发送成功", 200);
        } else {
            return new RspResult("发送失败，请稍后再试", 502);
        }
    }


    @ApiOperation(value = "登录，密码验证已加，参数数据我群里文件发了，麻烦看下", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RspResult login(LoginBo bo) {


        return userinfoService.login(bo);
    }
    @ApiOperation(value = "拿个人信息的列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findDetailById", method = RequestMethod.POST)
    public RspResult findDetailById(Long id) {


        return userinfoService.findDetailById(id);
    }


    @ApiOperation(value = "三方登录绑定手机号", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/bindPhone", method = RequestMethod.POST)
    public RspResult bindPhone(BindPhoneBo bo) {


        return userinfoService.bindPhone(bo);
    }


    @ApiOperation(value = "忘记密码", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public RspResult forgetPassword(ForgetPasswordBo bo) {


        return userinfoService.forgetPassword(bo);

    }


    @ApiOperation(value = "注册", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public RspResult regist(RegistBo bo) {

        return userinfoService.regist(bo);

    }

    @ApiOperation(value = "注册后上传密码", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addPassword", method = RequestMethod.POST)
    public RspResult addPassword(PasswordAddBo bo) {

        return userinfoService.addPassword(bo);

    }


    @ApiOperation(value = "开启交易密码", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addDealPassword", method = RequestMethod.POST)
    public RspResult addDealPassword(PasswordDealAddBo bo) {

        return userinfoService.addDealPassword(bo);

    }

    @ApiOperation(value = "修改交易密码", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updDealPassword", method = RequestMethod.POST)
    public RspResult updDealPassword(PasswordDealUpdBo bo) {

        return userinfoService.updDealPassword(bo);

    }
    @ApiOperation(value = "查看剩余华语币", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMoney", method = RequestMethod.POST)
    public RspResult findMoney(Long id) {

        return userinfoService.findMoney(id);

    }


    @ApiOperation(value = "修改个人资料", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updMember", method = RequestMethod.POST)
    public RspResult updMember(MemberUpdBo bo) {

        return userinfoService.updMember(bo);

    }

    @ApiOperation(value = "充值钱包", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addUserMoney", method = RequestMethod.POST)
    public RspResult addUserMoney(UserMoneyAddBo bo) {

        return userinfoService.addUserMoney(bo);

    }
    @ApiOperation(value = "设置支付密码", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addPasswordForPay", method = RequestMethod.POST)
    public RspResult addPasswordForPay(PasswordForPayAddBo bo) {

        return userinfoService.addPasswordForPay(bo);

    }

    @ApiOperation(value = "查看他人主页", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMainForOtherPeroson", method = RequestMethod.POST)
    public RspResult findMainForOtherPeroson(FindMainForOtherBo bo) {

        return userinfoService.findMainForOtherPeroson(bo);

    }

    @ApiOperation(value = "私聊", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addPrivateChat", method = RequestMethod.POST)
    public RspResult addPrivateChat(PrivateChatBo chat) {

        return userinfoService.addPrivateChat(chat);

    }
    @ApiOperation(value = "私聊列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findPrivateChat", method = RequestMethod.POST)
    public RspResult findPrivateChat(PrivateChatFindBo chat) {

        return userinfoService.findPrivateChat(chat);

    }
    @ApiOperation(value = "删除私聊传私聊的id，就是列表的", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delPrivateChat", method = RequestMethod.POST)
    public RspResult delPrivateChat(long id) {

        return userinfoService.delPrivateChat(id);

    }


}
