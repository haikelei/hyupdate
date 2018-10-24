package com.empathy.domain.baseCode;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class BaseCode extends BaseDomain {

    private String codeType;

    private String code;

}
