package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseClassifyDao;
import com.empathy.domain.classify.BaseClassify;
import com.empathy.domain.classify.bo.ClassifyAddBo;
import com.empathy.domain.classify.bo.ClassifyDelBo;
import com.empathy.domain.classify.bo.ClassifyUpdBo;
import com.empathy.domain.classify.bo.FindClassifyBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseClassifyService;
import com.empathy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/4/16.
 */
@Service
public class BaseClassifyService extends AbstractBaseService implements IBaseClassifyService {

    @Autowired
    private FileService fileService;

    @Autowired
    private BaseClassifyDao baseClassifyDao;

    @Override
    public RspResult delClassify(ClassifyDelBo bo) {
        baseClassifyDao.delById(bo.getId());
        return success();
    }

    @Override
    public String findClassifyCount(FindClassifyBo type) {
        int count = baseClassifyDao.count(type);
        return count + "";
    }

    @Override
    public RspResult findClassify(ClassifyDelBo bo) {
        BaseClassify byId = baseClassifyDao.findById(bo.getId());

        return success(byId);
    }

    @Override
    public RspResult findAllClassify(FindClassifyBo bo) {

        try {

            List<BaseClassify> list = baseClassifyDao.list(bo);

            return success(list);
        }catch (Exception e){

            return errorNo();
        }
    }

    @Override
    public RspResult updClassify(ClassifyUpdBo bo) {
        BaseClassify baseClassify = baseClassifyDao.findById(bo.getId());
        if (baseClassify == null) {
            return new RspResult("id绑定对象为空", 1);
        }
        if (!StringUtil.isNotEmpty(bo.getName())) {
            return error(1, "分类名不能为空");
        }
        if (!StringUtil.isNotIntegerEmpty(bo.getCode())) {
            return error(1, "分类排序值不能为空");
        }
        if (!StringUtil.isNotLongEmpty(bo.getParentId())) {
            return error(1, "主分类id不能为空");
        }
        baseClassify.setName(bo.getName());
        baseClassify.setCode(bo.getCode());
        baseClassify.setParentId(bo.getParentId());
        baseClassify.setLastRevampTime(System.currentTimeMillis());
        baseClassifyDao.update(baseClassify);
        if(StringUtil.isNotEmpty(bo.getUrl())) {


            fileService.insertFile("classify", bo.getUrl(), baseClassify.getId());
        }
        return success(baseClassify);
    }

    @Override
    public RspResult addClassify(ClassifyAddBo bo) {
        if (!StringUtil.isNotEmpty(bo.getName())) {
            return error(1, "分类名不能为空");
        }
        if (!StringUtil.isNotIntegerEmpty(bo.getCode())) {
            return error(1, "分类排序值不能为空");
        }
        if (!StringUtil.isNotIntegerEmpty(bo.getType())) {
            return error(1, "分类类型不能为空");
        }
        if (!StringUtil.isNotLongEmpty(bo.getId())) {
            return error(1, "主分类id不能为空");
        }

        BaseClassify baseClassify = new BaseClassify();
        baseClassify.setName(bo.getName());
        baseClassify.setCode(bo.getCode());
        baseClassify.setParentId(bo.getId());
        baseClassify.setType(bo.getType());
        baseClassify.setCreateTime(System.currentTimeMillis());
        baseClassify.setLastRevampTime(System.currentTimeMillis());
        baseClassifyDao.save(baseClassify);
        fileService.insertFile("classify",bo.getUrl(),baseClassify.getId());
        return success(baseClassify);
    }

    @Override
    public RspResult save(BaseClassify entity) {
        return null;
    }

    @Override
    public BaseClassify findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseClassify entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
