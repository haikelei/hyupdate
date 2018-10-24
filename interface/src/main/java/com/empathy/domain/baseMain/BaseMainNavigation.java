package com.empathy.domain.baseMain;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/19.
 */
@Getter
@Setter
public class BaseMainNavigation extends BaseDomain {

    private Long mainId;

    private Double leftDeviation;

    private String font;

    private String controlsType;

    private String size;

}
