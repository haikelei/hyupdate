package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseClassifyDao;
import com.empathy.dao.BaseMainClassifyDao;
import com.empathy.dao.FileDao;
import com.empathy.domain.baseMain.BaseMain;
import com.empathy.domain.baseMain.bo.MainButtonAddBo;
import com.empathy.domain.baseMain.bo.MainButtonDelBo;
import com.empathy.domain.baseMain.bo.MainButtonUpdBo;
import com.empathy.domain.bidding.File;
import com.empathy.domain.classify.BaseClassify;
import com.empathy.domain.classify.BaseMainClassify;
import com.empathy.domain.classify.bo.*;
import com.empathy.domain.file.bo.FileCarBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseClassifyService;
import com.empathy.service.IBaseMainClassifyService;
import com.empathy.service.IBaseMainService;
import com.empathy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/4/16.
 */
@Service
public class BaseMainClassifyService extends AbstractBaseService implements IBaseMainClassifyService {

    @Autowired
    private BaseMainClassifyDao baseMainClassifyDao;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileDao fileDao;

    @Override
    public RspResult save(BaseMainClassify entity) {
        return null;
    }

    @Override
    public BaseMainClassify findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult addMainClassify(ClassifyMainAddBo bo) {

        if(!StringUtil.isNotEmpty(bo.getName())){
            return error(1,"分类名不能为空");

        }
        if(!StringUtil.isNotIntegerEmpty(bo.getCode())){
            return error(1,"分类排序值不能为空");
        }
        if(!StringUtil.isNotIntegerEmpty(bo.getType())){
            return error(1,"分类类型不能为空");
        }
        BaseMainClassify baseMainClassify = new BaseMainClassify();
        baseMainClassify.setName(bo.getName());
        baseMainClassify.setCode(bo.getCode());
        baseMainClassify.setType(bo.getType());
        baseMainClassifyDao.save(baseMainClassify);

        fileService.insertFile("mainClassify",bo.getUrl(),baseMainClassify.getId());

        return success();
    }

    @Override
    public RspResult update(BaseMainClassify entity) {
        return null;
    }

    @Override
    public RspResult delMainClassify(ClassifyMainDelBo bo) {
        int count  = baseMainClassifyDao.findCountForMain(bo.getId());
        if(count>0){
            return error(1,"请先删除子分类");
        }
        baseMainClassifyDao.delById(bo.getId());
        return success();
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }

    @Override
    public RspResult findMainClassify(ClassifyDelBo bo) {
        if(!StringUtil.isNotLongEmpty(bo.getId())){
            return  error(1,"id不能为空");
        }
        BaseMainClassify baseMainClassify =  baseMainClassifyDao.findDetail(bo);
        return success(baseMainClassify);
    }

    @Override
    public String findMainClassifyCount(FindClassifyBo type) {
        int count = baseMainClassifyDao.findByType(type);
        return count+"";
    }

    @Override
    public RspResult findMainAllClassify(FindClassifyBo bo) {
        if(!StringUtil.isNotIntegerEmpty(bo.getType())){
            return error(1,"查看类型的参数不能为空");
        }
        try {

            int count =  baseMainClassifyDao.findByType(bo);
            List<BaseMainClassify> list = baseMainClassifyDao.listByType(bo);
            return success(count,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }

    }

    @Override
    public RspResult updMainClassify(ClassifyMainUpdBo bo) {
        BaseMainClassify mainClassify = baseMainClassifyDao.findById(bo.getId());
        if(mainClassify==null){
            return errorMo();
        }

        if(StringUtil.isNotEmpty(bo.getName())){
            mainClassify.setName(bo.getName());
        }

        if(StringUtil.isNotIntegerEmpty(bo.getCode())){
            mainClassify.setCode(bo.getCode());
        }



        mainClassify.setLastRevampTime(System.currentTimeMillis());
        baseMainClassifyDao.update(mainClassify);
        if(StringUtil.isNotEmpty(bo.getUrl())){
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setPurposeId(mainClassify.getId());
            fileCarBo.setType("mainClassify");
            File file = fileService.findFile(fileCarBo);
            file.setLocation(bo.getUrl());
            fileDao.updateByPrimaryKey(file);
        }
        return success();


    }
}
