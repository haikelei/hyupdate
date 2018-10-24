package com.empathy.domain.permission;

import com.empathy.common.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/11/2 13:39
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@ApiModel("权限")
public class Permission extends BaseDomain {



    @ApiModelProperty("权限描述")
    private String memo;

    @ApiModelProperty("权限编码:单体权限的拼接")
    private String code;


    @ApiModelProperty("权限编码:role_add")
    private  Long roleId;
}
