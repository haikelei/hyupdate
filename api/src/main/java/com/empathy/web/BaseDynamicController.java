package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.baseDynamic.bo.DynamicAddBo;
import com.empathy.domain.baseDynamic.bo.DynamicAddPointBo;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.service.IBaseDynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/26.
 */
@RestController
@RequestMapping("/dynamic")
@Api(tags = {"动态接口//作废"})
public class BaseDynamicController {

//    @Autowired
//    private IBaseDynamicService baseDynamicService;
//
//
//    @ApiOperation(value = "添加", httpMethod = "POST", response = String.class)
//    @RequestMapping(value = "/addDynamic", method = RequestMethod.POST)
//    public RspResult addDynamic(DynamicAddBo bo) {
//
//
//        return baseDynamicService.addDynamic(bo);
//    }
//
//
//    @ApiOperation(value = "点赞", httpMethod = "POST", response = String.class)
//    @RequestMapping(value = "/addPoint", method = RequestMethod.POST)
//    public RspResult addPoint(DynamicAddPointBo bo) {
//
//
//        return baseDynamicService.addPoint(bo);
//    }


}
