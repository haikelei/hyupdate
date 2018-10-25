package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.withdraw.Withdraw;
import com.empathy.domain.withdraw.bo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by MI on 2018/4/17.
 */
public interface IWithdrawService extends BaseService<Withdraw, Long, PageBo> {
    RspResult addWithdraw(WithdrawAddBo bo);

    RspResult updWithdraw(WithdrawUpdBo bo);

    RspResult findWithdraw(WithdrawFindBo bo);

    String findWithdrawCount(WithdrawFindBo bo);

    /**
     * Excel表格导出
     */
    void getExcel(WithdrawFindBo bo, HttpServletResponse response);

    RspResult exportExcle(MultipartFile file);


    RspResult modifyPayPassword(ModifyPasswordBo bo);

    RspResult getPayPasswordCode(ModifyPasswordCodeBo bo);
}
