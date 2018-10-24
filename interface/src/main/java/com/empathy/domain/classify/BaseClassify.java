package com.empathy.domain.classify;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/16.
 */
@Getter
@Setter
public class BaseClassify extends BaseDomain {

    private String name;
    private Integer code;
    private Integer type;
    private Long parentId;

    private String parentName;

    private String url;
}
