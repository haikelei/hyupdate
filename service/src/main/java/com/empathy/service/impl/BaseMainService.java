package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseMainDao;
import com.empathy.domain.baseMain.BaseMain;
import com.empathy.domain.baseMain.bo.MainButtonAddBo;
import com.empathy.domain.baseMain.bo.MainButtonDelBo;
import com.empathy.domain.baseMain.bo.MainButtonUpdBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseMainService;
import com.empathy.service.IBaseMemberService;
import com.empathy.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/19.
 */
@Service
public class BaseMainService extends AbstractBaseService implements IBaseMainService {

    @Autowired
    private BaseMainDao baseMainDao;


    @Override
    public RspResult updMainButton(MainButtonUpdBo bo) {
        BaseMain baseMain = baseMainDao.findById(bo.getId());
        if (baseMain == null) {
            return new RspResult("id绑定对象为空", 1);
        }
        if (StringUtil.isNotEmpty(bo.getCheckFontColor())) {
            baseMain.setCheckFontColor(bo.getCheckFontColor());
        }

        if (StringUtil.isNotEmpty(bo.getCheckPic())) {
            baseMain.setCheckPic(bo.getCheckPic());
        }

        if (StringUtil.isNotEmpty(bo.getControlsType())) {
            baseMain.setControlsType(bo.getControlsType());
        }

        if (StringUtil.isNotEmpty(bo.getDefaultFontColor())) {
            baseMain.setDefaultFontColor(bo.getDefaultFontColor());
        }
        if (StringUtil.isNotEmpty(bo.getDefaultPic())) {
            baseMain.setDefaultPic(bo.getDefaultPic());
        }
        if (StringUtil.isNotEmpty(bo.getFont())) {
            baseMain.setFont(bo.getFont());
        }
        if (StringUtil.isNotEmpty(bo.getRespondEvent())) {
            baseMain.setRespondEvent(bo.getRespondEvent());
        }
        baseMain.setLastRevampTime(System.currentTimeMillis());
        baseMainDao.update(baseMain);

        return new RspResult();
    }

    @Override
    public RspResult delMainButton(MainButtonDelBo bo) {
        baseMainDao.delById(bo.getId());
        return success();
    }

    @Override
    public RspResult addMainButton(MainButtonAddBo bo) {
        BaseMain baseMain = new BaseMain();
        baseMain.setCheckFontColor(bo.getCheckFontColor());
        baseMain.setCheckPic(bo.getCheckPic());
        baseMain.setDefaultFontColor(bo.getDefaultFontColor());
        baseMain.setDefaultPic(bo.getDefaultPic());
        baseMain.setControlsType(bo.getControlsType());
        baseMain.setFont(bo.getFont());
        baseMain.setRespondEvent(bo.getRespondEvent());
        baseMainDao.save(baseMain);

        return success();
    }

    @Override
    public RspResult save(BaseMain entity) {
        return null;
    }

    @Override
    public BaseMain findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseMain entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
