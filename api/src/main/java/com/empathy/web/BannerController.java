package com.empathy.web;

import com.baomidou.mybatisplus.toolkit.IOUtils;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.bo.*;
import com.empathy.domain.article.bo.ArticleAddBo;
import com.empathy.domain.banner.bo.BannerFindBo;
import com.empathy.domain.banner.bo.BannerSaveBo;
import com.empathy.domain.banner.bo.BannerUpdBo;
import com.empathy.domain.live.bo.ProveAccountUpdBo;
import com.empathy.domain.live.bo.ProveFreezeBo;
import com.empathy.domain.permission.bo.PermissionAddBo;
import com.empathy.domain.role.bo.RoleAddBo;
import com.empathy.domain.user.bo.FreezeUserBo;
import com.empathy.service.IAccountService;
import com.empathy.service.IBannerService;
import com.empathy.service.IBaseMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by MI on 2018/5/8.
 */

@RestController
@RequestMapping("/banner")
@Api(tags = {"banner接口"})
public class BannerController {


    @Autowired
    private IBannerService bannerService;

    /**
     * 查看banner
     *
     * @return
     */
    @ApiOperation(value = "查看banner", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findBanner", method = RequestMethod.POST)
    public RspResult findBanner(BannerFindBo bo) {

        return bannerService.findBanner(bo);
    }
    /**
     * 查看banner
     *
     * @return
     */
    @ApiOperation(value = "后台查看banner", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findBannerForAdmin", method = RequestMethod.POST)
    public RspResult findBannerForAdmin(BannerFindBo bo) {

        return bannerService.findBanner(bo);
    }
    /**
     * 查看banner个数
     *
     * @return
     */
    @ApiOperation(value = "后台查看banner个数", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findBannerCountForAdmin", method = RequestMethod.POST)
    public String findBannerCountForAdmin(BannerFindBo bo) {

        return bannerService.findBannerCountForAdmin(bo);
    }

    /**
     * 添加banner
     *
     * @return
     */
    @ApiOperation(value = "查看banner", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/saveBanner", method = RequestMethod.POST)
    public RspResult saveBanner(BannerSaveBo bo) {

        return bannerService.saveBanner(bo);
    }

    /**
     * 查看banner
     *
     * @return
     */
    @ApiOperation(value = "查看banner根据id", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findBannerById", method = RequestMethod.POST)
    public RspResult findBannerById(Long id) {

        return bannerService.findBannerById(id);
    }


    /**
     * 修改banner
     *
     * @return
     */
    @ApiOperation(value = "修改banner", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updBanner", method = RequestMethod.POST)
    public RspResult updBanner(BannerUpdBo bo) {

        return bannerService.updBanner(bo);
    }


    /**
     * 删除banner
     *
     * @return
     */
    @ApiOperation(value = "删除banner", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/delBanner", method = RequestMethod.POST)
    public RspResult delBanner(Long id) {

        return bannerService.delBanner(id);
    }




}
