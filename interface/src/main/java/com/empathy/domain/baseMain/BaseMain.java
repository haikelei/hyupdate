package com.empathy.domain.baseMain;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/19.
 */
@Getter
@Setter
public class BaseMain extends BaseDomain {

    private String controlsType;
    private String font;
    private String checkFontColor;
    private String defaultFontColor;
    private String checkPic;
    private String defaultPic;
    private String respondEvent;


}
