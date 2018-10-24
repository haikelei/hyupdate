package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.bo.*;
import com.empathy.domain.banner.BaseBanner;
import com.empathy.domain.banner.bo.BannerFindBo;
import com.empathy.domain.banner.bo.BannerSaveBo;
import com.empathy.domain.banner.bo.BannerUpdBo;
import com.empathy.domain.live.bo.ProveAccountUpdBo;
import com.empathy.domain.live.bo.ProveFreezeBo;
import com.empathy.domain.permission.bo.PermissionAddBo;
import com.empathy.domain.role.bo.RoleAddBo;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * Created by MI on 2018/5/9.
 */
public interface IBannerService extends BaseService<BaseBanner, Long, PageBo> {

    RspResult findBanner(BannerFindBo bo);

    RspResult saveBanner(BannerSaveBo bo);

    RspResult updBanner(BannerUpdBo bo);

    RspResult delBanner(Long id);

    String findBannerCountForAdmin(BannerFindBo bo);

    RspResult findBannerById(Long id);
}
