package com.empathy.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
    private Integer currentPage = 1;
    private Integer pageSize = 10;

    public Integer getStart() {
        return (this.currentPage - 1) * pageSize;
    }
}
