package com.empathy.domain.album.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/23.
 */
@Getter
@Setter
public class AlbumFindByClassifyIdBo extends PageBo {


    @ApiModelProperty("分类id")
    @Required
    private Long classifyId;
}
