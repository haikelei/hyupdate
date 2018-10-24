package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.baseGift.bo.GiftAddBo;
import com.empathy.domain.baseGift.bo.GiftDelBo;
import com.empathy.domain.baseGift.bo.GiftFindBo;
import com.empathy.domain.baseGift.bo.GiftUpdBo;
import com.empathy.domain.live.bo.GiveGiftBo;
import com.empathy.service.IBaseGiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/25.
 */
@RestController
@RequestMapping("/gift")
@Api(tags = {"礼物接口"})
public class GiftController {

    @Autowired
    private IBaseGiftService baseGiftService;


    @ApiOperation(value = "添加礼物", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addGift", method = RequestMethod.POST)
    public RspResult addGift(GiftAddBo bo) {
        return baseGiftService.addGift(bo);
    }

    @ApiOperation(value = "修改礼物", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updGift", method = RequestMethod.POST)
    public RspResult updGift(GiftUpdBo bo) {
        return baseGiftService.updGift(bo);
    }

    @ApiOperation(value = "删除礼物", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delGift", method = RequestMethod.POST)
    public RspResult delGift(GiftDelBo bo) {
        return baseGiftService.delGift(bo);
    }

    @ApiOperation(value = "查找礼物", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findGift", method = RequestMethod.POST)
    public RspResult findGift(GiftFindBo bo) {
        return baseGiftService.findGift(bo);
    }

    @ApiOperation(value = "查找礼物个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findGiftCount", method = RequestMethod.POST)
    public String findGiftCount() {
        return baseGiftService.findGiftCount();
    }

    @ApiOperation(value = "查找单个礼物", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findGiftById", method = RequestMethod.POST)
    public RspResult findGiftById(Long id) {
        return baseGiftService.findGiftById(id);
    }


}
