package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/24.
 */
@Getter
@Setter
public class ProveAddBo {

    @ApiModelProperty("用户id")
    @Required
    private Long userId;

    @ApiModelProperty("证件类型 100二代身份证 200湾湾通行证 300港澳的通行证 400护照")
    @Required
    private Integer cardClassify;

    @ApiModelProperty("证件号")
    @Required
    private String cardNumber;

    @ApiModelProperty("姓名")
    @Required
    private String name;




}
