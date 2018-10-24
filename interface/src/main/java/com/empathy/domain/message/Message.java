package com.empathy.domain.message;

import com.empathy.common.BaseDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/22 20:28
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class Message extends BaseDomain {

    @ApiModelProperty("发送人")
    private Long fuserId;

    @ApiModelProperty("接收人")
    private Long tuserId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("是否已读 0-未读；1-已读")
    private Integer readed = 0;

    @ApiModelProperty("引用对象ID")
    private Long reference;

    @ApiModelProperty("扩展")
    private String ext;
}
