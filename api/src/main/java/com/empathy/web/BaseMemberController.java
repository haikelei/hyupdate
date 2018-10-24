package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.service.IBaseMemberService;
import com.empathy.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description
 * @ Author         dong
 * @ Date           2018-09-20 15:38
 */
@RestController
@RequestMapping("/member")
@Api(tags = {"用户接口"})
public class BaseMemberController {

    @Autowired
    private IBaseMemberService baseMemberService;

    @ApiOperation(value = "查看用户等级", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findByUserExp", method = RequestMethod.POST)
    public RspResult findByUserExp(Long userId) { return baseMemberService.findByUserExp(userId); }


}
