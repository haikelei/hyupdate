package com.empathy.domain.file.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/17.
 */
@Getter@Setter
public class FileBo {


    @ApiModelProperty("文件用途")
    @Required
    private String filePurpose;

    @ApiModelProperty("用途id")
    @Required
    private Long fkPurposeId;

    @ApiModelProperty("文件类型")
    @Required
    private String type;



}
