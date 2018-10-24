package com.empathy.domain.album.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/8/23.
 */
@Getter@Setter
public class RecordAddUrlBo {

    @ApiModelProperty("录音id")
    @Required
    private Long id;
    @ApiModelProperty("录音唯一标识")
    @Required
    private String sign;

}
