package com.empathy.domain.comments;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/23.
 */
@Getter
@Setter
public class Comments extends BaseDomain {

    private String commentType;

    private long commentId;

    private long userId;

    private String url;

    private String content;

    private String username;


}
