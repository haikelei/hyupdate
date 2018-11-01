package com.empathy.domain.baseReport.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/26.
 */
@Getter
@Setter
public class ReportVo {

    private Long id;
    private String username;//举报人姓名
    private Long userId;//举报人id
    private String reportUsername;//举报人姓名
    private Long reportUserId;//被举报人id
    private String content;//举报内容
    private Integer type;//举报类型
    private Long liveId;//房间id
    private Long createTime;
    private Integer reportType;

    private String detail;//详细
}
