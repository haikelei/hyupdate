package com.empathy.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2017/12/22.
 */
@Getter
@Setter
public class PageQueryObject extends QueryObject {
    private Long userId;
    private Long id;
}
