package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.deal.bo.DealFindByUserIdBo;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.service.IBaseDealService;
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
@RequestMapping("/deal")
@Api(tags = {"交易记录接口"})
public class DealController {

    @Autowired
    private IBaseDealService baseDealService;


    @ApiOperation(value = "查看单人的交易记录", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
    public RspResult findByUserId(DealFindByUserIdBo bo) {


        return baseDealService.findByUserId(bo);
    }


    @ApiOperation(value = "后台查看所有交易记录", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAllDeal", method = RequestMethod.POST)
    public RspResult findAllDeal(DealFindByUserIdBo bo) {


        return baseDealService.findByUserId(bo);
    }


}
