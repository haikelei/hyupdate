package com.empathy.domain.classify;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/13.
 */
@Getter@Setter
public class BaseMainClassify extends BaseDomain {


    private String name;
    private Integer code;
    private Integer type;


    private String url;
}
