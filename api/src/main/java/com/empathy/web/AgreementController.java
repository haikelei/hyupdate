package com.empathy.web;

import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.bo.AccoutLoginBo;
import com.empathy.domain.account.bo.ProveListAddBo;
import com.empathy.domain.account.bo.ProveListBo;
import com.empathy.domain.account.bo.UpdPasswordBo;
import com.empathy.domain.agreement.Agreement;
import com.empathy.domain.agreement.bo.*;
import com.empathy.domain.user.bo.FreezeUserBo;
import com.empathy.service.IAccountService;
import com.empathy.service.IAgreementService;
import com.empathy.service.IBaseMemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MI on 2018/5/8.
 */

@RestController
@RequestMapping("/agreement")
@Api(tags = {"协议接口"})
public class AgreementController {

    @Autowired
    private IAgreementService agreementService;


    /**
     * 添加协议
     *
     * @return
     */
    @RequestMapping(value = "/addAgreement", method = RequestMethod.GET)
    @ResponseBody
    public RspResult addAgreement(AgreementAddBo bo) {


        return agreementService.addAgreement(bo);
    }


    /**
     * 修改协议
     *
     * @return
     */
    @RequestMapping(value = "/updAgreement", method = RequestMethod.GET)
    @ResponseBody
    public RspResult updAgreement(AgreementUpdBo bo) {


        return agreementService.updAgreement(bo);
    }


    /**
     * 删除协议
     *
     * @return
     */
    @RequestMapping(value = "/delAgreement", method = RequestMethod.GET)
    @ResponseBody
    public RspResult delAgreement(AgreementDelBo bo) {


        return agreementService.delAgreement(bo);
    }

    /**
     * 根据id查看协议
     *
     * @return
     */
    @RequestMapping(value = "/findAgreementById", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findAgreementById(AgreementFindByIdBo bo) {


        return agreementService.findAgreementById(bo);
    }


    /**
     * 根据类型查看协议
     *
     * @return
     */
    @RequestMapping(value = "/findAgreementByType", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findAgreementByType(AgreementFindByTypeBo bo) {


        return agreementService.findAgreementByType(bo);
    }

    /**
     * 查看协议得标题列表
     *
     * @return
     */
    @RequestMapping(value = "/findAgreementForType", method = RequestMethod.GET)
    @ResponseBody
    public RspResult findAgreementForTitle() {


        return agreementService.findAgreementForTitle();
    }


}
