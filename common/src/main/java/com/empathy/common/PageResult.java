package com.empathy.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PageResult {
    private Integer totalCount;     //总数量
    private List<?> data;            //每页显示的集合
    private Integer currentPage;    //当前页
    private Integer pageSize;       //每页显示数量
    private Integer prevPage;       //上一页
    private Integer nextPage;       //下一页
    private Integer totalPage;      //总页数


    public Integer getTotalPage() {
        return totalPage == 0 ? 1 : totalPage;
    }

    public PageResult(Integer totalCount, List<?> data, Integer currentPage, Integer pageSize) {
        super();
        this.totalCount = totalCount;
        this.data = data;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
        this.prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        this.nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
    }

    public static PageResult isEmpty(Integer pageSize) {
        return new PageResult(0, Collections.emptyList(), 1, pageSize);
    }
}
