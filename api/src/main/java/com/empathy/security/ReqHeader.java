package com.empathy.security;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tyb
 * @date 2016年7月10日上午10:05:50
 * @desc 请求头信息（header里面没有驼峰结构）
 */
@ApiModel("请求头")
@Getter
@Setter
public final class ReqHeader {

    @ApiModelProperty(value = "手机平台, 1:ios；2: android;")
    private Integer platform;

    @Required
    @ApiModelProperty(value = "访问token")
    private String token;

    @ApiModelProperty(value = "版本信息")
    private String version;
    @Required
    @ApiModelProperty(value = "用户唯一标志")
    private Long uid;

    @ApiModelProperty(value = "请求访问时间戳")
    private Long accesstime;

}
