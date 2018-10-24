package com.empathy.domain.article;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/7.
 */
@Getter@Setter
public class Article extends BaseDomain {


    private String firstContent;

    private Long userId;

    private String title;

    private String content;

    private Integer redPoint=0;

    private Integer code;
    private Long recordId;

    private Integer type;
}
