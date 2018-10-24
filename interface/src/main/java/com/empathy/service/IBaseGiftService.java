package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.baseGift.BaseGift;
import com.empathy.domain.baseGift.bo.GiftAddBo;
import com.empathy.domain.baseGift.bo.GiftDelBo;
import com.empathy.domain.baseGift.bo.GiftFindBo;
import com.empathy.domain.baseGift.bo.GiftUpdBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/25.
 */
public interface IBaseGiftService extends BaseService<BaseGift, Long, PageBo> {
    RspResult addGift(GiftAddBo bo);

    RspResult updGift(GiftUpdBo bo);

    RspResult delGift(GiftDelBo bo);

    RspResult findGift(GiftFindBo bo);

    String findGiftCount();

    RspResult findGiftById(Long id);
}
