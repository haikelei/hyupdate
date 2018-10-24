package com.empathy.service;

import com.empathy.common.BaseBo;
import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.role.Role;
import com.empathy.domain.role.bo.RoleBo;
import com.empathy.domain.user.PrivateChat;
import com.empathy.domain.user.Userinfo;
import com.empathy.domain.user.bo.*;

import java.util.List;


public interface IUserinfoService extends BaseService<Userinfo, Long, PageBo> {


    RspResult regist(RegistBo bo);

    RspResult login(LoginBo bo);

    RspResult addPassword(PasswordAddBo bo);

    RspResult bindPhone(BindPhoneBo bo);

    RspResult forgetPassword(ForgetPasswordBo bo);

    RspResult addDealPassword(PasswordDealAddBo bo);

    RspResult updDealPassword(PasswordDealUpdBo bo);

    RspResult updMember(MemberUpdBo bo);

    RspResult add(RegistBo bo);

    RspResult findMoney(Long id);

    RspResult addUserMoney(UserMoneyAddBo bo);

    RspResult findDetailById(Long id);

    RspResult addPasswordForPay(PasswordForPayAddBo bo);

    RspResult findMainForOtherPeroson(FindMainForOtherBo bo);

    RspResult addPrivateChat(PrivateChatBo chat);

    RspResult findPrivateChat(PrivateChatFindBo chat);

    RspResult delPrivateChat(long id);
}
