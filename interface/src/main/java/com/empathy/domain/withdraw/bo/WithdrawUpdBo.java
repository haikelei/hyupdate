package com.empathy.domain.withdraw.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class WithdrawUpdBo {


    @ApiModelProperty("提现状态 0 表示成功 1表示失败")
    @Required
    private Integer type;
    @ApiModelProperty("提现id")
    @Required
    private Long id;

}
