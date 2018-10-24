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
public class MainButtonUpdBo {


    @ApiModelProperty(value = "id")
    @Required
    private Long id;

    @ApiModelProperty(value = "控件类型")
    private String controlsType;
    @ApiModelProperty(value = "文字")
    private String font;
    @ApiModelProperty(value = "选中后文字颜色")
    private String checkFontColor;
    @ApiModelProperty(value = "默认文字颜色")
    private String defaultFontColor;
    @ApiModelProperty(value = "选中后图片")
    private String checkPic;
    @ApiModelProperty(value = "默认图片")
    private String defaultPic;
    @ApiModelProperty(value = "响应事件")
    private String respondEvent;

}
