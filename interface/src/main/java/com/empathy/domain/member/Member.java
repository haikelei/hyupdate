package com.empathy.domain.member;

import com.empathy.common.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:10
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@ApiModel("平台用户或者运营商")
public class Member extends BaseDomain {


    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("实名")
    private String realname;

    @ApiModelProperty("工号")
    private String jobNo;

    @ApiModelProperty("状态 1-正常；2-禁用")
    private Integer statuz = 1;
    /**
     *
     */
    @ApiModelProperty("成员类型")
    private Integer type;

    @ApiModelProperty("角色ID集合")
    private List<Long> roleIds = new ArrayList<>();

    @ApiModelProperty("角色")
    private String role;
}
