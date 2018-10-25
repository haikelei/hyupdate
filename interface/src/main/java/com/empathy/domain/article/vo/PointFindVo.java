package com.empathy.domain.article.vo;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PointFindVo {

    private Long id;

    private Long userId;

//    private Long articleId;

    private String username;

    private String url;

//    private Integer level;

    private Long createTime;
}
