package com.empathy.domain.baseDynamic;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/26.
 */
@Getter
@Setter
public class BaseDynamic extends BaseDomain {

    private Long userId;

    private String content;

    private String checkContent;

    private String title;

    private Integer redPoint;


}
