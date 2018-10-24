package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class MemberUpdBo {

    @ApiModelProperty("用户id")
    @Required
    private Long id;
    @ApiModelProperty("昵称 选填")
    private String username;
    @ApiModelProperty("性别 选填")
    private Integer sex;
    @ApiModelProperty("个性签名 选填")
    private String intro;
    @ApiModelProperty("地区 选填")
    private String address;


}
