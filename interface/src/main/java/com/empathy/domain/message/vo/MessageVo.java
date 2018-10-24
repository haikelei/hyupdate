package com.empathy.domain.message.vo;

import com.empathy.domain.message.Message;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/22 20:36
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class MessageVo extends Message {

    @ApiModelProperty("发生人姓名")
    private String fname;
    @ApiModelProperty("消息类型名称")
    private String typeStr;
    @ApiModelProperty("发送人头像")
    private String favatar;

    @ApiModelProperty(hidden = true)
    private String tname;
}
