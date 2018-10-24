package com.empathy.domain.role;

import com.empathy.common.BaseDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/10/30 21:12
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class Role extends BaseDomain {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String memo;

    @ApiModelProperty("角色编码")
    private String code;

    private List<Long> permissionIds = new ArrayList<>();

}
