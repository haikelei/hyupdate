package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/13.
 */
@Getter
@Setter
public class LoginBo {


    @ApiModelProperty("登录方式 根据登录方式来决定参数是否选填")
    @Required
    private String loginType;

    @ApiModelProperty("手机号 选填")
    private String phone;

    @ApiModelProperty("密码 选填")
    private String password;

    @ApiModelProperty("qq号 选填")
    private String qq;

    @ApiModelProperty("微信号 选填")
    private String wechat;

    @ApiModelProperty("token 三方token 选填")
    private String token;

    @ApiModelProperty("性别 三方登录就给性别 选填")
    private Integer sex;

    @ApiModelProperty("用户名 三方登录就给用户名选填")
    private String username;

    @ApiModelProperty("第三方得图片 三方登录就给 选填")
    private String file;


}
