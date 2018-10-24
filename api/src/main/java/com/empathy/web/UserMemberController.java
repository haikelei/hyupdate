package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.domain.user.bo.MemberAddBo;
import com.empathy.service.IUserMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/18.
 */
@RestController
@RequestMapping("/user/member")
@Api(tags = {"用户会员接口"})
public class UserMemberController {

    @Autowired
    private IUserMemberService userMemberService;

    @ApiOperation(value = "购买会员", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/tobeMember", method = RequestMethod.POST)
    public RspResult tobeMember(MemberAddBo bo) {

        return userMemberService.tobeMember(bo);
    }

}
