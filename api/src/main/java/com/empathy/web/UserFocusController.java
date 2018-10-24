package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.user.bo.FocusAddBo;
import com.empathy.domain.user.bo.FocusCancelBo;
import com.empathy.domain.user.bo.FocusFindBo;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.service.IUserFocusService;
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
@RequestMapping("/focus")
@Api(tags = {"用户关注接口"})
public class UserFocusController {

    @Autowired
    private IUserFocusService userFocusService;


    @ApiOperation(value = "添加关注", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addFocus", method = RequestMethod.POST)
    public RspResult addFocus(FocusAddBo bo) {


        return userFocusService.addFocus(bo);
    }


    @ApiOperation(value = "取消关注", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/cancelFocus", method = RequestMethod.POST)
    public RspResult cancelFocus(FocusCancelBo bo) {


        return userFocusService.cancelFocus(bo);
    }

    @ApiOperation(value = "查看关注列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findFocus", method = RequestMethod.POST)
    public RspResult findFocus(FocusFindBo bo) {


        return userFocusService.findFocus(bo);
    }


}
