package com.empathy.domain.withdraw.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class WithdrawFindBo extends PageBo {


    @ApiModelProperty("提现状态")
    private Integer withdrawStatus;
    @ApiModelProperty("提现方式")
    private Integer withdrawType;

    //private Integer withdrawStatus;


}
