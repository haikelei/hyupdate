package com.empathy.service.impl;

import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.TbPermission;
import com.empathy.domain.account.TbRole;
import com.empathy.domain.account.bo.*;
import com.empathy.domain.account.vo.ProveAddVo;
import com.empathy.domain.account.vo.ProveVo;
import com.empathy.domain.banner.BaseBanner;
import com.empathy.domain.banner.bo.BannerFindBo;
import com.empathy.domain.banner.bo.BannerSaveBo;
import com.empathy.domain.banner.bo.BannerUpdBo;
import com.empathy.domain.live.BaseLive;
import com.empathy.domain.live.bo.ProveAccountUpdBo;
import com.empathy.domain.live.bo.ProveFreezeBo;
import com.empathy.domain.permission.Permission;
import com.empathy.domain.permission.bo.PermissionAddBo;
import com.empathy.domain.role.bo.RoleAddBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IAccountService;
import com.empathy.service.IBannerService;
import com.empathy.utils.MD5Utils;
import com.empathy.utils.StringUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by MI on 2018/5/9.
 */
@Service
public class BannerService extends AbstractBaseService implements IBannerService {

    @Autowired
    private FileService fileService;

    @Autowired
    private BannerDao bannerDao;

    @Override
    public RspResult delBanner(Long id) {
        try {
            bannerDao.delById(id);
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult updBanner(BannerUpdBo bo) {
        BaseBanner banner = bannerDao.findById(bo.getId());
        if(banner==null){
            return errorMo();
        }
        if(StringUtil.isNotEmpty(bo.getUrl())){
            banner.setUrl(bo.getUrl());
        }

        if(StringUtil.isNotEmpty(bo.getTitle())){
            banner.setTitle(bo.getTitle());
        }
        if(StringUtil.isNotEmpty(bo.getImage())){
            banner.setImage(bo.getImage());
        }
        if(StringUtil.isNotIntegerEmpty(bo.getCode())){
            banner.setCode(bo.getCode());
        }
        bannerDao.update(banner);
        if(StringUtil.isNotEmpty(bo.getImage())){
            fileService.insertFile("banner",bo.getUrl(),banner.getId());
        }
        return success();
    }

    @Override
    public RspResult saveBanner(BannerSaveBo bo) {
        BaseBanner baseBanner = new BaseBanner();
        baseBanner.setImage(bo.getImage());
        baseBanner.setTitle(bo.getTitle());
        baseBanner.setType(bo.getType());
        baseBanner.setUrl(bo.getUrl());
        baseBanner.setCode(bo.getCode());
        bannerDao.save(baseBanner);
        fileService.insertFile("banner",bo.getUrl(),baseBanner.getId());
        return success(baseBanner);
    }

    @Override
    public RspResult findBanner(BannerFindBo bo) {
        try {

            List<BaseBanner> list = bannerDao.list(bo);
            return success(list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult findBannerById(Long id) {
        BaseBanner byId = bannerDao.findById(id);
        if(byId==null){
            return errorMo();
        }
        return success(byId);
    }

    @Override
    public String findBannerCountForAdmin(BannerFindBo bo) {
        int count = bannerDao.count(bo);

        return count+"";
    }

    @Override
    public RspResult save(BaseBanner entity) {
        return null;
    }

    @Override
    public BaseBanner findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseBanner entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
