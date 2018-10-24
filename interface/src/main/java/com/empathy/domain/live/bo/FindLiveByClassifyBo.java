package com.empathy.domain.live.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/28.
 */
@Getter@Setter
public class FindLiveByClassifyBo extends PageBo {

    @ApiModelProperty(value = "分类id")
    @Required
    private Long id;
}
