package com.empathy.domain.article.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/7.
 */
@Getter@Setter
public class ArticleUpdBo {

    private Long id;
    private String content;
    private String title;
    private String firstContent;
    private Integer code;
    private Integer redPoint;
    private String url;


}
