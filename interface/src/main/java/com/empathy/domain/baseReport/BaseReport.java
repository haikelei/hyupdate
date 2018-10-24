package com.empathy.domain.baseReport;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/26.
 */
@Getter
@Setter
public class BaseReport extends BaseDomain {


    private Long userId;

    private Long liveId;

    private Integer type;
    private Integer reportType;

    private String content;

    private Long reportUserId;

}
