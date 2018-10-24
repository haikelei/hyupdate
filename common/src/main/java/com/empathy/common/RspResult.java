package com.empathy.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/5 8:59
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
public class RspResult {

    @ApiModelProperty("响应码：200-成功；500-系统错误提示；501-重新登录；502-业务错误")
    private int code = 200;

    @ApiModelProperty("响应消息")
    private String msg;

    @ApiModelProperty("总行数")
    private long total = 0L;

    @ApiModelProperty("具体是数据，可以是任何JSON格式的数据，或者字符串")
    private Object data = null;

    public RspResult(String exMsg, Integer errcode) {
        this.code = errcode;
        this.msg = exMsg;
    }

    public RspResult() {
        this.code = 200;
        this.msg = "操作成功";
    }


}
