package com.empathy.domain.baseReadLog;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * @ Description    学堂阅读记录实体
 * @ Author         dong
 * @ Date           2018-09-25 19:28
 */
@Getter
@Setter
public class BaseReadLog extends BaseDomain {

    private Long userId;

    private Long articleId;

}
