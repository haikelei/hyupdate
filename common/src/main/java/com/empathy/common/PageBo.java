package com.empathy.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tybest
 * @date 2017/8/5 8:45
 * @email zhoujian699@126.com
 * @desc
 **/
@ApiModel(value = "分页基础")
public class PageBo {

    @ApiModelProperty(value = "分页开始的页码")
    protected int start = 0;

    @ApiModelProperty(value = "单页大小，默认10条")
    protected int limit = 10;

    public int getStart() {
        int tmp = this.start;
        if (tmp <= 1) {
            tmp = 0;
        } else {
            tmp = (tmp - 1)  * this.limit;
        }
        return tmp;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
