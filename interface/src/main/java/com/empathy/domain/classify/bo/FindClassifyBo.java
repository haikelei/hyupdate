package com.empathy.domain.classify.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/5/17.
 */
@Getter
@Setter
public class FindClassifyBo extends PageBo {
    @ApiModelProperty("分类类型 0代表专辑分类列表 1代表直播间分类")
    @Required
    private Integer type;


}
