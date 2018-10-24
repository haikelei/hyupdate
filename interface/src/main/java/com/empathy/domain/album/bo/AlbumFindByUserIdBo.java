package com.empathy.domain.album.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class AlbumFindByUserIdBo extends PageBo {


    @ApiModelProperty("用户id")
    @Required
    private Long userId;

}
