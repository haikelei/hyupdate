package com.empathy.domain.baseMain.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/19.
 */
@Getter
@Setter
public class MainButtonAddBo {

    @ApiModelProperty(value = "控件类型")
    @Required
    private String controlsType;
    @ApiModelProperty(value = "文字")
    @Required
    private String font;
    @ApiModelProperty(value = "选中后文字颜色")
    @Required
    private String checkFontColor;
    @ApiModelProperty(value = "默认文字颜色")
    @Required
    private String defaultFontColor;
    @ApiModelProperty(value = "选中后图片")
    @Required
    private String checkPic;
    @ApiModelProperty(value = "默认图片")
    @Required
    private String defaultPic;
    @ApiModelProperty(value = "响应事件")
    @Required
    private String respondEvent;
}
