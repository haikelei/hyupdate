package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.baseMain.bo.MainButtonAddBo;
import com.empathy.domain.baseMain.bo.MainButtonDelBo;
import com.empathy.domain.baseMain.bo.MainButtonUpdBo;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseMainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/19.
 */
@RestController
@RequestMapping("/main")
@Api(tags = {"主页下面5个按钮的接口"})
public class BaseMainController {

    @Autowired
    private IBaseMainService baseMainService;

    @ApiOperation(value = "添加按钮", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addMainButton", method = RequestMethod.POST)
    public RspResult addMainButton(MainButtonAddBo bo) {


        return baseMainService.addMainButton(bo);
    }

    @ApiOperation(value = "修改按钮", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updMainButton", method = RequestMethod.POST)
    public RspResult updMainButton(MainButtonUpdBo bo) {


        return baseMainService.updMainButton(bo);
    }

    @ApiOperation(value = "删除按钮", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delMainButton", method = RequestMethod.POST)
    public RspResult delMainButton(MainButtonDelBo bo) {


        return baseMainService.delMainButton(bo);
    }


}
