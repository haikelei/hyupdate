package com.empathy.domain.article.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by MI on 2018/6/7.
 */
@Getter@Setter
public class ArticleAddBo {
    @ApiModelProperty("后台传参，前端不用传")
    @Required
    private Integer code;
    @ApiModelProperty("后台传参，前端不用传，图片采用多图接口")
    @Required
    private String url;
    @ApiModelProperty("后台传参，前端不用传")
    @Required
    private String firstContent;
    @ApiModelProperty("内容")
    @Required
    private String content;
    @ApiModelProperty("后台传参，前端不用传")
    @Required
    private String title;
    @ApiModelProperty("主播id")
    @Required
    private Long userId;
    @ApiModelProperty("录音id选填")
    @Required
    private Long recordId;
    @ApiModelProperty("文章类型")
    @Required
    private Integer type;

}
