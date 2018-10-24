package com.empathy.domain.album.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class AlbumFindByIdBo {


    @ApiModelProperty("专辑id")
    @Required
    private Long id;


}
