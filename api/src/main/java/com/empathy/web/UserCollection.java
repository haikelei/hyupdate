package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.collection.bo.CollectionAddBo;
import com.empathy.domain.collection.bo.CollectionFindBo;
import com.empathy.domain.user.bo.FocusAddBo;
import com.empathy.domain.user.bo.FocusCancelBo;
import com.empathy.domain.user.bo.FocusFindBo;
import com.empathy.service.IUserCollectionService;
import com.empathy.service.IUserFocusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/24.
 */
@RestController
@RequestMapping("/collection")
@Api(tags = {"收藏接口"})
public class UserCollection {

    @Autowired
    private IUserCollectionService collectionService;

    @ApiOperation(value = "添加收藏", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addCollection", method = RequestMethod.POST)
    public RspResult addCollection(CollectionAddBo bo) {


        return collectionService.addCollection(bo);
    }


    @ApiOperation(value = "取消收藏", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/cancelCollection", method = RequestMethod.POST)
    public RspResult cancelCollection(Long id) {


        return collectionService.cancelCollection(id);
    }

    @ApiOperation(value = "查看收藏列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findCollection", method = RequestMethod.POST)
    public RspResult findCollection(CollectionFindBo bo) {


        return collectionService.findCollection(bo);
    }


}
