package com.empathy.domain.file.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by MI on 2018/6/8.
 */

@Getter@Setter
public class FileSaveOnlyBo {



    @ApiModelProperty("文件用途")
    @Required
    private String filePurpose;
    @ApiModelProperty("文件类型")
    @Required
    private String  type;

}
