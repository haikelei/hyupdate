package com.empathy.domain.article.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/7.
 */
@Getter@Setter
public class ArticleFindBo extends PageBo {

    @ApiModelProperty("搜索字符串")
    @Required
    private String str;
    @ApiModelProperty("用户id")
    @Required
    private Long userId;
    @ApiModelProperty("类型,0文章，1学堂")
    @Required
    private Integer type;
}
