package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.classify.bo.ClassifyAddBo;
import com.empathy.domain.classify.bo.ClassifyDelBo;
import com.empathy.domain.classify.bo.ClassifyUpdBo;
import com.empathy.domain.classify.bo.FindClassifyBo;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.service.IBaseClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/16.
 */
@RestController
@RequestMapping("/classify")
@Api(tags = {"分类接口"})
public class ClassifyController {

    @Autowired
    private IBaseClassifyService baseClassifyService;


    @ApiOperation(value = "添加", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addClassify", method = RequestMethod.POST)
    public RspResult addClassify(ClassifyAddBo bo) {


        return baseClassifyService.addClassify(bo);
    }

    @ApiOperation(value = "删除", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delClassify", method = RequestMethod.POST)
    public RspResult delClassify(ClassifyDelBo bo) {


        return baseClassifyService.delClassify(bo);
    }


    @ApiOperation(value = "修改", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updClassify", method = RequestMethod.POST)
    public RspResult updClassify(ClassifyUpdBo bo) {


        return baseClassifyService.updClassify(bo);
    }

    @ApiOperation(value = "查找全部 (点击分类的接口)", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAllClassify", method = RequestMethod.POST)
    public RspResult findAllClassify(FindClassifyBo bo) {


        return baseClassifyService.findAllClassify(bo);
    }

    @ApiOperation(value = "查找单个", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findClassify", method = RequestMethod.POST)
    public RspResult findClassify(ClassifyDelBo bo) {


        return baseClassifyService.findClassify(bo);
    }

    @ApiOperation(value = "查找个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findClassifyCount", method = RequestMethod.POST)
    public String findClassifyCount(FindClassifyBo type) {


        return baseClassifyService.findClassifyCount(type);
    }


}
