package com.empathy.domain.agreement;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/5/24.
 */
@Getter
@Setter
public class Agreement extends BaseDomain {


    private Integer type;

    private String title;

    private String content;


}
