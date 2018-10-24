package com.empathy.domain.account;

import com.empathy.common.BaseDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
@Getter
@Setter
public class TbRole extends BaseDomain {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String memo;

    @ApiModelProperty("角色编码")
    private String code;


    private String permissionIds;


}
