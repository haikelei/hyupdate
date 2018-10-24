package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.classify.bo.*;
import com.empathy.service.IBaseClassifyService;
import com.empathy.service.IBaseMainClassifyService;
import com.empathy.service.IBaseMainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/6/13.
 */
@RestController
@RequestMapping("/main/classify")
@Api(tags = {"主分类接口"})
public class BaseMainClassifyController {

    @Autowired
    private IBaseMainClassifyService baseMainServiceClassifyService;


    @ApiOperation(value = "添加", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addMainClassify", method = RequestMethod.POST)
    public RspResult addMainClassify(ClassifyMainAddBo bo) {


        return baseMainServiceClassifyService.addMainClassify(bo);
    }

    @ApiOperation(value = "删除", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delMainClassify", method = RequestMethod.POST)
    public RspResult delMainClassify(ClassifyMainDelBo bo) {


        return baseMainServiceClassifyService.delMainClassify(bo);
    }


    @ApiOperation(value = "修改", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updMainClassify", method = RequestMethod.POST)
    public RspResult updMainClassify(ClassifyMainUpdBo bo) {


        return baseMainServiceClassifyService.updMainClassify(bo);
    }


    @ApiOperation(value = "查找全部 (点击分类的接口)", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMainAllClassify", method = RequestMethod.POST)
    public RspResult findMainAllClassify(FindClassifyBo bo) {


        return baseMainServiceClassifyService.findMainAllClassify(bo);
    }


    @ApiOperation(value = "查找单个", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMainClassify", method = RequestMethod.POST)
    public RspResult findMainClassify(ClassifyDelBo bo) {


        return baseMainServiceClassifyService.findMainClassify(bo);
    }


    @ApiOperation(value = "查找个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findMainClassifyCount", method = RequestMethod.POST)
    public String findMainClassifyCount(FindClassifyBo type) {


        return baseMainServiceClassifyService.findMainClassifyCount(type);
    }
}
