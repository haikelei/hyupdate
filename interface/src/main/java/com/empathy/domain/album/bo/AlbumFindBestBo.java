package com.empathy.domain.album.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/18.
 */
@Getter@Setter
public class AlbumFindBestBo extends PageBo {
    @ApiModelProperty("标题携带的id")
    @Required
    private Long id;

}
