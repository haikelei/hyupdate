package com.empathy.domain.article.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PointFindBo extends PageBo {

    @ApiModelProperty("文章id")
    @Required
    private Long id;
}
