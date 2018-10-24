package com.empathy.domain.baseReport.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/26.
 */
@Getter
@Setter
public class ReportAddBo {


    @ApiModelProperty(value = "举报人id")
    @Required
    private Long userId;

    @ApiModelProperty(value = "房间id")
    @Required
    private Long liveId;


    @ApiModelProperty(value = "举报类型100，举报专辑，200举报个人，300举报文章")
    @Required
    private Integer type;

    @ApiModelProperty(value = "举报类型 100广告诈骗 200有害信息 300色情 400违法 500其它（这个字段必填）")
    @Required
    private Integer reportType;


    @ApiModelProperty(value = "其它选择")
    private String content;


}
