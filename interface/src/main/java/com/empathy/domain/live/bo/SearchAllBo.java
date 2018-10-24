package com.empathy.domain.live.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/29.
 */
@Getter@Setter
public class SearchAllBo extends PageBo{

    @ApiModelProperty("搜索类型 0专辑 1直播间 2动态")
    @Required
    private Integer type;
    @ApiModelProperty("搜索字符串")
    @Required
    private String str;
}
