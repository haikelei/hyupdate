package com.empathy.service.impl;

import cn.jiguang.common.utils.StringUtils;
import com.empathy.common.RspResult;
import com.empathy.dao.BaseGiftDao;
import com.empathy.domain.baseGift.BaseGift;
import com.empathy.domain.baseGift.bo.GiftAddBo;
import com.empathy.domain.baseGift.bo.GiftDelBo;
import com.empathy.domain.baseGift.bo.GiftFindBo;
import com.empathy.domain.baseGift.bo.GiftUpdBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseGiftService;
import com.empathy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/4/25.
 */
@Service
public class BaseGiftService extends AbstractBaseService implements IBaseGiftService {

    @Autowired
    private BaseGiftDao baseGiftDao;

    @Autowired
    private FileService fileService;


    @Override
    public RspResult findGiftById(Long id) {
        BaseGift baseGift = baseGiftDao.findById(id);

        return success(baseGift);
    }

    @Override
    public String findGiftCount() {

        int count = baseGiftDao.count();
        return count + "";
    }

    @Override
    public RspResult updGift(GiftUpdBo bo) {
        BaseGift baseGift = baseGiftDao.findById(bo.getId());
        if (StringUtil.isNotEmpty(bo.getGiftName())) {
            baseGift.setGiftName(bo.getGiftName());
        }

        if (StringUtil.isNotBigDecimalEmpty(bo.getMoney())) {
            baseGift.setMoney(bo.getMoney());
        }

        if (StringUtil.isNotIntegerEmpty(bo.getCode())) {
            baseGift.setCode(bo.getCode());
        }

        if (StringUtil.isNotBigDecimalEmpty(bo.getPrice())) {
            baseGift.setMoney(bo.getPrice());
        }
        if (StringUtil.isNotBigDecimalEmpty(bo.getDefaultMoney())) {
            baseGift.setDefaultMoney(bo.getDefaultMoney());
        }

        if (StringUtil.isNotBigDecimalEmpty(bo.getDefaultMoney())) {
            baseGift.setDefaultMoney(bo.getDefaultMoney());
        }

        if (StringUtil.isNotIntegerEmpty(bo.getExp())) {
            baseGift.setExp(bo.getExp());
        }
        baseGift.setLastRevampTime(System.currentTimeMillis());

        try{
            baseGiftDao.update(baseGift);
            if(StringUtils.isNotEmpty(bo.getUrl())){
                fileService.insertFile("gift",bo.getUrl(),baseGift.getId());
            }
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult delGift(GiftDelBo bo) {
        try {

            baseGiftDao.delById(bo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return error(1, "操作失败");
        }

        return success();
    }

    @Override
    public RspResult findGift(GiftFindBo bo) {
        List<BaseGift> list = baseGiftDao.list();

        return success(list);
    }

    @Override
    public RspResult addGift(GiftAddBo bo) {
        if (!StringUtil.isNotIntegerEmpty(bo.getCode())) {
            return error(1, "礼物排序值不能为空！");
        }
        if (!StringUtil.isNotEmpty(bo.getGiftName())) {
            return error(1, "礼物名不能为空！");
        }
        if (!StringUtil.isNotBigDecimalEmpty(bo.getDefaultMoney())) {
            return error(1, "礼物默认华语币不能为空！");
        }
        if (!StringUtil.isNotBigDecimalEmpty(bo.getDefaultPrice())) {
            return error(1, "礼物默认金额不能为空！");
        }
        if (!StringUtil.isNotBigDecimalEmpty(bo.getMoney())) {
            return error(1, "礼物华语币值不能为空！");
        }
        if (!StringUtil.isNotBigDecimalEmpty(bo.getPrice())) {
            return error(1, "礼物金额不能为空！");
        }
        if (!StringUtil.isNotIntegerEmpty(bo.getExp())) {
            return error(1, "礼物经验值不能为空！");
        }
        if (!StringUtil.isNotEmpty(bo.getUrl())) {
            return error(1, "礼物图片不能为空！");
        }
        BaseGift baseGift = new BaseGift();
        baseGift.setDefaultMoney(bo.getDefaultMoney());
        baseGift.setDefaultPrice(bo.getDefaultPrice());
        baseGift.setGiftName(bo.getGiftName());
        baseGift.setMoney(bo.getMoney());
        baseGift.setPrice(bo.getPrice());
        baseGift.setCode(bo.getCode());
        baseGift.setExp(bo.getExp());

        try{
            baseGiftDao.save(baseGift);
            if(StringUtils.isNotEmpty(bo.getUrl())){
                fileService.insertFile("gift",bo.getUrl(),baseGift.getId());
            }
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult save(BaseGift entity) {
        return null;
    }

    @Override
    public BaseGift findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseGift entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }


}
