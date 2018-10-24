package com.empathy.domain.baseDynamic.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/26.
 */
@Getter
@Setter
public class DynamicAddBo {

    @ApiModelProperty(value = "用户id")
    @Required
    private Long userId;
    @ApiModelProperty(value = "第一层的内容")
    @Required
    private String content;
    @ApiModelProperty(value = "富文本页面")
    @Required
    private String checkontent;
    @ApiModelProperty(value = "标题")
    @Required
    private String title;

}
