package com.empathy.domain.baseReport.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/26.
 */
@Getter
@Setter
public class ReportFindBo extends PageBo {


    @ApiModelProperty(value = "被举报人id")
    private Long userId;

    @ApiModelProperty(value = "举报类型 100诈骗 200政治 300色情 400色爆 500其它")
    private Integer type;


}
