package com.empathy.domain.album.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/30.
 */
@Getter@Setter
public class BuyAddBo {


    @ApiModelProperty("用户id")
    @Required
    private Long id;

    @ApiModelProperty("专辑id")
    @Required
    private Long albumId;

    @ApiModelProperty("录音id")
    @Required
    private Long recordId;
}
